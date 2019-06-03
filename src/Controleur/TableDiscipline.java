package Controleur;

import Modele.Classe;
import Modele.Discipline;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableDiscipline extends AbstractTableModel {
        ArrayList<Discipline> discipline ;

    public TableDiscipline(){
        discipline = new ArrayList<>();
    }

    public TableDiscipline(ArrayList< Discipline > discipline){
        this.discipline = discipline;
    }

        @Override
        public int getColumnCount() {
        return 1;
    }

        @Override
        public int getRowCount() {
        return discipline.size();
    }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return discipline.get(rowIndex).getNom_classe();
    }

        @Override
        public String getColumnName(int column) {
        return "Discipline Classe";
    }
}