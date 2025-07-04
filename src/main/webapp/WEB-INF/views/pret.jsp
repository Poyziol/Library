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
        <c:choose>
          <c:when test="${typeUsers == 'Client'}"><h3>Client.</h3></c:when>
          <c:when test="${typeUsers == 'Bibliothecaire'}"><h3>Bibliothécaire.</h3></c:when>
        </c:choose>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-book"></i> Livres</a></li>
        <c:if test="${typeUsers == 'Client'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Abonnement</a></li>
          <li><a href="#"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="#"><i class="fa fa-clock"></i> Prolongement</a></li>
        </c:if>
        <c:if test="${typeUsers == 'Bibliothecaire'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
          <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
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
      <header><h2>Bibliothèque backoffice</h2></header>
      <section class="content">
        <div class="dashboard-panel">
          <!-- Filtre livres existant... -->
          <form class="filter-bar" method="get" action="${pageContext.request.contextPath}/home">
            <!-- vos champs titre/auteur/ageMin ici -->
            ...
          </form>

          <h3>Liste des livres</h3>
          <table class="book-table">
            <!-- votre table livres ici -->
          </table>

          <!-- **Formulaire de création de prêt** (visible pour le bibliothécaire) -->
          <c:if test="${typeUsers == 'Bibliothecaire'}">
            <h3>Créer un nouveau prêt</h3>
            <form method="post" action="${pageContext.request.contextPath}/pret" class="filter-bar">
              <label for="adherant">Adhérent</label>
              <select id="adherant" name="idAdherant" required>
                <option value="">— Choisir —</option>
                <c:forEach var="a" items="${adherants}">
                  <option value="${a.idAdherant}">${a.nom} ${a.prenom}</option>
                </c:forEach>
              </select>

              <label for="exemplaire">Exemplaire</label>
              <select id="exemplaire" name="idExemplaire" required>
                <option value="">— Choisir —</option>
                <c:forEach var="e" items="${exemplairesDisponibles}">
                  <option value="${e.idExemplaire}">[#${e.idExemplaire}] Livre: ${e.livre.titre}</option>
                </c:forEach>
              </select>

              <label for="datePret">Date du prêt</label>
              <input type="date" id="datePret" name="datePret" value="${today}" required/>

              <label for="dateRetourEstime">Date retour estimée</label>
              <input type="date" id="dateRetourEstime" name="dateRetourEstime" required/>

              <button type="submit" class="btn-login"><i class="fa fa-hand-holding"></i> Enregistrer prêt</button>
            </form>

            <!-- **Liste des prêts** -->
            <h3>Prêts enregistrés</h3>
            <table class="book-table">
              <thead>
                <tr>
                  <th>ID prêt</th>
                  <th>Adhérent</th>
                  <th>Exemplaire</th>
                  <th>Date prêt</th>
                  <th>Date retour estimée</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <c:choose>
                  <c:when test="${not empty prets}">
                    <c:forEach var="p" items="${prets}">
                      <tr>
                        <td>${p.idPret}</td>
                        <td>${p.adherant.nom} ${p.adherant.prenom}</td>
                        <td>#${p.exemplaire.idExemplaire} (${p.exemplaire.livre.titre})</td>
                        <td>${p.datePret}</td>
                        <td>${p.dateRetourEstime}</td>
                        <td>
                          <form method="post" action="${pageContext.request.contextPath}/pret" style="display:inline">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="idPret" value="${p.idPret}"/>
                            <button type="submit" title="Retirer"><i class="fa fa-trash"></i></button>
                          </form>
                        </td>
                      </tr>
                    </c:forEach>
                  </c:when>
                  <c:otherwise>
                    <tr>
                      <td colspan="6" style="text-align:center; padding:1em;">
                        Aucun prêt enregistré.
                      </td>
                    </tr>
                  </c:otherwise>
                </c:choose>
              </tbody>
            </table>
          </c:if>

        </div>
      </section>
    </div>
  </div>
</body>
</html>
