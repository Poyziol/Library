<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="typeUsers" value="${sessionScope.type}" />
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des Pénalités</title>
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
          <li><a href="#"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="#"><i class="fa fa-clock"></i> Prolongement</a></li>
        </c:if>
        <c:if test="${typeUsers == 'Bibliothecaire'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Abonnements</a></li>
          <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêts</a></li>
          <li><a href="${pageContext.request.contextPath}/penalite"><i class="fa fa-gavel"></i> Pénalités</a></li>
          <li><a href="#"><i class="fa fa-calendar-check"></i> Réservations</a></li>
          <li><a href="#"><i class="fa fa-clock"></i> Prolongements</a></li>
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
        <h2>Gestion des Pénalités</h2>
      </header>
      
      <section class="content">
        <div class="dashboard-panel">
          <!-- Messages d'alerte -->
          <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success">
              ${sessionScope.successMessage}
              <c:remove var="successMessage" scope="session"/>
            </div>
          </c:if>
          
          <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">
              ${sessionScope.errorMessage}
              <c:remove var="errorMessage" scope="session"/>
            </div>
          </c:if>
          
          <!-- Bouton Nouvelle Pénalité -->
          <div class="form-container" style="margin-bottom: 20px;">
            <a href="${pageContext.request.contextPath}/penalite?action=new" class="btn-login">
              <i class="fa fa-plus"></i> Nouvelle Pénalité
            </a>
          </div>
          
          <!-- Liste des pénalités -->
          <div class="table-container">
            <c:choose>
              <c:when test="${empty penalites}">
                <p class="no-data">Aucune pénalité enregistrée.</p>
              </c:when>
              <c:otherwise>
                <table class="book-table">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Adhérent</th>
                      <th>Motif</th>
                      <th>Date début</th>
                      <th>Durée (jours)</th>
                      <th>Réglée</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="p" items="${penalites}">
                      <tr>
                        <td>${p.idPenalite}</td>
                        <td>${p.adherant.nom} ${p.adherant.prenom}</td>
                        <td>${p.motif}</td>
                        <td>${p.dateDebutPenalite}</td>
                        <td>${p.duree}</td>
                        <td>
                          <form method="post" action="${pageContext.request.contextPath}/penalite" style="display:inline">
                            <input type="hidden" name="action" value="toggle"/>
                            <input type="hidden" name="id" value="${p.idPenalite}"/>
                            <button type="submit" class="btn-status ${p.estReglee ? 'reglee' : 'non-reglee'}">
                              ${p.estReglee ? 'Oui' : 'Non'}
                            </button>
                          </form>
                        </td>
                        <td>
                          <a href="${pageContext.request.contextPath}/penalite?action=edit&id=${p.idPenalite}" 
                             class="btn-icon" title="Modifier">
                            <i class="fa fa-edit"></i>
                          </a>
                          <form method="post" action="${pageContext.request.contextPath}/penalite" style="display:inline">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="id" value="${p.idPenalite}"/>
                            <button type="submit" class="btn-icon" title="Supprimer" 
                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette pénalité?')">
                              <i class="fa fa-trash"></i>
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
        </div>
      </section>
    </div>
  </div>
</body>
</html>