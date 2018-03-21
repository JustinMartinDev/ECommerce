<%@ page pageEncoding="UTF-8"%>
<%@ include file="View/enTetePage.html"%>

<%
	if (session.getAttribute("panier")==null) {
		response.sendRedirect("./index.jsp");
	} else {
%>

	<%@ include file="View/headerArticle.html"%>
	<div id="contentArticle">
	</div>
<%@ include file="View/footerArticle.html"%>
<%@ include file="View/piedDePage.html"%>

<% } %>