<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Livre"%>
<%@ page import="java.util.Iterator"%>
<%
	CatalogueManager catalogueManager = (CatalogueManager)application.getAttribute("catalogueManager") ;
    Livre livre ;
    livre = new Livre() ;
    int refArticle = 0;
    if(request.getParameter("commande")!=null) {
      if (request.getParameter("commande").equals("AModifier")) {
        livre = (Livre)catalogueManager.chercherArticleParRef(request.getParameter("refArticle")) ;
        session.setAttribute("livre", livre) ;
        response.sendRedirect(response.encodeURL("modifie.jsp")) ;
      }
      if (request.getParameter("commande").equals("ajouter")
       || request.getParameter("commande").equals("modifier")) {
        livre.setRefArticle(request.getParameter("refArticle")) ;
        livre.setTitre(request.getParameter("titre")) ;
        livre.setAuteur(request.getParameter("auteur")) ;  
        livre.setISBN(request.getParameter("ISBN")) ; 
        livre.setImage(request.getParameter("image")) ; 
        livre.setNbPages(Integer.parseInt(request.getParameter("nbPages"))) ; 
        livre.setLangue(request.getParameter("langue")) ;
        livre.setDateDeParution(request.getParameter("dateDeParution")) ; 
        livre.setPrix(request.getParameter("prix")) ; 
        livre.setDisponibilite(request.getParameter("disponibilite")) ; 
        catalogueManager.soumettreArticle(livre) ;
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