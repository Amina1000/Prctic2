package lesson6.hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * homework lesson4
 *
 * @author Admin
 * 17.03.2021
 */
public class ClientWindow extends JFrame{

    final String ip = "localHost";
    final int port = 8189;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    JButton sendMassage;
    JTextField textField;
    JTextArea jTextArea;

    public ClientWindow() {

        drawWindow();

        try {
            echoClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

        actionSend();
        setVisible(true);
    }

    private void echoClient() throws IOException {

        socket = new Socket(ip, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String str = in.readUTF();
                    if (str.equalsIgnoreCase("/end")) {
                        System.out.println("Клиент отключился!");
                        break;
                    }
                    jTextArea.append(str + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void drawWindow() {

        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 500, 500);
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));

        textField = new JTextField();
        jp.add(textField);
        sendMassage = new JButton("Send massage",
                new ImageIcon("C:\\JavaPractic\\Prctic2\\src\\img\\send.png"));
        jp.add(sendMassage);

        jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        add(jScrollPane);
        add(jp, BorderLayout.SOUTH);
    }

    private void actionSend() {

        ActionListener send = e -> {
            try {
                out.writeUTF(textField.getText());
                textField.setText("");
                textField.requestFocus();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };

        textField.addActionListener(send);
        sendMassage.addActionListener(send);
    }

}
