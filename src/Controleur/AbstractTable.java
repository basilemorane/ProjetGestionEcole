package Controleur;

import javax.swing.table.AbstractTableModel;

/**
 * Claas par default de JModeleTable
 * N'est pas utiliser dans le projet
 */
public class AbstractTable extends AbstractTableModel {

   public  AbstractTable(){

   }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        return "";
    }
}
