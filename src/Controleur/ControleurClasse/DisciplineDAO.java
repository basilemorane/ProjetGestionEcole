package Controleur.ControleurClasse;

import Modele.AnneeScolaire;
import Modele.Discipline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import Controleur.Connexion;

public class DisciplineDAO extends Controleur<Discipline>{
    private int nombre_année_scolaire;
    private int nombre;

    public DisciplineDAO(Connexion conn) {
        super(conn);
        this.nombre = findAll().size()-1;
        this.nombre_année_scolaire = findAll().get(this.nombre).getId_discipline();
    }

    public boolean create(Discipline obj) throws ExceptionAlreadyExistant {

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Discipline WHERE nom_discipline ='" + obj.getNom_classe() +"'");
            if(result.first()) {
                throw new ExceptionAlreadyExistant();
            }
            Statement stmt =  this.connect.getConn().createStatement();
            stmt.executeUpdate("Insert INTO Discipline VALUES (Null,'" + obj.getNom_classe()+"')");
            System.out.println("New discipline create in the databse : ");
            this.nombre_année_scolaire+=1;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDisciplinetoPromo (Discipline obj, int idyear, int idlevel) {
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_classe FROM `classe` WHERE `id_annee_scolaire` ='"+ idyear + "' AND `id_niveau` = '"+ idlevel + "'");
                    while (result.next()) {
                        Statement stmt =  this.connect.getConn().createStatement();
                        stmt.executeUpdate("Insert INTO Enseignement VALUES (Null,'" +result.getInt("id_classe") + "', '" + obj.getId_discipline() +"', '3')");
                    }
        } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
        return true;
    }

    public boolean delete(Discipline obj) {
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Discipline WHERE nom_discipline = '" + obj.getNom_classe() + "'");
            if (result.first()) {
                Statement stmt = this.connect.getConn().createStatement();
                stmt.executeUpdate("Delete From discipline Where id_discipline = '" + result.getInt("id_discipline") + "'");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Discipline obj, String newName) throws ExceptionAlreadyExistant {
        try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Discipline WHERE nom_discipline = '" + obj.getNom_classe() + "'");
                if (result.first()) {
                    Statement stmt = this.connect.getConn().createStatement();
                    stmt.executeUpdate("Update Discipline SET nom_discipline = '" + newName + "' Where id_discipline = '" + result.getInt("id_discipline") + "'");
                return true;
                } else
                    throw new ExceptionAlreadyExistant();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Discipline find (Discipline obj){
        return obj;
    }


    public Discipline find(int id) {
        Discipline year = new Discipline();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id_discipline =" + id);
            if(result.first())
            year = new Discipline(
                    result.getInt("id_discipline"),
                    result.getString("nom_discipline")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return year;
    }

    public Discipline find(int id, String name) {
        Discipline year = new Discipline();
        id=0;
        try {
            for (int i=0; i< nombre_année_scolaire+1; i++) {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id_discipline = " + i + " AND nom_discipline ='" + name + "'");
                if (result.first())
                    year = new Discipline(
                            result.getInt("id_discipline"),
                            result.getString("nom_discipline")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return year;
    }

    public Discipline find (int id, int id1, int id2){
        id1 = id2 = 0;
        final Discipline anneeScolaire = find(id);
        return anneeScolaire;
    }


    public ArrayList<Discipline> findAll () {
        ArrayList<Discipline> YearArrayList = new ArrayList<>();
        try {
            ResultSet result =  this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Discipline ");
            while (result.next()){
                YearArrayList.add( new Discipline(
                        result.getInt("id_discipline"),
                        result.getString("nom_discipline")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return YearArrayList;
    }

    public ArrayList<Discipline> findAllDispline (int idBulletin) {
        ArrayList<Discipline> DisciplineArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE discipline.id_discipline IN ( SELECT enseignement.id_discipline FROM enseignement INNER JOIN detailbulletin ON detailbulletin.id_enseignement = enseignement.id_enseignement WHERE detailbulletin.id_bulletin = '" + idBulletin + "')");
            while (result.next()) {
                DisciplineArrayList.add(new Discipline(
                        result.getInt("id_discipline"),
                        result.getString("nom_discipline")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DisciplineArrayList;
    }

    public ArrayList<Discipline> findAll (int id2) {
        ArrayList<Discipline> DisciplineArrayList = new ArrayList<>();
        try {
            ResultSet result =  this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                  //  ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `discipline` INNER JOIN enseignement ON discipline.id_discipline = enseignement.id_discipline WHERE enseignement.id_classe = " + id2 );
            ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `discipline` INNER JOIN enseignement ON discipline.id_discipline = enseignement.id_discipline Where enseignement.id_classe = '" + id2 + "'");
            while (result.next()){
                DisciplineArrayList.add( new Discipline(
                        result.getInt("id_discipline"),
                        result.getString("nom_discipline")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DisciplineArrayList;
    }

    public ArrayList<Discipline> findAllAbsent (int id2, int idLevel) {
        ArrayList<Discipline> DisciplineArrayList = new ArrayList<>();
        try {
            ResultSet result =  this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    //  ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `discipline` INNER JOIN enseignement ON discipline.id_discipline = enseignement.id_discipline WHERE enseignement.id_classe = " + id2 );
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * from discipline WHERE id_discipline NOT IN (SELECT enseignement.id_discipline FROM `enseignement` INNER JOIN classe ON enseignement.id_classe = classe.id_classe WHERE classe.id_annee_scolaire = '" + id2 + "' AND classe.id_niveau = '" + idLevel + "')");
            while (result.next()){
                DisciplineArrayList.add( new Discipline(
                        result.getInt("id_discipline"),
                        result.getString("nom_discipline")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DisciplineArrayList;
    }

}

/*
SELECT * from discipline WHERE id_discipline IN (SELECT enseignement.id_discipline FROM `enseignement` INNER JOIN classe ON enseignement.id_classe = classe.id_classe WHERE classe.id_niveau = '1' AND classe.id_annee_scolaire = '3')
 */