package Controleur;

import Modele.AnneeScolaire;

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

public class TableAnneScolaire extends AbstractTableModel {
        ArrayList<AnneeScolaire> year;

    public  TableAnneScolaire(){
            year = new ArrayList<>();
        }

    public  TableAnneScolaire(ArrayList<AnneeScolaire> year){
        this.year = year;
    }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public int getRowCount() {
            return year.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return year.get(rowIndex).getYear();
        }

        @Override
        public String getColumnName(int column) {
            return "School Year";
        }
    }

