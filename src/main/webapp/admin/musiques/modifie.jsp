<%@ page pageEncoding="UTF-8"%>
<%@ include file="../enTetePage.html"%>
<%@ page import="commerce.catalogue.domaine.modele.Musique" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%
Musique  musique = (Musique)session.getAttribute("musique") ;
if (musique == null)
	response.sendRedirect(response.encodeURL("index.jsp")) ;
 else { 
%>
    <form action="controleMusiques.jsp?commande=modifier" method="post">
	  <input type="hidden" name="refArticle" value="<%=musique.getRefArticle() %>"/>
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
	Class noparams[] = {};
	Method method = musique.getClass().getSuperclass().getDeclaredMethod("get" + articleLabel,noparams) ;
%>
      <div>
        <label for="_<%=articleField.getName() %>_id"><%=articleLabel %></label>
		<div>
		  <input type="text" size="70" name="<%=articleField.getName() %>" value="<% if(method.invoke(musique, null)!=null) out.print(method.invoke(musique, null)); else out.print(""); %>"/>
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
	Class noparams[] = {};
	Method method = musique.getClass().getDeclaredMethod("get" + articleLabel,noparams) ;
%>
      <div>
        <label for="_<%=articleField.getName() %>_id"><%=articleLabel %></label>
		<div>
		  <input type="text" size="70" name="<%=articleField.getName() %>" value="<% if(method.invoke(musique, null)!=null) out.print(method.invoke(musique, null)); else out.print(""); %>"/>
		</div>
	  </div>
<%
  }
%>
      <div>
        <input type="Submit" name="Submit" Value="Submit"/>
	  </div>
	</form>
<%
}
%>
<%@ include file="../piedDePage.html"%>
