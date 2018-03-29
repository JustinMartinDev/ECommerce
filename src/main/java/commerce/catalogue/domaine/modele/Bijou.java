package commerce.catalogue.domaine.modele;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity (name="commerce.catalogue.domaine.modele.Bijou")
@DiscriminatorValue("bijou")
public class Bijou extends Article{
	private String marque;
	private String constructeur;
	private String categorie;

	@Basic
	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	@Basic
	public String getConstructeur() {
		return constructeur;
	}

	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}

	@Basic
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof Article))
			retour = false ;
		else {
			if (!(o instanceof Bijou))
				retour = super.equals(o) ;
			else {
				Bijou inBijou = (Bijou)o ;
				if (super.equals((Article)inBijou)
				  && this.getMarque().equals(inBijou.getMarque())
				  && this.getConstructeur().equals(inBijou.getConstructeur())
				  && this.getCategorie()==(inBijou.getCategorie()))
					retour = true ;
				else
					retour = false ;
			}
		}
		return retour ;
	}
}
