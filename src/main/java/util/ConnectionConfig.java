package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public final class ConnectionConfig {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";


    private ConnectionConfig() {
    }

    public static Connection open() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

