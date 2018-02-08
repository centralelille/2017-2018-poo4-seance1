/*
 * The MIT License
 *
 * Copyright 2018 Team SI.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package centralellile._2017_2018.poo4.seance1.tests;

import centralellile._2017_2018.poo4.seance1.modele.Service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class Test1 {

    public static void main(String[] args) {
	final EntityManagerFactory emf
		= Persistence.createEntityManagerFactory("2017-2018-poo4-seance1-pu");
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
		// Run to check db content
		// SELECT * FROM USERNAME.SERVICE FETCH FIRST 100 ROWS ONLY;
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
