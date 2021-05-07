package lesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

/**
 * homework lesson7
 *
 * @author Amina
 * 25.03.2021
 */
public class Server {
    private final Vector<ClientsSrv> client;
    public Server() throws SQLException {
        Authorization.connect();

        client = new Vector<>();
        ServerSocket serverSocket = null;
        Socket socket;

        try {
            serverSocket = new ServerSocket(8188);
            System.out.println("Сервер запущен");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                new ClientsSrv(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert serverSocket!=null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getMSG(String msg,String sender) {
        //Authorization.addMessageDb(sender,"null",msg);
        ChatStream.setTextHistory(sender,msg);
        for (ClientsSrv c : client) {
            c.sendMSG(sender+": "+ msg);
        }
    }
   /*
   2. * Реализовать личные сообщения, если клиент пишет «/w nick3 Привет»,
   то только клиенту с ником nick3 должно прийти сообщение «Привет»
   */
    public void getMSG(String msg, String receiver, String sender) {
       // Authorization.addMessageDb(sender,receiver,msg);
        ChatStream.setTextHistory(sender,msg);
        for (ClientsSrv c : client) {
           if(c.getNick().equalsIgnoreCase(receiver)||c.getNick().equalsIgnoreCase(sender))
               c.sendMSG("[Private from "+sender+": "+ msg);
        }
    }
    /*
    2.*Добавить в сетевой чат возможность смены ника.
     */
    public void changeNickMethod(String oldNick, String newNick,ClientsSrv clientsSrv) {
        Authorization.changeNickData(oldNick, newNick);
        for (ClientsSrv c:client) if(c.equals(clientsSrv)) c.setNick(newNick);
        broadCastClientList();
    }

    /*
    1. Добавить в сетевой чат авторизацию через базу данных SQLite.
    */
    public void subscribe(ClientsSrv clientsSrv) {
        client.add(clientsSrv);
        broadCastClientList();
       //clientsSrv.sendMSG(Authorization.getMessageDb(clientsSrv.getNick()));
        clientsSrv.sendMSG(ChatStream.getTextHistory(clientsSrv.getNick()));
    }

    public void unsubscribe(ClientsSrv clientsSrv) {
        client.remove(clientsSrv);
        broadCastClientList();
        Authorization.disconnect();
    }
    public void broadCastClientList(){

        StringBuilder sb = new StringBuilder();
        sb.append("/clientList ");

        for (ClientsSrv c:client) sb.append(c.getNick()).append(" ");
        String cList = sb.toString();

        for (ClientsSrv c:client) c.sendMSG(cList);
    }
}
