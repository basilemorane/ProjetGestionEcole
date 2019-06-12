package Controleur.ControleurClasse;
import Modele.Classe;

import Controleur.Connexion;
import Modele.Niveau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe Classe DAO
 * permet de recuperer les données dans la base de donnée pour la classe correspondante
 *      - un constructeur avec paramètre
 *      - une methdde create
 *      - une methode delete
 *      - une methode update
 *      - methode find () (Object)
 *      - methode findALL() (ArrayList <Object>)
 *
 */

public class ClasseDA0 extends Controleur<Classe>{
    private int nombre_classe;
    private int nombre;

    public ClasseDA0(Connexion conn) {
        super(conn);
        this.nombre = findAll().size()-1;
        this.nombre_classe = findAll().get(nombre).getId();
    }

    public boolean create(Classe obj) throws ExceptionAlreadyExistant {
        try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id_ecole = 1 AND nom_classe ='" + obj.getName() +"' AND id_niveau =" + obj.getIdNiveau() + " AND id_annee_scolaire = " + obj.getIdAnneeScolaire());
                if(result.first()) {
                    throw new ExceptionAlreadyExistant();
                }

            Statement stmt =  this.connect.getConn().createStatement();
            stmt.executeUpdate("Insert INTO classe VALUES (Null,'" + obj.getName()+"', 1, " + obj.getIdAnneeScolaire()+", "+ obj.getIdNiveau()+")");
            System.out.println("New school classe create in the databse : ");
            this.nombre_classe+=1;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Classe obj) throws ExceptionNotExisting {
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id_ecole = 1 AND nom_classe ='" + obj.getName() +"' AND id_niveau =" + obj.getIdNiveau() + " AND id_annee_scolaire = " + obj.getIdAnneeScolaire());
            if(result.first()) {
                Statement stmt = this.connect.getConn().createStatement();
                stmt.executeUpdate("Delete From Classe Where id_classe = " + result.getInt("id_classe"));
                return true;
            }
            throw new ExceptionNotExisting();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Classe obj, String newName) {
        newName ="";
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id_classe = " + obj.getId());
            if(result.first()) {
                Statement stmt = this.connect.getConn().createStatement();
                stmt.executeUpdate(" Update Classe Set nom_classe = '" + obj.getName() + "', id_annee_scolaire = " + obj.getIdAnneeScolaire() + ", id_niveau = " + obj.getIdNiveau() + " Where id_classe = " + result.getInt("id_classe"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Classe find(int id, int idyear, int idniveau) {
        Classe TD = new Classe();

        try {
            for (int i=0; i< nombre_classe+1; i++) {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery(
                        "SELECT * FROM Classe WHERE id_classe =" + id + " AND id_annee_scolaire = " + idyear + " AND id_niveau =" + idniveau);
                // "SELECT id_classe, classe.id_niveau, classe.id_annee_scolaire, nom_classe FROM classe LEFT JOIN niveau ON classe.id_niveau = "+ idniveau + " LEFT JOIN anneescolaire ON anneescolaire.id_annee_scolaire = " + idyear + " WHERE id_classe = " + id);

                if (result.first()) {
                    TD = new Classe(
                            result.getInt("id_classe"),
                            result.getString("nom_classe"),
                            idyear,
                            idniveau
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TD;
    }

    public Classe find(int id) {
        Classe TD = new Classe();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Classe WHERE id_classe =" + id);
            if(result.first()) {
                TD = new Classe(
                        result.getInt("id_classe"),
                        result.getString("nom_classe"),
                        result.getInt("id_niveau"),
                        result.getInt("id_annee_scolaire")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TD;
    }

    public Classe find (Classe obj) {
        Classe classe = new Classe();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE nom_classe ='" + obj.getName() +"' AND id_niveau =" + obj.getIdNiveau() + " AND id_annee_scolaire = " + obj.getIdAnneeScolaire());
            if(result.first())
            classe = new Classe(
                        result.getInt("id_classe"),
                        result.getString("nom_classe"),
                        result.getInt("id_niveau"),
                        result.getInt("id_annee_scolaire")
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
       return classe;
    }


    public Classe find(int id, String name) {
        Classe TD = new Classe();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Classe WHERE nom_classe = '" + name + "'");
            if (result.first()) {
                TD = new Classe(
                        result.getInt("id_classe"),
                        result.getString("nom_classe"),
                        result.getInt("id_niveau"),
                        result.getInt("id_annee_scolaire")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TD;
    }

    public ArrayList<Classe> findAll () {
        ArrayList<Classe> ArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Classe");
            if(result.first())
                ArrayList.add( new Classe(
                        result.getInt("id_classe"),
                        result.getString("nom_classe"),
                        result.getInt("id_niveau"),
                        result.getInt("id_annee_scolaire")
                ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ArrayList;
    }

    public ArrayList<Classe> findAll (int id, int idyear, int idniveau) {
        id = 1;
        ArrayList<Classe> ArrayList = new ArrayList<>();
        try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery(
                        "SELECT * FROM Classe WHERE id_annee_scolaire = " + idyear + " AND id_niveau =" + idniveau);
                while (result.next()) {
                    ArrayList.add(new Classe(
                            result.getInt("id_classe"),
                            result.getString("nom_classe"),
                            result.getInt("id_niveau"),
                            result.getInt("id_annee_scolaire")
                    ));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ArrayList;
    }
}

