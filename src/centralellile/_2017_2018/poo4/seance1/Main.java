/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralellile._2017_2018.poo4.seance1;

import centralellile._2017_2018.poo4.seance1.modele.Service;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hellow World");
				Service service = new Service();
				System.out.println(service.toString());
				service = new Service("Bob", "Beijing");
				System.out.println(service.toString());
    }
    
}
