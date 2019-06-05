package Controleur;

import Modele.Trimestre;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableTrimestre extends AbstractTableModel {

    ArrayList<Trimestre> trimestre;

    public TableTrimestre () {
        this.trimestre = new ArrayList<>();
    }

    public TableTrimestre(ArrayList<Trimestre> trimestre) {
        this.trimestre = trimestre;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return trimestre.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return trimestre.get(rowIndex).getDebut() + " " + trimestre.get(rowIndex).getFin();
    }

    @Override
    public String getColumnName(int column) {
        return "Trimestre";
    }
}
