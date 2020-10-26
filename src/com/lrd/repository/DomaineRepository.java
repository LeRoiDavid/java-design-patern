package com.lrd.repository;

import com.lrd.config.SingletonConnection;
import com.lrd.entity.Domaine;
import com.lrd.entity.Question;
import com.lrd.entity.Reponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomaineRepository implements Dao<Domaine>{
    private Connection connection = SingletonConnection.getConnection();
    private String tableName="domaines";

    public DomaineRepository(){}

    @Override
    public Domaine create(Domaine domaine) {
        Domaine newDomaine = null;
        String sql = "INSERT INTO "+tableName+"(domaine_name) values (?)";
        String[] returnId = { "id" };
        PreparedStatement insertS = null;
        try {
            insertS = connection.prepareStatement(sql, returnId);
            insertS.setString(1, domaine.getDomaine_name());
            int affectedRows = insertS.executeUpdate();


            if (affectedRows == 0) {
                throw new SQLException("Echec de la création du domaine.");
            }
            /*try (ResultSet generatedKeys = insertS.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newDomaine.setId(generatedKeys.getLong(1));
                    newDomaine.setDomaine_name(domaine.getDomaine_name());
                }
                else {
                    throw new SQLException("Echec, erreur innattendu");
                }
            }*/
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }

        return newDomaine;
    }

    @Override
    public Domaine update(Long id, Domaine domaine) {
        String sql = "UPDATE " + tableName + " SET domaine_name=? WHERE id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, domaine.getDomaine_name());
            preparedStatement.setLong(2, id);
            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new RuntimeException("Echec de la mise à jour du domains");
            }else{
                System.out.println("Mise à jour réussie");
            }
            domaine.setId(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return domaine;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM "+ tableName +" WHERE id=?";
        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            insertS.setLong(1, id);
            int rs = insertS.executeUpdate();
            if(rs == 0){
                System.out.println("Echec de la suppression");
            }else{
                System.out.println("Suppression réuissit");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Domaine find(Long id) {
        Domaine domaine = new Domaine();
        String sql = "SELECT * FROM "+ tableName +" WHERE id=?";
        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            insertS.setLong(1, id);
            ResultSet rs = insertS.executeQuery();

            if(rs.next()){
                domaine.setId(rs.getLong("id"));
                domaine.setDomaine_name(rs.getString("domaine_name"));
            }else {
                throw  new RuntimeException("Domaine not found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  domaine;
    }

    @Override
    public List<Domaine> findAll() {
        List<Domaine> domaines = new ArrayList<>();
        String sql = "SELECT * FROM "+ tableName;

        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            ResultSet rs = insertS.executeQuery();
            System.out.println(rs);

            while (rs.next()){
                domaines.add(new Domaine(rs.getLong("id"), rs.getString("domaine_name")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return domaines;
    }


}
