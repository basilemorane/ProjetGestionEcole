package Modele;

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
