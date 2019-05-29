package Modele;

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
