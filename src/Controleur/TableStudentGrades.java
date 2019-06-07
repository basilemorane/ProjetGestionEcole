package Controleur;

import Controleur.ControleurClasse.Controleur;
import Modele.Notes;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableStudentGrades extends AbstractTableModel {
    ArrayList<Notes> grades;

    public TableStudentGrades () {
        this.grades = new ArrayList<>();
    }

    public TableStudentGrades ( ArrayList<Notes> grades){
        this.grades = grades;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return grades.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return this.grades.get(rowIndex).getNote() + "/20";
        } else
            return "Evaluation " + (rowIndex+1) ;
    }

    public int getSize (){
        return grades.size();
    }

    public int getNote (int i) {
       return this.grades.get(i).getNote();
    }


    @Override
    public String getColumnName(int column) {
        if (column == 1) {
            return "Student Grades";
        } else
            return "Interrogation";
    }

    public double getSum() {
        double sum =0;
        for (int i=0; i< grades.size(); i++){
           sum += grades.get(i).getNote();
        }
        return (sum/grades.size());
    }

    public int getId (int i){
       return this.grades.get(i).getId_evaluation();
    }

    public String getComments (String comments) {
        return comments;
    }
}
