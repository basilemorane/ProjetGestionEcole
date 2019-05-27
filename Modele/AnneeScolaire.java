package Modele;

public class AnneeScolaire {
    private int id;
    private String year;

    public AnneeScolaire (){
        this.id = 0;
        this.year = "2018-2019";
    }

    public AnneeScolaire(int id, String year) {
        this.id = id;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
