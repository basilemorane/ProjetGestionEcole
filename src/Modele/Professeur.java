package Modele;


/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */

public class Professeur {
        //ID
        private int id = 0;
        //Nom de l'élève
        private String nom = "";
        //Prénom de l'élève
        private String prenom = "";
        // Type
        private int type = 1;

        public Professeur (int id, String nom, String prenom) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.type = 1;
        }
        public Professeur(){
            this.id = 0;
            this.nom = "";
            this.prenom = "";
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
    }


