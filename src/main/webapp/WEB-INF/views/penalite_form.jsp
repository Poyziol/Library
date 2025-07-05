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
      <!-- ... (identique à penalite.jsp) ... -->
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
          
          <form method="post" action="${pageContext.request.contextPath}/penalite">
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
              <select name="idPret" required>
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
            
            <button type="submit" class="btn-login">Enregistrer</button>
            <a href="${pageContext.request.contextPath}/penalite" class="btn-cancel">Annuler</a>
          </form>
        </div>
      </section>
    </div>
  </div>
</body>
</html>