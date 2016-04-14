<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.tutorial.service.*, net.tutorial.misc.*, net.tutorial.utility.*, java.io.InputStream"%>
<%

  FilenameInputStreamPair fiPair = HTTPUtility.upload(request);
  String cName = "sample";
  String fName = "s2t-os.wav";
  fiPair.setFilename(fName);



  //save wav file to object storage
  ObjectStorageService osServ = new ObjectStorageService();
  osServ.createContainer(cName);  //create sample container if it is not yet existing
  osServ.deleteFile(cName, fName); //delete file if existing
  osServ.putFile(cName, fiPair);

  //this is performed to see if file was really saved in object storage
  fiPair = osServ.getFile(cName, fName);

  //convert speech to text
  SpeechToTextService s2tServ = new SpeechToTextService();
  String text = s2tServ.convert(fiPair.getInputStream());

%>
Text is "<%=text%>"


<br><br>
Go to <a href= "index.jsp">index.jsp</a> to try another input.


<br><br>
Go back to  <a href= "../index.jsp">main menu</a>.