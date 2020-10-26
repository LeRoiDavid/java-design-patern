package com.lrd.entity;

import java.util.List;

public class Reponse {
    private Long id;
    private String libelle;
    private Question question;

    public Reponse(Long id, String libelle, Question question) {
        this.id = id;
        this.libelle = libelle;
        this.question = question;
    }

    public Reponse( String libelle, Question question) {
        this.libelle = libelle;
        this.question = question;
    }


    public Reponse(String libelle) {
        this.libelle = libelle;
    }

    public Reponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", question=" + question +
                '}';
    }
}
