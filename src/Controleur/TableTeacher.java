package Controleur;

import Modele.Eleve;
import Modele.Professeur;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Classe TableModele pour la classe adéquat
 *      - un constructeur par default
 *      - un constructeur avec une arrayList en parametre
 *      - methode :
 *              - recuper nombre colonne
 *              - recuperer nombre colonne
 *              - recupere titre du tableau
 *              - recupere la valeur de l'array list en fonction de la ligne et de la colonne
 *
 */

public class TableTeacher extends AbstractTableModel {
    private ArrayList<Professeur> teacher;

    public TableTeacher () {
        this.teacher = new ArrayList<>();
    }

    public TableTeacher (ArrayList<Professeur> Student){
        this.teacher = Student;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return teacher.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return teacher.get(rowIndex).getNom() + " " + teacher.get(rowIndex).getPrenom();
    }

    @Override
    public String getColumnName(int column) {
        return "Teacher SCHOOL";
    }

}
