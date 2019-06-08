package Controleur;

import Modele.AnneeScolaire;
import Modele.Classe;

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

public class TableClasse extends AbstractTableModel {
    ArrayList<Classe> Classe;

    public  TableClasse(){
        Classe = new ArrayList<>();
    }

    public TableClasse(ArrayList<Classe> year){
        this.Classe = year;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return Classe.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 1)
        return Classe.get(rowIndex).getName();
        else if (columnIndex == 2)
            return Classe.get(rowIndex).getIdNiveau();
        else
            return Classe.get(rowIndex).getIdAnneeScolaire();


    }

    @Override
    public String getColumnName(int column) {
        return "School Classe";
    }
}

