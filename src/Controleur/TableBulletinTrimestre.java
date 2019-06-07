package Controleur;

import Modele.BulletinClasse;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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
