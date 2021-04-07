package lesson7.server;

import java.sql.SQLException;

/**
 * homework lesson6.server
 *
 * @author Amina
 * 27.03.2021
 */
public class Start {
    public static void main(String[] args) {
        try {
            new Server();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
