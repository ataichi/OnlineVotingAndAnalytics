<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="bean.CandidateBean"%>

<% List<CandidateBean> ballot = (List<CandidateBean>) session.getAttribute("ballot"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.jsp">Logout</a>
		
		<h1>Candidate List</h1>
		
		<h2>Presidential Candidates</h2>
		
		<%
            int temp = 0;
			boolean selectedcand = false;
			List<CandidateBean> presidentlist = (List<CandidateBean>) session.getAttribute("presidentlist");
			
			out.println("<table>");
            temp = 0;
            for(int i=0; i<presidentlist.size(); i++) {
                temp = i+1;
                out.println("<tr>");
            
				selectedcand = false;
				for(int j=0; j<ballot.size(); j++) {
					if(ballot.get(j).getCandidateID() == presidentlist.get(i).getCandidateID()) {
						selectedcand = true;
					}
				}
			
				if(selectedcand) {
					out.println("<td>");
						out.println("<form action=\"VoteServlet\" method=\"post\">");
						out.println("<input type=\"radio\" name=\"selectpres\" value=\"" + presidentlist.get(i).getCandidateID() + "\" onChange=\"this.form.submit()\" checked><br>");
						out.println("</form>");
					out.println("</td>");
				} else {
					out.println("<td>");
						out.println("<form action=\"VoteServlet\" method=\"post\">");
						out.println("<input type=\"radio\" name=\"selectpres\" value=\"" + presidentlist.get(i).getCandidateID() + "\" onChange=\"this.form.submit()\"><br>");
						out.println("</form>");
					out.println("</td>");
				}
				
                out.println("<td>");
                    out.println("<form action=\"ViewProfileServlet\" method=\"post\">");
						out.println("<input type=\"hidden\" readonly name=\"profileid\" value=\"" + presidentlist.get(i).getCandidateID() + "\"/>");
						out.println("<input type=\"label\" name=\"candidatename\" value=\"" + presidentlist.get(i).getFirstName() + " " + presidentlist.get(i).getLastName() + "\" onclick=\"this.form.submit()\" readonly>");
                    out.println("</form>");
                out.println("</td>");
                
                out.println("</tr>");
            }
            out.println("</table>");
        %>
			
        <h2>Vice Presidential Candidates</h2>
		
		<%
			List<CandidateBean> vicepresidentlist = (List<CandidateBean>) session.getAttribute("vicepresidentlist");
			
			out.println("<table>");
            temp = 0;
            for(int i=0; i<vicepresidentlist.size(); i++) {
                temp = i+1;
                out.println("<tr>");
            
				selectedcand = false;
				for(int j=0; j<ballot.size(); j++) {
					if(ballot.get(j).getCandidateID() == vicepresidentlist.get(i).getCandidateID()) {
						selectedcand = true;
					}
				}
			
				if(selectedcand) {
					out.println("<td>");
						out.println("<form action=\"VoteServlet\" method=\"post\">");
						out.println("<input type=\"radio\" name=\"selectpres\" value=\"" + vicepresidentlist.get(i).getCandidateID() + "\" onChange=\"this.form.submit()\" checked><br>");
						out.println("</form>");
					out.println("</td>");
				} else {
					out.println("<td>");
						out.println("<form action=\"VoteServlet\" method=\"post\">");
						out.println("<input type=\"radio\" name=\"selectpres\" value=\"" + vicepresidentlist.get(i).getCandidateID() + "\" onChange=\"this.form.submit()\"><br>");
						out.println("</form>");
					out.println("</td>");
				}
                
                out.println("<td>");
                    out.println("<form action=\"ViewProfileServlet\" method=\"post\">");
					out.println("<input type=\"hidden\" readonly name=\"profileid\" value=\"" + vicepresidentlist.get(i).getCandidateID() + "\"/>");
                    out.println("<input type=\"label\" name=\"candidatename\" value=\"" + vicepresidentlist.get(i).getFirstName() + " " + vicepresidentlist.get(i).getLastName() + "\" onclick=\"this.form.submit()\" readonly>");
                    out.println("</form>");
                out.println("</td>");
                
                out.println("</tr>");
            }
            out.println("</table>");
        %>
			
        <h2>Senatorial Candidates</h2>
		
        <%
			List<CandidateBean> senatorlist = (List<CandidateBean>) session.getAttribute("senatorlist");
			
			out.println("<table>");
            temp = 0;
            for(int i=0; i<senatorlist.size(); i++) {
                temp = i+1;
                out.println("<tr>");
            
				selectedcand = false;
				for(int j=0; j<ballot.size(); j++) {
					if(ballot.get(j).getCandidateID() == senatorlist.get(i).getCandidateID()) {
						selectedcand = true;
					}
				}
				
				if(selectedcand) {
					out.println("<td>");
						out.println("<form action=\"VoteServlet\" method=\"post\">");
						out.println("<input type=\"radio\" name=\"selectpres\" value=\"" + senatorlist.get(i).getCandidateID() + "\" onChange=\"this.form.submit()\" checked><br>");
						out.println("</form>");
					out.println("</td>");
				} else {
					out.println("<td>");
						out.println("<form action=\"VoteServlet\" method=\"post\">");
						out.println("<input type=\"radio\" name=\"selectpres\" value=\"" + senatorlist.get(i).getCandidateID() + "\" onChange=\"this.form.submit()\"><br>");
						out.println("</form>");
					out.println("</td>");
				}
			
                out.println("<td>");
                    out.println("<form action=\"ViewProfileServlet\" method=\"post\">");
                    out.println("<input type=\"hidden\" readonly name=\"profileid\" value=\"" + senatorlist.get(i).getCandidateID() + "\"/>");
					out.println("<input type=\"label\" name=\"candidatename\" value=\"" + senatorlist.get(i).getFirstName() + " " + senatorlist.get(i).getLastName() + "\" onclick=\"this.form.submit()\" readonly>");
                    out.println("</form>");
                out.println("</td>");
               
                out.println("</tr>");
            }
            out.println("</table>");
        %>
		
    </body>
</html>
