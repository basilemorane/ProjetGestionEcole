package Vue;

import Controleur.*;
import Controleur.ControleurClasse.*;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener, ItemListener {
    private Connexion maconnexion;
    private final List ListYear, ListNiveau, ListClasse, ListEleve;
    private final JLabel loginBDD, passBDD, nameBDD, Updatebdd, JLanneeScolaire, JLniveau, JLclasse, JLeleve;
    private final JTextField loginBDDTexte, nameBDDTexte;
    private final JPasswordField passBDDTexte;
    private final JButton connect, JBclasse, JBeleve, JBteacher;
    private final JPanel p0, p1,p2, p3, p4, nord;

    private ArrayList<AnneeScolaire> anneeSelelect;
    private ArrayList<Niveau> niveauSelelect;
    private ArrayList<Classe> classeSelelect;


    public Window() {
        super (" Projet : Gestion d'une école");
        setLayout(new BorderLayout());
        setBounds(0,0,800,800);
        setResizable(true);
        setVisible(true);

        /**
         * Création des Jbuttons de la fentre
         * JButton connection Base de Donnees
         */
        connect = new JButton("Connexion BDD");
        JBclasse = new JButton("Classe");
        JBeleve = new JButton("Eleve");
        JBteacher = new JButton("je sais pas encore");

        /**
         * Création des Listes pour les tables
         */
        ListClasse = new List(1, false);
        ListNiveau = new List(1, false);
        ListYear = new List(1, false);
        ListEleve = new List(1, false);


        /**
         * initilastion des Arraylist
         */
        anneeSelelect = new ArrayList<>();
        niveauSelelect = new ArrayList<>();
        classeSelelect = new ArrayList<>();
        /**
         * Création des JTextField
         * Pouvoir se connecter à la base de données
         */
        loginBDDTexte = new JTextField();
        nameBDDTexte = new JTextField();
        passBDDTexte = new JPasswordField(8);

        /**
         * Creation des labels
         */

        loginBDD = new JLabel("Login : ", JLabel.CENTER);
        passBDD = new JLabel("Password : ", JLabel.CENTER);
        nameBDD = new JLabel("Name BDD : ");
        Updatebdd = new JLabel("Update your bdd",  JLabel.CENTER);
        JLanneeScolaire = new JLabel("Annee Scolaire",  JLabel.CENTER);
        JLclasse = new JLabel("Classe",  JLabel.CENTER);
        JLniveau = new JLabel("Niveau",  JLabel.CENTER);
        JLeleve = new JLabel("Eleve",  JLabel.CENTER);

        /**
         * Initialition des panels de la fenetre
         */
        p0 = new JPanel();
        p1 = new JPanel();
        nord = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();

        /**
         * Mise en page des panels de la fenetre
         */
        p0.setLayout(new GridLayout(1,11));
        p1.setLayout(new GridLayout(1,4));
        p4.setLayout(new GridLayout(1,4));
        nord.setLayout(new GridLayout(3,1));
        p2.setLayout(new GridLayout(1,4));
        p3.setLayout(new GridLayout(1,2));


        /**
         * Ajout des objets graphique dans les panels
         */
        p0.add(loginBDD);
        p0.add(loginBDDTexte);
        p0.add(passBDD);
        p0.add(passBDDTexte);
        p0.add(nameBDD);
        p0.add(nameBDDTexte);
        p0.add(connect);

        p1.add(Updatebdd);
        p1.add(JBclasse);
        p1.add(JBteacher);
        p1.add(JBeleve);

        p4.add(JLanneeScolaire);
        p4.add(JLniveau);
        p4.add(JLclasse);
        p4.add(JLeleve);

        nord.add("North", p0);
        nord.add("North", p1);
        nord.add("North", p4);

        p2.add(ListYear);
        p2.add((ListNiveau));
        p2.add(ListClasse);
        p2.add(ListEleve);


        /**
         * Ajout des Actionlisteners sur les JButtons
         */
        connect.addActionListener(this);
        JBclasse.addActionListener(this);
        JBeleve.addActionListener(this);
        JBteacher.addActionListener(this);
        loginBDDTexte.addActionListener(this);
        passBDDTexte.addActionListener(this);
        nameBDDTexte.addActionListener(this);

        ListYear.addItemListener(this);
        ListNiveau.addItemListener(this);
        ListClasse.addItemListener(this);
        ListEleve.addItemListener(this);

        /**
         * Ajout Couleur sur les panels et autre
         */
        p0.setBackground(Color.blue);
        ListClasse.setBackground(Color.CYAN);
        ListYear.setBackground(Color.GREEN);
        ListEleve.setBackground(Color.orange);

        /**
         * Disposition Graphique des panels
         */
        add("North", nord);
        add(p2);

        /**
         * Pour pouvoir fermer la fenetre
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Utilisation de la fonction actionPerformed pour gérer les actions sur les boutons
     * @param evt
     */
    @Override
    @SuppressWarnings("CallThreadDumpStack")
    public void actionPerformed (ActionEvent evt){
        Object source = evt.getSource();

        /**
         * Méthode appellée lors de l'appui sur le bouton Connect
         * On se connecte à la base de données
         * Si connection : on lance le programme
         */

        if (source == connect){
            String passBdd = "";//new String (passBDDTexte.getPassword());
            String loginBdd = "root";//new String (loginBDDTexte.getText());
            String nameBdd = "gestionecole";//new String (nameBDDTexte.getText());
            try {
                try {
                    /**
                     * On essaye de se connecter à la base de données rentrée
                     */
                    maconnexion = new Connexion(nameBdd, loginBdd, passBdd);
                    System.out.println("Succes to connect");
                   System.out.println( maconnexion.remplirChampsRequete("SELECT nom_ecole FROM Ecole WHERE id_ecole = 1"));

                    Controleur<Eleve> eleveDao = new DAOEleve(maconnexion);
                    for(int i = 1; i < 6; i++){
                        Eleve eleve = eleveDao.find(i);
                        JLabel Eleve = new JLabel("Elève N°" + eleve.getId() + "  - " + eleve.getNom() + " " + eleve.getPrenom());
                        System.out.println("Elève N°" + eleve.getId() + "  - " + eleve.getNom() + " " + eleve.getPrenom());
                        p2.add(Eleve);
                        add(p2);
                    }

                    /**
                     * Si la connexxion est un succès, alors on appelle la classe Controleur<AnnéeScolaire>
                     *     qui hérite de la classe Controleur
                     *     On affiche ainsi toutes les années de l'école présentes dans la base de donnée
                     */
                    Controleur<AnneeScolaire> yearDao = new AnneeScolaireDA0(maconnexion);
                    System.out.println("Try to call the method to look for the Schoolyear");
                    for (int i=1; i<4; i++){
                        AnneeScolaire year = yearDao.find(i);
                        System.out.println(year.getId() + " " + year.getYear());
                        this.ListYear.add(year.getYear());
                    }
                } catch (ClassNotFoundException e){
                    System.out.println(e);
                    e.printStackTrace();
                }
            }catch (SQLException sqlE){
                System.out.println(sqlE);
                sqlE.printStackTrace();
            }
        } else if (source == JBeleve){
                Udpatebdd upd = new Udpatebdd("personne");
        } else if (source == JBclasse) {
            ClasseVue classe = new ClasseVue("test");
        }
    }

    @Override
    @SuppressWarnings("CallThreadDumpStack")
    public void itemStateChanged (ItemEvent e){
        boolean test = false;

        if (e.getSource() == ListYear){
            ListNiveau.removeAll();
            this.anneeSelelect = new ArrayList<>();

            String year = ListYear.getSelectedItem();

            Controleur<AnneeScolaire> AnneeSelect = new AnneeScolaireDA0(maconnexion);
            for (int z = 0; z< 4; z++) {
               AnneeScolaire test1 = AnneeSelect.find(z, year);
                if (test1.getId() !=0){
                    System.out.println(test1.getId());
                    this.anneeSelelect.add(test1);
                }
            }

            /**
             * Affiche tous les niveaux disponible de la base de données
             */
            Controleur<Niveau> niveauDao = new NiveauDAO(maconnexion);
            for (int i = 1; i<6; i++){
                Niveau niveau = niveauDao.find(i);
                ListNiveau.add(niveau.getNom());
            }
        }

       if (e.getSource() == ListNiveau){
            String NiVeau = ListNiveau.getSelectedItem();
            niveauSelelect = new ArrayList<>();
            ListClasse.removeAll();

            Controleur<Niveau> niveauDAO = new NiveauDAO(maconnexion);
            for (int z = 0; z< 4; z++){
                Niveau niveau = niveauDAO.find(z,NiVeau);
                if (niveau.getId() != 0){
                    System.out.println(niveau.getId());
                    this.niveauSelelect.add(niveau);
                }
            }

            Controleur<Classe> classeDAO = new ClasseDA0(maconnexion);
            for (int i=1; i< 6; i++) {
               System.out.println("Id de l'année :" + anneeSelelect.get(0).getId() + " Id du niveau " + niveauSelelect.get(0).getId());
                Classe classe = classeDAO.find(i, anneeSelelect.get(0).getId(), niveauSelelect.get(0).getId());
                System.out.println(classe.getName());
                if (classe.getId() != 0) {
                    ListClasse.add(classe.getName());
                }
            }
        }

       if (e.getSource() == ListClasse) {
           ListEleve.removeAll();
           String TD = ListClasse.getSelectedItem();
           this.classeSelelect = new ArrayList<>();

           Controleur<Classe> classeDAO = new ClasseDA0(maconnexion);
           for (int z = 0; z<5; z++) {
              Classe classe = classeDAO.find(z, TD);
               if (classe.getId() != 0){
                   this.classeSelelect.add(classe);
               }
           }
           /**
            * On appelle la classe DAOELeve
            * On utlise la methode FIND
            * requete en fonction de l'ID de la classe de l'élève
            */
           Controleur<Eleve> Eleve = new DAOEleve(maconnexion);
           for (int i = 0; i<10; i++){
               System.out.println(this.classeSelelect.get(0).getId());
               Eleve student = Eleve.find(i, 1, this.classeSelelect.get(0).getId());
               if (student.getId() != 0){
                   ListEleve.add(student.getNom() + " " + student.getPrenom());
               }
           }

       }
    }

}
