<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.gestion.Panier"%>
<%@ page import="commerce.gestion.LignePanier"%>
<%@ page import="java.util.Iterator"%>
<%
	Iterator it;
	if (session.getAttribute("panier") == null) {
		response.sendRedirect("./index.jsp");
	} else {
		Panier lePanier = (Panier) session.getAttribute("panier");
		CatalogueManager catalogueManager = (CatalogueManager) application
				.getAttribute("catalogueManager");
		String commande = request.getParameter("commande");
		String refArticle = request.getParameter("refArticle");
		Article unArticle;
		int indice;
		if (commande != null) {
			if (commande.equals("ajouterLigne")) {
				unArticle = new Article();
				unArticle = catalogueManager
						.chercherArticleParRef(refArticle);
				lePanier.ajouterLigne(unArticle);
			} else if (commande.equals("recalculerPanier")) {
				it = lePanier.getLignesPanier().iterator();
				LignePanier uneLignePanier;
				while (it.hasNext()) {
					uneLignePanier = (LignePanier) it.next();
					unArticle = uneLignePanier.getArticle();
					uneLignePanier.setQuantite(Integer.parseInt(request
							.getParameter("cart["
									+ unArticle.getRefArticle()
									+ "][qty]")));
				}
				lePanier.recalculer();
			} else if (commande.equals("supprimerLigne")) {
				lePanier.supprimerLigne(refArticle);
			} else if (commande.equals("viderPanier")) {
				lePanier.viderPanier();
			}
		}
		it = lePanier.getLignesPanier().iterator();
		if (!it.hasNext()) {
			response.sendRedirect(response.encodeURL("affichePanierVide.jsp"));
		} else {
			response.sendRedirect(response.encodeURL("affichePanier.jsp")) ;
		} 
	}
%>