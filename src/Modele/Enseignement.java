package Modele;

/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */
public class Enseignement {
    private int id;
    private int id_classe;
    private int id_personne;
    private int id_discipline;

    public Enseignement (){
        this.id = 0;
        this.id_classe = 0;
        this.id_discipline = 0;
        this.id_personne = 0;
    }

    public Enseignement(int idEnseignement, int idClasse, int idDispline, int id_personne){
        this.id_personne = id_personne;
        this.id_discipline = idDispline;
        this.id = idEnseignement;
        this.id_classe = idClasse;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    public int getId() {
        return id;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

    public int getId_discipline() {
        return id_discipline;
    }

    public int getId_personne() {
        return id_personne;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId(int id) {
        this.id = id;
    }

}
