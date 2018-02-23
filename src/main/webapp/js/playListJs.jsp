<%@ page language="java"
	contentType="application/javascript; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.catalogue.domaine.modele.Livre"%>
<%@ page import="commerce.catalogue.domaine.modele.Musique"%>
<%@ page import="commerce.catalogue.domaine.modele.Piste"%>
<%@ page import="java.util.Iterator"%>
<%
	if (application.getAttribute("catalogueManager") == null) {
		response.sendRedirect("./index.jsp");
	} else {
		CatalogueManager catalogueManager = (CatalogueManager) application
				.getAttribute("catalogueManager");
		Iterator<Article> listeDesArticles = catalogueManager
				.getArticles().iterator();
		Livre livre = null;
		Musique musique = null;
		Article article;
%>
window.addEventListener("load", myFunction, false);
function myFunction(event) {
<%
		while (listeDesArticles.hasNext()) {
			article = (Article) listeDesArticles.next();
			if (article instanceof Musique) {
%>
var myPlaylist_<%=article.getRefArticle()%> = new jPlayerPlaylist({
cssSelectorAncestor : "#jp_container_<%=article.getRefArticle()%>",
jPlayer: "#jquery_jplayer_<%=article.getRefArticle()%>"}, [<%
	musique = (Musique) article;
				if (musique.getPistes().size() > 0) {
					Iterator<Piste> itPistes = musique.getPistes()
							.iterator();
					Piste unePiste;
					while (itPistes.hasNext()) {
						unePiste = itPistes.next();
%>
{ title:"<%=unePiste.getTitre().replace("\"", "\\\"" )%>", mp3:"<%=unePiste.getUrl()%>" },
<%
					}
				}
%>

], { swfPath : "/js/jplayer-2.9.2/jplayer", supplied : "mp3", wmode:
"window", useStateClassSkin: true, autoBlur: false, smoothPlayBar: true,
keyEnabled: true });
<%

			}
		}
%>
var jp_playlist_tab = document.getElementsByClassName("jp-playlist") ;
 for (i = 0; i < jp_playlist_tab.length; i++) {
    jp_playlist_tab[i].style.display = "none";
} 
}
<%
	}
%>