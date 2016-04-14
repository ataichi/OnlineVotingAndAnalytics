<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.tutorial.service.*, net.tutorial.misc.*"%>
<%
  RedisService redServ = new RedisService();

  String age = redServ.get("age");
  String gender = redServ.get("gender");
%>
Age: <%=age%>

<br><br>

Gender: <%=gender%>


<br><br>
Go to <a href= "index.jsp">index.jsp</a> to try another input.


<br><br>
Go back to  <a href= "../index.jsp">main menu</a>.
