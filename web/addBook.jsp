<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:28 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="container d-flex justify-content-center">
            <div class="card my-5" style="width: 40rem">
                <div class="card-body">
            <form action="createBook" method="POST">
                  <fieldset>
                            <legend>Добавление книги</legend>

                            <div class="form-group mb-3">
                              <label for="caption" class="form-label mt-4">Название книги</label>
                              <input type="text" class="form-control" id="caption" name="caption" aria-describedby="caption" placeholder="Введите название книги">
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
                
