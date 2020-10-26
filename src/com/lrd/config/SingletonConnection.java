package com.lrd.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
    /**
     * URL de connection
     */
    private static String url = "jdbc:mysql://localhost:3306/revision_java?serverTimezone=UTC";
    /**
     * Nom du user
     */
    private static String user = "david";
    /**
     * Mot de passe du user
     */
    private static String passwd = "david";
    /**
     * Objet Connection
     */
    private static Connection connection;

    static {
        try{
            connection = DriverManager.getConnection(url, user, passwd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

