package Controleur;

import Modele.Trimestre;

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
        return " Trimestre :: " + trimestre.get(rowIndex).getNumero() + " :: " +  trimestre.get(rowIndex).getDebut() + " ::  " + trimestre.get(rowIndex).getFin();
    }

    @Override
    public String getColumnName(int column) {
        return "Trimestre";
    }
}
