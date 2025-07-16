<%@ page contentType="text/html;charset=UTF-8" import="java.util.List, models.Abonnement" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des abonnements</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome-free-6.7.2-web/css/all.min.css">
</head>
<body>
  <div class="wrapper">

    <!-- === Sidebar  === -->
    <nav class="sidebar">
      <div class="sidebar-header">
        <h3>Bibliothécaire.</h3>
        <button class="toggle-btn"><i class="fa fa-bars"></i></button>
      </div>
      <ul class="sidebar-menu">
        <li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-book"></i> Livres</a></li>
        <li><a href="#"><i class="fa fa-id-card"></i> Gestion Abonnements</a></li>
        <li><a href="${pageContext.request.contextPath}/pret"><i class="fa fa-hand-holding"></i> Prêt</a></li>
        <li><a href="${pageContext.request.contextPath}/penalite"><i class="fa fa-gavel"></i> Penalites</a></li>
        <li><a href="${pageContext.request.contextPath}/reservation-biblio"><i class="fa fa-calendar-check"></i> Réservation</a></li>
        <li><a href="${pageContext.request.contextPath}/prolongement-biblio"><i class="fa fa-clock"></i> Prolongement</a></li>
      </ul>
      <ul class="sidebar-footer">
        <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out-alt"></i> Déconnexion</a></li>
      </ul>
    </nav>
    <!-- === /Sidebar === -->

    <div class="main-content">
      <header><h2>Gestion des abonnements</h2></header>
      <section class="content">
        <div class="dashboard-panel">
          <% @SuppressWarnings("unchecked")
             List<Abonnement> abonnements = (List<Abonnement>) request.getAttribute("abonnements");
          %>
          <table class="book-table">
            <thead>
              <tr>
                <th>Adhérent</th><th>Début</th><th>Fin</th><th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <% for (Abonnement abo : abonnements) { %>
                <tr>
                  <td><%= abo.getAdherant().getNom() %> <%= abo.getAdherant().getPrenom() %></td>
                  <td><%= abo.getDateDebut() %></td>
                  <td><%= abo.getDateFin() %></td>
                  <td>
                    <form method="post" style="display:inline" action="<%= request.getContextPath() %>/abonnement">
                      <input type="hidden" name="idAbo" value="<%= abo.getId() %>"/>
                      <button name="action" value="renew" title="Renouveler"><i class="fa fa-sync"></i></button>
                    </form>
                    <form method="post" style="display:inline" action="<%= request.getContextPath() %>/abonnement">
                      <input type="hidden" name="idAbo" value="<%= abo.getId() %>"/>
                      <button name="action" value="delete" title="Supprimer"><i class="fa fa-trash"></i></button>
                    </form>
                  </td>
                </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </section>
    </div>

  </div>
</body>
</html>
