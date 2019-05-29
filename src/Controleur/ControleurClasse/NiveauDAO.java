package Controleur.ControleurClasse;

import Controleur.Connexion;
import Modele.Niveau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NiveauDAO extends Controleur<Niveau>{

    public NiveauDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Niveau obj) throws ExceptionAlreadyExistant {
        try {
            for (int i=0; i< 10; i++){
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau WHERE id_niveau =" + i);
                if(result.first())
                    return false;
                throw new ExceptionAlreadyExistant();
            }
            Statement stmt =  this.connect.getConn().createStatement();
            stmt.executeUpdate("Insert INTO Niveau VALUES (Null,'" + obj.getNom()+"')");
            System.out.println("New school level create in the databse : ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Niveau obj) {
        return false;
    }

    public boolean update(Niveau obj) {
        return false;
    }

    public Niveau find(int id) {
        Niveau niveau = new Niveau();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau WHERE id_niveau =" + id);
            if(result.first())
                niveau = new Niveau(
                        id,
                        result.getString("nom_niveau")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return niveau;
    }

    public Niveau find(int id, String name) {
        Niveau niveau = new Niveau();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau WHERE nom_niveau ='" + name+ "' AND id_niveau =" +id);
            if(result.first())
                niveau = new Niveau(
                        result.getInt("id_niveau"),
                        result.getString("nom_niveau")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return niveau;
    }

    public Niveau find (int id, int id1, int id2){
        id1 = id2 = 0;
        final Niveau niveau = find(id);
        return niveau;
    }

    public Niveau recherch (int id, String name){
        Niveau niveau = new Niveau();
        return niveau;
    }
}


