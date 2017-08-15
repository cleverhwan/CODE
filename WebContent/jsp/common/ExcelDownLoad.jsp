<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-Type","application/vnd.ms-excel");
	String fileName = request.getParameter("fileName");
	response.setHeader("Content-Disposition","attachment;filename="+fileName+".xls");
	response.setHeader("Content-Description","JSP GeneratedData");
%>


${excelSource}