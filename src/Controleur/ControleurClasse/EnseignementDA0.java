package Controleur.ControleurClasse;

import Modele.Discipline;
import Controleur.Connexion;
import Modele.Enseignement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EnseignementDA0 extends Controleur<Enseignement>{

    public EnseignementDA0(Connexion conn) {
        super(conn);
    }

    public boolean create(Enseignement obj) throws ExceptionAlreadyExistant {

            return false;
    }

    public boolean addDisicplinetoClasse (int idniveau, int idannee, int idClasse) {
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT id_discipline FROM enseignement INNER JOIN classe ON enseignement.id_classe = classe.id_classe where classe.id_niveau = '" + idniveau + "' and classe.id_annee_scolaire = '" + idannee + "'");
            while (result.next()) {
                Statement stmt =  this.connect.getConn().createStatement();
                stmt.executeUpdate("INSERT INTO enseignement VALUES (NULL, '" + idClasse + "', '" + result.getInt("id_discipline") + "', '3' )");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Enseignement obj) {
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement WHERE id_discipline = " + obj.getId_discipline() +  " AND id_classe = " + obj.getId_classe());
            if(result.first()) {
                Statement stmt =  this.connect.getConn().createStatement();
                stmt.executeUpdate("Delete from enseignement where id_enseignement = " + result.getInt("id_enseignement"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Enseignement obj, String newName) throws ExceptionAlreadyExistant {
        return false;
    }

    public Enseignement find (Enseignement obj){
        return obj;
    }

    public Enseignement find(int id) {
        Enseignement year = new Enseignement();
        return year;
    }

    public Enseignement find(int id, String name) {
        Enseignement year = new Enseignement();
        return year;
    }

    public Enseignement find (int id, int id1, int id2){
        id1 = id2 = 0;
        final Enseignement anneeScolaire = find(id);
        return anneeScolaire;
    }


    public ArrayList<Enseignement> findAll () {
        ArrayList<Enseignement> ArrayList = new ArrayList<>();
        return ArrayList;
    }

    public ArrayList<Enseignement> findAll (int idclasse) {
        ArrayList<Enseignement> ArrayList = new ArrayList<>();

        try {
            ResultSet result =  this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement where id_classe = '" + idclasse + "'");
            while (result.next()){
                ArrayList.add( new Enseignement(
                        result.getInt("id_enseignement"),
                        result.getInt("id_classe"),
                        result.getInt("id_discipline"),
                        result.getInt("id_personne")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ArrayList;
    }
}

