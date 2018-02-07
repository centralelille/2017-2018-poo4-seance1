/*
 * The MIT License
 *
 * Copyright 2018 Team 2i.
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
				name="NOM",
				length = 64,
				unique = true,
				nullable = false
		)
		private String nom;
		@Column(
				name="LOCALISATION",
				length = 64,
				nullable = false
		)
		private String localisation;

		public Service(String nom, String localisation) {
				setNom(nom);
				setLocalisation(localisation);
		}

		public Service() {
				setNom("Default Name");
				setLocalisation("Default Localisation");
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
				this.id = id;
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

		// il faut éviter de se baser sur l’identifiant généré
		// automatiquement pour l’égalité car l’identifiant n’est généré que lorsque l’entité
		// est rendue persistante dans la base
		@Override
		public boolean equals(Object object) {
				// TODO: Warning - this method won't work in the case the id fields are not set
				if (!(object instanceof Service)) {
						return false;
				}
				Service other = (Service) object;
				if (this.id != other.id) {
						return false;
				}
				return true;
		}

		@Override
		public String toString() {
				return "{id: "+this.id+", nom: "+this.nom+", localisation:"+this.localisation+"}";
		}

}
