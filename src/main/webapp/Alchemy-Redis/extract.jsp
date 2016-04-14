<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.tutorial.service.*, net.tutorial.service.result.AlchemyResult, net.tutorial.misc.*"%>
<%
  //extract information
  String strUrl = request.getParameter("url");
  AlchemyService alServ = new AlchemyService();
  AlchemyResult alResult = alServ.recognizeFaces(strUrl);

  String age = alResult.getAge(0);
  String gender = alResult.getGender(0);

  RedisService redServ = new RedisService();
  redServ.set("age", age);
  redServ.set("gender", gender);
%>
Information Extraction Complete
<br><br>
<!--%=alResult.toString()%-->
<%=strTemp%>

<br><br>
<%=alResult.getImageFace(0)%>

<br><br>
<%=alResult.getAge(0)%>

<br><br>
<%=alResult.getGender(0)%>

<br><br>
Go to <a href= "viewinfo.jsp">viewinfo.jsp</a> to view contents from Redis.


<br><br>
Go to <a href= "index.jsp">index.jsp</a> to try another input.


<br><br>
Go back to  <a href= "../index.jsp">main menu</a>.
