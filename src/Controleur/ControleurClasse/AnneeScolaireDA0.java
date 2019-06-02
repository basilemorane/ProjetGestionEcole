package Controleur.ControleurClasse;


import Controleur.Connexion;
import Modele.AnneeScolaire;
import Modele.Niveau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnneeScolaireDA0 extends Controleur<AnneeScolaire> {
    private int nombre_année_scolaire;
    private int nombre;

    public AnneeScolaireDA0(Connexion conn) {
        super(conn);
        this.nombre = findAll().size()-1;
        this.nombre_année_scolaire = findAll().get(this.nombre).getId();
    }

    public boolean create(AnneeScolaire obj) throws ExceptionAlreadyExistant {

        try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE nom_anneScolaire ='" + obj.getYear() +"'");
                if(result.first()) {
                    throw new ExceptionAlreadyExistant();
                }
            Statement stmt =  this.connect.getConn().createStatement();
            stmt.executeUpdate("Insert INTO AnneeScolaire VALUES (Null,'" + obj.getYear()+"')");
                System.out.println("New school year create in the databse : ");
                this.nombre_année_scolaire+=1;
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(AnneeScolaire obj) {

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE nom_anneScolaire ='" + obj.getYear() +"'");
                    if ( result.first()){
                        Statement stmt = this.connect.getConn().createStatement();
                        stmt.executeUpdate("Delete From AnneeScolaire Where id_annee_scolaire = " + result.getInt("id_annee_scolaire"));
                        System.out.println("School year delete ");
                        return true;
                    }
            } catch (SQLException e) {
                 e.printStackTrace();

        }
        return false;
    }

    public boolean update(AnneeScolaire obj, String newName) throws ExceptionAlreadyExistant {
        try {
            for ( int i=1; i< this.nombre_année_scolaire+1; i++) {

                   if ( find(1,newName).getId() != 0 ) {
                       throw new ExceptionAlreadyExistant();
                   }

                Statement stmt = this.connect.getConn().createStatement();
                stmt.executeUpdate("Update AnneeScolaire SET nom_anneScolaire = '" + newName + "' Where id_annee_scolaire = " + i + " AND nom_anneScolaire = '" + obj.getYear() + "'");
            }
            System.out.println("school year update ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AnneeScolaire find (AnneeScolaire obj){
        return obj;
    }

    public AnneeScolaire find(int id) {
        AnneeScolaire year = new AnneeScolaire();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE id_annee_scolaire =" + id);
            if(result.first())
                System.out.println("Find at least one SchoolYear in the database");
                year = new AnneeScolaire(
                       result.getInt("id_annee_scolaire"),
                        result.getString("nom_anneScolaire")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return year;
    }

    public AnneeScolaire find(int id, String name) {
        AnneeScolaire year = new AnneeScolaire();
        id=0;
        try {
            for (int i=0; i< nombre_année_scolaire+1; i++) {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE id_annee_scolaire = " + i + " AND nom_anneScolaire ='" + name + "'");
                if (result.first())
                    year = new AnneeScolaire(
                            result.getInt("id_annee_scolaire"),
                            result.getString("nom_anneScolaire")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return year;
    }

    public AnneeScolaire find (int id, int id1, int id2){
        id1 = id2 = 0;
        final AnneeScolaire anneeScolaire = find(id);
        return anneeScolaire;
    }

    public AnneeScolaire recherch (int id, String name) {
            AnneeScolaire year = new AnneeScolaire();
            return year;
    }

    public ArrayList<AnneeScolaire> findAll () {
        ArrayList<AnneeScolaire> YearArrayList = new ArrayList<>();
        try {
            ResultSet result =  this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire "); //order by nom_anneScolaire DESC");
            while (result.next()){
                YearArrayList.add( new AnneeScolaire(
                        result.getInt("id_annee_scolaire"),
                        result.getString("nom_anneScolaire")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return YearArrayList;
    }

    public ArrayList<AnneeScolaire> findAll (int id2) {
        id2 = 0;
        ArrayList<AnneeScolaire> YearArrayList = new ArrayList<>();
        try {
            ResultSet result =  this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire "); //order by nom_anneScolaire DESC");
            while (result.next()){
                YearArrayList.add( new AnneeScolaire(
                        result.getInt("id_annee_scolaire"),
                        result.getString("nom_anneScolaire")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return YearArrayList;
    }
}


