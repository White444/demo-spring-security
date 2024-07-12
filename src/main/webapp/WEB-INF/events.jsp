<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  
  <h1>Hola desde Eventos !!!</h1>
  <h1><c:out value="${user.fullName()}" /></h1>
  <h2>Permitido el acceso por Spring Security</h2>

  <a href="/logout">Cerrar Sesión</a>
  <h1><c:out value="${title}" /> </h1>

  
  <c:forEach items="${user.roles}" var="role">
    <c:if test="${role.name eq 'ROLE_ADMIN'}" >
      <a href="/admin">Admin</a>
  </c:if>
  </c:forEach>

  
</body>
</html>