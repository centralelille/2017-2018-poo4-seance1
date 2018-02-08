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
import modele.Medecin;

/**
 *
 * @author Aurélien Ledieu
 */
public class Test4 {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HopitalPU");
        final EntityManager em = emf.createEntityManager();

        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                Medecin med1 = new Medecin("Trancen", "Jean", 2135.23);
                Medecin med2 = new Medecin("Gator", "Coralie", 3156.00);
                Medecin med3 = new Medecin("Gator", "Magalie", 2545.3723);

                med1.setChef(med3);
                med2.setChef(med1);
                med1.setChef(med2);

                em.persist(med1);
                em.persist(med2);
                em.persist(med3);
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
