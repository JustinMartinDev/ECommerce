<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 21/03/2018
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.catalogue.domaine.modele.Livre"%>
<%@ page import="commerce.catalogue.domaine.modele.Musique"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>

<%
    Livre livre = null;
    Musique musique = null;

    out.println(showArticle(getArticleToShow(application), response));
%>
<%!
    private static final String PATH_VIEW = "/src/main/webapp/View";

    private Iterator<Article> getArticleToShow(javax.servlet.ServletContext application) throws Exception {
        CatalogueManager catalogueManager = (CatalogueManager) application.getAttribute("catalogueManager");
        Iterator<Article> listeDesArticles = catalogueManager.getArticles().iterator();
        /*
        if(request.getParameter("type")!=null){
            if(request.getParameter("type") == "price"){
                if(request.getParameter("firsPrice")!= null && request.getParameter("secondPrice")!=null){
                    listeDesArticles = catalogueManager.getArticlesWithPriceBetween(
                            request.getParameter("firsPrice"),
                            request.getParameter("secondPrice")
                    ).iterator();
                }
                else{
                    out.println("<script>toastr[\"erreur\"](\"Erreur argument ! \")</script>");
                }
            }
        }*/

        return listeDesArticles;
    }

    private String fileToString(String filename) throws IOException {
        String currentDir = System.getProperty("user.dir");
        BufferedReader reader = new BufferedReader(new FileReader(currentDir+PATH_VIEW+filename));
        StringBuilder builder = new StringBuilder();
        String line;

      // For every line in the file, append it to the string builder
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        return builder.toString();
    }

    private String showArticle(Iterator<Article> listeDesArticles, javax.servlet.http.HttpServletResponse response) throws IOException {

        Article article;
        Livre livre = null;
        Musique musique = null;
        int compteur = 0;
        int nbElement = 0;
        String htmlContentPage = "";
        while (listeDesArticles.hasNext()) {
            article = (Article) listeDesArticles.next();
            String imgURL;
            if (article.getImage().startsWith("http")) imgURL = article.getImage();
            else imgURL = "./images/" + article.getImage();

            String urlClickImage = response.encodeURL("./controlePanier.jsp?refArticle=" + article.getRefArticle() + "&amp;commande=ajouterLigne");

            String htmlArticle = fileToString("/Template/ArticleCore.html");


            if (nbElement % 3 == 0 || nbElement == 0) {
                htmlArticle = "<div class=\"row\">" + htmlArticle;
            }

            htmlArticle = htmlArticle.replace("%imgURL%", imgURL);
            htmlArticle = htmlArticle.replace("%nomArticle%", article.getTitre());
            htmlArticle = htmlArticle.replace("%urlClickImage%", urlClickImage);
            htmlArticle = htmlArticle.replace("%prixArticle%", article.getPrix() + "");

            if (article instanceof Musique) {
                musique = (Musique) article;
                if (musique.getPistes().size() > 0) {
                    String refArticle = article.getRefArticle();

                    String htmlMusicPlayer = fileToString("/Template/SpeMusic.html");
                    htmlMusicPlayer = htmlMusicPlayer.replace("%refArticle%", article.getRefArticle());
                    htmlArticle += htmlMusicPlayer;
                }
            }

            htmlArticle += "</div></div></div>";

            if (nbElement % 3 == 2 || nbElement==2) {
             htmlArticle += "</div>";
            }
            htmlContentPage += htmlArticle;
            nbElement++;
        }
        return htmlContentPage;
    }
%>