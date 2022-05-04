<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.math.BigDecimal, java.text.*, com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" %>

<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>
<body>
<header>
<h1> Converting ${param.from} to ${param.to} </h1>
</header>
<p>
${param.amount} ${param.from} =
<%= currencies.convert(new BigDecimal(request.getParameter("amount")),
request.getParameter("from"),request.getParameter("to")) %> ${param.to}
</p>
</body>