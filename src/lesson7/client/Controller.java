package lesson7.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    @FXML
    public ListView<String> clientList;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    final String ip = "localHost";
    final int port = 8189;
    String nick;
    Stage stage;
    String newNick;

    public void setSingIn(boolean singIn) {
        if (singIn) {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);

        } else {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
        }
    }

    protected void connect() {
        try {
            socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    /*
                    1. Добавить в сетевой чат авторизацию через базу данных SQLite.
                     */
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/autoOk")) {
                            setSingIn(true);
                            nick = str.split(" ")[1];
                            break;
                        }
                        textAria.appendText(str + "\n");
                    }
                    SetTitle();
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                break;
                            }
                            /*
                            2.*Добавить в сетевой чат возможность смены ника.
                             */
                            if (str.startsWith("/clientList")) {
                                String[] cList = str.split(" ");
                                Platform.runLater(() -> {
                                    clientList.getItems().clear();
                                    for (int i = 1; i < cList.length; i++) {
                                        clientList.getItems().add(cList[i]);
                                    }
                                    clientList.setCellFactory(TextFieldListCell.forListView());
                                    clientList.setOnEditCommit(event -> {
                                        String oldNick = clientList.getSelectionModel().getSelectedItem();
                                        clientList.getItems().set(event.getIndex(), event.getNewValue());
                                        newNick = clientList.getSelectionModel().getSelectedItem();
                                        try {
                                            out.writeUTF("/chNick " + newNick + " " + oldNick);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                });
                            }
                        } else {
                            textAria.appendText(str + "\n");
                        }
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
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SetTitle() {
        Platform.runLater(() -> {
            stage = (Stage) textAria.getScene().getWindow();
            stage.setTitle("Chat " + nick);
            stage.setOnCloseRequest(e -> {
                System.out.println("buy");
                try {
                    out.writeUTF("/end");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        });
    }

    public void clickClientList() {
        String receiver = clientList.getSelectionModel().getSelectedItem();
        textField.setText("/w " + receiver + " ");
    }
}
