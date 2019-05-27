package Controleur.ControleurClasse;

import Controleur.Connexion;
import Modele.Niveau;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NiveauDAO extends Controleur<Niveau>{

    public NiveauDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Niveau obj) {
        return false;
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


