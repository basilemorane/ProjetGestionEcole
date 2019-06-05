package Modele;

public class DetailsBulletin {
    private int id;
    private int id_bulletin;
    private int id_enseignement;

    public DetailsBulletin () {
        this.id = 0;
        this.id_bulletin = 0;
        this.id_enseignement = 0;
    }

    public DetailsBulletin (int id, int id_bulletin, int id_enseignement){
        this.id = id;
        this.id_bulletin = id_bulletin;
        this.id_enseignement = id_enseignement;
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
