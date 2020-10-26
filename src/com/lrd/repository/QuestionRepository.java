package com.lrd.repository;

import com.lrd.config.SingletonConnection;
import com.lrd.entity.Domaine;
import com.lrd.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository implements Dao<Question> {
    private Connection connection = SingletonConnection.getConnection();

    private String tableName = "questions";

    public QuestionRepository(){ }

    @Override
    public Question create(Question question) {
        Question newDomaine = null;
        String sql = "INSERT INTO "+tableName+"(libelle, domaine_id) values (?, ?)";
        String[] returnId = { "id" };
        PreparedStatement insertS = null;
        try {
            insertS = connection.prepareStatement(sql, returnId);
            insertS.setString(1, question.getLibelle());
            insertS.setLong(2, question.getDomaine().getId());
            int affectedRows = insertS.executeUpdate();


            if (affectedRows == 0) {
                throw new SQLException("Echec de la création du question.");
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
    public Question update(Long id, Question question) {
        String sql = "UPDATE " + tableName + " SET libelle=? WHERE id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, question.getLibelle());
            preparedStatement.setLong(2, id);
            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new RuntimeException("Echec de la mise à jour de la question");
            }else{
                System.out.println("Mise à jour réussie");
            }
            question.setId(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return question;
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
    public Question find(Long id) {
        Question question = new Question();
        String sql = "SELECT * FROM "+ tableName +" WHERE id=?";
        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            insertS.setLong(1, id);
            ResultSet rs = insertS.executeQuery();

            if(rs.next()){
                question.setId(rs.getLong("id"));
                question.setLibelle(rs.getString("libelle"));
            }else {
                throw  new RuntimeException("Question not found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  question;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM "+ tableName;

        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            ResultSet rs = insertS.executeQuery();

            while (rs.next()){
                questions.add(new Question(rs.getLong("id"), rs.getString("libelle")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questions;
    }

    public List<Question> getQuestionByDomaine(Long id){
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM "+ tableName +" WHERE domaine_id=?";

        PreparedStatement insertS = null;

        try {
            insertS = connection.prepareStatement(sql);
            insertS.setLong(1, id);
            ResultSet rs = insertS.executeQuery();

            while (rs.next()){
                questions.add(new Question(rs.getLong("id"), rs.getString("libelle")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questions;
    }
}
