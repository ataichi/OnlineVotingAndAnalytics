<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.tutorial.Math" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<%
Math m = new Math();
%>

<%="5 + 9 = " + m.add(5, 9)%>
<br>
<%="8 - 2 = " + m.sub(8, 2)%>
<br>
<%="4 x 7 = " + m.multiply(4, 7)%>
<br>

</body>
</html>
