package Vue;

import Controleur.Connexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Udpatebdd extends JFrame implements ActionListener {
    private Connexion maconnexion;
    private  JLabel Search;
    private JTextField lookingFor;
    private  JButton Validate;
    private  JPanel p0, p1, nord;

    public Udpatebdd () {

        super (" Projet : Gestion d'une école : Update the Bdd");
        setLayout(new BorderLayout());
        setBounds(0,0,800,800);
        setResizable(true);
        setVisible(true);

        try {
            try {
                /**
                 * On essaye de se connecter à la base de données rentrée
                 */
                maconnexion = new Connexion("gestionecole", "root", "");
                System.out.println("Succes to connect");


                /**
                 * Création des Jbuttons de la fentre
                 * JButton connection Base de Donnees
                 */
                Validate = new JButton("Search");

                /**
                 * Création des JTextField
                 * Pouvoir se connecter à la base de données
                 */
                lookingFor = new JTextField();

                /**
                 * Creation des labels
                 */

               Search = new JLabel("Search : ");


                /**
                 * Initialition des panels de la fenetre
                 */
                p0 = new JPanel();
                p1 = new JPanel();
                nord = new JPanel();


                /**
                 * Mise en page des panels de la fenetre
                 */
                p0.setLayout(new GridLayout(1,3));
                p1.setLayout(new GridLayout(1,1));
                nord.setLayout(new GridLayout(2,1));

                /**
                 * Ajout des objets graphique dans les panels
                 */

                p0.add(Search);
                p0.add(lookingFor);
                p0.add(Validate);
                nord.add("North", p0);
                nord.add("North", p1);

                /**
                 * Ajout des Actionlisteners sur les JButtons
                 */
                Validate.addActionListener(this);
                lookingFor.addActionListener(this);
                /**
                 * Ajout Couleur sur les panels et autre
                 */
                p0.setBackground(Color.blue);
                /**
                 * Disposition Graphique des panels
                 */
                nord.add("North", p0);
                nord.add("North", p1);
                add("North", nord);
                /**
                 * Pour pouvoir fermer la fenetre
                 */
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });


            } catch (ClassNotFoundException e){
                System.out.println(e);
                e.printStackTrace();
            }
        }catch (SQLException sqlE){
            System.out.println(sqlE);
            sqlE.printStackTrace();
        }
    }

    /**
     * Utilisation de la fonction actionPerformed pour gérer les actions sur les boutons
     * @param evt
     */
    @Override
    @SuppressWarnings("CallThreadDumpStack")
    public void actionPerformed (ActionEvent evt){
        Object source = evt.getSource();

        /**
         * Méthode appellée lors de l'appui sur le bouton Connect
         * On se connecte à la base de données
         */

        if (source == Validate){
               String requete = lookingFor.getText();
               if (requete == "") {
                   System.out.println("There no search to do.");
               } else {
                   try {
                       ArrayList<String> RequestPersonne = maconnexion.remplirChampsRequete("SELECT * FROM Personne WHERE nom_personne LIKE '%" + requete + "%'");
                       if (RequestPersonne.size() == 0 ){
                           RequestPersonne = maconnexion.remplirChampsRequete("SELECT * FROM Personne WHERE prenom_personne LIKE '%" + requete + "%'");
                           System.out.println("No Match find. Sorry");
                           System.out.println(RequestPersonne);
                       } else
                           System.out.println(RequestPersonne);
                   } catch (SQLException e) {
                       System.out.println(e);
                   }
               }
        }

    }


}

