package Controleur.ControleurClasse;

import Modele.Bulletin;
import Controleur.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Modele.DetailsBulletin;

public class DetailsBulletinDAO extends Controleur<DetailsBulletin> {

    public DetailsBulletinDAO (Connexion conn) {
        super(conn);
    }

    public boolean create (DetailsBulletin obj) throws ExceptionAlreadyExistant {
        try {
            Statement stmt = this.connect.getConn().createStatement();
            stmt.executeUpdate("Insert Into detailbulletin VALUES (NULL, '" + obj.getId_bulletin() + "', '" + obj.getId_enseignement() + "')");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean create(int id_enseignement, int idBulletin){
        try {
                Statement stmt = this.connect.getConn().createStatement();
                stmt.executeUpdate("Insert Into detailbulletin VALUES (NULL, '" + idBulletin + "', '" + id_enseignement + "')");
                return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean delete(DetailsBulletin obj) {

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

    public boolean update(DetailsBulletin obj, String newName) throws ExceptionAlreadyExistant {
        return false;
    }


    public DetailsBulletin find(DetailsBulletin obj) {
        return obj;
    }


    public DetailsBulletin find(int id, String name) {
        DetailsBulletin year = new DetailsBulletin();
        return year;
    }

    public  DetailsBulletin find(int id, int id1, int id2) {
        return new  DetailsBulletin();
    }

    public ArrayList< DetailsBulletin> findAll() {
        ArrayList< DetailsBulletin> YearArrayList = new ArrayList<>();
        return YearArrayList;
    }

    public ArrayList< DetailsBulletin> findOne(int idbulletin, int idenseignement) {
        ArrayList< DetailsBulletin> YearArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `Detailbulletin`WHERE `id_bulletin` = '" + idbulletin + "' AND id_enseignement = '" + idenseignement + "' ");
            while (result.next()) {
                YearArrayList.add(new DetailsBulletin(
                        result.getInt("id_detail_bulletin"),
                        result.getInt("id_bulletin"),
                        result.getInt("id_enseignement")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return YearArrayList;
    }

    public ArrayList< DetailsBulletin> findAll(int id_bulletin) {
        ArrayList< DetailsBulletin> ArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM detailbulletin WHERE id_bulletin ='" + id_bulletin + "'");
            while (result.next()) {
                ArrayList.add(new DetailsBulletin(
                        result.getInt("id_detail_bulletin"),
                        result.getInt("id_bulletin"),
                        result.getInt("id_enseignement")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ArrayList;
    }

}