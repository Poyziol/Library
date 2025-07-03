<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String typeUsers = (String) request.getAttribute("typeUsers");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Tableau de bord</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
</head>
<body>
  <div class="wrapper">
    <!-- Sidebar -->
    <nav class="sidebar">
      <div class="sidebar-header">
        <h3>Admin.</h3>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-chart-line"></i> Tableau de bord</a></li>

        <!-- Pour tous : lien Livres -->
        <li><a href="#"><i class="fa fa-book"></i> Livres</a></li>

        <!-- Si client seulement -->
        <c:if test="${typeUsers == 'Client'}">
          <li><a href="#"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="#"><i class="fa fa-clock"></i> Prolongement</a></li>
        </c:if>

        <!-- Si bibliothécaire -->
        <c:if test="${typeUsers == 'Bibliothecaire'}">
          <li><a href="#"><i class="fa fa-hand-holding"></i> Prêt</a></li>
          <li><a href="#"><i class="fa fa-calendar-check"></i> Réservation</a></li>
          <li><a href="#"><i class="fa fa-clock"></i> Prolongement</a></li>
          <li><a href="#"><i class="fa fa-calendar-alt"></i> Calendrier</a></li>
        </c:if>
      </ul>

      <ul class="sidebar-footer">
        <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
      </ul>
    </nav>

    <!-- Main content -->
    <div class="main-content">
      <header>
        <h2>Tableau de bord</h2>
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Accueil</a></li>
            <li class="breadcrumb-item active" aria-current="page">Tableau de bord</li>
          </ol>
        </nav>
      </header>

      <section class="content">
        <!-- Zone grisée de la capture -->
        <div class="dashboard-panel">
          <!-- Ici tes widgets, graphiques, etc. -->
        </div>
      </section>
    </div>
  </div>
</body>
</html>
