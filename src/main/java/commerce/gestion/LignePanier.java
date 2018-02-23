/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * @author  A. Corbière
 * @version 1.1, 03/02/13
 */

package commerce.gestion;

import commerce.catalogue.domaine.modele.Article;

public class LignePanier {
	private Article article;
	private double prixUnitaire;
	private double prixTotal;
	private int quantite;
	public LignePanier() {}
	public void setArticle(Article inArticle) {
		article = inArticle;
	}
	public Article getArticle() {
		return article;
	}
	public void setPrixUnitaire(double inPrixUnitaire) {
		prixUnitaire = inPrixUnitaire;
	}
	public double getPrixUnitaire() {
		prixUnitaire = article.getPrix() ;
		return prixUnitaire;
	}
	public double getPrixTotal() {
		return (prixTotal);
	}
	public void setQuantite(int inQuantite) {
		quantite = inQuantite;
	}
	public int getQuantite() {
		return quantite ;
	}
	public void recalculer() {
		prixTotal = prixUnitaire * quantite;
	}
	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof LignePanier))
			retour = false ;
		else {
			LignePanier inLignePanier = (LignePanier)o ;
			if (this.getArticle().equals(inLignePanier.getArticle())
				&& (this.getPrixUnitaire() == inLignePanier.getPrixUnitaire())
				&& (this.getPrixTotal() == inLignePanier.getPrixTotal())		
				&& (this.getQuantite() == inLignePanier.getQuantite()))
				retour = true ;
			else
				retour = false ;
		}
		return retour ;
	}
}
