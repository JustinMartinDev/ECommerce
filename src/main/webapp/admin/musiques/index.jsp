<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager" %>
<%
  if (application.getAttribute("catalogueManager")==null) {
    CatalogueManager catalogueManager = new CatalogueManager() ;
    application.setAttribute("catalogueManager", catalogueManager) ;
  }
  response.sendRedirect(response.encodeURL("affiche.jsp")) ;
%>