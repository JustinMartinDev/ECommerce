<%@ page pageEncoding="UTF-8"%>
<%@ include file="../enTetePage.html"%>
<%@ page import="commerce.catalogue.service.CatalogueManager" %>
<%@ page import="commerce.catalogue.domaine.modele.Musique" %>
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
    Musique musique = null ;
    Article article = null ; 
    Boolean enteteAffiche = false ;
    Iterator<Article> listeDesLivres = catalogueManager.getArticles().iterator() ;
    while(listeDesLivres.hasNext()) {
	  article = listeDesLivres.next() ; 
	  if (article instanceof Musique) {
        if (!enteteAffiche) {
%>
        <thead>
          <tr>
            <th>R&eacute;f&eacute;rence&nbsp;</th>
            <th>Titre</th>
            <th>Disponibilit&eacute;</th>
            <th>AEN</th>
			<th>Image</th>
            <th>prix</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
<%
          enteteAffiche = true ;
        }
        musique = (Musique)article ;
%>

          <tr>
            <td><%=musique.getRefArticle() %></td>
            <td><%=musique.getTitre() %></td>
            <td><%=musique.getDisponibilite() %></td>
            <td><%=musique.getEAN() %></td>
<%
        if (musique.getImage() != null) { 
%>
		    <td><%=musique.getImage() %></td>
<%
        }
        else { 
%>
		    <td>Inexistante</td>
<%
        }
%>
            <td class="product-price"><%=musique.getPrix() %></td>
            <td><a href="controleMusiques.jsp?refArticle=<%=musique.getRefArticle() %>&amp;commande=AModifier">Modifier</a></td>
            <td><a id="<%=musique.getRefArticle() %>" href="">Supprimer</a></td>
            <script type="text/javascript">
// <![CDATA[
    document.getElementById("<%=musique.getRefArticle() %>").addEventListener("click", function(event){
		event.preventDefault();
		if(confirm("Etes-vous sur de vouloir supprimer cet article ?"))
		{
			location.href = "controleMusiques.jsp?commande=supprimer&refArticle=<%=musique.getRefArticle() %>" ;
		}
	}) ;
	// preventdefault
// ]]>
            </script>
          </tr>
        </tbody>
<%
      }
    } 
%>
      </table>
      <table class="wp-list-table">
        <tr>
          <td><a href="ajoute.jsp">Ajouter un nouvel album musical</a></td>
        </tr>
      </table>
     </section>
<%
  }
%>
<%@ include file="../piedDePage.html"%>
