package Modele;


/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */

public class Trimestre {
    private int id;
    private String debut;
    private String fin;
    private int id_annee;
    private int numero;

    public Trimestre () {
        this.id = 0;
        this.numero = 0;
        this.debut = "0000-01-01";
        this.fin = "0000-01-01";
        this.id_annee = 1;
    }

    public Trimestre ( int id, String debut, String fin, int id_annee, int numero){
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.id_annee = id_annee;
        this.numero = numero;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_annee() {
        return id_annee;
    }

    public String getDebut() {
        return debut;
    }

    public String getFin() {
        return fin;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setId_annee(int id_annee) {
        this.id_annee = id_annee;
    }

    public int getNumero() {
        return numero;
    }
}
