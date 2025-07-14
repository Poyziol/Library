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
          <li><a href="${pageContext.request.contextPath}/reservation-client"><i class="fa fa-calendar-plus"></i> Réserver</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-client"><i class="fa fa-clock"></i> Prolongement</a></li>
        </c:if>
        <c:if test="${typeUsers == 'Bibliothecaire'}">
          <li><a href="${pageContext.request.contextPath}/abonnement"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
          <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
          <li><a href="${pageContext.request.contextPath}/penalite" class="active"><i class="fa fa-gavel"></i> Pénalité</a></li>
          <li><a href="${pageContext.request.contextPath}/reservation-biblio"><i class="fa fa-calendar-check"></i> Réservation</a></li>
          <li><a href="${pageContext.request.contextPath}/prolongement-biblio"><i class="fa fa-clock"></i> Prolongements</a></li>
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
          
          <!-- Liste des pénalités -->
          <div class="table-container">
            <c:choose>
              <c:when test="${empty penalites}">
                <div class="no-data-container">
                  <i class="fa fa-info-circle"></i>
                  <p>Aucune pénalité enregistrée.</p>
                </div>
              </c:when>
              <c:otherwise>
                <table class="book-table penalite-table">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Adhérent</th>
                      <th>Motif</th>
                      <th>Date début</th>
                      <th>Durée</th>
                      <th>Réglée</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="p" items="${penalites}">
                      <tr>
                        <td>${p.idPenalite}</td>
                        <td>${p.adherant.nom} ${p.adherant.prenom}</td>
                        <td title="${p.motif}">${p.motif}</td>
                        <td>${p.dateDebutPenalite}</td>
                        <td>${p.duree} jours</td>
                        <td>
                          <form method="post" action="${pageContext.request.contextPath}/penalite" style="display:inline">
                            <input type="hidden" name="action" value="toggle"/>
                            <input type="hidden" name="id" value="${p.idPenalite}"/>
                            <button type="submit" 
                                    class="status-toggle ${p.estReglee ? 'reglee' : 'non-reglee'}">
                              ${p.estReglee ? 'Oui' : 'Non'}
                            </button>
                          </form>
                        </td>
                        <td>
                          <div class="action-links">
                            <a href="${pageContext.request.contextPath}/penalite?action=edit&id=${p.idPenalite}" 
                               class="btn-edit">
                              <i class="fa fa-edit"></i> Modifier
                            </a>
                            <form method="post" action="${pageContext.request.contextPath}/penalite" style="display:inline">
                              <input type="hidden" name="action" value="delete">
                              <input type="hidden" name="id" value="${p.idPenalite}">
                              <button type="submit" class="btn-delete" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette pénalité ?')">
                                <i class="fa fa-trash"></i> Supprimer
                              </button>
                            </form>
                          </div>
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
  
  <script>
    // Confirmation avant de changer le statut
    document.querySelectorAll('.status-toggle').forEach(button => {
      button.addEventListener('click', function(e) {
        const isReglee = this.textContent.trim() === 'Oui';
        const newStatus = isReglee ? 'non réglée' : 'réglée';
        
        if (!confirm(`Voulez-vous vraiment marquer cette pénalité comme ${newStatus} ?`)) {
          e.preventDefault();
        }
      });
    });
  </script>
</body>
</html>