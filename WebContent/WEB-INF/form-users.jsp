<%@ page  import = "net.javaguides.todoapp.dao.UserCRUD"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TODO Application</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">


</head>
<body>
 <header>
  <nav class="navbar navbar-expand-md navbar-dark"
   style="background-color: #0f4c81">
   <div>
    <a href="https://www.csccclub.com" class="navbar-brand"> Todo App</a>
   </div>

   
   
   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/users/list"
     class="nav-link">Users</a></li>
   </ul>
	<ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/todo/list"
     class="nav-link">Todos</a></li>
   </ul>
   <ul class="navbar-nav navbar-collapse justify-content-end">
    <li><a href="<%=request.getContextPath()%>/users/logout"
     class="nav-link">Logout</a></li>
   </ul>
   
  </nav>
 </header>

 <div class="container col-md-5">
  <div class="card">
   <div class="card-body">
    <c:if test="${users != null}">
     <form action="<%=request.getContextPath()%>/users/update" method="post">
    </c:if>
    <c:if test="${users == null}">
     <form action="<%=request.getContextPath()%>/users/insert" method="post">
    </c:if>

    <caption>
     <h2>
      <c:if test="${users != null}">
               Edit User
              </c:if>
      <c:if test="${users == null}">
               Add New User
              </c:if>
     </h2>
    </caption>

    <c:if test="${users != null}">
     <input type="hidden" name="id" value="<c:out value='${listUser.id}' />" />
    </c:if>

    <fieldset class="form-group">
    <label>Firstname</label>
    <input type="text" value="<c:out value='${empty user ? "" : listUser.firstName}' />" class="form-control" name="first_name" required="required" minlength="5">
</fieldset>

<fieldset class="form-group">
    <label>Lastname</label>
    <input type="text" value="<c:out value='${empty user ? "" : listUser.lastName}' />" class="form-control" name="last_name" minlength="5">
</fieldset>

<fieldset class="form-group">
    <label>Username</label>
    <input type="text" value="<c:out value='${empty user ? "" : listUser.username}' />" class="form-control" name="username" minlength="5">
</fieldset>

<!-- Role dropdown -->
<fieldset class="form-group">
    <label>Role</label>
    <select class="form-control" name="role">
        <c:forEach var="role" items="${roles}">
            <option value="<c:out value='${empty user ? "" : listUser.role}' />"><c:out value='${empty user ? "" : listUser.role}' /></option>
        </c:forEach>
    </select>
</fieldset>

<!-- Score input -->
<fieldset class="form-group">
    <label>Score</label>
    <input type="number" value="<c:out value='${empty user ? "" : listUser.score}' />" class="form-control" name="score" />
</fieldset>


    <button type="submit" class="btn btn-success">Save</button>
    </form>
   </div>
  </div>
 </div>

 <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>