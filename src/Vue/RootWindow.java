/*
 * Created by JFormDesigner on Tue May 28 20:27:54 CEST 2019
 */

package Vue;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListDataListener;

import Controleur.Connexion;
import Controleur.ControleurClasse.AnneeScolaireDA0;
import Controleur.ControleurClasse.Controleur;
import Controleur.ControleurClasse.ExceptionAlreadyExistant;
import Controleur.ControleurClasse.NiveauDAO;
import Modele.AnneeScolaire;
import Modele.Niveau;
import Controleur.AbstractTable;

/**
 * @author basile, benoit, Alexandre
 */
public class RootWindow extends JFrame {

    /**
     * Attributs utilse pour afficher les tables
     */
    private int nombreannee = 3;

    private JPanel JPanelRoot;
    private JPanel JPanelConnect;
    private JLabel loginbdd;
    private JTextField loginBddText;
    private JLabel pswBdd;
    private JPasswordField pswBddText;
    private JLabel nameBdd;
    private JTextField nameBddtext;
    private JButton connectJButton;
    private JLabel ErrorConnect;
    private JPanel JPanelMain;
    private JPanel JPanelEnd;
    private JTabbedPane SchoolGestion;
    private JTabbedPane SchoolYearTabbed;
    private JPanel Level;
    private JPanel ShowYear;
    private JPanel AddYear;
    private JPanel UpdateYear;
    private JPanel DeleteYear;
    private JLabel ErrorMessage;
    private JLabel AddYearBdd;
    private JTextField NameNewYearSchoolText;
    private JButton JButtonAddNewYear;
    private JLabel NameNewYearSchool;
    private JLabel MessageNewYear;
    private JComboBox SelectyearDelete;
    private JButton FinalizeyearDelete;
    private JComboBox SelectYearUpdate;
    private JLabel MessageGenerale;
    private JLabel Selectyearlabel;
    private JLabel UpdateYearlabel;
    private JTextField updateYearText;
    private JButton updateYearButton;
    private JPanel SchoolYear;
    private JTabbedPane LevelTabbed;
    private JPanel ShowLevel;
    private JPanel AddLevel;
    private JPanel UpdateLevel;
    private JPanel DeleteLevel;
    private JLabel SelectYearLevel;
    private JComboBox SeclectyearLevelText;
    private JTextField AddLevelText;
    private JButton AddLevelJButton;
    private JLabel Addlevellabel;
    private JPanel Classe;
    private JTabbedPane ClasseTabbed;
    private JPanel Subject;
    private JPanel Student;
    private JPanel Grades;
    private JPanel Teacher;
    private JPanel Others;
    private JPanel ShowClasse;
    private JPanel AddClasse;
    private JPanel UpdateClasse;
    private JPanel DeleteClasse;
    private JComboBox SelectYearClasse;
    private JLabel SelectYearClasseLabel;
    private JComboBox SelectNiveau;
    private JLabel SelectNiveauLabel;
    private JLabel NewClasseName;
    private JTextField NewClassenameTexte;
    private JButton NewnameClasseJButton;
    private JTable table1;
    private JTable table2;

    private Connexion maconnexion;

    public RootWindow() {
        setTitle("Application de gestion d'une école");
        setSize(800,600);
        add(JPanelRoot);

            /**
             * Méthode appellée lors de l'appui sur le bouton Connect
             * On se connecte à la base de données
             * Si connection : on lance le programme
             */
       connectJButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String passBdd = "";//new String (pswBddText.getPassword());
               String loginBdd = "root";//new String (loginBddText.getText());
               String nameBdd = "gestionecole";//new String (nameBddtext.getText());

               try {
                   try {
                       /**
                        * On essaye de se connecter à la base de données rentrée
                        */
                       maconnexion = new Connexion(nameBdd, loginBdd, passBdd);
                       System.out.println("Succes to connect");
                       ErrorConnect.setText("Succes");
                       ErrorMessage.setText("The database is charged");

                       /**
                        * Si la connexxion est un succès, alors on appelle la classe Controleur<AnnéeScolaire>
                        *     qui hérite de la classe Controleur
                        *     On affiche ainsi toutes les années de l'école présentes dans la base de donnée
                        */
                       Controleur<AnneeScolaire> yearDao = new AnneeScolaireDA0(maconnexion);
                       System.out.println("Try to call the method to look for the Schoolyear");

                       ArrayList Listyear = new ArrayList();

                       for (int i=1; i<nombreannee + 1; i++){
                           AnneeScolaire year = yearDao.find(i);
                           System.out.println(year.getId() + " " + year.getYear());
                           if (year.getId() != 0 ){
                               Listyear.add(year.getYear());
                           }
                       }

                   } catch (ClassNotFoundException evt) {
                       System.out.println(evt);
                       ErrorConnect.setText("ClassNotFoundException");
                       evt.printStackTrace();
                   }
               } catch (SQLException sqlE) {
                   System.out.println(sqlE);
                   ErrorConnect.setText("SQLException");
                   sqlE.printStackTrace();
               }
           }
       });

        /**
         * Méthodes pour gérer les Années Scolaires dans la base de données
         * Méthode Shows
         */


        /**
         * Methode pour ajouter une annee scholaire dans la base de donnée
         * Lorsque de l'appui sur le bouton,
         * on vérifie tout d'abord que la nouvelle année n'existe pas deja
         * sinon on l'ajoute à la base de donnée dans la table AnneeScolaire
         */
        JButtonAddNewYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newYear = NameNewYearSchoolText.getText();
                   Controleur<AnneeScolaire> Newyear = new AnneeScolaireDA0(maconnexion);
                   AnneeScolaire newyearclasse = new AnneeScolaire(0, newYear);
                   try {
                       boolean create = Newyear.create(newyearclasse);
                       ErrorMessage.setText("Adding year in the database succesfull");
                   }catch (Exception expt) {
                       ErrorMessage.setText("Problems in adding this year in the database");
                   }
            }
        });
        /**
         * Methode pour ajouter un niveau d'une année
         */
        AddLevelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newLevelText = AddLevelText.getText();
                Controleur<Niveau> NewLevel = new NiveauDAO(maconnexion);
                Niveau newlevel = new Niveau(0, newLevelText);
                try {
                    boolean create = NewLevel.create(newlevel);
                } catch (Exception expt) {
                    ErrorMessage.setText("Problems in adding this level in the database");
                }
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 = new JTable(new AbstractTable());
        table2 = new JTable(new AbstractTable());
    }
}