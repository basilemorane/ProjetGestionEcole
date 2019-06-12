package Controleur;

import Modele.Eleve;

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

public class TableStudent extends AbstractTableModel {
    private ArrayList<Eleve> Student;

    public TableStudent () {
        this.Student = new ArrayList<>();
    }

    public TableStudent (ArrayList<Eleve> Student){
        this.Student = Student;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return Student.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return Student.get(rowIndex).getNom() + " " + Student.get(rowIndex).getPrenom();
    }

    @Override
    public String getColumnName(int column) {
        return "Student";
    }

}
