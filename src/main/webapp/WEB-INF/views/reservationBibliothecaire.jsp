<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des Réservations</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
</head>
<body>
  <div class="wrapper">
    <!-- Sidebar (identique à votre page home) -->
    <nav class="sidebar">
        <div class="sidebar-header">
          <h3>Bibliothécaire.</h3>
          <button class="toggle-btn"><i class="fa fa-bars"></i></button>
        </div>
        <ul class="sidebar-menu">
          <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-book"></i> Livres</a></li>
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
          <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
          <li><a href="${pageContext.request.contextPath}/penalite"><i class="fa fa-gavel"></i> Penalites</a></li>
          <li><a href="#"><i class="fa fa-calendar-check"></i> Réservation</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-biblio"><i class="fa fa-clock"></i> Prolongement</a></li>
          <li><a href="#"><i class="fa fa-calendar-alt"></i> Calendrier</a></li>
        </ul>
        <ul class="sidebar-footer">
          <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
        </ul>
      </nav>
    <!-- Main content -->
    <div class="main-content">
      <header>
        <h2>Gestion des Réservations</h2>
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
          
          <div class="table-container">
            <h3>Réservations en attente</h3>
            <c:choose>
              <c:when test="${empty reservations}">
                <div class="no-data-container">
                  <i class="fa fa-info-circle"></i>
                  <p>Aucune réservation en attente.</p>
                </div>
              </c:when>
              <c:otherwise>
                <table class="book-table">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Adhérent</th>
                      <th>Livre</th>
                      <th>ID Exemplaire</th>
                      <th>Date Réservation</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="dto" items="${reservations}">
                      <tr>
                        <td>${dto.reservation.idReservation}</td>
                        <td>${dto.reservation.adherant.nom} ${dto.reservation.adherant.prenom}</td>

                        <!-- Accès via le DTO -->
                        <td>${dto.exemplaire.livre.titre}</td>
                        <td>#${dto.exemplaire.idExemplaire}</td>

                        <td>${dto.reservation.dateReservation}</td>
                        <td>
                          <form method="post" action="${pageContext.request.contextPath}/reservation-biblio">
                            <input type="hidden" name="reservationId" value="${dto.reservation.idReservation}">
                            <input type="hidden" name="adherantId" value="${dto.reservation.adherant.idAdherant}">
                            <button type="submit" class="btn-confirmer">
                              <i class="fa fa-check"></i> Confirmer
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