package Controleur;

import Controleur.ControleurClasse.Controleur;
import Controleur.ControleurClasse.NiveauDAO;
import Modele.AnneeScolaire;
import Modele.Niveau;

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

public class TableLevel extends AbstractTableModel {
    private ArrayList<Niveau> level;

    public  TableLevel(){
        this.level = new ArrayList<>();
    }

    public TableLevel (ArrayList<Niveau> level){
        this.level = level;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return level.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return level.get(rowIndex).getNom();
    }

    @Override
    public String getColumnName(int column) {
        return "School Level";
    }
}
