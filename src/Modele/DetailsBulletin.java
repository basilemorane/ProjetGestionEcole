package Modele;

public class DetailsBulletin {
    private int id;
    private int id_bulletin;
    private int id_enseignement;
    private String commentaire;

    public DetailsBulletin () {
        this.id = 0;
        this.id_bulletin = 0;
        this.id_enseignement = 0;
        this.commentaire = "";
    }

    public DetailsBulletin (int id, int id_bulletin, int id_enseignement, String commentaire){
        this.id = id;
        this.id_bulletin = id_bulletin;
        this.id_enseignement = id_enseignement;
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_bulletin() {
        return id_bulletin;
    }

    public int getId_enseignement() {
        return id_enseignement;
    }

    public void setId_bulletin(int id_bulletin) {
        this.id_bulletin = id_bulletin;
    }

    public void setId_enseignement(int id_enseignement) {
        this.id_enseignement = id_enseignement;
    }
}
