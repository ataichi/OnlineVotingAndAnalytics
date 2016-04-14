<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.tutorial.service.*, net.tutorial.misc.*, java.util.*, net.tutorial.service.result.AlchemyResult"%>


<%
  CloudantService cloudantServ = new CloudantService();
  String strJSON = cloudantServ.getLast("imageinfo");

  AlchemyResult alResult = new AlchemyResult(strJSON);

  String age = alResult.getAge(0);
  String gender = alResult.getGender(0);

%>
Age: <%=age%>

<br><br>

Gender: <%=gender%>


<br><br>
Go to <a href= "index.jsp">index.jsp</a> to try another input.


<br><br>
Go back to  <a href= "../index.jsp">main menu</a>.
