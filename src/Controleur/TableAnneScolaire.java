package Controleur;

import Modele.AnneeScolaire;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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

