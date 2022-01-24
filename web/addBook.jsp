<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:28 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrapmateria.css"  rel="stylesheet" >
        <title>JSP Page</title>
    </head>
    <body>
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index">JKTV20 Library</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="addBook.jsp">Добавить книгу</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
            Dropdown
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control mr-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
        <div class="container d-flex justify-content-center">
            <div class="card my-5" style="width: 40rem">
                <div class="card-body">
            <form action="createBook" method="POST">
                  <fieldset>
                            <legend>Добавление книги</legend>

                            <div class="form-group mb-3">
                              <label for="caption" class="form-label mt-4">Название книги</label>
                              <input type="text" class="form-control" id="caption" aria-describedby="caption" placeholder="Введите название книги">
                              <small id="emailHelp" class="form-text text-muted d-none">Это поле не должно быть пустым.</small>
                            </div>
                            
                            <div class="form-group mt-3">
                              <label for="exampleSelect2" class="form-label mt-4">Авторы</label>
                              <select multiple="" class="form-select" id="authors" name="authors">
                                  <c:forEach var="author" items="${authors}">
                                      <option value="${author.id}">${author.name} ${author.lastname}. ${author.year}</option>
                                  </c:forEach>
                              </select>
                            </div>
                            
                            <div class="form-group mt-3">
                              <label for="exampleInputEmail1" class="form-label mt-4">Год издания</label>
                              <input type="text" class="form-control" id="publicationYear" name="publicationYear" aria-describedby="publicationYear" placeholder="Введите год издания">
                              <small id="emailHelp" class="form-text text-muted d-none">Это поле не должно быть пустым.</small>
                            </div>

                            <div class="form-group mb-3">
                              <label for="exampleInputEmail1" class="form-label mt-4">Количество</label>
                              <input type="text" class="form-control" id="quantity" name="quantity" aria-describedby="bookCount" placeholder="Введите количество">
                              <small id="emailHelp" class="form-text d-none">Это поле не должно быть пустым.</small>
                            </div>

                            

                            <button type="submit" class="btn btn-primary">Добавить книгу</button>
                  </fieldset>
</form>
                </div>
            </div>
        </div>
        
    </body>
</html>
