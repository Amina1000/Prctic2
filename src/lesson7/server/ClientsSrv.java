package lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * homework lesson7.client
 *
 * @author Amina
 * 27.03.2021
 */
public class ClientsSrv {
    DataInputStream in;
    DataOutputStream out;
    String nick;

    public ClientsSrv(Server server, Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try {
                    // авторизация
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) {
                            String[] token = str.split(" ");
                            String newNick = Authorization.getNickName(token[1],token[2]);
                            if (newNick!=null){
                                sendMSG("/autoOk");
                                nick = newNick;
                                server.subscribe(this);
                                System.out.println("Клиент " + nick +" авторизовался");
                                break;
                            }else {
                                sendMSG("Неверный логин");
                            }
                        }
                    }
                    // переписка
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            break;
                        }
                         /*
                         2. * Реализовать личные сообщения, если клиент пишет «/w nick3 Привет»,
                         то только клиенту с ником nick3 должно прийти сообщение «Привет»
                         */
                        if (str.startsWith("/w")) {
                            String[] token = str.split(" ");
                            server.getMSG(nick + ": " + token[2], token[1]);
                        } else
                            server.getMSG(nick + ": " + str);
                    }
                }catch (IOException e) {
                    e.printStackTrace();
            }finally {
                    try {
                       socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                    System.out.println("Клиент " + nick +" отключился");
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMSG(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
