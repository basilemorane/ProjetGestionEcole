package Controleur.ControleurClasse;

import Controleur.Connexion;
import Modele.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEleve extends Controleur<Eleve> {

        public DAOEleve(Connexion conn) {
            super(conn);
        }

        public boolean create(Eleve obj) {
            return false;
        }

        public boolean delete(Eleve obj) {
            return false;
        }

        public boolean update(Eleve obj, String newName) {
            return false;
        }

        public Eleve find(int id) {
            Eleve eleve = new Eleve();

            try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE type_personne = '0' AND id_personne = " + id);
                if(result.first())
                    eleve = new Eleve(
                            result.getInt("id_personne"),
                            result.getString("nom_personne"),
                            result.getString("prenom_personne")
                    );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return eleve;
        }

    public Eleve find (Eleve obj){
        return obj;
    }


    public Eleve find(int id, String name) {
        Eleve eleve = new Eleve();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE nom_personne ='" + name + "'");
            if(result.first())
                eleve = new Eleve(
                        result.getInt("id_personne"),
                        result.getString("nom_personne"),
                        result.getString("prenom_personne")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eleve;
    }

    public Eleve find (int id, int id1, int id2){
            id1 =0;
            Eleve eleve = new Eleve();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    //ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISCTINCT * FROM personne INNER JOIN inscription ON personne.id_personne = inscription.id_personne AND inscription.id_classe = " + id2);
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription WHERE id_classe = " + id2 + " AND id_inscription = " + id);
            if (result.first()) {
                Inscription inscription = new Inscription(
                        result.getInt("id_inscription"),
                        result.getInt("id_classe"),
                        result.getInt("id_personne")
                );
                 result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        //ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISCTINCT * FROM personne INNER JOIN inscription ON personne.id_personne = inscription.id_personne AND inscription.id_classe = " + id2 ');
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id_personne = " + inscription.getId() + " AND type_personne = '0'");
            if (result.first())
                eleve = new Eleve(
                        result.getInt("id_personne"),
                        result.getString("nom_personne"),
                        result.getString("prenom_personne")
                );
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eleve;
    }

    public Eleve recherch ( int id, String name){
        Eleve eleve = new Eleve();

        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id_personne = " + id + " AND nom_personne LIKE '%" + name + "%'" );
            if(result.first())
                eleve = new Eleve(
                        result.getInt("id_personne"),
                        result.getString("nom_personne"),
                        result.getString("prenom_personne")
                );
            else {
                result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id_personne = " + id + " AND prenom_personne LIKE '%" + name + "%'");
                if (result.first())
                    eleve = new Eleve(
                            result.getInt("id_personne"),
                            result.getString("nom_personne"),
                            result.getString("prenom_personne")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eleve;
    }
    public ArrayList<Eleve> findAll (int id2) {
        ArrayList<Eleve> ArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    //ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISCTINCT * FROM personne INNER JOIN inscription ON personne.id_personne = inscription.id_personne AND inscription.id_classe = " + id2);
                   // ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription WHERE id_classe = " + id2 );
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne INNER JOIN inscription On inscription.id_classe = "+ id2 + " AND personne.id_personne = inscription.id_personne");
            while (result.next()) {
                ArrayList.add(new Eleve(
                        result.getInt("id_personne"),
                        result.getString("nom_personne"),
                        result.getString("prenom_personne")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ArrayList;
    }

    public ArrayList<Eleve> findAll () {
        ArrayList<Eleve> ArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    //ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISCTINCT * FROM personne INNER JOIN inscription ON personne.id_personne = inscription.id_personne AND inscription.id_classe = " + id2);
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription " );
            if (result.first()) {
                Inscription inscription = new Inscription(
                        result.getInt("id_inscription"),
                        result.getInt("id_classe"),
                        result.getInt("id_personne")
                );
                result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id_personne = " + inscription.getId() + " AND type_personne = '0'");
                if (result.first())
                    ArrayList.add(new Eleve(
                            result.getInt("id_personne"),
                            result.getString("nom_personne"),
                            result.getString("prenom_personne")
                    ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ArrayList;
    }

}

