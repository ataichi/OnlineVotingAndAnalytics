<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.CandidateBean"%>
<%@page import="bean.EducationalBGBean" %>

<!DOCTYPE html>

<%
    CandidateBean candidate = (CandidateBean) session.getAttribute("candidateprofile");
	List<EducationalBGBean> educbg = (List<EducationalBGBean>) session.getAttribute("candidateeducbg");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Profile</title>
    </head>
    <body>
        <h1><% out.println(candidate.getFirstName() + " " + candidate.getLastName()); %></h1>
        <h1>Educational Background</h1>

		<h1>Political Background</h1>
		
		<h1>Platform & Advocacy</h1>
		
		<a href="home.jsp">Back to Vote Page</a>
    </body>
</html>
