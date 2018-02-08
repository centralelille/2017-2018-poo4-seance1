/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralellile._2017_2018.poo4.seance1.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import centralellile._2017_2018.poo4.seance1.modele.Medecin;
import centralellile._2017_2018.poo4.seance1.modele.Service;

/**
 *
 * @author Aurélien Ledieu
 */
public class Test3 {

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
		Medecin med1 = new Medecin("Trancen", "Jean", 2135.23);
		Medecin med2 = new Medecin("Gator", "Coralie", 3156.00);
		Medecin med3 = new Medecin("Gator", "Magalie", 2545.37);
		Medecin med4 = new Medecin("Hitmieu", "Helmer", 1873.30);
		Medecin med5 = new Medecin("Cambronne", "Maude", 3765.20);
		Medecin med6 = new Medecin("Haybon", "Sylvain", 2980.00);

		serv1.addMedecin(med1);
		serv1.addMedecin(med2);
		serv1.addMedecin(med3);
		serv2.addMedecin(med4);
		serv3.addMedecin(med5);
		serv3.addMedecin(med6);

		med4.addServiceDirige(serv2);
		med5.addServiceDirige(serv1);
		med5.addServiceDirige(serv3);

		em.persist(serv1);
		em.persist(serv2);
		em.persist(serv3);

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
