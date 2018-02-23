<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager" %>
<%@ page import="commerce.catalogue.domaine.modele.Musique" %>
<%@ page import="java.util.Iterator" %>
<%
	CatalogueManager catalogueManager = (CatalogueManager)application.getAttribute("catalogueManager") ;
    Musique musique ;
    musique = new Musique() ;
    int refArticle = 0;
    if(request.getParameter("commande")!=null) {
      if (request.getParameter("commande").equals("AModifier")) {
        musique = (Musique)catalogueManager.chercherArticleParRef(request.getParameter("refArticle")) ;
        session.setAttribute("musique", musique) ;
        response.sendRedirect(response.encodeURL("modifie.jsp")) ;
      }
      if (request.getParameter("commande").equals("ajouter")
       || request.getParameter("commande").equals("modifier")) {
        musique.setRefArticle(request.getParameter("refArticle")) ;
        musique.setTitre(request.getParameter("titre")) ;
        musique.setEAN(request.getParameter("EAN")) ; 
        musique.setImage(request.getParameter("image")) ; 
        musique.setPrix(request.getParameter("prix")) ; 
        musique.setDisponibilite(request.getParameter("disponibilite")) ; 
        catalogueManager.soumettreArticle(musique) ;
	    response.sendRedirect(response.encodeURL("affiche.jsp")) ;
      }
      if (request.getParameter("commande").equals("supprimer")) {
        catalogueManager.supprimerArticleParRef(request.getParameter("refArticle")) ;
	    response.sendRedirect(response.encodeURL("affiche.jsp")) ;
      }
    }
    else
      response.sendRedirect(response.encodeURL("affiche.jsp")) ;
%>