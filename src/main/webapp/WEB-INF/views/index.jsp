<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

  <head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

    <title>Home</title>

  </head>

  <body>

    <section>

      <div>
        <h1>CRUD</h1>
      </div>

      <div>

        <div>
          <form action="choix" method="get">
            <input type="hidden" name="hide" value="L">
            <button type="submit">GET</button>
          </form>
        </div>

        <div>
          <form action="choix" method="get">
            <input type="hidden" name="hide" value="C">
            <button type="submit">POST</button>
          </form>
        </div>

      </div>

    </section>

  </body>

</html>
