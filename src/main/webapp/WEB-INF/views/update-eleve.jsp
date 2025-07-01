<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Eleve, models.Promotion, java.util.List" %>
<%
    Eleve eleve = (Eleve) request.getAttribute("eleve");
    List<Promotion> tab_promo = (List<Promotion>) request.getAttribute("tab");
%>
<!DOCTYPE html>

<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Modifier eleve</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create.css">
</head>

<body>
  <h1>Modifier eleve #<%= eleve.getIdEleve() %></h1>
  <form action="modifyeleve" method="post">

    <input type="hidden" name="id" value="<%= eleve.getIdEleve() %>" />

    <div>
      <label for="promotion">Promotion :</label>
      <select name="promotion" id="promotion">
        <% for (Promotion p : tab_promo) { %>
          <option value="<%= p.getIdPromotion() %>"
            <%= (p.getIdPromotion().equals(eleve.getIdPromotion()) ? "selected" : "") %>>
            <%= p.getNom() %>
          </option>
        <% } %>
      </select>
    </div>

    <div>
      <label for="nom">Nom :</label>
      <input type="text" name="nom" id="nom" value="<%= eleve.getNom() %>" required />
    </div>

    <button type="submit">Enregistrer</button>

  </form>

  <p><a href="${pageContext.request.contextPath}/eleve?hide=L">← Retour à la liste</a></p>

</body>

</html>
