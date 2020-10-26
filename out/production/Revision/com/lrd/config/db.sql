CREATE DATABASE `revision_java`;
USE `revision_java`;

CREATE TABLE `domaines` (
    `id` BIGINT(20) AUTO_INCREMENT,
    `domaine_name` VARCHAR(70) NOT NULL,
    CONSTRAINT key1 PRIMARY KEY (id)
);



CREATE TABLE `questions` (
    `id` BIGINT(20) AUTO_INCREMENT,
    `libelle` VARCHAR(70) NOT NULL,
    `domaine_id` BIGINT(20),
    CONSTRAINT key1 PRIMARY KEY (id),
    FOREIGN KEY (domaine_id) REFERENCES `domaines`(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `reponses` (
    `id` BIGINT(20)  AUTO_INCREMENT,
    `libelle` VARCHAR(70) NOT NULL,
    `question_id` BIGINT(20),
         CONSTRAINT key1 PRIMARY KEY (id),
         FOREIGN KEY (question_id) REFERENCES `questions`(id)
             ON DELETE CASCADE
             ON UPDATE CASCADE
);
