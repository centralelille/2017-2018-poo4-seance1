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
package centralellile._2017_2018.poo4.seance1.modele;

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
 * @author user
 */
@Entity
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    // La clé primaire sera
    // générée automatiquement et dans la base de donnée la colonne devra s’appeler
    // SERVNO.
    @Id
    @Column(name = "SERVNO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // Les noms doivent être uniques, et le nom et la localisation doivent
    // avoir une taille maximale et être non nuls. Ils doivent aussi être écrits en majus-
    // cules pour éviter les problèmes liés à la casse
    @Column(
	    name = "NOM",
	    length = 64,
	    unique = true,
	    nullable = false
    )
    private String nom;
    @Column(
	    name = "LOCALISATION",
	    length = 64,
	    nullable = false
    )
    private String localisation;

    //Permet de stocker tous les médecins d'un service
    @OneToMany(mappedBy = "service",
	    cascade = {
		CascadeType.PERSIST
	    })
    private Set<Medecin> regroupe;

    //Permet de stocker le chef du service
    @ManyToOne
    @JoinColumn(name = "ID_MEDECIN_DIRIGE")
    private Medecin dirigePar;

    public Service(String nom, String localisation) {
	this();
	setNom(nom);
	setLocalisation(localisation);
    }

    public Service() {
	setNom("Default Name");
	setLocalisation("Default Localisation");
	this.regroupe = new HashSet<>();
    }

    public int getId() {
	return id;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom.toUpperCase();
    }

    public String getLocalisation() {
	return localisation;
    }

    public void setLocalisation(String localisation) {
	this.localisation = localisation.toUpperCase();
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) id;
	return hash;
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

    // il faut éviter de se baser sur l’identifiant généré
    // automatiquement pour l’égalité car l’identifiant n’est généré que lorsque l’entité
    // est rendue persistante dans la base
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
	return "{id: " + this.id + ", nom: " + this.nom + ", localisation:" + this.localisation + "}";
    }

}
