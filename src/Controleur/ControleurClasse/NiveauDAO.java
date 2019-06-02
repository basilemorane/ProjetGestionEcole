package Controleur.ControleurClasse;

import Controleur.Connexion;
import Modele.AnneeScolaire;
import Modele.Niveau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NiveauDAO extends Controleur<Niveau>{
    private int nombre_niveau;
    private int nombre;

    public NiveauDAO(Connexion conn) {
        super(conn);
        this.nombre = findAll().size()-1;
        this.nombre_niveau = findAll().get(nombre).getId();
    }

    public boolean create(Niveau obj) throws ExceptionAlreadyExistant {
        try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau WHERE  nom_niveau = '" + obj.getNom() + "'");
                if(result.first()) {
                    throw new ExceptionAlreadyExistant();
                }

            Statement stmt =  this.connect.getConn().createStatement();
            stmt.executeUpdate("Insert INTO Niveau VALUES (Null,'" + obj.getNom()+"')");
            System.out.println("New school level create in the databse : ");
            nombre_niveau +=1;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Niveau obj) throws ExceptionNotExisting {
        try {
            if ( find (obj).getId() != 0) {
                Statement stmt = this.connect.getConn().createStatement();
                stmt.executeUpdate("Delete From Niveau Where id_niveau = " + find (obj).getId() );
                System.out.println("School level delete " + obj.getNom());
                return true;
            } else
                throw new ExceptionNotExisting();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Niveau obj, String newName) throws ExceptionAlreadyExistant{
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau WHERE  nom_niveau = '" + newName + "'");
            if(result.first()) {
               throw new ExceptionAlreadyExistant();
            }
            Statement stmt = this.connect.getConn().createStatement();
            stmt.executeUpdate("Update Niveau Set nom_niveau = '" + newName + "' Where  nom_niveau = '" + obj.getNom() + "'");

        System.out.println("School level updating");
        return true;
        } catch (SQLException e) {
            e.printStackTrace();
             return false;
        }
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

    public ArrayList<Niveau> findAll () {
        ArrayList<Niveau> niveauArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau");
            while (result.next()){
                niveauArrayList.add(new Niveau(
                        result.getInt("id_niveau"),
                        result.getString("nom_niveau")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return niveauArrayList;
    }

    public Niveau find(int id, String name) {
        Niveau niveau = new Niveau();
        id = 0;

        try {
            for (int i=1; i<nombre_niveau +1; i++) {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau WHERE nom_niveau ='" + name + "' AND id_niveau =" + i);
                if (result.first())
                    niveau = new Niveau(
                            result.getInt("id_niveau"),
                            result.getString("nom_niveau")
                    );
            }
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

    public Niveau find (Niveau obj){
        Niveau niveau = new Niveau();

        try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Niveau WHERE nom_niveau ='" + obj.getNom() + "'");
                if (result.first())
                    niveau = new Niveau(
                            result.getInt("id_niveau"),
                            result.getString("nom_niveau")
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return niveau;
    }
}


