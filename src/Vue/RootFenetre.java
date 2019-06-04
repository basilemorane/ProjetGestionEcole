package Vue;

import Controleur.Connexion;
import Controleur.ControleurClasse.*;
import Modele.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import Controleur.*;

public class RootFenetre extends JFrame {
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

                        refillTableStudentSchool ();
                        refillTableTeacherSchool();

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
       /* comboBoxSelectYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refillComboBoxLevel();
            }
        });*/

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
                refillTableTeacherPresent();
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


        CreateLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newLevelText = textFieldLevel.getText();
                try {
                    new NiveauDAO(maconnexion).create(new Niveau(0, newLevelText));
                    JLabelMessage.setText("Succes adding level school in the database");
                    refillComboBoxLevel();

                } catch (Exception expt) {
                    JLabelMessage.setText("Problems in adding this level in the database");
                }
            }
        });

        DeleteLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deletelevel = (String) comboBoxSelectYear.getSelectedItem();
                try {
                    new NiveauDAO(maconnexion).delete(new NiveauDAO(maconnexion).find(1, deletelevel));
                    JLabelMessage.setText("Succes deleting level school in the database");
                    refillComboBoxLevel();
                } catch (ExceptionNotExisting evt){
                    JLabelMessage.setText("Problem in deleting this school level");
                }
            }
        });

        UpdateLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updtateLevel = (String) comboBoxSelectLevel.getSelectedItem();
                String newnameLevel = textFieldLevel.getText();
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
                String levelClasse = (String) SelectNewLevelClasse.getSelectedItem();

                int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
                int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();
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
         * Dans SCHOOL LEVEL
         * lorsque l'on clique sur gestion discipline
         * méthode - ajouter une matière à la promo sélectionnée
         * méthode - enlever une matière à la promo sélectionnée
         */
        newButtonDispiline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String discipline = (String) tableDisciplineAbsent.getValueAt(tableDisciplineAbsent.getSelectedRow(), tableDisciplineAbsent.getSelectedColumn());
               Discipline discipline1 =  new DisciplineDAO(maconnexion).find(1, discipline);
                int idyear = new AnneeScolaireDA0(maconnexion).find(1, (String) comboBoxSelectYear.getSelectedItem()).getId();
                int idlevel = new NiveauDAO(maconnexion).find(1, (String) comboBoxSelectLevel.getSelectedItem()).getId();

               new DisciplineDAO(maconnexion).addDisciplinetoPromo(discipline1, idyear, idlevel);

               refillTableDisplineNotPresent();
               refillTableDiscipline();
               refillComboBoxDiscipline();
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

                try {
                    new InscriptionDAO(maconnexion).create(inscription);
                    JLabelMessage.setText("Succes in adding student from this classe");
                    refillComboBoxStudent();
                    refillTableStudentAbsent();
                    refillTableStudentPresent(idClasse);
                } catch (ExceptionAlreadyExistant evt){
                    JLabelMessage.setText("Problem in adding student from this classe");
                }
            }
        });

        /**
         * GESTION DISCIPLINE
         */

        Discipline.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refillTableDisciplineSchool();
            }
        });

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
         * STUDENT
         */

        Student.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refillTableStudentSchool ();
            }
        });

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

        buttonStudentUpgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastnamename = (String) tableStudentSchool.getValueAt(tableStudentSchool.getSelectedRow(), tableStudentSchool.getSelectedColumn());
                new DAOEleve(maconnexion).update(new Eleve(1, textFieldStudentLastName.getText(), textFieldStudentName.getText()),lastnamename );
                JLabelMessage.setText("Succes in updating the student");
                refillTableStudentSchool();
            }
        });

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
         * Gestion des clic qur le tableau
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
            comboBoxSelectStudent.addItem(eleve.getNom() + " " +eleve.getPrenom());
        });
    }

    public void refillComboBoxDiscipline (){
        int idClasse = FindIdClasseForStudent();
        System.out.println("Id CLASSE :: "+ idClasse);
        new DisciplineDAO(maconnexion).findAll(idClasse).forEach(discipline -> {
            System.out.println(discipline.getNom_classe());
            comboBoxSelectDispline.addItem(discipline.getNom_classe());
        });
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

    public int FindIdClasseForStudent () {
        String yearClasse = (String) comboBoxSelectYear.getSelectedItem();
        String levelClasse = (String) comboBoxSelectLevel.getSelectedItem();
        String nameClasse = (String) comboBoxSelectClasse.getSelectedItem();
        int idyearClasse = new AnneeScolaireDA0(maconnexion).find(1, yearClasse).getId();
        int idLevelClasse = new NiveauDAO(maconnexion).find(1, levelClasse).getId();

        Classe classe = new Classe(1,nameClasse, idyearClasse, idLevelClasse);
       return new ClasseDA0(maconnexion).find(classe).getId();
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
        String year = (String) comboBoxSelectYear.getSelectedItem();
        int idyear = new AnneeScolaireDA0(maconnexion).find(1, year).getId();
        tableDisciplinePresent.setModel(new TableDiscipline(new DisciplineDAO(maconnexion).findAll(idyear)));
    }

    public void refillTableDisplineNotPresent (){
        String year = (String) comboBoxSelectYear.getSelectedItem();
        String level = (String) comboBoxSelectLevel.getSelectedItem();
        int idyear = new AnneeScolaireDA0(maconnexion).find(1, year).getId();
        int idLevel = new NiveauDAO(maconnexion).find(1, level).getId();

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

        tableTeacherPresent.setModel(new TableTeacher(new ProfesseurDAO(maconnexion).findAll(FindIdClasseForStudent(), (String) comboBoxSelectDispline.getSelectedItem())));
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
    }
}
