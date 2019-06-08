package Controleur;

import Modele.BulletinClasse;

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

public class TableBulletinTrimestre extends AbstractTableModel {
    ArrayList<BulletinClasse> arrayList;

    public TableBulletinTrimestre (){
        this.arrayList = new ArrayList<>();
    }

    public TableBulletinTrimestre (ArrayList<BulletinClasse> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return arrayList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 1)
            return arrayList.get(rowIndex).getCommentaireDetails();
        else if (columnIndex == 2)
            return arrayList.get(rowIndex).getMoyenne();
        else
            return this.arrayList.get(rowIndex).getNomDsipline();
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

    public String getComments (String comment) {
        return comment;
    }

}
