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
            serverSocket = new ServerSocket(8189);
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

    public void getMSG(String msg) {
        for (ClientsSrv c : client) {
            c.sendMSG(msg);
        }
    }
   /*
   2. * Реализовать личные сообщения, если клиент пишет «/w nick3 Привет»,
   то только клиенту с ником nick3 должно прийти сообщение «Привет»
   */
    public void getMSG(String msg, String nick) {
        for (ClientsSrv c : client) {
           if(c.nick.equalsIgnoreCase(nick)) c.sendMSG(msg);
        }
    }
    public void subscribe(ClientsSrv clientsSrv) {
        client.add(clientsSrv);
    }

    public void unsubscribe(ClientsSrv clientsSrv) {
        client.remove(clientsSrv);
        Authorization.disconnect();
    }
}
