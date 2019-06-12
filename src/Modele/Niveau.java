package Modele;


/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */

public class Niveau {
    private int id;
    private String nom;

    public Niveau (){
        this.id = 0;
        this.nom = "no niveau";
    }

    public Niveau (int id, String nom){
        this.id = id;
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
