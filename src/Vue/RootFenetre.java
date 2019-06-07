package Vue;

import Controleur.Connexion;
import Controleur.ControleurClasse.*;
import Modele.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import Controleur.*;

/**
 * @author
 * Description : Cette classe RootFenetre hérite de la classe java JFrame ; elle permet de gérer tout le graphique de l'application,
 * par le biais de Listeners :
 *                  - actionListener
 *                  - mouseListener
 *                  - ItemListener
 *
 * par le biais de composans graphiques :
 *                  - JPanel
 *                  - JTextFiels
 *                  - JLabel
 *                  - JComboBox
 *                  - JTable
 *                  - JTabbedPane
 *                  - JButton
 *
 *  Ses attributs prives sont aussi une maconnexion, item de la classe Connexion, qui nous permet de se connecter à la base de
 *  données locale
 *  Après verification de la connection, le graphique se met en place dans le programme à l'aide de sous-programme
 *  qui remplissent les tables et les comboBox, chacune remplissant l'autre à l'aide des Listeners
 */
public class RootFenetre extends JFrame {
    /**
     * Tous les attributs privés graphique de la classe RootFenetre
     *             - JPanel
     *             - JTextFiels
     *             - JLabel
     *             - JComboBox
     *             - JTable
     *             - JTabbedPane
                   - JButton
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
    private JTabbedPane JPanedSchool;
    private JPanel JpanelMessage;
    private JPanel JpanelSelect;
    private JLabel JLabelMessage;
    private JComboBox comboBoxSelectYear;
    private JComboBox comboBoxSelectLevel;
    private JComboBox comboBoxSelectClasse;
    private JComboBox comboBoxSelectStudent;
    private JPanel SchoolYear;
    private JTabbedPane SchoolYearTabbed;
    private JPanel ShowYear;
    private JPanel ActionSchoolYear;
    private JTextField textFieldAddYearSchool;
    private JButton buttonAddYearSchool;
    private JButton updateButtonYear;
    private JTextField textFieldAddYearSchoo;
    private JButton deleteButtonYear;
    private JPanel Level;
    private JTabbedPane LevelTabbed;
    private JPanel ShowLevel;
    private JPanel Classe;
    private JPanel ModifyLevel;
    private JTextField textFieldLevel;
    private JButton DeleteLevel;
    private JButton CreateLevel;
    private JButton UpdateLevel;
    private JTabbedPane ClasseTabbed;
    private JPanel ShowClasse;
    private JPanel ActionClasse;
    private JTextField textFieldClasseName;
    private JButton DeleteClasse;
    private JButton CreateClasse;
    private JButton UpdateClasse;
    private JPanel ActionClasseSubject;
    private JPanel ActionClasseStudent;
    private JComboBox SelectNewLevelClasse;
    private JComboBox SelectNewYearClasse;
    private JTable tableSchoolYear;
    private JTable tableStudentAbsent;
    private JTable tableStudentPresent;
    private JButton buttonDeleteStudentToClasse;
    private JButton buttonAddStudentToClasse;
    private JTable tableClasse;
    private JTable tableLevelSchool;
    private JComboBox comboBoxSelectDispline;
    private JTable tableTeacherPresent;
    private JTable tableTeacherAbsent;
    private JPanel GestionDiscipline;
    private JTable tableDisciplinePresent;
    private JTable tableDisciplineAbsent;
    private JPanel Discipline;
    private JTextField textFieldNewDiscipline;
    private JButton buttonCreateDiscipline;
    private JButton buttonUpdateDiscpline;
    private JButton buttonDeleteDiscipline;
    private JTable tableDisplineSchool;
    private JPanel Teacher;
    private JPanel Student;
    private JTable tableStudentSchool;
    private JButton buttonStudentUpgrade;
    private JButton buttonStudentDelete;
    private JTextField textFieldStudentLastName;
    private JButton buttonCreateStudent;
    private JTextField textFieldStudentName;
    private JTable tableTeacherSchool;
    private JTextField textFieldteacherLastname;
    private JTextField textFieldTeachername;
    private JButton buttonCreateteacher;
    private JButton buttonUpdatingTeacher;
    private JButton buttonDeleteTeacher;
    private JButton removeButton;
    private JButton newButtonDispiline;
    private JComboBox comboBoxSelectTeacher;
    private JButton ButtonPutTeacherInClasse;
    private JTextField textFieldSchoolLevel;
    private JButton ButtonDeleteSchoolLevel;
    private JButton ButtonAddSchoolLevel;
    private JButton ButtonUpdateSchoolLevel;
    private JPanel Grades;
    private JTabbedPane tabbedGrades;
    private JPanel ClasseGrades;
    private JPanel GradesStudent;
    private JButton buttonAddTrimestre;
    private JList listTrimestre;
    private JTable tableTrimestre;
    private JTextField textFieldAnneeScholaire;
    private JButton buttonCreateAnneeScolaire;
    private JButton buttonDeleteAnneeScolaire;
    private JButton buttonUpdateAnneeScholaire;
    private JTable tableBulletinsClasse;
    private JTextField textField1;
    private JTable tableStudentNotes;
    private JTextField textFieldAppreciationDetailsBulletin;
    private JButton newButton;
    private JLabel JLabelSumStudent;
    private JComboBox comboBoxSelectNewGrades;
    private JButton buttonUpdateGrades;
    private JButton buttonDeleteGrades;
    private JComboBox comboBoxSelectTrimestre;
    private JButton putItButton;
    private JButton addButton;
    private JButton removeButton1;
    private JButton updateButton;

    /**
    L'attribut public de la classe RootFenetre qui permet la connexion à la base de données
     Connexion maconnexion
     */
    public Connexion maconnexion ;

