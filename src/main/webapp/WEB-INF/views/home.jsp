<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="typeUsers" value="${sessionScope.type}" />
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
        <c:if test="${typeUsers == 'Client'}">
            <h3>Client.</h3>
        </c:if>
        <c:if test="${typeUsers == 'Bibliothecaire'}">
            <h3>Bibliothecaire.</h3>
        </c:if>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <!-- Pour tous : lien Livres -->
        <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-book"></i> Livres</a></li>

        <!-- Si client seulement -->
        <c:if test="${typeUsers == 'Client'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Abonnement</a></li>
          <li><a href="#"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="#"><i class="fa fa-clock"></i> Prolongement</a></li>
        </c:if>

        <!-- Si bibliothécaire -->
        <c:if test="${typeUsers == 'Bibliothecaire'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
          <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
          <li><a href="${pageContext.request.contextPath}/penalite"><i class="fa fa-gavel"> Penalites</i></a></li>
          <li><a href="#"><i class="fa fa-calendar-check"></i> Réservation</a></li>
          <li><a href="#"><i class="fa fa-clock"></i> Prolongement</a></li>
          <li><a href="#"><i class="fa fa-calendar-alt"></i> Calendrier</a></li>
        </c:if>
      </ul>

      <ul class="sidebar-footer">
        <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
      </ul>
    </nav>

    <!-- Main content -->
    <div class="main-content">
      <header>
        <h2>Bibliotheque backoffice</h2>
      </header>

      <section class="content">
        <div class="dashboard-panel">

          <form class="filter-bar" method="get" action="${pageContext.request.contextPath}/home">
            <input type="text" name="titre" placeholder="Titre contient…" value="${filterTitre}" />

            <select name="auteur">
              <option value="">— Auteur —</option>
              <c:forEach var="a" items="${auteurs}">
                <option value="${a}" 
                  <c:if test="${a == filterAuteur}">selected</c:if>>
                  ${a}
                </option>
              </c:forEach>
            </select>

            <select name="ageMin">
              <option value="">— Age min. —</option>
              <c:forEach var="ag" items="${ages}">
                <option value="${ag}" 
                  <c:if test="${ag == filterAge}">selected</c:if>>
                  ${ag}+
                </option>
              </c:forEach>
            </select>

            <button type="submit"><i class="fa fa-search"></i> Rechercher</button>
          </form>

            <h3>Liste des livres</h3>

            <table class="book-table">
              <thead>
                <tr>
                  <th>Titre</th>
                  <th>Auteur</th>
                  <th>Année</th>
                  <th>Âge min.</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="livre" items="${livres}">
                  <tr>
                    <td>${livre.titre}</td>
                    <td>${livre.auteur}</td>
                    <td>${livre.anneePublication}</td>
                    <td>${livre.ageMin}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
        </div>
      </section>

    </div>
  </div>


</body>
</html>
