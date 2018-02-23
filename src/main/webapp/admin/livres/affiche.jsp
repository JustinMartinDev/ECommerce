<%@ page pageEncoding="UTF-8"%>
<%@ include file="../enTetePage.html"%>
<%@ page import="commerce.catalogue.service.CatalogueManager" %>
<%@ page import="commerce.catalogue.domaine.modele.Livre" %>
<%@ page import="commerce.catalogue.domaine.modele.Article" %>
<%@ page import="java.util.Iterator" %>
<%
 if (application.getAttribute("catalogueManager")==null) {
    response.sendRedirect("./index.jsp") ;
  }
  else {
	CatalogueManager catalogueManager = (CatalogueManager)application.getAttribute("catalogueManager") ;
%>
    <section class="entry">
      <table>
<%
    Livre livre = null ;
    Article article = null ; 
    Boolean enteteAffiche = false ;
    Iterator<Article> listeDesLivres = catalogueManager.getArticles().iterator() ;
    while(listeDesLivres.hasNext()) {
	  article = listeDesLivres.next() ; 
	  if (article instanceof Livre) {
        if (!enteteAffiche) {
%>
        <thead>
          <tr>
            <th>R&eacute;f&eacute;rence&nbsp;</th>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Disponibilit&eacute;</th>
            <th>ISBN</th>
			<th>Image</th>
            <th>Nombre de pages</th>
            <th>langue</th>
            <th>Date de parution</th>
            <th>prix</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
<%
          enteteAffiche = true ;
        }
        livre = (Livre)article ;
%>
		<tbody>
          <tr>
            <td><%=livre.getRefArticle() %></td>
            <td><%=livre.getTitre() %></td>
            <td><%=livre.getAuteur() %></td>
            <td><%=livre.getDisponibilite() %></td>
            <td><%=livre.getISBN() %></td>
<%
        if (livre.getImage() != null) { 
%>
		    <td><%=livre.getImage() %></td>
<%
        }
        else { 
%>
		    <td>Inexistante</td>
<%
        }
%>
            <td><%=livre.getNbPages() %></td>
            <td><%=livre.getLangue() %></td>
            <td><%=livre.getDateDeParution() %></td>
            <td><%=livre.getPrix() %></td>
            <td><a href="controleLivres.jsp?refArticle=<%=livre.getRefArticle() %>&amp;commande=AModifier">Modifier</a></td>
            <td><a href="javascript:deleteObject('refArticle','<%=livre.getRefArticle() %>')">Supprimer</a></td>
            <script type="text/javascript">
// <![CDATA[
	function deleteObject(id,idval)
	{
		if(confirm("Etes-vous sur de vouloir supprimer cet article ?"))
		{
			location.href = "controleLivres.jsp?commande=supprimer&" + id + "=" + idval;
		}
	}
// ]]>
            </script>
          </tr>
        </tbody>
<%
      }
    } 
%>
      </table>				
      <table>
        <tr>
          <td><a href="ajoute.jsp">Ajouter un nouveau livre</a></td>
        </tr>
      </table>
     </section>
<%
  }
%>
<%@ include file="../piedDePage.html"%>
