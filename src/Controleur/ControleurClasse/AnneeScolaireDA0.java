package Controleur.ControleurClasse;


import Controleur.Connexion;
import Modele.AnneeScolaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnneeScolaireDA0 extends Controleur<AnneeScolaire> {

    public AnneeScolaireDA0(Connexion conn) {
        super(conn);
    }

    public boolean create(AnneeScolaire obj) throws ExceptionAlreadyExistant {

        try {
            for ( int i=0; i< 10; i++){
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE id_annee_scolaire = "+ i + " AND nom_anneScolaire ='" + obj.getYear() +"'");
                if(result.first()) {
                    throw new ExceptionAlreadyExistant();
                }
            }
            Statement stmt =  this.connect.getConn().createStatement();
            stmt.executeUpdate("Insert INTO AnneeScolaire VALUES (Null,'" + obj.getYear()+"')");
                System.out.println("New school year create in the databse : ");
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(AnneeScolaire obj) {

        try {
            Statement stmt =  this.connect.getConn().createStatement();
            stmt.executeUpdate("Delete From AnneeScolaire Where nom_anneScolaire = '" + obj.getYear()+"'");
            System.out.println("school year delete ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(AnneeScolaire obj) {

        return false;
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

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE id_annee_scolaire = "+ id + " AND nom_anneScolaire ='" + name +"'");
            if(result.first())
                year = new AnneeScolaire(
                        result.getInt("id_annee_scolaire"),
                        result.getString("nom_anneScolaire")
                );
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
}

