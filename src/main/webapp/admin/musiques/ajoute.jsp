<%@ page pageEncoding="UTF-8"%>
<%@ include file="../enTetePage.html"%>
<%@ page import="commerce.catalogue.domaine.modele.Musique" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%
  Musique musique = new Musique() ;
%>
    <form action="controleMusiques.jsp?commande=ajouter" method="post">
<%
  Field[] articleFields = musique.getClass().getSuperclass().getDeclaredFields();
  ArrayList<Field> list = new ArrayList<Field>(Arrays.asList(articleFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("refArticle")) {
	  list.remove(i);
    }
  }
  articleFields = list.toArray(new Field[articleFields.length-1]);
  for (Field articleField : articleFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
	String value = "" ;
	if(articleField.getType().toString().equals("int"))
      value = "0";
    if(articleField.getType().toString().equals("double"))
      value = "0.0";
    if(articleField.getType().toString().equals("class java.lang.String"))
      value = "";
%>
      <div>
        <label for="_<%=articleField.getName() %>_id"><%=articleLabel %></label>
		<div>
		  <input type="text" size="70" name="<%=articleField.getName() %>" value="<%=value %>"/>
		</div>
	  </div>
<%
  }
  Field[] musiqueFields = musique.getClass().getDeclaredFields();
  list = new ArrayList<Field>(Arrays.asList(musiqueFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("dateDeParution")) {
	  list.remove(i);
    }
	if (list.get(i).getName().equals("pistes")) {
	  list.remove(i);
    }
  }
  musiqueFields = list.toArray(new Field[musiqueFields.length-2]);
  for (Field articleField : musiqueFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
	String value = "" ;
	if(articleField.getType().toString().equals("int"))
      value = "0";
    if(articleField.getType().toString().equals("double"))
      value = "0.0";
    if(articleField.getType().toString().equals("class java.lang.String"))
      value = "";
%>
      <div>
        <label for="_<%=articleField.getName() %>_id"><%=articleLabel %></label>
		<div>
		  <input type="text" size="70" name="<%=articleField.getName() %>" value="<%=value %>"/>
		</div>
	  </div>
<%
  }
%>
      <div>
        <input type="Submit" name="Submit" Value="Submit"/>
	  </div>
	</form>
<%@ include file="../piedDePage.html"%>
