package Controleur.ControleurClasse;
import Modele.Classe;

import Controleur.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasseDA0 extends Controleur<Classe>{

    public ClasseDA0(Connexion conn) {
        super(conn);
    }

    public boolean create(Classe obj) {
        return false;
    }

    public boolean delete(Classe obj) {
        return false;
    }

    public boolean update(Classe obj) {
        return false;
    }

    public Classe find(int id, int idyear, int idniveau) {
        Classe TD = new Classe();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                            "SELECT * FROM Classe WHERE id_classe =" + id + " AND id_annee_scolaire = " + idyear + " AND id_niveau =" + idniveau);
                   // "SELECT id_classe, classe.id_niveau, classe.id_annee_scolaire, nom_classe FROM classe LEFT JOIN niveau ON classe.id_niveau = "+ idniveau + " LEFT JOIN anneescolaire ON anneescolaire.id_annee_scolaire = " + idyear + " WHERE id_classe = " + id);

            if(result.first()) {
                TD = new Classe(
                        result.getInt("id_classe"),
                        result.getString("nom_classe"),
                        idyear,
                        idniveau
                );
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
}

