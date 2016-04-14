<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Language Translation & Text To Speech</title>
    </head>
    <body>
        <form action="convert.jsp" method="post">
            Enter Spanish text to convert to English speech: <input type="text" name="text" />
            <input type="submit" value="Convert" />
        </form>
        <br><br>
        Sample Spanish Text: <br>
        Hoy es sabado.
        <br><br>
        English Translation: <br>
        Today is Saturday.


        <br><br>
        Go back to  <a href= "../index.jsp">main menu</a>.
    </body>
</html>
