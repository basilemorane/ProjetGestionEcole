package Controleur.ControleurClasse;

public class ExceptionAlreadyExistant extends Exception {
    @Override
    public String getMessage() {
        String erreur = " Object alrealdy exist in the database";
        return erreur;
    }
}
