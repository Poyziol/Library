<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="typeUsers" value="${sessionScope.type}" />
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des prêts</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
</head>
<body>
  <div class="wrapper">
    <!-- Sidebar -->
    <nav class="sidebar">
      <div class="sidebar-header">
        <c:choose>
          <c:when test="${typeUsers == 'Client'}"><h3>Client</h3></c:when>
          <c:when test="${typeUsers == 'Bibliothecaire'}"><h3>Bibliothécaire</h3></c:when>
        </c:choose>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-book"></i> Livres</a></li>
        <c:if test="${typeUsers == 'Client'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Abonnement</a></li>
          <li><a href="${pageContext.request.contextPath}/reservation-client"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-client"><i class="fa fa-clock"></i> Prolongement</a></li>
        </c:if>
        <c:if test="${typeUsers == 'Bibliothecaire'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
          <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
          <li><a href="${pageContext.request.contextPath}/penalite"><i class="fa fa-gavel"></i>Penalites</a></li>
          <li><a href="${pageContext.request.contextPath}/reservation-biblio"><i class="fa fa-calendar-check"></i> Réservation</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-biblio"><i class="fa fa-clock"></i> Prolongement</a></li>
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
        <h2>
          <c:choose>
            <c:when test="${typeUsers == 'Client'}">Espace client</c:when>
            <c:when test="${typeUsers == 'Bibliothecaire'}">Gestion des prêts</c:when>
          </c:choose>
        </h2>
      </header>
      
      <section class="content">
        <div class="dashboard-panel">
          <!-- Formulaire de création de prêt (visible pour le bibliothécaire) -->
          <c:if test="${typeUsers == 'Bibliothecaire'}">
            <div class="form-container">
              <h3>Créer un nouveau prêt</h3>
              <form method="post" action="${pageContext.request.contextPath}/pret" class="filter-bar">
                <div class="form-group">
                  <label for="adherant">Adhérent</label>
                  <select id="adherant" name="idAdherant" required>
                    <option value="">— Choisir —</option>
                    <c:forEach var="a" items="${adherants}">
                      <option value="${a.idAdherant}" 
                          ${param.idAdherant == a.idAdherant ? 'selected' : ''}>
                        ${a.nom} ${a.prenom}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <div class="form-group">
                  <label for="exemplaire">Exemplaire</label>
                  <select id="exemplaire" name="idExemplaire" required>
                    <option value="">— Choisir —</option>
                    <c:forEach var="e" items="${exemplairesDisponibles}">
                      <option value="${e.idExemplaire}" 
                          ${param.idExemplaire == e.idExemplaire ? 'selected' : ''}>
                        [#${e.idExemplaire}] ${e.livre.titre}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <div class="form-group">
                  <label for="datePret">Date du prêt</label>
                  <input type="date" id="datePret" name="datePret" 
                         value="${not empty param.datePret ? param.datePret : today}" required/>
                </div>

                <div class="form-group">
                  <label for="dateRetourEstime">Date retour estimée</label>
                  <input type="date" id="dateRetourEstime" name="dateRetourEstime" 
                         value="${not empty param.dateRetourEstime ? param.dateRetourEstime : ''}" required/>
                </div>

                <button type="submit" class="btn-login">
                  <i class="fa fa-hand-holding"></i> Enregistrer prêt
                </button>
              </form>
            </div>

            <c:if test="${not empty sessionScope.errorMessage}">
              <div class="alert alert-danger">
                ${sessionScope.errorMessage}
                <c:if test="${sessionScope.errorMessage.contains('pénalités')}">
                  <a href="${pageContext.request.contextPath}/penalite?idAdherant=${param.idAdherant}" 
                     class="btn btn-warning btn-sm">
                     Gérer les pénalités
                  </a>
                </c:if>
              </div>
              <c:remove var="errorMessage" scope="session"/>
            </c:if>

            <!-- Liste des prêts -->
            <div class="table-container">
              <h3>Prêts enregistrés</h3>
              <c:choose>
                <c:when test="${empty prets}">
                  <p class="no-data">Aucun prêt enregistré.</p>
                </c:when>
                <c:otherwise>
                  <table class="book-table">
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Adhérent</th>
                        <th>Exemplaire</th>
                        <th>Date prêt</th>
                        <th>Retour estimé</th>
                        <th>Actions: Rendre livre</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="p" items="${prets}">
                        <tr>
                          <td>${p.idPret}</td>
                          <td>${p.adherant.nom} ${p.adherant.prenom}</td>
                          <td>#${p.exemplaire.idExemplaire} (${p.exemplaire.livre.titre})</td>
                          <td>${p.datePret}</td>
                          <td>${p.dateRetourEstime}</td>
                          <td>
                            <form method="post" action="${pageContext.request.contextPath}/pret" class="action-form">
                              <input type="hidden" name="action" value="delete"/>
                              <input type="hidden" name="idPret" value="${p.idPret}"/>
                              <button type="submit" class="btn-icon" title="Supprimer">
                                <i class="fa fa-undo"></i>
                              </button>
                            </form>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </c:otherwise>
              </c:choose>
            </div>

            <!-- Nouvelle section : Historique des livres rendus -->
          <c:if test="${typeUsers == 'Bibliothecaire'}">
            <div class="table-container" style="margin-top: 40px;">
              <h3>Historique des livres rendus</h3>
              
              <c:choose>
                <c:when test="${empty historique}">
                  <div class="no-data-container">
                    <i class="fa fa-info-circle"></i>
                    <p>Aucun livre rendu dans la période sélectionnée.</p>
                  </div>
                </c:when>
                <c:otherwise>
                  <table class="book-table">
                    <thead>
                      <tr>
                        <th>Date retour</th>
                        <th>Adhérent</th>
                        <th>Livre</th>
                        <th>Exemplaire</th>
                        <th>Durée du prêt</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="h" items="${historique}">
                        <tr>
                          <td>${h.dateRetourReel}</td>
                          <td>${h.adherant.nom} ${h.adherant.prenom}</td>
                          <td>${h.exemplaire.livre.titre}</td>
                          <td>#${h.exemplaire.idExemplaire}</td>
                          <td>${h.dureePret} jours</td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </c:otherwise>
              </c:choose>
            </div>
          </c:if>
          </c:if>
        </div>
      </section>
    </div>
  </div>
</body>
</html>