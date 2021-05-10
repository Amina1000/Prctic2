package lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * homework lesson7.client
 *
 * @author Amina
 * 27.03.2021
 */
public class ClientsSrv {
    DataInputStream in;
    DataOutputStream out;
    private String nick;

    public String getNick() {
        return nick;
    }

    @Override
    public String toString() {
        return nick;
    }

    public ClientsSrv(Server server, Socket socket) {
        /*
        2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            executorService.execute(() -> {
                try {
                    // авторизация
                    /*
                    Добавить отключение неавторизованных пользователей по таймауту
                    (120 сек. ждём после подключения клиента, и если он не авторизовался за это время, закрываем соединение).
                     */
                    socket.setSoTimeout(3000);
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) {
                            String[] token = str.split(" ");
                            String newNick = Authorization.getNickName(token[1], token[2]);
                            if (newNick != null) {
                                // обнуляем время ожидания авторизации.
                                socket.setSoTimeout(0);
                                sendMSG("/autoOk " + newNick);
                                nick = newNick;
                                server.subscribe(this);
                                System.out.println("Клиент " + nick + " авторизовался");
                                break;
                            } else {
                                sendMSG("Неверный логин");
                            }
                        }
                    }
                    // переписка
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            sendMSG("/end");
                            break;
                        }
                         /*
                         2. * Реализовать личные сообщения, если клиент пишет «/w nick3 Привет»,
                         то только клиенту с ником nick3 должно прийти сообщение «Привет»
                         */
                        if (str.startsWith("/w")) {
                            String[] token = str.split(" ");
                            server.getMSG(str, token[1], nick);
                        }
                        /*
                        2.*Добавить в сетевой чат возможность смены ника.
                         */
                        if (str.startsWith("/chNick ")) {
                            String[] token = str.split(" ");
                            server.getMSG("nickname " + token[2] + " has been changed to " + token[1], nick);
                            server.changeNickMethod(token[2], token[1], this);
                        } else
                            server.getMSG(str, nick);
                    }
                } catch (SocketTimeoutException e) {
                    /*
                    Добавить отключение неавторизованных пользователей по таймауту
                    (120 сек. ждём после подключения клиента, и если он не авторизовался за это время, закрываем соединение).
                     */
                    System.out.println("клиент  превысил время ожидания " + socket.getRemoteSocketAddress());
                    sendMSG("/end");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                    System.out.println();
                    System.out.println("Клиент " + nick + " отключился " + new Date(System.currentTimeMillis()));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public void sendMSG(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
