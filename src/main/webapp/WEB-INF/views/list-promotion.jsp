<%@ page import="java.util.List" %>
<%@ page import="controllers.*" %>
<%@ page import="models.*" %>
<%
    List<Promotion> tab_promotion = (List<Promotion>)request.getAttribute("tab");
    int val = -1;
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">

    <title>List of Promotions</title>

</head>

<body>

    <section>
        <div>
            <h1>Liste des promotions</h1>
        </div>
       
        <div>
            <table>
                <thead>
                    <tr>
                        <th>id_promotion</th>
                        <th>nom</th>
                        <th>Changement</th>
                        <th>Supression</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Promotion promotion : tab_promotion) { %>
                        <tr>
                            <td><%= promotion.getIdPromotion() %></td>
                            <td><%= promotion.getNom() %></td> 
                            <td><a href="modifypromotion?num=<%= val %>">PUT</a></td>
                            <td><a href="deletepromotion?num=<%= promotion.getIdPromotion() %>">DELETE</a></td>
                        </tr>
                    <% val++; } %>
                </tbody>
            </table>

            <div>
                <a href="${pageContext.request.contextPath}/home">Main menu</a>
            </div>

        </div>
    </section>

</body>

</html>