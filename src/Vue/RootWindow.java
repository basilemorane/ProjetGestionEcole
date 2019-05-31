/*
 * Created by JFormDesigner on Tue May 28 20:27:54 CEST 2019
 */

package Vue;

import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import Controleur.Connexion;
import Controleur.ControleurClasse.*;
import Modele.AnneeScolaire;
import Modele.Classe;
import Modele.Niveau;
import Controleur.AbstractTable;

/**
 * @author basile, benoit, Alexandre
 */
public class RootWindow extends JFrame {

    /**
     * Attributs utilse pour afficher les tables
     */
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
    private JTextField newNameClasseUpdate;
    private JButton UpdateClasseJButton;
    private JComboBox SelectClasseUpdate;
    private JComboBox SelectlevelDelete;
    private JButton LevelDelete;
    private JComboBox SelctLevelUpdate;
    private JTextField SelectLevelUpdateTexte;
    private JButton LevelUpdateJButton;
    private JComboBox SelectClassDelete;
    private JButton ClassDeleteJButton;
    private JComboBox SelectUpdateYearClasse;
    private JComboBox SelectUpdatelevelClasse;
    private JTabbedPane StudentTabbed;
    private JPanel ShowStudent;
    private JPanel AddStudent;
    private JPanel UpdateStudent;
    private JPanel DeleteStudent;
    private JPanel GradesStudent;
    private JComboBox comboBoxYearStudent;
    private JComboBox comboBoxLevelStudent;
    private JComboBox comboBoxClasseStudent;
    private JComboBox comboBoxSelectStudentUpdate;

    private Connexion maconnexion ;

