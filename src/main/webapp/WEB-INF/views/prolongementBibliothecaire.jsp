<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<c:set var="typeUsers" value="${sessionScope.type}" />
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des prolongements</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
  <style>
    .btn {
      padding: 8px 12px;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s;
      border: none;
      color: white;
      margin: 0 5px;
    }
    
    .btn-success {
      background-color: #28a745;
    }
    
    .btn-danger {
      background-color: #dc3545;
    }
    
    .btn-success:hover {
      background-color: #218838;
    }
    
    .btn-danger:hover {
      background-color: #c82333;
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
        <c:if test="${typeUsers == 'Bibliothecaire'}">
            <h3>Bibliothecaire.</h3>
        </c:if>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-book"></i> Livres</a></li>
        <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
        <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
        <li><a href="${pageContext.request.contextPath}/penalite"><i class="fa fa-gavel"></i> Penalites</a></li>
        <li><a href="${pageContext.request.contextPath}/reservation-biblio"><i class="fa fa-calendar-check"></i> Réservation</a></li>
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
        <h2>Gestion des prolongements</h2>
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

          <h3>Demandes de prolongement</h3>
          <c:choose>
            <c:when test="${empty demandes}">
              <div class="no-data-container">
                <i class="fa fa-info-circle"></i>
                <p>Aucune demande en attente.</p>
              </div>
            </c:when>
            <c:otherwise>
              <table class="book-table">
                <thead>
                  <tr>
                    <th>Adhérent</th>
                    <th>Titre</th>
                    <th>Ancienne date</th>
                    <th>Nouvelle date</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="demande" items="${demandes}">
                    <tr>
                      <td>${demande.pret.adherant.nom} ${demande.pret.adherant.prenom}</td>
                      <td>${demande.pret.exemplaire.livre.titre}</td>
                      <td>${demande.pret.dateRetourEstime.format(DateTimeFormatter.ofPattern('dd/MM/yyyy'))}</td>
                      <td>${demande.nouvelleDateRetour.format(DateTimeFormatter.ofPattern('dd/MM/yyyy'))}</td>
                      <td>
                        <form method="post" action="${pageContext.request.contextPath}/prolongement-biblio">
                          <input type="hidden" name="prolongementId" value="${demande.idProlongement}">
                          <button type="submit" name="action" value="accepter" class="btn btn-success">Accepter</button>
                          <button type="submit" name="action" value="refuser" class="btn btn-danger">Refuser</button>
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