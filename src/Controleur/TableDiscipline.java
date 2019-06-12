package Controleur;

import Modele.Classe;
import Modele.Discipline;

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