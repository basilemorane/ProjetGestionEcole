package Controleur;

import Controleur.ControleurClasse.Controleur;
import Modele.Notes;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableStudentGrades extends AbstractTableModel {
    ArrayList<Notes> grades;

    public TableStudentGrades () {
        this.grades = new ArrayList<>();
    }

    public TableStudentGrades ( ArrayList<Notes> grades){
        this.grades = grades;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return grades.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return 1;
    }

    @Override
    public String getColumnName(int column) {
        return "Student Grades";
    }
}
