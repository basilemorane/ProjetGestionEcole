CREATE TABLE Ecole
(
id_ecole INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
nom_ecole VARCHAR(255) NOT NULL
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE AnneeScolaire
(
id_annee_scolaire INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
nom_anneScolaire  VARCHAR(255) NOT NULL
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE Trimestre
(
id_trimestre INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
numero INT(11) NOT NULL,
debut DATE NOT NULL,
fin DATE NOT NULL,
id_annee_scolaire INT (11) NOT NULL,
FOREIGN KEY (id_annee_scolaire) REFERENCES AnneeScolaire(id_annee_scolaire)
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE Niveau
(
id_niveau INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
nom_niveau  VARCHAR(255) NOT NULL
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE Classe
(
id_classe INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
nom_classe  VARCHAR(255) NOT NULL,
id_ecole INT(11) NOT NULL,
id_niveau INT(11) NOT NULL,
id_annee_scolaire INT (11) NOT NULL,
FOREIGN KEY (id_ecole) REFERENCES Ecole (id_ecole),
FOREIGN KEY (id_niveau) REFERENCES Niveau(id_niveau),
FOREIGN KEY (id_annee_scolaire) REFERENCES AnneeScolaire(id_annee_scolaire)
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE Discipline
(
id_discipline INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
nom_discipline VARCHAR(255) NOT NULL
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE Personne
(
    id_personne INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nom_personne VARCHAR (255) NOT NULL,
    prenom_personne VARCHAR(255) NOT NULL,
    type_personne BOOLEAN
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE Enseignement
(
    id_enseignement INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_classe INT(11) NOT NULL,
    id_discipline INT(11) NOT NULL,
    id_personne INT(11) NOT NULL,
    FOREIGN KEY (id_classe) REFERENCES Classe (id_classe),
    FOREIGN KEY (id_discipline) REFERENCES Discipline (id_discipline),
    FOREIGN KEY (id_personne) REFERENCES Personne (id_personne)
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;

CREATE TABLE INSCRIPTION
(
    id_inscription INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_classe INT(11) NOT NULL,
    id_personne INT(11) NOT NULL,
    FOREIGN KEY (id_classe) REFERENCES Classe (id_classe),
    FOREIGN KEY (id_personne) REFERENCES Personne (id_personne)
)ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_bin ;


INSERT INTO `Ecole` (`id_ecole`, `nom_ecole`)
VALUES (NULL, 'ECE PARIS');

INSERT INTO `AnneeScolaire` (`id_annee_scolaire`, `nom_anneScolaire`)
VALUES (NULL, '2016-2017'),(NULL, '2017-2018'),(NULL, '2018-2019');

INSERT INTO `Niveau` (`id_niveau`, `nom_niveau`)
VALUES (NULL, 'ING1'),(NULL, 'ING2'),(NULL, 'ING3'),(NULL, 'ING4'),(NULL, 'ING5');

INSERT INTO `Classe` (`id_classe`,`nom_classe`,`id_ecole`, `id_niveau`, id_annee_scolaire)
VALUES (NULL, 'TD01', '1', '1', '3'), (NULL, 'TD02', '1', '1', '3'), (NULL, 'TD03', '1', '1', '3'),(NULL, 'TD04', '1', '1', '3'),
(NULL, 'TD01', '1', '2', '3'), (NULL, 'TD02', '1', '2', '3'), (NULL, 'TD03', '1', '2', '3'),(NULL, 'TD04', '1', '2', '3'),
(NULL, 'TD01', '1', '3', '3'), (NULL, 'TD02', '1', '3', '3'), (NULL, 'TD03', '1', '3', '3'),(NULL, 'TD04', '1', '3', '3');

INSERT INTO `Discipline` (`id_discipline`, `nom_discipline`)
VALUES (NULL, 'Traitement du signal 1'), (NULL, 'Analyse de Fourier'), (NULL, 'Anglais'), (NULL, 'POO JAVA'),(NULL, 'Droit du travail');

INSERT INTO `personne` (`id_personne`, `nom_personne`, `prenom_personne`, `type_personne`)
VALUES (NULL, 'Eleve', '1', '0'),
(NULL, 'Eleve', '2', '0'),
(NULL, 'Prof', '1', '1'),
(NULL, 'Prof', '2', '1'),
(NULL, 'Eleve', '3', '0'),
(NULL, 'Eleve', '4', '0'),
(NULL, 'Eleve', '5', '0'),
(NULL, 'Eleve', '6', '0'),
(NULL, 'Eleve', '7', '0'),
(NULL, 'Eleve', '8', '0'),
(NULL, 'Eleve', '9', '0'),
(NULL, 'Eleve', '10', '0'),
(NULL, 'Eleve', '11', '0');

INSERT INTO `inscription` (`id_inscription`, `id_classe`, `id_personne`)
VALUES (NULL, '2', '1'),
(NULL, '1', '1'),
(NULL, '2', '2'),
(NULL, '3', '5'),
(NULL, '4', '6'),
(NULL, '1', '7'),
(NULL, '2', '8'),
(NULL, '3', '9'),
(NULL, '4', '10'),
(NULL, '1', '11');