    public RootFenetre (){
        setTitle("Application de gestion d'une école");
        setSize(800,600);
        add(JPanelRoot);

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
                        JLabelMessage.setText("The database is charged");

                        /**
                         * Si la connexxion est un succès, alors on appelle la classe Controleur<AnnéeScolaire>
                         *     qui hérite de la classe Controleur
                         *     On affiche ainsi toutes les années de l'école présentes dans la base de donnée
                         */

                        refillComboBoxYear();
                        refillTableSchoolYear();
                        refillComboBoxLevel();

                        refillComboBoxSelectNewGrades ();

                        refillTableStudentSchool ();
                        refillTableTeacherSchool();

                        refillTableNiveau();

                        refillComboBoxSelectTrimestre();
                        refillTableDisciplineSchool();

                    } catch (ClassNotFoundException evt) {
                        System.out.println(evt);
                        ErrorConnect.setText("ClassNotFoundException");
                        evt.printStackTrace();
                        ErrorConnect.setText("ClassNotFoundException");
                    }
                } catch (SQLException sqlE) {
                    System.out.println(sqlE);
                    ErrorConnect.setText("SQLException");
                    sqlE.printStackTrace();
                    ErrorConnect.setText("SQLException");
                }
            }
        });

        /**
         * Les méthodes pour gérer les différentes comboBox du programme
         *
         */

        comboBoxSelectLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refillComboBoxClasse();
                refillTableClasse();
            }
        });

            comboBoxSelectClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refillComboBoxStudent();
                refillComboBoxDiscipline();
                refillTableDisplineNotPresent ();

                refillTableStudentPresent(FindIdClasseForStudent());
                refillTableStudentAbsent();
                 }
            });

        comboBoxSelectDispline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refillTableDiscipline ();
                refillTableDisplineNotPresent();
                refillTableTeacherAbsent();
                refillTableTeacherPresent();
                refillComboBoxTeacher ();
            }
        });

        /**
         * Les méthodes de gestions pour les Années Scolaires
         * Ajouter une année scolaire
         * Modifier une année scolaire
         * Supprimer une année scolaire
         */

        SchoolYearTabbed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textFieldAddYearSchoo.setText((String) comboBoxSelectYear.getSelectedItem());
                refillTableSchoolYear();
                refillTrimestre();

            }
        });

        buttonAddYearSchool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newYear = textFieldAddYearSchoo.getText();
                try {
                    new AnneeScolaireDA0(maconnexion).create( new AnneeScolaire(0, newYear));
                    JLabelMessage.setText("Adding year in the database succesfull");
                    refillComboBoxYear();
                }catch (ExceptionAlreadyExistant expt) {
                    JLabelMessage.setText("Problems in adding this year in the database");
                }
            }
        });

        updateButtonYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Selectyear = (String) comboBoxSelectYear.getSelectedItem();
                String UpdateyearSelect = textFieldAddYearSchoo.getText();
                try {
                new AnneeScolaireDA0(maconnexion).update(new AnneeScolaireDA0(maconnexion).find(1, Selectyear),UpdateyearSelect);
                JLabelMessage.setText("Succes updating level school in the database");
                refillComboBoxYear();
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("This level School already existing in this database");
                }
            }
        });

        deleteButtonYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DeleteYear = (String) comboBoxSelectYear.getSelectedItem();
                new AnneeScolaireDA0(maconnexion).delete(new AnneeScolaireDA0(maconnexion).find(1, DeleteYear));
                JLabelMessage.setText("Succes deleting level school in the database");
                refillComboBoxYear();
            }
        });

        buttonCreateAnneeScolaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newYear = textFieldAnneeScholaire.getText();
                try {
                    new AnneeScolaireDA0(maconnexion).create( new AnneeScolaire(0, newYear));
                    JLabelMessage.setText("Adding year in the database succesfull");
                    refillComboBoxYear();
                    refillTableSchoolYear();
                    refillTrimestre();
                }catch (ExceptionAlreadyExistant expt) {
                    JLabelMessage.setText("Problems in adding this year in the database");
                }
            }
        });

        buttonDeleteAnneeScolaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DeleteYear = (String) tableSchoolYear.getValueAt(tableSchoolYear.getSelectedRow(), tableSchoolYear.getSelectedColumn());
               try {
                   new TrimestreDAO(maconnexion).delete(new AnneeScolaireDA0(maconnexion).find(1, DeleteYear));
                   refillTrimestre();
                   new AnneeScolaireDA0(maconnexion).delete(new AnneeScolaireDA0(maconnexion).find(1, DeleteYear));
                   JLabelMessage.setText("Succes deleting level school in the database");

                   refillComboBoxYear();
                   refillTableSchoolYear();

               } catch (ExceptionNotExisting evt){
                   JLabelMessage.setText("Error delete school year and trimestre");
               }
            }
        });
        buttonUpdateAnneeScholaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Selectyear = (String) tableSchoolYear.getValueAt(tableSchoolYear.getSelectedRow(), tableSchoolYear.getSelectedColumn());
                String UpdateyearSelect = textFieldAnneeScholaire.getText();
                try {
                    new AnneeScolaireDA0(maconnexion).update(new AnneeScolaireDA0(maconnexion).find(1, Selectyear),UpdateyearSelect);
                    JLabelMessage.setText("Succes updating level school in the database");
                    refillComboBoxYear();
                    refillTableSchoolYear();

                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("This level School already existing in this database");
                }
            }
        });

        /**
         * Si on clique sur le tableau d'annee scolaire
         *
         *
         */

        tableSchoolYear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refillTrimestre();
                textFieldAnneeScholaire.setText((String) tableSchoolYear.getValueAt(tableSchoolYear.getSelectedRow(), tableSchoolYear.getSelectedColumn()));
            }
        });


        /**
         * Année Scholaire
         * Panel Action sur School Year
         * Gestion TRIMESTRE
         */

        buttonAddTrimestre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Year = (String) tableSchoolYear.getValueAt(tableSchoolYear.getSelectedRow(), tableSchoolYear.getSelectedColumn());
                try {
                    new TrimestreDAO(maconnexion).create(new AnneeScolaireDA0(maconnexion).find(1, Year));
                    JLabelMessage.setText("Succes adding Trimestre");
                    refillTrimestre();
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("Problem adding trimestre");
                }
            }
        });

        /**
         * SCHOOL LEVEL
         */

        LevelTabbed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textFieldLevel.setText((String) comboBoxSelectLevel.getSelectedItem());
                refillTableNiveau ();

            }
        });

        /**
         * Lors d'un clic sur le tableau de niveaux scolaire
         * - on remplit la texteField du Jpanel avec l'année sélectionnée
         */
        tableLevelSchool.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textFieldSchoolLevel.setText( (String) tableLevelSchool.getValueAt( tableLevelSchool.getSelectedRow(), tableLevelSchool.getSelectedColumn()));
            }
        });


        /**
         * Niveaux Scolaire
         * SHOW + GESTION
         * ADD, DELETE, UPDATE
         */

        ButtonDeleteSchoolLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deletelevel = (String) tableLevelSchool.getValueAt(tableLevelSchool.getSelectedRow(), tableLevelSchool.getSelectedColumn());
                try {
                    new NiveauDAO(maconnexion).delete(new NiveauDAO(maconnexion).find(1, deletelevel));
                    JLabelMessage.setText("Succes deleting level school in the database");
                    refillComboBoxLevel();
                    refillTableNiveau();

                } catch (ExceptionNotExisting evt){
                    JLabelMessage.setText("Problem in deleting this school level");
                }
            }
        });

        ButtonAddSchoolLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newLevelText = textFieldSchoolLevel.getText();
                try {
                    new NiveauDAO(maconnexion).create(new Niveau(0, newLevelText));
                    JLabelMessage.setText("Succes adding level school in the database");
                    refillComboBoxLevel();
                    refillTableNiveau();

                } catch (Exception expt) {
                    JLabelMessage.setText("Problems in adding this level in the database");
                }
            }
        });

