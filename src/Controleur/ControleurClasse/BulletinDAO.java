package Controleur.ControleurClasse;

import Modele.AnneeScolaire;
import Modele.Bulletin;
import Controleur.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BulletinDAO extends Controleur<Bulletin> {

    public BulletinDAO (Connexion conn) {
        super(conn);
    }

    public boolean create(Bulletin obj) throws ExceptionAlreadyExistant {
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id_inscription ='" + obj.getId_inscription() + "'");
            if (result.first()) {
               throw new ExceptionAlreadyExistant();
            } else {
                Statement stmt = this.connect.getConn().createStatement();
                stmt.executeUpdate("INSERT INTO bulletin VALUES (NULL, '1', '" + obj.getId_inscription() + "'),(NULL, '2', '" + obj.getId_inscription() + "') ");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Bulletin obj) {

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE nom_anneScolaire ='" + obj.getId() + "'");
            if (result.first()) {
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

    public boolean update(Bulletin obj, String newName) throws ExceptionAlreadyExistant {
            return false;
        }


    public Bulletin find(Bulletin obj) {
        return obj;
    }


    public Bulletin find(int id, String name) {
        Bulletin year = new Bulletin();
        return year;
    }

    public  Bulletin find(int id, int id1, int id2) {
        return new  Bulletin();
    }

    public ArrayList< Bulletin> findAll(int idtrimestre, int idClasse) {
        ArrayList< Bulletin> YearArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `bulletin`INNER JOIN inscription ON bulletin.id_inscription = inscription.id_inscription WHERE `id_trimestre` = '" + idtrimestre + "' AND inscription.id_classe = '" + idClasse + "' ");
            while (result.next()) {
                YearArrayList.add(new Bulletin(
                        result.getInt("id_bulletin"),
                        result.getInt("id_trimestre"),
                        result.getInt("id_annee_scolaire")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return YearArrayList;
    }

    public ArrayList< Bulletin> findAll(int idInscription) {
        ArrayList< Bulletin> YearArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `bulletin` WHERE id_inscription = '" + idInscription + "' ");
            while (result.next()) {
                YearArrayList.add(new Bulletin(
                        result.getInt("id_bulletin"),
                        result.getInt("id_trimestre"),
                        result.getInt("id_inscription")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return YearArrayList;
    }

    public ArrayList< Bulletin> findOne(int idtrimestre, int idInscription) {
        ArrayList< Bulletin> YearArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `bulletin`WHERE `id_trimestre` = '" + idtrimestre + "' AND id_inscription = '" + idInscription + "' ");
            while (result.next()) {
                YearArrayList.add(new Bulletin(
                        result.getInt("id_bulletin"),
                        result.getInt("id_trimestre"),
                        result.getInt("id_inscription")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return YearArrayList;
    }

    public ArrayList< Bulletin> findAll() {
        ArrayList< Bulletin> YearArrayList = new ArrayList<>();
        return YearArrayList;
    }

}



