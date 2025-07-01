<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Promotion" %>
<%
    Promotion promo = (Promotion) request.getAttribute("promotion");
%>
<!DOCTYPE html>

<html lang="fr">

<head>
  <meta charset="UTF-8">
  <title>Modifier Promotion</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create.css">
</head>

<body>

  <h1>Modifier Promotion #<%= promo.getIdPromotion() %></h1>

  <form action="modifypromotion" method="post">

    <input type="hidden" name="id" value="<%= promo.getIdPromotion() %>" />

    <div>
      <label for="nom">Nom :</label>
      <input type="text" id="nom" name="nom" value="<%= promo.getNom() %>" required/>
    </div>

    <button type="submit">Enregistrer</button>

  </form>

  <p><a href="${pageContext.request.contextPath}/promotion?hide=L">← Retour à la liste</a></p>

</body>

</html>
