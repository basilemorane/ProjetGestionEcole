package Controleur.ControleurClasse;
import Controleur.Connexion;

import java.util.ArrayList;

public abstract class Controleur <T> {

        protected Connexion connect;

        public Controleur(Connexion conn){
            this.connect = conn;
        }

        /**
         * Méthode de création
         * @param obj
         * @return boolean
         */
        public abstract boolean create(T obj) throws ExceptionAlreadyExistant;

        /**
         * Méthode pour effacer
         * @param obj
         * @return boolean
         */
        public abstract boolean delete(T obj) throws ExceptionNotExisting;

        /**
         * Méthode de mise à jour
         * @param obj
         * @return boolean
         */
        public abstract boolean update(T obj, String newName) throws ExceptionAlreadyExistant;

        /**
         * Méthode de recherche des informations
         * @param name
         * @return T
         */

        /**
         * Methode pour retrouver un object dans la base donnée s
         * @param id
         * @param name
         * @return
         */

        public abstract T find (int id, String name);

        public abstract T find (int id, int id1, int id2);

        public abstract T find (T obj);

        public abstract ArrayList<T> findAll();




    }

