/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
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

/**
 *
 * @author Aurélien Ledieu
 */
@Entity
public class Service implements Serializable {

    /* PARAMETRES*/
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVNO")
    private int id;

    @Column(unique = true)
    private String nom;

    @Column(nullable = false,
            length = 25)
    private String localisation;

    @OneToMany(mappedBy = "service",
            cascade = {
                CascadeType.PERSIST
            })
    private Set<Medecin> regroupe;

    @ManyToOne
    @JoinColumn(name = "ID_MEDECIN_DIRIGE")
    private Medecin dirigePar;

    /* CONSTRUCTEURS */
    public Service() {
        this.regroupe = new HashSet<>();
    }

    public Service(String nom, String localisation) {
        this();
        this.setNom(nom);
        this.setLocalisation(localisation);
    }

    /* GETTER ET SETTER */
    //id
    public int getId() {
        return id;
    }

    //nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    //Localisation
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation.toUpperCase();
    }

    //dirigePar
    public Medecin getDirigePar() {
        return dirigePar;
    }

    public void setDirigePar(Medecin m) {
        if (this.dirigePar != null) {
            Medecin oldDirigePar = this.dirigePar;
            oldDirigePar.getServiceDirige().remove(this);
        }
        this.dirigePar = m;
    }

    //regroupe
    public Set<Medecin> getRegroupe() {
        return regroupe;
    }

    public boolean addMedecin(Medecin m) {
        if (regroupe.add(m)) {
            m.setService(this);
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.nom);
        hash = 47 * hash + Objects.hashCode(this.localisation);
        hash = 47 * hash + Objects.hashCode(this.regroupe);
        hash = 47 * hash + Objects.hashCode(this.dirigePar);
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
        final Service other = (Service) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.localisation, other.localisation)) {
            return false;
        }
        if (!Objects.equals(this.regroupe, other.regroupe)) {
            return false;
        }
        if (!Objects.equals(this.dirigePar, other.dirigePar)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Service[ID : " + this.id + ", Nom :" + this.nom + ", Localisation :" + this.localisation + "]";
    }

}
