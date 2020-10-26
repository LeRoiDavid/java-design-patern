package com.lrd.entity;


import java.util.List;

public class Domaine {
    private Long id;
    private String domaine_name;
    private List<Question> questions;

    public Domaine(Long id, String domaine_name) {
        this.id = id;
        this.domaine_name = domaine_name;
    }

    public Domaine( String domaine_name) {
        this.domaine_name = domaine_name;
    }

    public Domaine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomaine_name() {
        return domaine_name;
    }

    public void setDomaine_name(String domaine_name) {
        this.domaine_name = domaine_name;
    }

    @Override
    public String toString() {
        return "Domaine{" +
                "id=" + id +
                ", domaine_name='" + domaine_name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
