<%@page import="net.javaguides.todoapp.dao.TodoDaoImpl"%>
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

</head>
<body>
 <header>
  <nav class="navbar navbar-expand-md navbar-dark"
   style="background-color: #0f4c81">
   <div>
    <a href="https://www.csccclub.com" class="navbar-brand"> Todo App</a>
   </div>

   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/todo/list"
     class="nav-link">Todos</a></li>
   </ul>

   <ul class="navbar-nav navbar-collapse justify-content-end">
    <li><a href="<%=request.getContextPath()%>/todo/logout"
     class="nav-link">Logout</a></li>
   </ul>
  </nav>
 </header>
 <div class="container col-md-5">
  <div class="card">
   <div class="card-body">
    <c:if test="${todo != null}">
     <form action="/todo/update" method="post">
    </c:if>
    <c:if test="${todo == null}">
     <form action="/todo/insert" method="post">
    </c:if>

    <caption>
     <h2>
      <c:if test="${todo != null}">
               Edit Todo
              </c:if>
      <c:if test="${todo == null}">
               Add New Todo
              </c:if>
     </h2>
    </caption>

    <c:if test="${todo != null}">
     <input type="hidden" name="id" value="<c:out value='${todo.id}' />" />
    </c:if>

    <fieldset class="form-group">
     <label>Todo Title</label> <input type="text"
      value="<c:out value='${todo.title}' />" class="form-control"
      name="title" required="required" minlength="5">
    </fieldset>

    <fieldset class="form-group">
     <label>Todo Decription</label> <input type="text"
      value="<c:out value='${todo.description}' />" class="form-control"
      name="description" minlength="5">
    </fieldset>

    <fieldset class="form-group">
     <label>Todo Status</label> <select class="form-control" name="status">
      <option value= "New">New</option>
      <option value= "In Progress">In Progress</option>
      <option value="Complete">Complete</option>
      <option value="Blocked">Blocked</option>
      
     </select>
    </fieldset>

    <fieldset class="form-group">
     <label>Todo Target Date</label> <input type="date"
      value="<c:out value='${todo.targetDate}' />" class="form-control"
      name="targetDate" required="required">
    </fieldset>
    

    
    <fieldset class="form-group">
    <label>Users</label>
    <select class="form-control" name="userId">
        <option value=""></option>

        <c:forEach var="user" items="${listUser}">
            <option value="${user.id}">${user.username}</option>
        </c:forEach>
    </select>
</fieldset>

    <button type="submit" class="btn btn-success">Save</button>
    </form>
   </div>
  </div>
 </div>

 <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>