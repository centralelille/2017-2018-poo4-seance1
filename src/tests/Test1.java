/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modele.Service;

/**
 *
 * @author Aurélien Ledieu
 */
public class Test1 {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HopitalPU");
        final EntityManager em = emf.createEntityManager();

        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                Service serv1 = new Service("Cardiologie", "Bat A, 1er étage");
                Service serv2 = new Service("Pneumologie", "Bat B, 1er étage");
                Service serv3 = new Service("Urgence", "Bat C, 1er étage");

                em.persist(serv1);
                em.persist(serv2);

                serv1.setLocalisation("Bat D, 2ème étage");

                et.commit();
            } catch (Exception ex) {
                et.rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

}
