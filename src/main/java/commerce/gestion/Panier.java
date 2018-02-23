/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * @author  A. Corbière
 * @version 1.1, 11/02/14
 */

package commerce.gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import commerce.catalogue.domaine.modele.Article;

public class Panier {
	private double total;
	private ArrayList<LignePanier> lignesPanier;

	public Panier() {
		lignesPanier = new ArrayList<LignePanier>();
	}
	public void setTotal() {
		recalculer();
	}
	public double getTotal() {
		recalculer();
		return total;
	}
	public List<LignePanier> getLignesPanier() {
		return lignesPanier;
	}
	public void recalculer() {
		total=0.0;
		Iterator<LignePanier> i = lignesPanier.iterator() ;
		while (i.hasNext()) {
			LignePanier ligne=(LignePanier)i.next() ;
			ligne.recalculer();
			total += ligne.getPrixTotal();
		}
	}
	public void ajouterLigne(Article inArticle) {
		LignePanier lp = chercherLignePanier(inArticle) ;
		if (lp == null) {
			lp = new LignePanier() ;
			lp.setArticle(inArticle) ;
			lp.setPrixUnitaire(inArticle.getPrix()) ;
			lp.setQuantite(1) ;
			lp.recalculer() ;
			lignesPanier.add(lp) ;
		}
		else {
			lp.setQuantite(lp.getQuantite() + 1) ;
			lp.recalculer() ;
		}
	}
	public LignePanier chercherLignePanier(Article inArticle) {
		Iterator<LignePanier> it = this.lignesPanier.iterator() ;
		LignePanier lp ;
		LignePanier retour = null ;
		while (it.hasNext() && retour == null) {
			lp = it.next();
			if (lp.getArticle().equals(inArticle))
				retour = lp ;
		}
		return retour ;
	}
	public void supprimerLigne(String inRefArticle) {
		Iterator<LignePanier> it = this.lignesPanier.iterator() ;
		LignePanier lp ;
		LignePanier retour = null ;
		while (it.hasNext() && retour == null) {
			lp = it.next();
			if (lp.getArticle().getRefArticle().equals(inRefArticle))
				retour = lp ;
		}
		if (retour != null) {
			this.lignesPanier.remove(retour) ;
			recalculer() ;
		}
	}
	public void viderPanier() {
		lignesPanier.clear() ;
	}
	public boolean equals(Object o) {
		boolean retour = false ;
		if (!(o instanceof Panier))
			retour = false ;
		else {
			Panier inPanier = (Panier)o ;
			if (this.getTotal() == inPanier.getTotal()
				&& (this.getLignesPanier() == inPanier.getLignesPanier()))
				retour = true ;
			else
				retour = false ;
		}
		return retour ;
	}
}
