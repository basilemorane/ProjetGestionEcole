package Modele;

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
