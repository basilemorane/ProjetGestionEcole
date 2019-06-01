package Controleur.ControleurClasse;

import Modele.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Controleur.Connexion;

public class InscriptionDAO extends Controleur<Inscription> {

        private int nombre_inscription;
        private int nombre;

        public InscriptionDAO(Connexion conn) {
            super(conn);
            //this.nombre = findAll().size()-1;
            //this.nombre_inscription = findAll().get(this.nombre).getId();
        }

        public boolean create(Inscription obj) throws ExceptionAlreadyExistant {
            try {
                    ResultSet result = this.connect.getConn().createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription WHERE id_personne = " + obj.getId_personne() + " AND id_classe = " + obj.getId_classe());
                    if(result.first()) {
                        throw new ExceptionAlreadyExistant();
                    }

                Statement stmt =  this.connect.getConn().createStatement();
                stmt.executeUpdate("Insert INTO inscription VALUES (Null,'" + obj.getId_classe()+"', '" + obj.getId_personne() + "')");
                System.out.println("The student is sign in a class ");

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean delete(Inscription obj) {
                return false;
        }

        public boolean update(Inscription obj, String newName) {

                return false;

        }

        public Inscription find (Inscription obj){
            return obj;
        }

        public Inscription find(int id) {
            Inscription obj = new Inscription();
                return obj;
        }

        public Inscription find(int id, String name) {
            Inscription obj = new Inscription();
                return obj;
        }

        public Inscription find (int id, int id1, int id2){
            id1 = id2 = 0;
            final Inscription anneeScolaire = find(id);
            return anneeScolaire;
        }

        public Inscription recherch (int id, String name) {
            Inscription year = new Inscription();
            return year;
        }

        public ArrayList<Inscription> findAll () {
            ArrayList<Inscription> YearArrayList = new ArrayList<>();
            return YearArrayList;
        }
    }




