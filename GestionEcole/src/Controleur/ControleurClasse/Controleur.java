package Controleur.ControleurClasse;
import Controleur.Connexion;

public abstract class Controleur <T> {

        protected Connexion connect = null;

        public Controleur(Connexion conn){
            this.connect = conn;
        }

        /**
         * Méthode de création
         * @param obj
         * @return boolean
         */
        public abstract boolean create(T obj);

        /**
         * Méthode pour effacer
         * @param obj
         * @return boolean
         */
        public abstract boolean delete(T obj);

        /**
         * Méthode de mise à jour
         * @param obj
         * @return boolean
         */
        public abstract boolean update(T obj);

        /**
         * Méthode de recherche des informations
         * @param id
         * @return T
         */
        public abstract T find(int id);

        /**
         * Méthode de recherche des informations
         * @param name
         * @return T
         */

        public abstract T find (int id, String name);

        public abstract T find (int id, int id1, int id2);
    }