    public RootWindow() {
        setTitle("Application de gestion d'une école");
        setSize(800,600);
        add(JPanelRoot);

            /** BOUTON CONNEXION
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
                       System.out.println("Succes to connect");

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

        /** ANNNEE SCOLAIRE
         * Si on clique sur le panel des Années scolaires
         * On afficeh dans la combox tous les années de la database
         */
        SchoolYearTabbed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                SelectYearUpdate.removeAllItems();
                new AnneeScolaireDA0(maconnexion).findAll().forEach(anneeScolaire -> {
                    /**
                     * Remplir la combox pour choisir l'année scolaire à update
                     */
                    SelectYearUpdate.addItem(anneeScolaire.getYear());
                });

                /**
                 * Supprimer tous les items de la comboBox avant de la ré-remplir
                 * Remplir la combox pour choisir l'année scolaire à supprimer
                 */
                SelectyearDelete.removeAllItems();
                for (AnneeScolaire anneeScolaire : new AnneeScolaireDA0(maconnexion).findAll()) {
                    SelectyearDelete.addItem(anneeScolaire.getYear());
                }
            }
        });

        /**
         * Methode pour ajouter une année scolaire dans la base de donnée
         * Lorsque de l'appui sur le bouton,
         * on vérifie tout d'abord que la nouvelle année n'existe pas deja
         * sinon on l'ajoute à la base de donnée dans la table AnneeScolaire
         */
        JButtonAddNewYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newYear = NameNewYearSchoolText.getText();
                try {
                    new AnneeScolaireDA0(maconnexion).create( new AnneeScolaire(0, newYear));
                    ErrorMessage.setText("Adding year in the database succesfull");
                }catch (ExceptionAlreadyExistant expt) {
                    ErrorMessage.setText("Problems in adding this year in the database");
                }
            }
        });

        FinalizeyearDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DeleteYear = (String) SelectyearDelete.getSelectedItem();
                new AnneeScolaireDA0(maconnexion).delete(new AnneeScolaireDA0(maconnexion).find(1, DeleteYear));
                ErrorMessage.setText("Succes deleting level school in the database");
            }
        });

        updateYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String UpdateyearSelect = (String) SelectYearUpdate.getSelectedItem();
                 String NewNameyear = updateYearText.getText();
                new AnneeScolaireDA0(maconnexion).update(new AnneeScolaireDA0(maconnexion).find(1, UpdateyearSelect),NewNameyear);
                ErrorMessage.setText("Succes updating level school in the database");
            }
        });

        /**
         * NIVEAUX SCOLAIRE
         * Si on clique sur la fenetre des niveaux scolaire
         */
        LevelTabbed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                /**
                 * On supprime tous les items de la comboBox
                 */
                SeclectyearLevelText.removeAllItems();
                SelectlevelDelete.removeAllItems();
                SelctLevelUpdate.removeAllItems();
                new AnneeScolaireDA0(maconnexion).findAll().forEach(anneeScolaire -> {
                    /**
                     * Remplir la combox pour choisir l'année scolaire à update
                     */
                    SeclectyearLevelText.addItem(anneeScolaire.getYear());
                });
                new NiveauDAO(maconnexion).findAll().forEach(niveau -> {
                    SelectlevelDelete.addItem(niveau.getNom());
                    SelctLevelUpdate.addItem(niveau.getNom());
                });
            }
        });

        /**
         * Methode pour ajouter un niveau d'une année
         */
        AddLevelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newLevelText = AddLevelText.getText();
                try {
                     new NiveauDAO(maconnexion).create(new Niveau(0, newLevelText));
                    ErrorMessage.setText("Succes adding level school in the database");
                } catch (Exception expt) {
                    ErrorMessage.setText("Problems in adding this level in the database");
                }
            }
        });

        /**
         * Méthode pour supprimer un niveau scolaire de la base de donnée
         *
         */
        LevelDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deletelevel = (String) SelectlevelDelete.getSelectedItem();
                new NiveauDAO(maconnexion).delete(new NiveauDAO(maconnexion).find(1,deletelevel));
                ErrorMessage.setText("Succes deleting level school in the database");
            }
        });

        /**
         * Méthode pour modifer le nom d'un niveaux scolaire
         * lors du clic sur le bouton LevelUpdtaeJButton
         */

        LevelUpdateJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updtateLevel = (String) SelctLevelUpdate.getSelectedItem();
                String newnameLevel = SelectLevelUpdateTexte.getText();
                new NiveauDAO(maconnexion).update(new NiveauDAO(maconnexion).find(1,updtateLevel), newnameLevel);
                ErrorMessage.setText("Succes updating level school in the database");
            }
        });


        /**
         * CLASSE
         * Lors d'un clic sur l'onglet Classe, on remplit les combox Box de selection
         */
        ClasseTabbed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SelectYearClasse.removeAllItems();
                SelectUpdatelevelClasse.removeAllItems();
                SelectUpdateYearClasse.removeAllItems();
                    new AnneeScolaireDA0(maconnexion).findAll().forEach(anneeScolaire -> {
                        SelectYearClasse.addItem(anneeScolaire.getYear());
                        SelectUpdateYearClasse.addItem(anneeScolaire.getYear());
                    });
                SelectNiveau.removeAllItems();
                    new NiveauDAO(maconnexion).findAll().forEach((niveau)->{
                        SelectNiveau.addItem(niveau.getNom());
                        SelectUpdatelevelClasse.addItem(niveau.getNom());
                    });
                }
        });

                /**
                 * Losque l'on sélectionne une année pour les classes scolaires
                 */
        SelectYearClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectClasseUpdate.removeAllItems();
                String yearClasse = (String) SelectYearClasse.getSelectedItem();
                String levelClasse = (String) SelectNiveau.getSelectedItem();

                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

                new ClasseDA0(maconnexion).findAll(1, idyearClasse, idLevelClasse).forEach(classe -> {
                    SelectClasseUpdate.addItem(classe.getName());
                });
            }
    });

        SelectNiveau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectClasseUpdate.removeAllItems();
                SelectClassDelete.removeAllItems();
                String yearClasse = (String) SelectYearClasse.getSelectedItem();
                String levelClasse = (String) SelectNiveau.getSelectedItem();

                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

                new ClasseDA0(maconnexion).findAll(1, idyearClasse, idLevelClasse).forEach(classe -> {
                    SelectClasseUpdate.addItem(classe.getName());
                    SelectClassDelete.addItem(classe.getName());
                });
            }
        });
        /**
         * Méthode pour ajouter une classe dans la base de données
         */
        NewnameClasseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String yearClasse = (String) SelectYearClasse.getSelectedItem();
                String levelClasse = (String) SelectNiveau.getSelectedItem();
                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

                String newNameClasse = NewClassenameTexte.getText();

                Classe newClasse = new Classe(0, newNameClasse, idyearClasse, idLevelClasse);
                try {
                    new ClasseDA0(maconnexion).create(newClasse);
                    ErrorMessage.setText("Class adding in the database");
                } catch (ExceptionAlreadyExistant evt){
                    ErrorMessage.setText("This class already exist in the database");
                }
            }
        });

        /**
         * Méthode pour supprimer une classe
         */

        ClassDeleteJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectClassDelete.removeAllItems();

                String yearClasse = (String) SelectYearClasse.getSelectedItem();
                String levelClasse = (String) SelectNiveau.getSelectedItem();
                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();
                String SelectClass = (String) SelectClassDelete.getSelectedItem();

                Classe deleteClasse = new Classe(0, SelectClass, idyearClasse, idLevelClasse);

                new ClasseDA0(maconnexion).delete(deleteClasse);
                ErrorMessage.setText("Classe deleting from the database");
            }
        });

        /**
         * Appel de la méthode pour modifier les éléments de la classe CLASSE
         * seulemnt modifier :
         * l'année de la classe
         * le niveau de la classe
         * le nom de la classe
         */
        UpdateClasseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * On retoruve l'id de la classe sléectionnée par l'utilsiateur
                 * On peut ainsi facilement retrouvé modifiler les variables de cette item
                 */
                String yearClasse = (String) SelectYearClasse.getSelectedItem();
                String levelClasse = (String) SelectNiveau.getSelectedItem();
                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();
                String SelectClassName = (String) SelectClassDelete.getSelectedItem();
                int id =  new ClasseDA0(maconnexion).find(new Classe(0, SelectClassName, idyearClasse, idLevelClasse)).getId();

                /**
                 * On récupère les changements de varibles de l'item sélectionnée
                 * On instancie a nouveau l'item sélectionnée
                 */
                String newNameClasse = newNameClasseUpdate.getText();
                int newidyearClasse = new AnneeScolaireDA0(maconnexion).find(1,(String) SelectUpdateYearClasse.getSelectedItem() ).getId();
                int newidLevelClasse = new NiveauDAO(maconnexion).find(1, (String) SelectUpdatelevelClasse.getSelectedItem()).getId();

                new ClasseDA0(maconnexion).update(new Classe(id, newNameClasse, newidyearClasse, newidLevelClasse), "");
                ErrorMessage.setText("Class updating succefull");

            }
        });

        /**
         * STUDENT
         * Afficher dans les comboBOX les années scolaires, les niveaux scolaires et les classes
         * On récupère ainsi tous les élèves d'une classe
         */


        StudentTabbed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                comboBoxYearStudent.removeAllItems();

                new AnneeScolaireDA0(maconnexion).findAll().forEach(anneeScolaire -> {
                    comboBoxYearStudent.addItem(anneeScolaire.getYear());
                });


            }
        });

        comboBoxYearStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxLevelStudent.removeAllItems();

                new NiveauDAO(maconnexion).findAll().forEach((niveau) -> {
                    comboBoxLevelStudent.addItem(niveau.getNom());
                });
            }
        });

        comboBoxLevelStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxClasseStudent.removeAllItems();

                String yearClasse = (String) comboBoxYearStudent.getSelectedItem();
                String levelClasse = (String) comboBoxLevelStudent.getSelectedItem();

                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

                new ClasseDA0(maconnexion).findAll(1, idyearClasse, idLevelClasse).forEach(classe -> {
                    comboBoxClasseStudent.addItem(classe.getName());
                });
            }
        });

        comboBoxClasseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxSelectStudentUpdate.removeAllItems();

                String yearClasse = (String) comboBoxYearStudent.getSelectedItem();
                String levelClasse = (String) comboBoxLevelStudent.getSelectedItem();
                String nameClasse = (String) comboBoxClasseStudent.getSelectedItem();

                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

               int idClasse = new ClasseDA0(maconnexion).find(new Classe(1,nameClasse, idyearClasse, idLevelClasse)).getId();

              new DAOEleve(maconnexion).findAll(idClasse).forEach(eleve -> {
                  comboBoxSelectStudentUpdate.addItem(eleve.getNom() + " " +eleve.getPrenom());
              });

            }
        });



        /**
         * Pour ajouter une personne
         */

        /**
         * Pour modifier une personne
         */


        /**
         * Pour supprimer une personne
         */

        /**
         * JE SAIS PAS OU METTRE L'AJOUT DE PERSONNE DANS UNE CLASSE AINSI QUE LA SUPPREMSION D'UNE PERSONNE DANS LA CLASSE
         * A Y PENSER
         * VOILA VOILA
         */

        /**
         * Je pense mettre un genre de truc un nouvelle onglet sur les classe ou tu peux selectionner tous les eleves sans classe
         */
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
       //table1 = new JTable(new AbstractTable());
        //test.findAll();
        //table1 = new JTable(new TableAnneScolaire(new AnneeScolaireDA0(maconnexion).findAll()));
        table2 = new JTable(new AbstractTable());
    }

}