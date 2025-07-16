<%@ page contentType="text/html;charset=UTF-8" import="models.Abonnement" %>
<%
    Abonnement abo = (Abonnement) request.getAttribute("abonnement");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Mon abonnement</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/home.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome-free-6.7.2-web/css/all.min.css">
</head>
<body>
  <div class="wrapper">

    <!-- === Sidebar === -->
    <nav class="sidebar">
      <div class="sidebar-header">
        <h3>Client.</h3>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <li><a href="<%= request.getContextPath() %>/home"><i class="fa fa-book"></i> Livres</a></li>
        <li><a href="#"><i class="fa fa-id-card"></i> Abonnement</a></li>
        <li><a href="${pageContext.request.contextPath}/reservation-client"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
        <li><a href="${pageContext.request.contextPath}/prolongement-client"><i class="fa fa-clock"></i> Prolongement</a></li>
      </ul>
      <ul class="sidebar-footer">
        <li><a href="<%= request.getContextPath() %>/login"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
      </ul>
    </nav>
    <!-- === /Sidebar === -->

    <div class="main-content">
      <header><h2>Mon abonnement</h2></header>
      <section class="content">
        <div class="dashboard-panel">

          <% if (abo != null) { %>
            <p>Bonjour <strong><%= abo.getAdherant().getNom() %> <%= abo.getAdherant().getPrenom() %></strong>,</p>
            <p>Votre abonnement est valide jusqu au <strong><%= abo.getDateFin() %></strong>.</p>
          <% } else { %>
            <p>Vous n êtes pas encore abonné·e. 
          <% } %>
        </div>
      </section>
    </div>

  </div>
</body>
</html>
