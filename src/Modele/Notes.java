package Modele;


/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */

public class Notes {

    private int id_evaluation;
    private int id_detail_bulletin;
    private int note;

    public Notes () {
        this.id_evaluation = 0;
        this.id_detail_bulletin = 0;
        this.note = 0;
    }

    public Notes (int id_evaluation, int id_detail_bulletin, int note) {
        this.id_evaluation = id_evaluation;
        this.id_detail_bulletin = id_detail_bulletin;
        this.note = note;
    }

    public int getId_detail_bulletin() {
        return id_detail_bulletin;
    }

    public int getId_evaluation() {
        return id_evaluation;
    }

    public int getNote() {
        return note;
    }

    public void setId_detail_bulletin(int id_detail_bulletin) {
        this.id_detail_bulletin = id_detail_bulletin;
    }

    public void setId_evaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
