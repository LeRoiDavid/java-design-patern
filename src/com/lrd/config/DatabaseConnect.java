package com.lrd.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    /**
     * URL de connection
     */
    private String url = "jdbc:mysql://localhost:3306/revision_java?serverTimezone=UTC";
    /**
     * Nom du user
     */
    private String user = "david";
    /**
     * Mot de passe du user
     */
    private String passwd = "david";
    /**
     * Objet Connection
     */
    private static Connection connect;

    /**
     * Constructeur privé
     */
    private DatabaseConnect(){
        try {
            connect = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui va nous retourner notre instance
     * et la créer si elle n'existe pas...
     * @return
     */
    public static Connection getInstance(){
        if(connect == null){
            new DatabaseConnect();
        }
        return connect;
    }
}
