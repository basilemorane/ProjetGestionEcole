package Controleur;

import Modele.Discipline;
import Modele.Enseignement;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableBulletin extends AbstractTableModel {

    private ArrayList sum;
    private ArrayList<Discipline> enseignements;

    public TableBulletin () {
        this.sum = new ArrayList();
        this.enseignements = new ArrayList<>();
    }

    public TableBulletin (ArrayList sum, ArrayList<Discipline> enseignements) {
        this.sum = sum;
        this.enseignements = enseignements;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return sum.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 1)
            return "no comments";
        else if (columnIndex == 2)
            return sum.get(rowIndex);
        else
            return this.enseignements.get(rowIndex).getNom_classe();
    }

    @Override
    public String getColumnName(int column) {

        if (column == 1) {
            return "Comments";
        }
        else if (column == 2) {
            return "Sum";
        }
        else
            return "Subject";

    }
}

