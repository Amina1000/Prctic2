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

    public static String getNickName(String login, String password){
        String sql = String.format(
                "select nickname from main\n" +
                "where login = '%s' and password = '%s'",login, password);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
