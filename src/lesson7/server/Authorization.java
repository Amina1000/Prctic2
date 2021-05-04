package lesson7.server;

import java.sql.*;

/**
 * homework lesson7.server
 *
 * @author Amina
 * 06.04.2021
 */
public class Authorization {
    private static Connection connection;
    private static Statement statement;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat_data.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickName(String login, String password) {
        String sql = String.format(
                "select nickname from main\n" +
                "where login = '%s' and password = '%s'",login, password);
        try {
            ResultSet rs = executeDataQuery(sql);
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMessageDb(String login){
        String sql = String.format(
                "SELECT * FROM messages\n"+
                "Where receiver= '%s' or sender = '%s' or receiver is null",login,login);
       StringBuilder sb = new StringBuilder();
        try {
            ResultSet rs = executeDataQuery(sql);
            while (rs.next()){
                String sender = rs.getString(2);
                String receiver = rs.getString(3);
                String text = rs.getString(4);
                String date =  rs.getString(5);
                if (receiver ==null){
                    sb.append(date).append(": ").append(sender).append(": ").append(text).append("\n");
                }else sb.append(date).append(": ").append("[Private from ").append(sender).append(": ").append(text).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return sb.toString();
    }

    public static void addMessageDb(String sender, String receiver, String text){

        String sql = String.format(
                "INSERT INTO messages (sender,receiver, text)\n"+
                        "VALUES ('%s', '%s'  ,'%s' );",sender,receiver,text);
        executeDataUpdate(sql);
    }
    public static void changeNickData(String oldNick, String newNick){

        String sql = String.format(
                "UPDATE main set nickname ='%s' \n"+
                        "WHERE nickname ='%s';",newNick, oldNick);
        executeDataUpdate(sql);
    }

    private static ResultSet executeDataQuery(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    private static void executeDataUpdate(String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
