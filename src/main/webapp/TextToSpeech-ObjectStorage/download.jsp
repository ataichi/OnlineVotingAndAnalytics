<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@page import="net.tutorial.service.*, net.tutorial.misc.*, net.tutorial.utility.*, java.io.InputStream"%><%

  String cName = "sample";
  String fName = "t2s-os.wav";
  FilenameInputStreamPair fiPair = null;

  ObjectStorageService osServ = new ObjectStorageService();
  fiPair = osServ.getFile(cName, fName);
  HTTPUtility.download(fiPair, response);
%>