import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private static final String DB_CONNECTION = "jdbc:sqlite:users.db";
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_CONNECTION);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        try (PreparedStatement stm = connection
                .prepareStatement("SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "'");
             ResultSet resultSet = stm.executeQuery()) {
            if (resultSet.next()) {
                return login;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}

