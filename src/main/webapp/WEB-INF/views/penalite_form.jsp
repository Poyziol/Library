<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="typeUsers" value="${sessionScope.type}" />
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>${empty penalite ? 'Nouvelle Pénalité' : 'Modifier Pénalité'}</title>
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
        <h2>${empty penalite ? 'Créer une nouvelle pénalité' : 'Modifier la pénalité'}</h2>
      </header>
      
      <section class="content">
        <div class="dashboard-panel">
          <!-- Messages d'alerte -->
          <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">
              ${sessionScope.errorMessage}
              <c:remove var="errorMessage" scope="session"/>
            </div>
          </c:if>
          
          <form method="post" action="${pageContext.request.contextPath}/penalite" class="form-container">
            <c:if test="${not empty penalite}">
              <input type="hidden" name="id" value="${penalite.idPenalite}">
              <input type="hidden" name="action" value="update">
            </c:if>
            <c:if test="${empty penalite}">
              <input type="hidden" name="action" value="create">
            </c:if>
            
            <div class="form-group">
              <label for="idAdherant">Adhérent</label>
              <select id="idAdherant" name="idAdherant" required>
                <option value="">— Choisir un adhérent —</option>
                <c:forEach var="a" items="${adherants}">
                  <option value="${a.idAdherant}" 
                    ${not empty penalite && penalite.adherant.idAdherant == a.idAdherant ? 'selected' : ''}>
                    ${a.nom} ${a.prenom}
                  </option>
                </c:forEach>
              </select>
            </div>
            
            <div class="form-group">
              <label for="motif">Motif</label>
              <textarea id="motif" name="motif" rows="3" required>${penalite.motif}</textarea>
            </div>
            
            <div class="form-group">
              <label for="dateDebut">Date début</label>
              <input type="date" id="dateDebut" name="dateDebut" 
                     value="${not empty penalite ? penalite.dateDebutPenalite : today}" required>
            </div>
            
            <div class="form-group">
              <label for="duree">Durée (jours)</label>
              <input type="number" id="duree" name="duree" min="1" 
                     value="${not empty penalite ? penalite.duree : 7}" required>
            </div>
            
            <div class="form-group">
              <label for="estReglee">Statut</label>
              <select id="estReglee" name="estReglee" required>
                <option value="false" ${empty penalite || !penalite.estReglee ? 'selected' : ''}>Non réglée</option>
                <option value="true" ${not empty penalite && penalite.estReglee ? 'selected' : ''}>Réglée</option>
              </select>
            </div>
            
            <div class="form-actions">
              <button type="submit" class="btn-login">
                ${empty penalite ? 'Créer' : 'Mettre à jour'}
              </button>
              <a href="${pageContext.request.contextPath}/penalite" class="btn-cancel">
                Annuler
              </a>
            </div>
          </form>
        </div>
      </section>
    </div>
  </div>
</body>
</html>