package com.lrd.entity;


import java.util.List;

public class Question {
    private Long id;
    private String libelle;
    private Domaine domaine;
    private List<Reponse> reponses;

    public Question(Long id, String libelle, Domaine domaine) {
        this.id = id;
        this.libelle = libelle;
        this.domaine = domaine;
    }

    public Question(String libelle, Domaine domaine) {
        this.id = id;
        this.libelle = libelle;
        this.domaine = domaine;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Question() {
    }

    public Question(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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

    public void setLibelle(String  libelle) {
        this.libelle = libelle;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public void addReponse(Reponse rep){ this.reponses.add(rep); }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", libelle=" + libelle +
                ", domaine=" + domaine +
                ", reponse=" + reponses +
                '}';
    }
}

