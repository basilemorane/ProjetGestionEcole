package Controleur.ControleurClasse;


/**
 * Exception throw si un Object existe deja
 * Claase heritant d'Exception
 */
public class ExceptionAlreadyExistant extends Exception {
    @Override
    public String getMessage() {
        String erreur = " Object alrealdy exist in the database";
        return erreur;
    }
}
