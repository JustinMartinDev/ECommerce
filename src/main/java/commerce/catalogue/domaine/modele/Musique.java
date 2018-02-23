package commerce.catalogue.domaine.modele;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * Author  A. Corbière
 * Version 2.0, 23/09/10
 */

@Entity (name="commerce.catalogue.domaine.modele.Musique")
@DiscriminatorValue("musique")
public class Musique extends Article {
	private String artiste;
	private String EAN;
	private String dateDeParution;
	private List<Piste> pistes;

	@Basic
	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String inArtiste) {
		artiste = inArtiste;
	}

	@Basic
	public String getEAN() {
		return EAN;
	}
	public void setEAN(String inEAN) {
		EAN = inEAN;
	}

	@Basic
	public String getDateDeParution() {
		return dateDeParution;
	}
	public void setDateDeParution(String inDateDeParution) {
		dateDeParution = inDateDeParution;
	}
	
	@OneToMany
	@LazyCollection (LazyCollectionOption.FALSE)
	public List<Piste> getPistes() {
		return pistes;
	}
	public void setPistes(List<Piste> inPistes) {
		pistes = inPistes;
	}

	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof Article))
			retour = false ;
		else {
			if (!(o instanceof Musique))
				retour = super.equals(o) ;
			else {
				Musique inMusique = (Musique)o ;
				if (super.equals((Article)inMusique)
				  && this.getTitre().equals(inMusique.getTitre()))
					retour = true ;
				else
					retour = false ;
			}
		}
		return retour ;
	}
}
