package com.lrd.repository;

import com.lrd.config.SingletonConnection;
import com.lrd.entity.Reponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReponseRepository implements Dao<Reponse> {
    private Connection connection = SingletonConnection.getConnection();
    private String tableName= "reponses";

    public ReponseRepository(){}

    @Override
    public Reponse create(Reponse reponse){
        Reponse newReponse = null;
        String sql = "INSERT INTO "+tableName+ "(libelle, question_id) values (?, ?)";
        String[] returnId = { "id" };
        PreparedStatement insertS = null;
        try {
            insertS = connection.prepareStatement(sql, returnId);
            insertS.setString(1, reponse.getLibelle());
            insertS.setLong(2, reponse.getQuestion().getId());
            int affectedRows = insertS.executeUpdate();


            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertS.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //newReponse.setId(generatedKeys.getLong(1));
                    //newReponse.setLibelle(reponse.getLibelle());
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }

        return newReponse;
    }

    @Override
    public Reponse update(Long id, Reponse reponse) {
        String sql = "UPDATE reponses SET libelle=? WHERE id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, reponse.getLibelle());
            preparedStatement.setLong(2, id);
            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new RuntimeException("Echec de la mise à jour");
            }else{
                System.out.println("Mise à jour réussie");
            }
            reponse.setId(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reponse;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM reponses WHERE id=?";
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
    public Reponse find(Long id) {
        Reponse reponse = new Reponse();
        String sql = "SELECT * FROM reponses WHERE id=?";
        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            insertS.setLong(1, id);
            ResultSet rs = insertS.executeQuery();

            if(rs.next()){
                reponse.setId(rs.getLong("id"));
                reponse.setLibelle(rs.getString("libelle"));
            }else {
                throw  new RuntimeException("Domaine not found");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  reponse;
    }

    @Override
    public List<Reponse> findAll() {
        List<Reponse> reponses = new ArrayList<>();
        String sql = "SELECT * FROM reponses";

        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            ResultSet rs = insertS.executeQuery();
            System.out.println(rs);

            while (rs.next()){
                //reponses.add(new Reponse(rs.getLong("id"), rs.getString("libelle")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reponses;
    }

    public List<Reponse> getQuestionByQuestionId(Long id) {
        List<Reponse> reponses = new ArrayList<>();
        String sql = "SELECT * FROM reponses WHERE question_id=?";

        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            insertS.setLong(1,id);
            ResultSet rs = insertS.executeQuery();
            System.out.println(rs);

            while (rs.next()){
                //reponses.add(new Reponse(rs.getLong("id"), rs.getString("libelle")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reponses;
    }
}
