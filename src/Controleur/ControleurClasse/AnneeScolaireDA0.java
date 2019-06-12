package Controleur.ControleurClasse;


import Controleur.Connexion;
import Modele.AnneeScolaire;
import Modele.Niveau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe AnneeScolaire
 * permet de recuper les donnes de la base de données
 */
public class AnneeScolaireDA0 extends Controleur<AnneeScolaire> {
    private int nombre_année_scolaire;
    private int nombre;

    /**
     * constructeur de la classe
     * @param conn
     */
    public AnneeScolaireDA0(Connexion conn) {
        super(conn);
        this.nombre = findAll().size() - 1;
        this.nombre_année_scolaire = findAll().get(this.nombre).getId();
    }

    /**
     * Creer une annee scolaire dans la base de donnee
     * @param obj
     * @return
     * @throws ExceptionAlreadyExistant
     */
    public boolean create(AnneeScolaire obj) throws ExceptionAlreadyExistant {

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE nom_anneScolaire ='" + obj.getYear() + "'");
            if (result.first()) {
                throw new ExceptionAlreadyExistant();
            }
            /**
             * On sépare l'année scolaire en 2 années
             */
            String[] data = obj.getYear().split("-", 2);

            this.connect.getConn().createStatement().executeUpdate("Insert INTO AnneeScolaire VALUES (Null,'" + obj.getYear() + "')");
            System.out.println("New school year create in the databse : ");
            this.nombre_année_scolaire += 1;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * delete dans la base de données
     * @param obj
     * @return
     */
    public boolean delete(AnneeScolaire obj) {

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire WHERE nom_anneScolaire ='" + obj.getYear() + "'");
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

    /**
     * update dans la base de donnée
     * @param obj
     * @param newName
     * @return
     * @throws ExceptionAlreadyExistant
     */
    public boolean update(AnneeScolaire obj, String newName) throws ExceptionAlreadyExistant {
        try {
            for (int i = 1; i < this.nombre_année_scolaire + 1; i++) {

                if (find(1, newName).getId() != 0) {
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

    public AnneeScolaire find(AnneeScolaire obj) {
        return obj;
    }

    /**
     * trouver une annee scolaire dans la base donnée
     * @param id
     * @param name
     * @return
     */
    public AnneeScolaire find(int id, String name) {
        AnneeScolaire year = new AnneeScolaire();
        id = 0;
        try {
            for (int i = 0; i < nombre_année_scolaire + 1; i++) {
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

    public AnneeScolaire find(int id, int id1, int id2) {
        return new AnneeScolaire();
    }

    /**
     * trouevr toues les années scolaires dans la database
     * @return
     */
    public ArrayList<AnneeScolaire> findAll() {
        ArrayList<AnneeScolaire> YearArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM AnneeScolaire "); //order by nom_anneScolaire DESC");
            while (result.next()) {
                YearArrayList.add(new AnneeScolaire(
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


