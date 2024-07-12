<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Spring Security</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous"></script>
</head>

<body>
  <h1>Bienvenidos a Spring Security</h1>
  <a href="/events">Eventos</a>

  <div class="container d-flex gap-4 justify-content-around  align-items-start">
    <form:form action="/users/new" method="POST" modelAttribute="user"
      class="d-flex flex-column border border-4 border-secondary p-5 rounded">

      <form:label path="firstName">Nombre</form:label>
      <form:input type="text" path="firstName" value="David" />
      <form:errors class="text-danger" path="firstName" />

      <form:label path="lastName">Apellido</form:label>
      <form:input type="text" path="lastName" value="Osorno" />
      <form:errors class="text-danger" path="lastName" />

      <form:label path="email">Email</form:label>
      <form:input type="text" path="email" value="dosorno@codingdojo.cl" />
      <form:errors class="text-danger" path="email" />

      <form:label class="col-sm-4 col-form-label" path="city">City:</form:label>
      <form:input class="form-control col-sm-6" type="text" path="city" value="Los Angeles" />

      <form:label path="password">Contraseña</form:label>
      <form:input type="password" path="password" value="pass1234" />
      <form:errors class="text-danger" path="password" />

      <form:label path="passwordConfirmation">Confirmar Contraseña</form:label>
      <form:input type="password" path="passwordConfirmation" value="pass1234" />
      <form:errors class="text-danger" path="passwordConfirmation" />

      <br>
      <button class="rounded">Crear</button>
    </form:form>

    <!-- <form action="/login" method="post"  -->
    <form action="/" method="post" 
    class="border border-4 border-secondary p-5 rounded d-flex flex-column">
      <label for="">Email</label>
      <input type="email" name="email" value="dosorno@codingdojo.cl">
      <label for="">Contraseña</label>
      <input type="password" name="password" value="pass1234">
      <span class="text-danger ">
        <c:out value="${userError}" />
      </span>
      <br>
      <button class="rounded">Iniciar Sesión</button>
    </form>
  </div>
</body>

</html>