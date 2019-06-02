package Controleur;

import Modele.Eleve;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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
