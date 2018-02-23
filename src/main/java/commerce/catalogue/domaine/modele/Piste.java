package commerce.catalogue.domaine.modele;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * Author  A. Corbière
 * Version 2.0, 10/01/15
 */

@Entity (name="commerce.catalogue.domaine.modele.Piste")
public class Piste {
	private String refPiste;
	private String titre;
	private String url;

	@Id
	public String getRefPiste() {
		return refPiste;
	}
	public void setRefPiste(String inRefPiste) {
		refPiste = inRefPiste;
	}
	
	@Basic
	public String getTitre() {
		return titre;
	}
	public void setTitre(String inTitre) {
		titre = inTitre;
	}
	
	@Basic
	public String getUrl() {
		return url;
	}
	public void setUrl(String inUrl) {
		url = inUrl;
	}
}
