<%@ page import="java.util.List" %>
<%@ page import="controllers.*" %>
<%@ page import="models.*" %>
<%
    List<Eleve> tab_eleve = (List<Eleve>)request.getAttribute("tab");
    int val = -1;
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">

    <title>List of students</title>

</head>

<body>

    <section>
        <div>
            <h1>Liste des eleves</h1>
        </div>
       
        <div>
            <table>
                <thead>
                    <tr>
                        <th>id_eleve</th>
                        <th>id_promotion</th>
                        <th>nom</th>
                        <th>Changement</th>
                        <th>Supression</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Eleve eleve : tab_eleve) { %>
                        <tr>
                            <td><%= eleve.getIdEleve() %></td>
                            <td><%= eleve.getIdPromotion() %></td>
                            <td><%= eleve.getNom() %></td> 
                            <td><a href="modifyeleve?num=<%= val %>">PUT</a></td>
                            <td><a href="deleteeleve?num=<%= eleve.getIdEleve() %>">DELETE</a></td>
                        </tr>
                    <% val++; } %>
                </tbody>
            </table>
        </div>

        <div>
            <a href="${pageContext.request.contextPath}/home">Main menu</a>
        </div>

    </section>

</body>

</html>