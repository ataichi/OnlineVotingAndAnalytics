<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.tutorial.service.*, net.tutorial.service.result.AlchemyResult, net.tutorial.misc.*"%>
<%
  //extract information
  String strUrl = request.getParameter("url");
  AlchemyService alServ = new AlchemyService();
  AlchemyResult alResult = alServ.recognizeFaces(strUrl);

  CloudantService cloudantServ = new CloudantService();
  cloudantServ.putJSON("imageinfo", alResult.toString());
%>
Information Extraction Complete
<br><br>
<%=alResult.toString()%>

<br><br>
Go to <a href= "viewinfo.jsp">viewinfo.jsp</a> to view contents from Cloudant.


<br><br>
Go to <a href= "index.jsp">index.jsp</a> to try another input.


<br><br>
Go back to  <a href= "../index.jsp">main menu</a>.