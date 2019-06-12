package Modele;

/**
 * Classe permettant de recupere coorectment les datas de la base de donénes
 * Elle sont été crée en adaquation avec les donnes des tables
 * un constructeur par default
 * un constructeur avec paramètre
 * getter et setters de tous les attributs de la classe
 */
public class Classe {
    /**
     * Les attributs de la class Classe
     */
    private String name;
    private int id;
    private int idEcole;
    private int idNiveau;
    private int idAnneeScolaire;

    /**
     * Constructeur par défault
     * Constructeur avec paramètre
     */
    public Classe(){
        this.id = 0;
        this.name = "";
        this.idAnneeScolaire = 0;
        this.idNiveau = 0;
        this.idEcole = 1;
    }

    public Classe (int id, String name, int id3, int id4){
        this.id = id;
        this.name = name;
        this.idEcole = 1;
        this.idAnneeScolaire = id3;
        this.idNiveau = id4;
    }

    /**
     * Getters et Setters
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnneeScolaire() {
        return idAnneeScolaire;
    }

    public int getIdNiveau () {
        return idNiveau;
    }
}
