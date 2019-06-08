package Modele;

/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */

public class Ecole {
    /**
     * Les attributs de la classe
     */
    private int id;
    private String name;

    /**
     * Constructeurs par défaults
     */
    public Ecole (){
        this.id = 0;
        this.name = "";
    }

    /**
     * Constructeurs avec paramètres
     */
    public Ecole (int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Getters et Setters
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
