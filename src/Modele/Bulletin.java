package Modele;

/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */

public class Bulletin {
    private int id;
    private int id_trimestre;
    private int id_inscription;
    private String commentaire;

    public Bulletin () {
        this.id = 0;
        this.id_trimestre = 0;
        this.id_inscription = 0;
        this.commentaire = "";
    }

    public Bulletin (int id, int idtrimestre, int idInscription, String commentaire) {
        this.id = id;
        this.id_trimestre = idtrimestre;
        this.id_inscription = idInscription;
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_inscription() {
        return id_inscription;
    }

    public int getId_trimestre() {
        return id_trimestre;
    }

    public void setId_inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }

    public void setId_trimestre(int id_trimestre) {
        this.id_trimestre = id_trimestre;
    }

}
