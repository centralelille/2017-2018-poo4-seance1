/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import com.sun.istack.internal.NotNull;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Aurélien Ledieu
 */
@Entity
@Table(name = "MEDECIN",
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"PRENOM", "NOM"})
        }
)
public class Medecin implements Serializable {

    /* PARAMETRES*/
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDNO")
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(scale = 2)
    private double salaire;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICE")
    private Service service;

    @OneToMany(mappedBy = "dirigePar",
            cascade = {
                CascadeType.PERSIST
            })
    private Set<Service> serviceDirige;

    @OneToMany(mappedBy = "estGerePar",
            cascade = {
                CascadeType.PERSIST
            })
    private Set<Medecin> gere;

    @ManyToOne
    @JoinColumn(name = "ID_MEDECIN_CHEF")
    private Medecin estGerePar;

    /* CONSTRUCTEURS */
    public Medecin() {
        this.serviceDirige = new HashSet<>();
        this.gere = new HashSet<>();
    }

    public Medecin(@NotNull String nom, @NotNull String prenom, double salaire) {
        this();
        if (nom != null && prenom != null) {
            this.setNom(nom);
            this.setPrenom(prenom);
            this.setSalaire(salaire);
        }
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

    //prenom
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom.toUpperCase();
    }

    //salaire
    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        if (salaire <= 0) {
            this.salaire = 0;
        }
        this.salaire = Math.floor(salaire * 100) / 100;
    }

    //service
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        if (this.service != null) {
            Service oldService = this.service;
            oldService.getRegroupe().remove(this);
        }
        this.service = service;
    }

    //serviceDirige
    public Set<Service> getServiceDirige() {
        return serviceDirige;
    }

    public boolean addServiceDirige(Service s) {
        if (serviceDirige.add(s)) {
            s.setDirigePar(this);
            return true;
        }
        return false;
    }

    //gere
    public boolean setChef(Medecin m) {
        if (this.gere.add(m)) {
            Medecin oldGerePar = this.estGerePar;
            if (oldGerePar != null) {
                oldGerePar.gere.remove(this);
            }
            this.estGerePar = m;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nom);
        hash = 83 * hash + Objects.hashCode(this.prenom);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.salaire) ^ (Double.doubleToLongBits(this.salaire) >>> 32));
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
        if (Double.doubleToLongBits(this.salaire) != Double.doubleToLongBits(other.salaire)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medecin[ID =" + this.id + ", Nom =" + this.nom + ", Prenom =" + this.prenom + ", Salaire =" + this.salaire + " + Services diriges=(" + this.serviceDirige + ")]";
    }

}
