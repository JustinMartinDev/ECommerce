package commerce.catalogue.domaine.modele;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;

/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * Author  A. Corbière
 * Version 2.0, 23/09/10
 */

@Entity (name="commerce.catalogue.domaine.modele.Article")
@DiscriminatorColumn(
  name="article_type",
  discriminatorType= DiscriminatorType.STRING
)
@DiscriminatorValue("article")
public class Article {
	private String refArticle; 
	private String titre;
	private String image;
	private String langue;
	private double prix;
	private int disponibilite;

	@Id
	public String getRefArticle() {
		return refArticle;
	}
	public void setRefArticle(String inRefArticle) {
		refArticle = inRefArticle;
	}
	
	@Basic
	public String getTitre() {
		return titre;
	}
	public void setTitre(String inTitre) {
		titre = inTitre;
	}	  
	
	@Basic
	public String getImage() {
		return image;
	}
	public void setImage(String inImage) {
		image = inImage;
	}
	
	@Basic
	public String getLangue() {
		return langue;
	}
	public void setLangue(String inLangue) {
		langue = inLangue;
	}
	
	@Basic
	public double getPrix() {
		return prix;
	}
	public void setPrix(double inPrix) {
		prix = inPrix;
	}
	public void setPrix(String inPrix) {
		prix = Double.parseDouble(inPrix);
	}
	
	@Basic
	public int getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(String inDisponibilite) {
		disponibilite = Integer.parseInt(inDisponibilite);
	}
	public void setDisponibilite(int inDisponibilite) {
		disponibilite = inDisponibilite;
	}

	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof Article))
			retour = false ;
		else {
			Article inArticle = (Article)o ;
			if (this.getRefArticle().equals(inArticle.getRefArticle()))
				retour = true ;
			else
				retour = false ;
		}
		return retour ;
	}
}
