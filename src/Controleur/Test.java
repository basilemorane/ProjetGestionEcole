package Controleur;
import Vue.RootFenetre;
import Vue.RootWindow;
import Vue.Window;

/**
 * Classe test permettant d'appeler le constructeur de la classe RootFenetre
 * On met à true la visiblité de la classe Root fenetre
 * le projet gestionEcole ECE JAVA 2019 se lance
 * @author Basile MORANE, Alexandre Barron, Benoit Coville
 * TD 06
 * Mr. Hina & Mr. Segado
 */
public class Test {
        public static void main(String[] s){
            RootFenetre rootFenetre = new RootFenetre();
            rootFenetre.setVisible(true);
        }
    }
