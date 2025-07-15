<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<c:set var="typeUsers" value="${sessionScope.type}" />
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Demande de prolongement</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome-free-6.7.2-web/css/all.min.css">
  <style>
    .btn {
      padding: 8px 12px;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s;
      border: none;
      color: white;
    }
    
    .btn-primary {
      background-color: #007bff;
    }
    
    .btn-primary:hover {
      background-color: #0069d9;
    }
    
    .no-data-container {
      text-align: center;
      padding: 20px;
      background-color: #f8f9fa;
      border-radius: 4px;
      margin: 20px 0;
    }
    
    .no-data-container i {
      font-size: 48px;
      color: #6c757d;
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
  <div class="wrapper">
    <!-- Sidebar -->
    <nav class="sidebar">
      <div class="sidebar-header">
        <c:if test="${typeUsers == 'Client'}">
            <h3>Client.</h3>
        </c:if>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-book"></i> Livres</a></li>
        <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Abonnement</a></li>
        <li><a href="${pageContext.request.contextPath}/reservation-client"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
        <li><a href="${pageContext.request.contextPath}/prolongement-client"><i class="fa fa-clock"></i> Prolongement</a></li>
      </ul>

      <ul class="sidebar-footer">
        <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
      </ul>
    </nav>

    <!-- Main content -->
    <div class="main-content">
      <header>
        <h2>Demande de prolongement</h2>
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

          <h3>Mes prêts en cours</h3>
          <c:choose>
            <c:when test="${empty prets}">
              <div class="no-data-container">
                <i class="fa fa-info-circle"></i>
                <p>Aucun prêt en cours.</p>
              </div>
            </c:when>
            <c:otherwise>
              <table class="book-table">
                <thead>
                  <tr>
                    <th>Titre</th>
                    <th>Date retour estimée</th>
                    <th>Prolonger de (jours)</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="pret" items="${prets}">
                    <tr>
                      <td>${pret.exemplaire.livre.titre}</td>
                      <td>${pret.dateRetourEstime.format(DateTimeFormatter.ofPattern('dd/MM/yyyy'))}</td>
                      <td>
                        <form method="post" action="${pageContext.request.contextPath}/prolongement-client">
                          <input type="hidden" name="pretId" value="${pret.idPret}">
                          <input type="number" name="jours" min="1" max="14" required>
                      </td>
                      <td>
                          <button type="submit" class="btn btn-primary">Demander</button>
                        </form>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </c:otherwise>
          </c:choose>
        </div>
      </section>
    </div>
  </div>
</body>
</html>