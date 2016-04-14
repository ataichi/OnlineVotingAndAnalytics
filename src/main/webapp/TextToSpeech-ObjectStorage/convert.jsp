<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.tutorial.service.*, net.tutorial.misc.*, java.io.InputStream"%>
<%

  //convert text to speech
  String text = request.getParameter("text");
  TextToSpeechService t2sServ = new TextToSpeechService();
  InputStream inStream = t2sServ.convert(text);

  String cName = "sample";
  String fName = "t2s-os.wav";
  FilenameInputStreamPair fiPair = new FilenameInputStreamPair(fName, inStream);


  //save wav file to object storage
  ObjectStorageService osServ = new ObjectStorageService();
  osServ.createContainer(cName);  //create sample container if it is not yet existing
  osServ.deleteFile(cName, fName); //delete file if existing
  osServ.putFile(cName, fiPair);
%>
<a href="download.jsp">Download file</a>


<br><br>
Go to <a href= "index.jsp">index.jsp</a> to try another input.


<br><br>
Go back to  <a href= "../index.jsp">main menu</a>.