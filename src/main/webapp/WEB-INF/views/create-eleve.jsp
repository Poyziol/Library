<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, models.Promotion" %>
<%
    List<Promotion> tab_promo = (List<Promotion>) request.getAttribute("tab");
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create.css">

  <title>Student creation</title>

</head>

<body>

    <section>

    <h1>Create eleve</h1>

    <form action="eleve" method="post">

        <div>

            <label for="id-promotion">Promotion:</label>

            <select name="promotion" id="id-promotion">
                <% for (Promotion promo : tab_promo) { %>
                <option value="<%= promo.getIdPromotion() %>">
                    <%= promo.getNom() %>
                </option>
                <% } %>
            </select>

        </div>

        <div>
            <label for="id-nom">Nom:</label>
            <input type="text" name="nom" id="id-nom" required>
        </div>

        <div>
            <button type="submit">Insérer</button>
        </div>

    </form>

</section>

</body>

</html>