/**
 * NE FONCTIONNE PAS A VOIR !!!!!!!
 */
        ButtonUpdateSchoolLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updtateLevel = (String) tableLevelSchool.getValueAt(tableLevelSchool.getSelectedRow(), tableLevelSchool.getSelectedColumn());

                String newnameLevel = textFieldSchoolLevel.getText();
                try {
                    new NiveauDAO(maconnexion).update(new NiveauDAO(maconnexion).find(1, updtateLevel), newnameLevel);
                    JLabelMessage.setText("Succes updating level school in the database");
                    refillComboBoxLevel();
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("level school already existing in this database. ");
                }
            }
        });

        /**
         * Dans SCHOOL LEVEL GESTION DISCIPLINE
         * lorsque l'on clique sur gestion discipline
         *
         * méthode - ajouter une matière à la promo sélectionnée
         *          - ajouter a tous les eleves de la classe un details bulletin
         *          - on récupère tous les id d'inscription de la classe
         *          - on récupère l'id de la matière ajouté
         *          - on appelle la fonction create du controleur DetailsBulletin
         *
         * méthode - enlever une matière à la promo sélectionnée
         *
         * On ré-affiche les tableaux mis à jour
         */
        newButtonDispiline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String discipline = (String) tableDisciplineAbsent.getValueAt(tableDisciplineAbsent.getSelectedRow(), tableDisciplineAbsent.getSelectedColumn());
                Discipline discipline1 =  new DisciplineDAO(maconnexion).find(1, discipline);
                int idyear = new AnneeScolaireDA0(maconnexion).find(1, (String) comboBoxSelectYear.getSelectedItem()).getId();
                int idlevel = new NiveauDAO(maconnexion).find(1, (String) comboBoxSelectLevel.getSelectedItem()).getId();

                /**
                 * On ajoute la discipline
                 * on récupère l'id de toutes les inscriptions dans toutes les classes du niveau sélectionné
                 */
                new DisciplineDAO(maconnexion).addDisciplinetoPromo(discipline1, idyear, idlevel);

                    ArrayList<Inscription> inscriptions = new InscriptionDAO(maconnexion).findAll(FindIdLevel(), FindIdLevel());

                /**
                 * On récupère ainsi l'id de tous les bulletins des isncrits dans ces classes
                 * A partir de ces id, on peut appeller la méthode create de la classe Enseignement
                 */
                ArrayList<Bulletin> bulletins = new ArrayList<>();
                    for (int i=0; i<inscriptions.size(); i++) {
                        bulletins = new BulletinDAO(maconnexion).findAll(inscriptions.get(i).getId());
                    }

                    for (int i=0; i<bulletins.size(); i++){
                        DetailsBulletin create = new DetailsBulletin(1,bulletins.get(i).getId(), discipline1.getId_discipline());
                        try {
                            new DetailsBulletinDAO(maconnexion).create(create);
                        } catch (ExceptionAlreadyExistant evt){
                            JLabelMessage.setText("problem in creating details bulletin ");
                        }
                    }

                /**
                 * On re -affiche tous les tableuax présent sur le panel
                 * Appel des sous-programmes
                 */
                refillTableDisplineNotPresent();
                refillTableDiscipline();
                refillComboBoxDiscipline();
            }
        });



        /**
         * Pour supprimer une matière des classes du niveau sléctionné
         */
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String discipline = (String) tableDisciplinePresent.getValueAt(tableDisciplinePresent.getSelectedRow(),tableDisciplinePresent.getSelectedColumn());
                Discipline discipline1 =  new DisciplineDAO(maconnexion).find(1, discipline);
                /**
                 * On récupère tous les classes du niveau sélectionnée dans une ArrayList<>
                 * Ensuite dans le boucle allant de 0 au nombre de classe ( normalement 2)
                 * on appelle la méthode delete du controleur Enseignement pour toutes les classes avec en paramètre les
                 * id des classes, et le id de la displine à supprimer
                 */
               ArrayList<Classe> classe =  new ClasseDA0(maconnexion).findAll(1,FindIdYear(), FindIdLevel());

               for (int i = 0; i< classe.size(); i++) {
                   Enseignement enseignement = new Enseignement(1, classe.get(i).getId(), discipline1.getId_discipline(), 1);
                   new EnseignementDA0(maconnexion).delete(enseignement);
               }
                refillTableDisplineNotPresent();
                refillTableDiscipline();
                refillComboBoxDiscipline();
            }
        });






        /**
         * SCHOOL CLASSE
         */
        ClasseTabbed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refillSelectNewLevelClasse();
                refillSelectNewYearClasse();

                refillTableStudentPresent(FindIdClasseForStudent());
                refillTableStudentAbsent();
                refillTableClasse();
                refillTableDiscipline();
                refillTableDisplineNotPresent ();
            }
        });

        CreateClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String yearClasse = (String) SelectNewYearClasse.getSelectedItem();
                System.out.println(yearClasse);
                String levelClasse = (String) SelectNewLevelClasse.getSelectedItem();
                System.out.println(levelClasse);
                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();
                System.out.println(idyearClasse);
                System.out.println(idLevelClasse);
                String newNameClasse = textFieldClasseName.getText();

                try {
                    new ClasseDA0(maconnexion).create(new Classe(0, newNameClasse, idyearClasse, idLevelClasse));
                    JLabelMessage.setText("Class adding in the database");
                    refillComboBoxClasse();
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("This class already exist in the database");
                }
            }
        });


        /**
         * Appel de la méthode pour modifier les éléments de la classe CLASSE
         * seulemnt modifier :
         * l'année de la classe
         * le niveau de la classe
         * le nom de la classe
         */
        UpdateClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    /**
                    * On retoruve l'id de la classe sléectionnée par l'utilsiateur
                    * On peut ainsi facilement retrouvé modifiler les variables de cette item
                    */
                String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
                String levelClasse = (String) comboBoxSelectLevel.getSelectedItem();
                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();
                String SelectClassName = (String) comboBoxSelectClasse.getSelectedItem();

                int id =  new ClasseDA0(maconnexion).find(new Classe(0, SelectClassName, idyearClasse, idLevelClasse)).getId();
                /**
                 * On récupère les changements de varibles de l'item sélectionnée
                 * On instancie a nouveau l'item sélectionnée
                 */
                String newNameClasse = textFieldClasseName.getText();
                int newidyearClasse = new AnneeScolaireDA0(maconnexion).find(1,(String) SelectNewYearClasse.getSelectedItem() ).getId();
                int newidLevelClasse = new NiveauDAO(maconnexion).find(1, (String) SelectNewLevelClasse.getSelectedItem()).getId();

                new ClasseDA0(maconnexion).update(new Classe(id, newNameClasse, newidyearClasse, newidLevelClasse), "");
                JLabelMessage.setText("Class updating succefull");

                refillComboBoxClasse();
            }
        });
        DeleteClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
                String levelClasse = (String) comboBoxSelectLevel.getSelectedItem();

                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

                String SelectClass = (String) comboBoxSelectClasse.getSelectedItem();
                try {
                    new ClasseDA0(maconnexion).delete(new Classe(0, SelectClass, idyearClasse, idLevelClasse));
                    JLabelMessage.setText("Classe deleting from the database");
                    refillComboBoxClasse();
                } catch (ExceptionNotExisting evt){
                    JLabelMessage.setText("Problem for classe deleting from the database");
                }
            }
        });

        /**
         * Gestion des éleves d'une classe
         * Supprimer un élève de la classe
         */
        buttonDeleteStudentToClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String nomStudent = (String) tableStudentPresent.getValueAt(tableStudentPresent.getSelectedRow(), tableStudentPresent.getSelectedColumn());
               JLabelMessage.setText(nomStudent);
               String[] data = nomStudent.split(" ", 2);
               Eleve Student = new DAOEleve(maconnexion).find(new Eleve(0, data[0], data[1]));
               int idClasse = FindIdClasseForStudent();
               Inscription inscription = new Inscription(0, idClasse, Student.getId());
                try {
                    new InscriptionDAO(maconnexion).delete(inscription);
                    JLabelMessage.setText("Succes in deleting student from this classe");
                    refillComboBoxStudent();
                    refillTableStudentAbsent();
                    refillTableStudentPresent(idClasse);

                } catch (ExceptionNotExisting evt){
                    JLabelMessage.setText("Problem in deleting student from this classe");
                }
            }
        });
        /**
         * Ajouter un élève dans la classe sélectionnée
         * Pour se faire on ajoute lors de l'ajout d'un élève dans une classe, un bulletin et un détials bulletin associé à lélève
         *
         */
        buttonAddStudentToClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomStudent = (String) tableStudentAbsent.getValueAt(tableStudentAbsent.getSelectedRow(), tableStudentAbsent.getSelectedColumn());
                JLabelMessage.setText(nomStudent);
                String[] data = nomStudent.split(" ", 2);
                Eleve Student = new DAOEleve(maconnexion).find(new Eleve(0, data[0], data[1]));
                int idClasse = FindIdClasseForStudent();
                Inscription inscription = new Inscription(0, idClasse, Student.getId());

                int idTrimestre = FindIdTrimestre();


                try {
                    new InscriptionDAO(maconnexion).create(inscription);
                    JLabelMessage.setText("Succes in adding student from this classe");
                    refillComboBoxStudent();
                    refillTableStudentAbsent();
                    refillTableStudentPresent(idClasse);
                        try {
                            /**
                             * On ajoute 2 bulletin ( 1 par trimestre ) lié à l'inscription de l'élève
                             */
                           int idInscription = new InscriptionDAO(maconnexion).find(inscription).getId();

                            Bulletin bulletin = new Bulletin(1, idTrimestre, idInscription);
                            new BulletinDAO(maconnexion).create(bulletin);


                                    /**
                                     * On ajoute les details bulletins par matière en fonction de l'élève
                                     * On récupère les id des bulletins de l'élève
                                     * on récupère les id des enseignements de sa classe
                                     * boucle permettant de générer n insert de detailsBulletin pour l'élève
                                     */
                                    int idinscrip = new InscriptionDAO(maconnexion).find(inscription).getId();
                                    int idClas = FindIdClasseForStudent();

                                    ArrayList<Bulletin> bull =  new BulletinDAO(maconnexion).findAll(idinscrip);
                                    ArrayList<Enseignement> ensiegnemnt = new EnseignementDA0(maconnexion).findAll(idClas);

                                    for (int i = 0; i< bull.size(); i++){
                                        for (int y = 0; y < ensiegnemnt.size(); y++){
                                            new DetailsBulletinDAO(maconnexion).create(ensiegnemnt.get(y).getId(), bull.get(i).getId());
                                            System.out.println(bull.get(i).getId() + "  :: " + ensiegnemnt.get(y).getId());
                                        }
                                    }

                        }catch (ExceptionAlreadyExistant exceptionAlreadyExistant){
                            JLabelMessage.setText("Problem in adding bulletin to this student");
                        }
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("Problem in adding student from this classe");
                }
            }
        });

        /**
         * GESTION DISCIPLINE (A COMPLETER UPDATE AJOUTER A LA CLASSE )
         */

        /**
         * Lord d'un clic sur le tableau,
         * on met dans le textField la matière sélectionnée
         */
        tableDisplineSchool.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textFieldNewDiscipline.setText( (String) tableDisplineSchool.getValueAt(tableDisplineSchool.getSelectedRow(), tableDisplineSchool.getSelectedColumn()));
            }
        });
        /**
         * Clic sur le bouton pour ajouter une discpline dans la base de données
         * on récupère le nom de la nouvelle discipline
         * on appelle la méthode create du controleur Discipline
         */
        buttonCreateDiscipline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newDiscipline = textFieldNewDiscipline.getText();
                try {
                    new DisciplineDAO(maconnexion).create(new Discipline(1, newDiscipline));
                    JLabelMessage.setText("Succes in creating this discipline in the databasez");
                    refillTableDisciplineSchool();
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("Error in creating this discipline in the database");
                }
            }
        });

        /**
         * Lord du clic sur le bouton,
         * on récupère la matière sélectionnée,
         * on récupère la nouvelle valeur de la matière
         * On appelle la méthode du controleur displine pour update la sléectionnée
         */
        buttonUpdateDiscpline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = textFieldNewDiscipline.getText();
                String displine = (String) tableDisplineSchool.getValueAt(tableDisplineSchool.getSelectedRow(), tableDisplineSchool.getSelectedColumn());
                try {
                    new DisciplineDAO(maconnexion).update(new Discipline(1, displine), newName);
                    refillTableDisciplineSchool();
                }catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("Error in updating this displine");
                }
            }
        });

        /**
         * lors du clic sur le bouton delete Dsicipline
         * On recupere la matière a supprimer
         * on appelle la methode delete du controleur Discipline
         */
        buttonDeleteDiscipline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String displine = (String) tableDisplineSchool.getValueAt(tableDisplineSchool.getSelectedRow(), tableDisplineSchool.getSelectedColumn());
                    new DisciplineDAO(maconnexion).delete(new Discipline(1, displine));
                    refillTableDisciplineSchool();
            }
        });

        /**
         * STUDENT
         */
        /**
         * Action lors d'un clic sur le panel Student
         * Utilisation de ce panel :
         * - Afficher tous les éleves de l'école
         * - ajouter, suprrimer, modifier un élève
         */
        Student.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refillTableStudentSchool ();
            }
        });

        /**
         * On veut ajouter un eleve dans la base de donnée
         */

        buttonCreateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newLastname = textFieldStudentLastName.getText();
                String newname = textFieldStudentName.getText();
                try {
                    new DAOEleve(maconnexion).create(new Eleve(1, newLastname, newname));
                    JLabelMessage.setText("Succes adding student in the database");
                    refillTableStudentSchool ();
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("Error in adding student in the database");
                }
            }
        });
        /**
         * On veut modifier les items d'une personne 'eleve'
         * Nom, prenom
         */
        buttonStudentUpgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastnamename = (String) tableStudentSchool.getValueAt(tableStudentSchool.getSelectedRow(), tableStudentSchool.getSelectedColumn());
                new DAOEleve(maconnexion).update(new Eleve(1, textFieldStudentLastName.getText(), textFieldStudentName.getText()),lastnamename );
                JLabelMessage.setText("Succes in updating the student");
                refillTableStudentSchool();
            }
        });

        /**
         * On veut supprimer un eleve de la base de données
         */
        buttonStudentDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastnamename = (String) tableStudentSchool.getValueAt(tableStudentSchool.getSelectedRow(), tableStudentSchool.getSelectedColumn());
                String[] data = lastnamename.split(" ", 2);
                new DAOEleve(maconnexion).delete(new Eleve(1, data[0], data[1]));
                JLabelMessage.setText("Succes deleting student");
                refillTableStudentSchool();
            }
        });

        /**
         * Gestion des clic qur le tableau de tous les étudiants
         */
        tableStudentSchool.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String lastnamename = (String) tableStudentSchool.getValueAt(tableStudentSchool.getSelectedRow(), tableStudentSchool.getSelectedColumn());
                String[] data = lastnamename.split(" ", 2);
                textFieldStudentLastName.setText(data[0]);
                textFieldStudentName.setText(data[1]);
            }
        });

        /**
         * TEACHER
         */


        buttonCreateteacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newLastname = textFieldteacherLastname.getText();
                String newname = textFieldTeachername.getText();
                try {
                    new ProfesseurDAO(maconnexion).create(new Professeur(1, newLastname, newname));
                    JLabelMessage.setText("Succes adding professeur in the database");
                    refillTableTeacherSchool();
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("Error in adding professeur in the database");
                }
            }
        });

        buttonUpdatingTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastnamename = (String) tableTeacherSchool.getValueAt(tableTeacherSchool.getSelectedRow(), tableTeacherSchool.getSelectedColumn());
                new ProfesseurDAO(maconnexion).update(new Professeur(1, textFieldteacherLastname.getText(), textFieldTeachername.getText()),lastnamename );
                JLabelMessage.setText("Succes in updating the teacher");
                refillTableTeacherSchool();
            }
        });


        buttonDeleteTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastnamename = (String) tableTeacherSchool.getValueAt(tableTeacherSchool.getSelectedRow(), tableTeacherSchool.getSelectedColumn());
                String[] data = lastnamename.split(" ", 2);
                new ProfesseurDAO(maconnexion).delete(new Professeur(1, data[0], data[1]));
                JLabelMessage.setText("Succes deleting teacher");
                refillTableTeacherSchool();
            }
        });

        /**
         * Gestion Teacher / matière dans une classe
         */

        ActionClasseSubject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refillTableTeacherPresent();
                refillTableTeacherAbsent();
            }
        });
        /**
         * Gestion Teacher
         */
        ButtonPutTeacherInClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameTeacher = (String) tableTeacherAbsent.getValueAt(tableTeacherAbsent.getSelectedRow(), tableTeacherAbsent.getSelectedColumn());
                int idteacher = FindIdTeacher(nameTeacher);
                new ProfesseurDAO(maconnexion).UpdateTeacherClasse(idteacher, FindIdClasseForStudent(), FindIdDiscipline());
                refillTableTeacherPresent();
                refillTableTeacherAbsent();

                refillComboBoxTeacher();
            }
        });

        /**
         * GRADES
         * gestion Student
         */



        GradesStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refillTableNotes();
                refillTableBulletin();
            }
        });

        /**
         * Si on clique sur le bouton pour ajouter une note
         */

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notes note = new Notes (1, FindIdBetailsBulletin(), (int) comboBoxSelectNewGrades.getSelectedItem());
                try {
                    new EvaluationDAO(maconnexion).create(note);
                    refillTableNotes();
                } catch (ExceptionAlreadyExistant evt ){
                    JLabelMessage.setText("Problems in adding this note");
                }
            }
        });

        /**
         * ActionListener sur le button Update grades of student
         * On récupère la note sélectionnée avec le nuémro de la ligne sélectionnée ( pour récupere corectement l'id de la note)
         * et aussi la méthode getId de la classe
         * on récupère la nouvelle note
         * On appelle la méthodeUpdate du controleur Evaluation
         * On réaffiche le tableau de notes de l'élève dans la matière sélectionnée
         */
        buttonUpdateGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = (int) comboBoxSelectTrimestre.getSelectedItem();
                int idTrimestre = new TrimestreDAO(maconnexion).findOne(FindIdYear(), numero).getId();

                TableStudentGrades table = new TableStudentGrades(new EvaluationDAO(maconnexion).findAll(FindIdEnseignement(), FindIdInscription(), idTrimestre));
                int idNotes = table.getId(tableStudentNotes.getSelectedRow());
                int newGrades =  (int) comboBoxSelectNewGrades.getSelectedItem();

                new EvaluationDAO(maconnexion).update(new Notes(1, 1, newGrades), idNotes);
                refillTableNotes();

            }
        });
        /**
         * Pour supprimer un enotes d'un élève
         * Lors de l'appui sur le bouton 'buttonDeleteGrades"
         */
        buttonDeleteGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = (int) comboBoxSelectTrimestre.getSelectedItem();
                int idTrimestre = new TrimestreDAO(maconnexion).findOne(FindIdYear(), numero).getId();

                TableStudentGrades table = new TableStudentGrades(new EvaluationDAO(maconnexion).findAll(FindIdEnseignement(), FindIdInscription(), idTrimestre));
                int idNotes = table.getId(tableStudentNotes.getSelectedRow());
                int newGrades =  (int) comboBoxSelectNewGrades.getSelectedItem();

                new EvaluationDAO(maconnexion).delete(idNotes);
                refillTableNotes();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refillTableBulletin();
            }
        });
    }



    /**
     * METHODES DE LA CLASSE
     *
     *
     *
     *
     *
     *
     */

    /**
     * Find id of object
     */

    public int FindIdYear (){
        String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
        return new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
    }

    public int FindIdLevel () {
        String levelClasse = (String) comboBoxSelectLevel.getSelectedItem();
       return new NiveauDAO(maconnexion).find(1, levelClasse).getId();
    }

    public int FindIdDiscipline () {
        String DisciplineClasse = (String) comboBoxSelectDispline.getSelectedItem();
        return new DisciplineDAO(maconnexion).find(1, DisciplineClasse).getId_discipline();
    }

    public int FindIdClasseForStudent () {
        String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
        String levelClasse = (String) comboBoxSelectLevel.getSelectedItem();
        String nameClasse = (String) comboBoxSelectClasse.getSelectedItem();
        int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
        int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

        Classe classe = new Classe(1,nameClasse, idyearClasse, idLevelClasse);
        return new ClasseDA0(maconnexion).find(classe).getId();
    }

    public int FindIdTeacher (String name) {
       return new ProfesseurDAO(maconnexion).find(1, name).getId();
    }

    public int FindIdStudent (){
       return new DAOEleve(maconnexion).find (1, (String) comboBoxSelectStudent.getSelectedItem()).getId();
    }

    public int FindIdInscription (){
        int idClasse = FindIdClasseForStudent();
        int idEleve = FindIdStudent();
        System.out.println("id Classe :: " + idClasse + " id Eleve :: " + idEleve);
        return new InscriptionDAO(maconnexion).find(new Inscription(1, idClasse, idEleve)).getId();
    }

    public int FindIdEnseignement (){
        int idClasse = FindIdClasseForStudent();
        int idDiscipline = FindIdDiscipline();
        int idProf = FindIdTeacher((String) comboBoxSelectTeacher.getSelectedItem());
        return new ProfesseurDAO(maconnexion).FindEnseignement(new Enseignement(1, idClasse, idDiscipline, idProf));
    }

    public int FindIdBulletin (){
        /**
         * RAJOUTER LA NOTION DE TRIMESTRE
         */
        int numero = (int) comboBoxSelectTrimestre.getSelectedItem();
        int idTrimestre = new TrimestreDAO(maconnexion).findOne(FindIdYear(), numero).getId();

        return new BulletinDAO(maconnexion).findOne(idTrimestre, FindIdInscription()).get(0).getId();
    }

    public int FindIdBetailsBulletin () {
        return  new DetailsBulletinDAO(maconnexion).findOne(FindIdBulletin(), FindIdEnseignement()).get(0).getId();
    }

    public int FindIdTrimestre (){
        return new TrimestreDAO(maconnexion).findOne(FindIdYear(), (int) comboBoxSelectTrimestre.getSelectedItem()).getId();
    }
    /**
     * Refill the combxbox of selection
     */

    public void refillComboBoxYear (){
        comboBoxSelectYear.removeAllItems();
        new AnneeScolaireDA0(maconnexion).findAll().forEach(anneeScolaire -> {
            /**
             * Remplir la combox pour choisir l'année scolaire
             */
            comboBoxSelectYear.addItem(anneeScolaire.getYear());
        });
    }

    public void refillComboBoxLevel () {

        comboBoxSelectLevel.removeAllItems();

        new NiveauDAO(maconnexion).findAll().forEach(niveau -> {
            comboBoxSelectLevel.addItem(niveau.getNom());
        });
    }

    public void refillComboBoxClasse () {
        comboBoxSelectClasse.removeAllItems();

        String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
        String levelClasse = (String) comboBoxSelectLevel.getSelectedItem();

        int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
        int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

        new ClasseDA0(maconnexion).findAll(1, idyearClasse, idLevelClasse).forEach(classe -> {
            comboBoxSelectClasse.addItem(classe.getName());
        });
    }

    public void refillComboBoxStudent () {
        comboBoxSelectStudent.removeAllItems();
        int idClasse = FindIdClasseForStudent();

        new DAOEleve(maconnexion).findAll(idClasse).forEach(eleve -> {
            comboBoxSelectStudent.addItem(eleve.getNom() + " " + eleve.getPrenom());
        });
    }

    public void refillComboBoxTeacher () {
        comboBoxSelectTeacher.removeAllItems();
        int idClasse = FindIdClasseForStudent();
        int idDiscipline = FindIdDiscipline();

        new ProfesseurDAO(maconnexion).FindTeacher(idClasse,idDiscipline ).forEach(professeur -> {
            comboBoxSelectTeacher.addItem(professeur.getNom() + " " + professeur.getPrenom());
        });
    }


    public void refillComboBoxDiscipline (){
        comboBoxSelectDispline.removeAllItems();
        int idClasse = FindIdClasseForStudent();
        System.out.println("Id CLASSE :: "+ idClasse);
        new DisciplineDAO(maconnexion).findAll(idClasse).forEach(discipline -> {
            System.out.println(discipline.getNom_classe());
            comboBoxSelectDispline.addItem(discipline.getNom_classe());
        });

    }

    public void refillComboBoxSelectNewGrades () {
        for (int i=0; i<21; i++){
            comboBoxSelectNewGrades.addItem(i);
        }
    }

    public void refillComboBoxSelectTrimestre () {
        for (int i =1; i<3; i++) {
            comboBoxSelectTrimestre.addItem(i);
        }
    }

    public void refillSelectNewYearClasse (){
        SelectNewYearClasse.removeAllItems();
        new AnneeScolaireDA0(maconnexion).findAll().forEach(anneeScolaire -> { SelectNewYearClasse.addItem(anneeScolaire.getYear());
        });
    }

    public void refillSelectNewLevelClasse () {
        SelectNewLevelClasse.removeAllItems();
        new NiveauDAO(maconnexion).findAll().forEach(niveau -> { SelectNewLevelClasse.addItem(niveau.getNom()); });
    }

    public void refillTableSchoolYear () {
        tableSchoolYear.setModel(new TableAnneScolaire(new AnneeScolaireDA0(maconnexion).findAll()));
    }

    public void refillTableStudentPresent (int idClasse) {
        tableStudentPresent.setModel(new TableStudent(new DAOEleve(maconnexion).findAll(idClasse)));
    }


    public void refillTableStudentAbsent () {
        String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
        int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
        tableStudentAbsent.setModel(new TableStudent(new DAOEleve(maconnexion).findAllAbsent(idyearClasse)));
    }

    public void refillTableClasse () {
        String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
        String levelClasse = (String) comboBoxSelectLevel.getSelectedItem();
        int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
        int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();
        tableClasse.setModel(new TableClasse(new ClasseDA0(maconnexion).findAll(0, idyearClasse, idLevelClasse)));

    }

    public void refillTableNiveau () {
        tableLevelSchool.setModel(new TableLevel(new NiveauDAO(maconnexion).findAll()));
    }

    public void refillTableDiscipline () {

        int idClasse = FindIdClasseForStudent();

        tableDisciplinePresent.setModel(new TableDiscipline(new DisciplineDAO(maconnexion).findAll(idClasse)));
    }

    public void refillTableDisplineNotPresent (){
        String year = (String) comboBoxSelectYear.getSelectedItem();
        String level = (String) comboBoxSelectLevel.getSelectedItem();
        int idyear = new AnneeScolaireDA0(maconnexion).find(1, year).getId();
        System.out.println(idyear);
        int idLevel = new NiveauDAO(maconnexion).find(1, level).getId();
        System.out.println(idLevel);
        tableDisciplineAbsent.setModel(new TableDiscipline(new DisciplineDAO(maconnexion).findAllAbsent(idyear, idLevel)));
    }

    public void refillTableDisciplineSchool () {
        tableDisplineSchool.setModel(new TableDiscipline(new DisciplineDAO(maconnexion).findAll()));
    }

    public void refillTableStudentSchool (){
        tableStudentSchool.setModel(new TableStudent(new DAOEleve(maconnexion).findAllStudent()));
    }

    public void refillTableTeacherSchool() {
        tableTeacherSchool.setModel(new TableTeacher(new ProfesseurDAO(maconnexion).findAllTeacher()));
    }

    public void refillTableTeacherPresent () {
        int idDiscipline = new DisciplineDAO(maconnexion).find(1, (String) comboBoxSelectDispline.getSelectedItem()).getId_discipline();
        tableTeacherPresent.setModel(new TableTeacher(new ProfesseurDAO(maconnexion).FindTeacher(FindIdClasseForStudent(), idDiscipline)));
    }

    public void refillTableTeacherAbsent () {
        tableTeacherAbsent.setModel(new TableTeacher(new ProfesseurDAO(maconnexion).findAllAbsent(FindIdClasseForStudent())));

    }

    public void refillTrimestre () {
        String Year = (String) tableSchoolYear.getValueAt(tableSchoolYear.getSelectedRow(), tableSchoolYear.getSelectedColumn());
        tableTrimestre.setModel(new TableTrimestre(new TrimestreDAO(maconnexion).findAll( new AnneeScolaireDA0(maconnexion).find(1, Year))));
    }

    public void refillTableNotes (){
        System.out.println(" id Enseignement :: " + FindIdEnseignement() + "  Id Inscription  :: " + FindIdInscription() );
        int numero = (int) comboBoxSelectTrimestre.getSelectedItem();
        int idTrimestre = new TrimestreDAO(maconnexion).findOne(FindIdYear(), numero).getId();

        TableStudentGrades table = new TableStudentGrades(new EvaluationDAO(maconnexion).findAll(FindIdEnseignement(), FindIdInscription(), idTrimestre));
        tableStudentNotes.setModel(table);
        JLabelSumStudent.setText(comboBoxSelectDispline.getSelectedItem() + " Sum :: " + table.getSum() + "20");
    }

    public void refillTableBulletin (){
        int numero = (int) comboBoxSelectTrimestre.getSelectedItem();
        int idTrimestre = new TrimestreDAO(maconnexion).findOne(FindIdYear(), numero).getId();

        ArrayList<Enseignement> arraylist = new EnseignementDA0(maconnexion).findAll(FindIdClasseForStudent());
        ArrayList<Discipline> listDispline = new ArrayList<>();

        for (int i=0; i<arraylist.size(); i++){
            listDispline.add(new DisciplineDAO(maconnexion).find(i));
        }

        ArrayList<Double> listSum = new ArrayList<>();

        for (int i=0; i<0; i++) {
            TableStudentGrades table = new TableStudentGrades(new EvaluationDAO(maconnexion).findAll(arraylist.get(i).getId(), FindIdInscription(), idTrimestre));
            listSum.add(table.getSum());
        }

        tableBulletinsClasse.setModel(new TableBulletin(listSum, listDispline));
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        tableSchoolYear = new JTable(new TableAnneScolaire());

        tableStudentPresent = new JTable(new TableStudent());
        tableStudentAbsent = new JTable(new TableStudent());

        tableClasse = new JTable(new TableClasse());
        tableLevelSchool = new JTable(new TableLevel());
        tableDisciplinePresent = new JTable(new TableDiscipline());
        tableDisciplineAbsent = new JTable(new TableDiscipline());
        tableDisplineSchool = new JTable(new TableDiscipline());

        tableStudentSchool = new JTable(new TableStudent());
        tableTeacherSchool = new JTable(new TableTeacher());

        tableTeacherPresent = new JTable(new TableTeacher());
        tableTeacherAbsent = new JTable(new TableTeacher());

        tableTrimestre = new JTable(new TableTrimestre());

        tableStudentNotes = new JTable(new TableStudentGrades());

        tableBulletinsClasse = new JTable(new TableBulletin());

    }
}
