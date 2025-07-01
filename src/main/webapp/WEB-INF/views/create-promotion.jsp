<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

  <head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create.css">

    <title>Promotion creation</title>

  </head>

  <body>

    <section>

      <div>
        <h1>Create eleve</h1>
      </div>

      <div>

        <form action="promotion" method="post">
            <div>
                <label for="id-nom">Nom:</label>
            </div>

            <div>
                <input type="text" name="nom" id="id-nom">
            </div>

            <div>
                <button type="submit">Inserer</button>
            </div>
        </form>

      </div>

    </section>

  </body>

</html>
