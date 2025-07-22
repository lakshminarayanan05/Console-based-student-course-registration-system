package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getDBConnection(){
        Connection connection = null;
        try (FileInputStream fis = new FileInputStream("src/db.properties")){
            Properties properties = new Properties();
            properties.load(fis);

            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        if(connection != null)
            System.out.println("DB Connection Established");
        else
            System.out.println("DB Connection failed");


        return connection;
    }
}
