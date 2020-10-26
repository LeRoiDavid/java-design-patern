package com.lrd;

import com.lrd.config.DatabaseConnect;
import com.lrd.entity.Domaine;
import com.lrd.entity.Question;
import com.lrd.entity.Reponse;
import com.lrd.repository.DomaineRepository;
import com.lrd.repository.QuestionRepository;
import com.lrd.repository.ReponseRepository;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection connection = DatabaseConnect.getInstance();
        ReponseRepository reponseRepository = new ReponseRepository();
        DomaineRepository domRep = new DomaineRepository();
        QuestionRepository questionRep = new QuestionRepository();
        Domaine domA = new Domaine("Domaine A");
        Domaine domB = new Domaine("Domaine B");
        //System.out.println(domRep.update(new Long(1), domA));
        //System.out.println(domRep.create(domA));
        //System.out.println(domRep.find(new Long(3)));
        domRep.findAll().forEach(System.out::println);

        domA.setId(new Long(1));
        domB.setId(new Long(2));

        Question question = new Question("Quelle sont vos notes", domA);
        Question question1 = new Question("D'ou vient tu ?", domB);
        Question question3 = new Question("Comment appel tu", domB);
        //questionRep.create(question);
        //questionRep.create(question1);
        //questionRep.create(question3);

        questionRep.getQuestionByDomaine(domA.getId()).forEach(System.out::println);

        question.setId(new Long(1));
        question1.setId(new Long(2));
        Reponse rep1 = new Reponse("Sa note est  17/20", question);
        Reponse rep2 = new Reponse("Je vient de marche", question1);

        reponseRepository.create(rep2);






        /*Reponse rs = new Reponse("votre date de naissane ?");
        System.out.println(reponseRepository.create(rs));*/
        reponseRepository.findAll().forEach(System.out::println);
        //System.out.println(reponseRepository.find(new Long(1)));

        //reponseRepository.update(new Long(7), new Reponse("Votre Résidence ?"));

    }
}
