<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Réservation</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome-free-6.7.2-web/css/all.min.css">
</head>
<body>
  <div class="wrapper">
    <!-- Sidebar (identique à votre page home) -->
    <nav class="sidebar">
        <div class="sidebar-header">
          <h3>Client.</h3>
          <button class="toggle-btn"><i class="fa fa-bars"></i></button>
        </div>
        <ul class="sidebar-menu">
          <li><a href="<%= request.getContextPath() %>/home"><i class="fa fa-book"></i> Livres</a></li>
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Abonnement</a></li>
          <li><a href="#"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-client"><i class="fa fa-clock"></i> Prolongement</a></li>
        </ul>
        <ul class="sidebar-footer">
          <li><a href="<%= request.getContextPath() %>/login"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
        </ul>
      </nav>
    <!-- Main content -->
    <div class="main-content">
      <header>
        <h2>Réservation d'exemplaires</h2>
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
            <h3>Exemplaires disponibles</h3>
            <c:choose>
              <c:when test="${empty exemplaires}">
                <div class="no-data-container">
                  <i class="fa fa-info-circle"></i>
                  <p>Aucun exemplaire disponible pour le moment.</p>
                </div>
              </c:when>
              <c:otherwise>
                <table class="book-table">
                  <thead>
                    <tr>
                      <th>Livre</th>
                      <th>Auteur</th>
                      <th>ID Exemplaire</th>
                      <th>Date</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="ex" items="${exemplaires}">
                      <tr>
                        <td>${ex.livre.titre}</td>
                        <td>${ex.livre.auteur}</td>
                        <td>#${ex.idExemplaire}</td>
                        <form method="post" action="${pageContext.request.contextPath}/reservation-client">
                        <td>
                          <input type="date" name="dateReservation">
                        </td>
                        <td>
                          <input type="hidden" name="exemplaireId" value="${ex.idExemplaire}">
                          <button type="submit" class="btn-reserver">
                            <i class="fa fa-bookmark"></i> Réserver
                          </button>
                        </td>
                        </form>
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