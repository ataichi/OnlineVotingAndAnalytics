<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@page import="net.tutorial.service.*, net.tutorial.service.result.*, net.tutorial.misc.*, net.tutorial.utility.*, java.io.InputStream"%><%
  String spanishText = request.getParameter("text");
  LanguageTranslationService ltServ = new LanguageTranslationService();
  LanguageTranslationResult ltResult = ltServ.translate(spanishText, "es", "en");

  String englishText = ltResult.getTranslation(0);

  //convert text to speech
  TextToSpeechService t2sServ = new TextToSpeechService();
  InputStream inStream = t2sServ.convert(englishText);

  //download
  String fName = "translation.wav";
  FilenameInputStreamPair fiPair = new FilenameInputStreamPair(fName, inStream);
  HTTPUtility.download(fiPair, response);
%>