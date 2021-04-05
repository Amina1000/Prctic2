package lesson6.hw;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * homework lesson6
 *
 * @author Amina
 * 25.03.2021
 */
public class ServerHW {

    final static  int port = 8189;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            sendMSG(out);
            getMSG(in, out);


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

    private static void sendMSG(DataOutputStream out) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        new Thread(() -> {
            try {
                while (true) {
                    String str = input.readLine();
                    out.writeUTF(str);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }}).start();
    }

    private static void getMSG(DataInputStream in, DataOutputStream out) throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.equals("/end")){
                System.out.println("Клиент отключился");
                break;
            }
            System.out.println("Клиент: " +str);
            out.writeUTF(str);
            out.flush();
        }
    }
}
