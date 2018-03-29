package commerce.catalogue.domaine.modele;

import java.util.ArrayList;
import java.util.List;

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

@Entity (name="commerce.catalogue.domaine.modele.Film")
@DiscriminatorValue("film")
public class Film extends Article {
	private String acteur;
	private String directeur;
	private String genre;
	private String dateDeSortie;
	private String studio;
	private int duree;
	
	@Basic
	public String getActeur() {
		return acteur;
	}
	public void setActeur(String acteur) {
		this.acteur = acteur;
	}

	@Basic
	public String getDirecteur() {
		return directeur;
	}
	public void setDirecteur(String directeur) {
		this.directeur = directeur;
	}

	@Basic
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Basic
	public String getDateDeSortie() {
		return dateDeSortie;
	}
	public void setDateDeSortie(String dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}

	@Basic
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}

	@Basic
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}

	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof Article))
			retour = false ;
		else {
			if (!(o instanceof Livre))
				retour = super.equals(o) ;
			else {
				Film inFilm = (Film)o ;
				if (super.equals((Article)inFilm)
				  && this.getActeur().equals(inFilm.getActeur())
				  && this.getDirecteur().equals(inFilm.getDirecteur())
		  		  && this.getGenre().equals(inFilm.getGenre())
				  && this.getDateDeSortie().equals(inFilm.getDateDeSortie())
				  && this.getStudio().equals(inFilm.getStudio())
				  && this.getDuree() == inFilm.getDuree())
					retour = true ;
				else
					retour = false ;
			}
		}
		return retour ;
	}
}
