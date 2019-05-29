package Modele;

public class Inscription {
    private int id;
    private int id_classe;
    private int id_personne;

    public Inscription (){
        this.id =0;
        this.id_classe = 0;
        this.id_personne = 0;
    }

    public Inscription (int id, int id_classe, int id_personne) {
        this.id = id;
        this.id_personne = id_personne;
        this.id_classe = id_classe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_classe() {
        return id_classe;
    }

    public int getId_personne() {
        return id_personne;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

}
