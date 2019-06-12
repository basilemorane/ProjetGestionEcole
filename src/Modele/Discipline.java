package Modele;

/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */

public class Discipline {
    private int id_discipline;
    private String nom_classe;

    public Discipline () {
        this.id_discipline = 0;
        this.nom_classe = "";
    }

    public Discipline (int idClasse, String nameClasse){
        this.id_discipline = idClasse;
        this.nom_classe = nameClasse;
    }

    public int getId_discipline() {
        return id_discipline;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }
}
