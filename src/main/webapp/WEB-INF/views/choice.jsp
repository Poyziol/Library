<%@ page import="controllers.*" %>
<%@ page import="models.*" %>
<%
    String crud = (String)request.getAttribute("crud");
    String val = "";
    if("L".equals(crud)) 
    {
        val = "GET";
    } 
    else if("C".equals(crud)) 
    {
        val = "POST";
    } 
    else if("U".equals(crud)) 
    {
        val = "PUT";
    } 
    else if("D".equals(crud)) 
    {
        val = "DELETE";
    } else 
    {
        val = "GET";
    }
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

    <title>Choice</title>

</head>

<body>

    <section>

        <div>
            <h1>Choose one to <%= val %></h1>
        </div>

        <div>
            <div>
                <form action="eleve" method="get">
                    <input type="hidden" name="hide" value="<%= crud %>">
                    <button type="submit">Eleve</button>
                </form>
            </div>
            <div>
                <form action="promotion" method="get">
                    <input type="hidden" name="hide" value="<%= crud %>">
                    <button type="submit">Promotion</button>
                </form>
            </div>
        </div>

    </section>

</body>

</html>