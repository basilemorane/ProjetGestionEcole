package Controleur.ControleurClasse;


/**
 * Exception throw quand un object n'existe pas dans la base de données
 */
public class ExceptionNotExisting extends Exception {
    @Override
    public String getMessage() {
        String erreur = " Object not exist in the database";
        return erreur;
    }
}
