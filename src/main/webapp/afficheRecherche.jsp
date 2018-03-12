<%@ page pageEncoding="UTF-8"%>
<%@ include file="enTetePage.html"%>
<script type="text/javascript" src="./js/playListJs.jsp"></script>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.catalogue.domaine.modele.Livre"%>
<%@ page import="commerce.catalogue.domaine.modele.Musique"%>
<%@ page import="commerce.catalogue.domaine.modele.Piste"%>
<%@ page import="java.util.Iterator"%>
<%
	if (session.getAttribute("panier")==null) {
		response.sendRedirect("./index.jsp");
	} else {
%>

	<%@ include file="headerArticle.html"%>

<%
		CatalogueManager catalogueManager = (CatalogueManager) application.getAttribute("catalogueManager");
			Iterator<Article> listeDesArticles = catalogueManager.getArticles().iterator();
			Livre livre = null;
			Musique musique = null;
			Article article;
			int compteur = 0;
			while (listeDesArticles.hasNext()) {
				article = (Article) listeDesArticles.next();
				if(compteur==0 || compteur%3==0) {
		%>
					<div class="row">
			  <%}
			  	String imgURL;
				if(article.getImage().startsWith("http")){
				    imgURL = article.getImage();
				} else {
				    imgURL = "./images/"+article.getImage();
				}
				%>

				<!--Second row-->
					<!--First columnn-->
				<div class="col-lg-4">
					<!--Card-->
					<div class="card mb-r wow fadeIn" data-wow-delay="0.2s">

						<!--Card image-->
						<img class="img-fluid"
							 src="<%=imgURL %>"
							 alt="<%=article.getTitre()%>"
							 href="<%=response.encodeURL("./controlePanier.jsp?refArticle=" + article.getRefArticle()+ "&amp;commande=ajouterLigne")%>">

						<!--Card content-->
						<div class="card-body">
							<!--Title-->
							<h5 class="font-bold">
								<strong><%=article.getTitre()%></strong>
							</h5>
							<hr>
							<h4>
								<strong><%=article.getPrix()%>€</strong>
							</h4>
							<!--Text-->
							<p class="card-text mt-4">Some quick example text to build on the card title.
							</p>

							<a class="btn btn-info btn-sm"
							   href="<%=response.encodeURL("./controlePanier.jsp?refArticle="+ article.getRefArticle() + "&amp;commande=ajouterLigne")%>">Mettre dans le panier</a>

						<%
						if (article instanceof Musique) {
							musique = (Musique) article;
							if (musique.getPistes().size() > 0) {
						%>
								<div id="jquery_jplayer_<%=article.getRefArticle()%>" class="jp-jplayer"></div>
								<div id="jp_container_<%=article.getRefArticle()%>" class="jp-audio" role="application">
									<div class="jp-type-playlist">
										<div class="jp-gui jp-interface">
											<div class="jp-controls-holder">
												<div class="jp-controls">
													<button class="jp-previous" role="button" tabindex="0">previous</button>
													<button class="jp-play" role="button" tabindex="0">play</button>
													<button class="jp-stop" role="button" tabindex="0">stop</button>
													<button class="jp-next" role="button" tabindex="0">next</button>
												</div>
											</div>
										</div>
									<div class="jp-playlist">
										<ul>
											<li>&nbsp;</li>
										</ul>
									</div>
									<div class="jp-no-solution">
										<span>Update Required</span> To play the media you will need to
										either update your browser to a recent version or update your
										<a href="http://get.adobe.com/flashplayer/" target="_blank">Flash
										plugin</a>.
									</div>
								</div>
							</div>
					<%
						}
					}
					%>
					</div>
					<!--/.Card-->
				</div>
			</div>
		<% if (compteur==0 || (compteur%3)==0)
			out.println("</div>");

			compteur++;
		}
	}
%>
<%@ include file="footerArticle.html"%>
<%@ include file="piedDePage.html"%>