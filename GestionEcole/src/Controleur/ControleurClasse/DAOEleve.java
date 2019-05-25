package Controleur.ControleurClasse;

import Controleur.Connexion;
import Modele.Eleve;
import Modele.Inscription;

import java.sql.ResultSet;
import java.sql.SQLException;

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

        public boolean update(Eleve obj) {
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
}

