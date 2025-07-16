<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="typeUsers" value="${sessionScope.type}" />
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des Pénalités</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome-free-6.7.2-web/css/all.min.css">
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
          <li><a href="${pageContext.request.contextPath}/client"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-client"><i class="fa fa-clock"></i> Prolongement</a></li>
        </c:if>
        <c:if test="${typeUsers == 'Bibliothecaire'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
          <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
          <li><a href="${pageContext.request.contextPath}/penalite" class="active"><i class="fa fa-gavel"></i> Pénalité</a></li>
          <li><a href="${pageContext.request.contextPath}/reservation-biblio"><i class="fa fa-calendar-check"></i> Réservation</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-biblio"><i class="fa fa-clock"></i> Prolongements</a></li>
        </c:if>
      </ul>
      <ul class="sidebar-footer">
        <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
      </ul>
    </nav>

    <!-- Main content -->
    <div class="main-content">
      <header>
        <h2>${empty penalite ? 'Nouvelle Pénalité' : 'Modifier Pénalité'}</h2>
      </header>
      
      <section class="content">
        <div class="dashboard-panel">
          <!-- Messages d'alerte -->
          <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success">
              <i class="fa fa-check-circle"></i> ${sessionScope.successMessage}
              <c:remove var="successMessage" scope="session"/>
            </div>
          </c:if>
          
          <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">
              <i class="fa fa-exclamation-circle"></i> ${sessionScope.errorMessage}
              <c:remove var="errorMessage" scope="session"/>
            </div>
          </c:if>
          
          <form method="post" action="${pageContext.request.contextPath}/penalite" class="filter-bar">
            <input type="hidden" name="action" value="${empty penalite ? 'create' : 'update'}">
            <c:if test="${not empty penalite}">
              <input type="hidden" name="id" value="${penalite.idPenalite}">
            </c:if>
            
            <div class="form-group">
              <label>Motif:</label>
              <input type="text" name="motif" value="${penalite.motif}" required>
            </div>
            
            <div class="form-group">
              <label>Date début:</label>
              <input type="date" name="dateDebut" 
                     value="${empty penalite ? today : penalite.dateDebutPenalite}" required>
            </div>
            
            <div class="form-group">
              <label>Durée (jours):</label>
              <input type="number" name="duree" 
                     value="${penalite.duree}" min="1" required>
            </div>
            
            <div class="form-group">
              <label>Adhérent:</label>
              <select name="idAdherant" required>
                <option value="">-- Sélectionner --</option>
                <c:forEach items="${adherants}" var="a">
                  <option value="${a.idAdherant}" 
                    ${penalite.adherant.idAdherant == a.idAdherant ? 'selected' : ''}>
                    ${a.nom} ${a.prenom}
                  </option>
                </c:forEach>
              </select>
            </div>
            
            <div class="form-group">
              <label>Prêt associé:</label>
              <select name="idPret">
                <option value="">-- Sélectionner --</option>
                <c:forEach items="${prets}" var="p">
                  <option value="${p.idPret}" 
                    ${penalite.pret.idPret == p.idPret ? 'selected' : ''}>
                    Pret #${p.idPret} - ${p.adherant.nom}
                  </option>
                </c:forEach>
              </select>
            </div>
            
            <div class="form-group">
              <label>Réglée:</label>
              <input type="checkbox" name="estReglee" 
                     ${penalite.estReglee ? 'checked' : ''}>
            </div>
            
            <div class="form-actions">
              <button type="submit" class="btn-login">
                <i class="fa fa-save"></i> Enregistrer
              </button>
              <a href="${pageContext.request.contextPath}/penalite" class="btn-cancel">
                <i class="fa fa-times"></i> Annuler
              </a>
            </div>
          </form>
        </div>
      </section>
    </div>
  </div>
  
  <script>
    // Ajouter le script de bascule du sidebar si nécessaire
    document.querySelector('.toggle-btn').addEventListener('click', function() {
      document.querySelector('.sidebar').classList.toggle('collapsed');
    });
  </script>
</body>
</html>