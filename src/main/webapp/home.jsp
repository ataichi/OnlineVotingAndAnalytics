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
		
        <% List<CandidateBean> presidentlist = (List<CandidateBean>) session.getAttribute("presidentlist"); %>
            <form action=\"VoteServlet\" method=\"post\">
				<% for (int i = 0; i < presidentlist.size(); i++) { %>
					<input type=\"radio\" name=\"selectpres\" value=\" <% i++ %> " + onChange=\"this.form.submit()\">");
					<a href="ViewProfile.jsp">presidentlist.get(i).getFirstName() + " " + presidentlist.get(i).getLastName()</a>
				<br>
				<% } %>
				<form action=\"ViewProfile\">
				</form>
			</form>
			
        <h2>Vice Presidential</h2>
		
        <% List<CandidateBean> vicepresidentlist = (List<CandidateBean>) session.getAttribute("vicepresidentlist"); %>
			<form action=\"VoteServlet\" method=\"post\">
				<% for (int i = 0; i < vicepresidentlist.size(); i++) { %>
					<input type=\"radio\" name=\"selectvicepres\" value=\" <% i++ %> " + onChange=\"this.form.submit()\">");
					<a href="ViewProfile.jsp">vicepresidentlist.get(i).getFirstName() + " " + vicepresidentlist.get(i).getLastName()</a>
					<br>
				<% } %>
			</form>
			
        <h2>Senatorial</h2>
		
        <% List<CandidateBean> senatorlist = (List<CandidateBean>) session.getAttribute("senatorlist"); %>
			<form action=\"VoteServlet\" method=\"post\">
				<% for (int i = 0; i < senatorlist.size(); i++) { %>
					<input type=\"radio\" name=\"selectsen\" value=\" <% i++ %> " + onChange=\"this.form.submit()\">");
					<a href="ViewProfile.jsp">senatorlist.get(i).getFirstName() + " " + senatorlist.get(i).getLastName()</a>
					<br>
				<% } %>
			</form>
    </body>
</html>
