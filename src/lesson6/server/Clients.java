package lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * homework lesson6.client
 *
 * @author Amina
 * 27.03.2021
 */
public class Clients {
    DataInputStream in;
    DataOutputStream out;

    public Clients(Server server, Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(()->{
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            System.out.println("Клиент отключился");
                            break;
                        }
                          server.getMSG(str);
                    }
                }catch (IOException e) {
                    e.printStackTrace();
            }}).start();
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
