package Controleur.ControleurClasse;

public class ExceptionNotExisting extends Exception {
    @Override
    public String getMessage() {
        String erreur = " Object not exist in the database";
        return erreur;
    }
}
