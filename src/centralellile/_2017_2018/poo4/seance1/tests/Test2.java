/*
 * The MIT License
 * To change this template file, choose Tools | Templates
 * Copyright 2018 Team SI.
 * and open the template in the editor.
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
package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modele.Medecin;

/**
 * Représente les tests sur les médecins
 * @author user
 */
public class Test2 {
    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hopitalPU");
        final EntityManager em = emf.createEntityManager();
        
        try{
            final EntityTransaction et = em.getTransaction();
            try{
                et.begin();
                // création d’entités persistantes                
                Medecin med1 = new Medecin("Trancen", "Jean", 2135.23);
                Medecin med2 = new Medecin("Gator", "Coralie", 3156.00);
                Medecin med3 = new Medecin("Gator", "Magalie", 2545.3723);
                em.persist(med1);
                em.persist(med2);
                em.persist(med3);
                et.commit();
                
            } catch (Exception ex) {
                et.rollback();
            }
        } finally {
            if(em != null && em.isOpen()){
                em.close();
            }
            if(emf != null && emf.isOpen()){
                emf.close();
            }
        }
    }
}
