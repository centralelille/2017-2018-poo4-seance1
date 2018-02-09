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
package modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entité représentant un médecin
 * @author user
 */
@Entity
@Table(name="MEDECIN",
    uniqueConstraints={
        @UniqueConstraint(
        columnNames ={"MEDENAME", "MEDEFIRSTNAME"})
    }
)
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="MEDENO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="MEDENAME",length = 50,nullable = false)
    private String nom;
    
    @Column(name="MEDEFIRSTNAME",length = 50,nullable = false)
    private String prenom;
    
    @Column(name="MEDESALARY",scale = 2, nullable = false)
    private double salaire;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="service")
    private Service service;
    
    @OneToMany(mappedBy="manager",
            cascade=CascadeType.PERSIST)
    private Set<Service> ensServicesDiriges; 
    
    @ManyToOne
    private Medecin chef;
    
    @OneToMany(mappedBy="chef",
            cascade=CascadeType.PERSIST)
    private Set<Medecin> subordonnes;
    
    /**
     * Constructeur par défault
     */
    public Medecin() {
        this.ensServicesDiriges = new HashSet<>();
        this.subordonnes = new HashSet<>();
    }

    /**
     * Constructeur par données
     * @param nom
     * @param prenom
     * @param salaire 
     */
    public Medecin(String nom, String prenom, double salaire) {
        this();
        this.nom = nom.toUpperCase();
        this.prenom = prenom.toUpperCase();
        this.salaire = (salaire > 0) ? salaire : 0;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public double getSalaire() {
        return salaire;
    }
    
    public Service getService() {
        return service;
    }
    
    public Collection<Service> getEnsServicesDiriges(){
        return new HashSet<>(ensServicesDiriges);
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public void setService(Service service) {
        this.service = service;
    }    

    /**
     * Ajouter un service à la liste des services dirigés par un médecin
     * @param s
     * @return 
     */
    public boolean addServiceDirige(Service s) {
        Medecin m_old = s.getManager();
        if(ensServicesDiriges.add(s)){
            if(m_old != null){
                m_old.ensServicesDiriges.remove(s);                
            }
            s.setManager(this);
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Définis le médecin en chef
     * @param m
     * @return 
     */
    public boolean setChef(Medecin m) {
        if(m == this) return false;
        if(m == chef) return true;
        
        if(chef != null) {
            chef.subordonnes.remove(this);
        }
        m.subordonnes.add(this);
        chef = m;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.prenom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medecin other = (Medecin) obj;
        if (!Objects.equals(this.nom, other.nom.toUpperCase())) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom.toUpperCase())) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        String result = "Médecin "+ id + " [\n\tNom :" + nom + "\n\tPrénom :" + prenom + 
                "\n\tSalaire :" + salaire + "\n\tDirige les services ";
        for(Service s : ensServicesDiriges){
            result += s.getNom() + " - " + s.getLocalisation();
        }
        return result+"\n]";
    }
    
}
