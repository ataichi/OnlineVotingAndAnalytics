<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@page import="net.tutorial.service.*, net.tutorial.service.result.AlchemyResult, net.tutorial.misc.*, net.tutorial.utility.*, java.io.InputStream"%><%

  //extract information
  String strUrl = request.getParameter("url");
  AlchemyService alServ = new AlchemyService();
  AlchemyResult alResult = alServ.recognizeFaces(strUrl);

  String age = alResult.getAge(0);
  String gender = alResult.getGender(0);
  String his_her = "his";
  if (gender.equalsIgnoreCase("female"))
    his_her = "her";

  String text = "Information Extraction Complete.  The person in the picture is a "+gender+".  "+his_her+" age is "+age;

  //convert text to speech
  TextToSpeechService t2sServ = new TextToSpeechService();
  InputStream inStream = t2sServ.convert(text);

  //download
  String fName = "imageinfo.wav";
  FilenameInputStreamPair fiPair = new FilenameInputStreamPair(fName, inStream);
  HTTPUtility.download(fiPair, response);
%>