<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TODO Application</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">

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

 <div class="row">
  <div class="container">
   <h3 class="text-center">List of Users</h3>
   <hr>
   <div class="container text-left">
    <a href="<%=request.getContextPath()%>/users/new"
     class="btn btn-success">Add User</a>
   </div>
   <br>
   <table class="table table-bordered">
    <thead>
     <tr>
      <th>ID</th>
      <th>Firstname</th>
      <th>Lastname</th>
      <th>Username</th>
      <th>Role</th> <!-- Added Role column -->
      <th>Score</th> <!-- Added Score column -->
      <th>Actions</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach var="user" items="${listUser}">
      <tr>
       <td><c:out value="${user.id}" /></td>
       <td><c:out value="${user.firstName}" /></td>
       <td><c:out value="${user.lastName}" /></td>
       <td><c:out value="${user.username}" /></td>
       <td><c:out value="${user.role}" /></td> <!-- Display Role -->
       <td><c:out value="${user.score}" /></td> <!-- Display Score -->
       <td>
        <a href="<%=request.getContextPath()%>/users/edit?id=<c:out value='${user.id}' />">Edit</a>
        <a href="<%=request.getContextPath()%>/users/delete?id=<c:out value='${user.id}' />">Delete</a>
       </td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

 <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
