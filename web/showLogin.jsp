<%-- 
    Document   : showLogin
    Created on : Jan 27, 2022, 10:08:13 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

            <div class="card my-5" style="width: 40rem">
                <div class="card-body">
            <form action="login" method="POST">
                  <fieldset>
                            <legend>Авторизация</legend>

                            <div class="form-group mb-3">
                              <label for="login" class="form-label mt-4">Логин</label>
                              <input type="text" class="form-control" id="login" name="login" aria-describedby="login" placeholder="Введите название login">
                              <small id="login" class="form-text text-muted d-none">Это поле не должно быть пустым.</small>
                            </div>   
                            
                            <div class="form-group mt-3">
                              <label for="password" class="form-label mt-4">Пароль</label>
                              <input type="text" class="form-control" id="password" name="password" aria-describedby="password" placeholder="Введите год password">
                              <small id="password" class="form-text text-muted d-none">Это поле не должно быть пустым.</small>
                            </div>

                            <button type="submit" class="btn btn-primary">авторизироваться</button>
                  </fieldset>
</form>
                </div>
            </div>

