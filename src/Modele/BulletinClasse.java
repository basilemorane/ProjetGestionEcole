package Modele;

/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */
public class BulletinClasse {
    private String nomDsipline;
    private double moyenne;
    private String commentaireDetails;
    private String commentaire;

    public BulletinClasse (){
        this.commentaire ="";
        this.commentaireDetails = "";
        this.moyenne = 0;
        this.nomDsipline = "";
    }

    public BulletinClasse (String nomDsipline, double moyenne, String commentaireDetails, String commentaire) {
        this.nomDsipline = nomDsipline;
        this.moyenne = moyenne;
        this.commentaireDetails =commentaireDetails;
        this.commentaire = commentaire;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public String getNomDsipline() {
        return nomDsipline;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getCommentaireDetails() {
        return commentaireDetails;
    }

    public void setCommentaireDetails(String commentaireDetails) {
        this.commentaireDetails = commentaireDetails;
    }

    public void setNomDsipline(String nomDsipline) {
        this.nomDsipline = nomDsipline;
    }

}
