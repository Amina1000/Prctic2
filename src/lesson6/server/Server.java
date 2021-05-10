package lesson6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * homework lesson6
 *
 * @author Amina
 * 25.03.2021
 */
public class Server {
    private final Vector<ClientsSrv> client;

    public Server() {
        client = new Vector<>();
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен");
            while (true) {
                    socket = serverSocket.accept();
                    System.out.println("Клиент подключился");
                    client.add(new ClientsSrv(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert socket != null;
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
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
}
