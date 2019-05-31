package Controleur;

import Controleur.ControleurClasse.Controleur;
import Controleur.ControleurClasse.NiveauDAO;
import Modele.AnneeScolaire;
import Modele.Niveau;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableLevel extends AbstractTableModel {
    private Controleur<Niveau> test;
    private ArrayList<Niveau> level;

    public  TableLevel(Connexion conn){
        this.test = new NiveauDAO(conn);
        this.level = test.findAll();
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
        return level.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return "School Level";
    }
}
