<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Logout</h2>
		
		<h1>Candidate List</h1>
			
		<h2>Presidential</h2>
        <%
            ArrayList presidentlist = (ArrayList) session.getAttribute("presidentlist");
            out.println("<table>");
            for (int i = 0; i < presidentlist.size(); i++) {
                out.println("<tr>");
                out.println("<td><label for=\"name\">" + presidentlist.get(i).getLastName() + ", " + presidentlist.get(i).getFirstName() + "</label></td>");
                out.println("<td><form action=\"ViewProfile\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"View Profile\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("<td><form action=\"Vote\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Vote\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("<td><form action=\"Unvote\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Unvote\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
        <h2>Vice Presidential</h2>
        <%
            ArrayList vicepresidentlist = (ArrayList) session.getAttribute("vicepresidentlist");
            out.println("<table>");
            for (int j = 0; j < vicepresidentlist.size(); j++) {
                out.println("<tr>");
                out.println("<td><label for=\"name\">" + vicepresidentlist.get(i).getLastName() + ", " + vicepresidentlist.get(i).getFirstName() + "</label></td>");
                out.println("<td><form action=\"ViewProfile\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"View Profile\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("<td><form action=\"Vote\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Vote\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("<td><form action=\"Unvote\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Unvote\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>

        <h2>Senatorial</h2>
        <%
            ArrayList senatoriallist = (ArrayList) session.getAttribute("senatoriallist");
            out.println("<table>");
            for (int i = 0; i < presidentlist.size(); i++) {
                out.println("<tr>");
                out.println("<td><label for=\"name\">" + senatoriallist.get(i).getLastName() + ", " + senatoriallist.get(i).getFirstName() + "</label></td>");
                out.println("<td><form action=\"ViewProfile\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"View Profile\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("<td><form action=\"Vote\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Vote\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("<td><form action=\"Unvote\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Unvote\" name=\"" + i + "\">");
                out.println("</form></td>");
                out.println("</tr>");
            }
            out.println("<tr>");
        %>
    </body>
</html>
