package Controleur.ControleurClasse;

import Controleur.Connexion;
import Modele.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOEleve extends Controleur<Eleve> {
        private int nombre;
        private int nombre_eleve;

        public DAOEleve(Connexion conn) {
            super(conn);
            nombre = findAll().size()-1;
            nombre_eleve = findAll().get(nombre).getId();
        }

        public boolean create(Eleve obj) throws ExceptionAlreadyExistant {
            try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE  nom_personne ='" + obj.getNom() +"' AND prenom_personne ='" + obj.getPrenom() + "' AND type_personne = 0");
                if(result.first()) {
                    throw new ExceptionAlreadyExistant();
                }
                    Statement stmt =  this.connect.getConn().createStatement();
                        stmt.executeUpdate("Insert INTO personne VALUES (Null,'" + obj.getNom()+"', '" + obj.getPrenom() + "', 0)");
                        System.out.println("New student create in the databse : ");
                             this.nombre_eleve+=1;
                            return true;
                    } catch (SQLException e) {
                         e.printStackTrace();
                         return false;
            }
        }

        public boolean delete(Eleve obj) {
            try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE  nom_personne ='" + obj.getNom() +"' AND prenom_personne ='" + obj.getPrenom() + "' AND type_personne = 0");
                if(result.first()) {
                    Statement stmt = this.connect.getConn().createStatement();
                    stmt.executeUpdate("Delete From personne Where id_personne = " + result.getInt("id_personne"));
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public boolean update(Eleve obj, String newName) {
            String[] data = newName.split(" ", 2);
            try {
                ResultSet result = this.connect.getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE  nom_personne ='" + data[0]+"' AND prenom_personne ='" + data[1] + "' AND type_personne = 0");
                if(result.first()) {
                    Statement stmt = this.connect.getConn().createStatement();
                    stmt.executeUpdate("Update personne SET nom_personne = '" + obj.getNom() + "', prenom_personne = '" + obj.getPrenom() + "'  Where id_personne = " + result.getInt("id_personne"));
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            Eleve eleve = new Eleve();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE  nom_personne ='" + obj.getNom() +"' AND prenom_personne ='" + obj.getPrenom() + "' AND type_personne = 0");
            if(result.first()) {
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

    public  ArrayList<Eleve> findAllAbsent (int idyear) {
        ArrayList<Eleve> ArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                   ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `personne` WHERE id_personne NOT IN (SELECT id_personne FROM `inscription` INNER JOIN classe ON inscription.id_classe = classe.id_classe WHERE classe.id_annee_scolaire = '" + idyear + "') AND type_personne = '0'" );
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

    public ArrayList<Eleve> findAllStudent () {
        ArrayList<Eleve> ArrayList = new ArrayList<>();
        try {
            ResultSet result = this.connect.getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    //ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISCTINCT * FROM personne INNER JOIN inscription ON personne.id_personne = inscription.id_personne AND inscription.id_classe = " + id2);
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne where type_personne = '0' " );
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
}

/*
SELECT DISTINCT  personne.id_personne FROM `personne` LEFT JOIN inscription ON personne.id_personne NOT IN (
   SELECT inscription.id_personne FROM inscription LEFT JOIN classe ON inscription.id_classe IN ( SELECT id_classe FROM classe WHERE id_annee_scolaire = '3')) AND type_personne = 0
 */