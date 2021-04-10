package lesson7.client;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller {
    @FXML
    public TextArea textAria;
    @FXML
    public javafx.scene.control.TextField textField;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public HBox upperPanel;
    @FXML
    public HBox bottomPanel;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    final String ip = "localHost";
    final int port = 8189;

    public void setSingIn(boolean singIn) {
        if (singIn){
            upperPanel.setVisible(false);
            bottomPanel.setVisible(true);
        }else{
            upperPanel.setVisible(true);
            bottomPanel.setVisible(false);
        }
    }


    protected void connect() {
        try {
            socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/autoOk")){
                            setSingIn(true);
                            break;
                        }
                        textAria.appendText(str + "\n");
                    }
                    while (true) {
                        String str = in.readUTF();
                        textAria.appendText(str + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert socket != null;
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setSingIn(false);
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMSG() {
        try {
            out.writeUTF(textField.getText());
            textField.setText("");
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth() {
        if (socket==null || socket.isClosed()){
            connect();
        }
        try {
            out.writeUTF("/auth "+ loginField.getText() + " "+ passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
