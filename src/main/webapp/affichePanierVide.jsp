<%@ page pageEncoding="UTF-8"%>
<%@ include file="enTetePage.html"%>
<nav id="navigation" class="col-full" role="navigation">
	<ul id="main-nav" class="nav fl">
		<li id="menu-item-290"
			class="menu-item menu-item-type-custom menu-item-object-custom">
			<a href="<%=response.encodeURL("./afficheRecherche.jsp")%>">Rechercher
				un article</a>
		</li>
		<li id="menu-item-290"
			class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item">
			<a href="<%=response.encodeURL("./controlePanier.jsp")%>">Panier</a>
		</li>
	</ul>
</nav>
<div id="content" class="col-full">
	<div id="main-sidebar-container">
		<header>
			<h1 class="title entry-title">Panier</h1>
		</header>
		<div class="woocommerce">
			<section class="entry">
				<p class="cart-empty">Votre panier est vide.</p>
			</section>
		</div>
	</div>
</div>
<%@ include file='piedDePage.html'%>

