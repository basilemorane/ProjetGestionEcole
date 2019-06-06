package Controleur.ControleurClasse;

import Modele.AnneeScolaire;
import Modele.Inscription;
import Modele.Professeur;
import Modele.Trimestre;
import Controleur.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrimestreDAO extends Controleur<Trimestre> {

    public TrimestreDAO (Connexion conn) {
        super(conn);
    }

    public boolean create (Trimestre obj ) throws ExceptionAlreadyExistant {
        return false;
    }
    public boolean create (AnneeScolaire obj) throws ExceptionAlreadyExistant {
        String [] data = obj.getYear().split("-", 2);
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE nom_anneScolaire ='" + obj.getYear() + "'");
            if (result.first()) {
                this.connect.getConn().createStatement().executeUpdate("Insert into trimestre VALUES (Null, 1, '" + data[0] + "-09-01', '" + data[0] + "-12-31', '" + obj.getId() + "'), (Null, 2, '" + data[1] + "-01-01', '" + data[1] + "-06-30', '" + obj.getId() + "')");
                return true ;
            } else
                throw new ExceptionAlreadyExistant();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Trimestre obj) throws ExceptionNotExisting {
        return false;
    }

    public boolean delete(AnneeScolaire obj) throws ExceptionNotExisting {
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE nom_anneScolaire ='" + obj.getYear() + "'");
            if (result.first()) {
                this.connect.getConn().createStatement().executeUpdate("DELETE FROM trimestre WHERE id_annee_scolaire = " + obj.getId());
                return true ;
            } else
                throw new ExceptionNotExisting();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Trimestre obj, String newName) {
        return false;
    }

    public Trimestre find (Trimestre obj){
        Trimestre eleve = new Trimestre();
        return eleve;
    }


    public Trimestre find(int id, String name) {
        Trimestre eleve = new Trimestre();
        return eleve;
    }

    public Trimestre find (int id, int id1, int id2){
        id1 =0;
        Trimestre eleve = new Trimestre();
        return eleve;
    }


    public ArrayList<Trimestre> findAll (AnneeScolaire obj) {
        ArrayList<Trimestre> ArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `trimestre` INNER JOIN anneescolaire ON trimestre.id_annee_scolaire = anneescolaire.id_annee_scolaire WHERE anneescolaire.id_annee_scolaire = '" + obj.getId() + "'");
            while (result.next()) {
                    ArrayList.add(new Trimestre(
                            result.getInt("id_trimestre"),
                            result.getString("debut"),
                            result.getString("fin"),
                            result.getInt("id_annee_scolaire"),
                            result.getInt("numero")
                    ));
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return ArrayList;
    }

    public ArrayList<Trimestre> findAll () {
        ArrayList<Trimestre> ArrayList = new ArrayList<>();
        return ArrayList;
    }

    public Trimestre findOne (int idyear, int numtrismestre) {
        Trimestre trimestre = new Trimestre();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `trimestre` INNER JOIN anneescolaire ON trimestre.id_annee_scolaire = anneescolaire.id_annee_scolaire WHERE anneescolaire.id_annee_scolaire = '" + idyear + "' AND trimestre.numero = '" + numtrismestre + "'");
            while (result.next()) {
               trimestre = new Trimestre(
                        result.getInt("id_trimestre"),
                        result.getString("debut"),
                        result.getString("fin"),
                        result.getInt("id_annee_scolaire"),
                        result.getInt("numero")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trimestre;
    }


}



