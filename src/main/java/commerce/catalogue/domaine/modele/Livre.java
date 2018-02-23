package commerce.catalogue.domaine.modele;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * Author  A. Corbière
 * Version 2.0, 23/09/10
 */

@Entity (name="commerce.catalogue.domaine.modele.Livre")
@DiscriminatorValue("livre")
public class Livre extends Article {
	private String auteur;
	private String ISBN;
	private int nbPages;
	private String dateDeParution;

	@Basic
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String inAuteur) {
		auteur = inAuteur;
	}

	@Basic
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String inISBN) {
		ISBN = inISBN;
	}

	@Basic
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int inNbPages) {
		nbPages = inNbPages;
	}

	@Basic
	public String getDateDeParution() {
		return dateDeParution;
	}
	public void setDateDeParution(String inDateDeParution) {
		dateDeParution = inDateDeParution;
	}

	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof Article))
			retour = false ;
		else {
			if (!(o instanceof Livre))
				retour = super.equals(o) ;
			else {
				Livre inLivre = (Livre)o ;
				if (super.equals((Article)inLivre)
				  && this.getTitre().equals(inLivre.getTitre())
				  && this.getISBN().equals(inLivre.getISBN())
				  && this.getNbPages()==(inLivre.getNbPages())
		  		  && this.getLangue().equals(inLivre.getLangue())
				  && this.getDateDeParution().equals(inLivre.getDateDeParution()))
					retour = true ;
				else
					retour = false ;
			}
		}
		return retour ;
	}
}
