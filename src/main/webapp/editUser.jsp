<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.surya.model.User"%>
<%@ page import="com.surya.dao.UserDao"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.surya.dao.UserDaoImpl"%>

<%
UserDaoImpl userDaoImpl = new UserDaoImpl();
int userId = Integer.parseInt(request.getParameter("userId"));
User user = userDaoImpl.getUserById(userId);
//User user = (User) request.getAttribute("user");
System.out.println(user);
/* 
    if (task == null) {
        // Handle task not found error
        response.sendRedirect("admin/admin1.jsp");
        return;
    }
 */
/* if ("POST".equalsIgnoreCase(request.getMethod())) {
    String name = request.getParameter("name");
    String description = request.getParameter("description");
    boolean completed = Boolean.parseBoolean(request.getParameter("completed"));

    task.setName(name);
    task.setDescription(description);
    task.setCompleted(completed);

    taskService.updateTask(task);

    response.sendRedirect("index.jsp");
    return;
} */
%>


<html>
<head>
<title>Edit User</title>
</head>
<body>
	<h1>Edit User</h1>
	<form method="post" action="userServlet">
		ID: <input type="text" name="userId" value="<%= user.getUserId() %>" readonly><br>
	 
		<%--    <select name="myList">
<% for (String value : myValues) { %>
<option value="<%= value %>"><%= value %></option>
<% } %>
</select> --%>

		UserRole:
		<select name="userRole">
			<option value="Admin"
				<%=user.getUserRole().equals("Admin") ? "selected" : ""%>>Admin</option>
			<option value="Manager"
				<%=user.getUserRole().equals("Manager") ? "selected" : ""%>>Manager</option>
			<option value="Employee"
				<%=user.getUserRole().equals("Employee") ? "selected" : ""%>>Employee</option>
		</select><br> 
		UserName: <input type="text" name="userName"
			value="<%=user.getUserName()%>"><br> 
		Email: <input type="email" name="userEmail" value="<%=user.getUserEmail()%>"><br>
		Date of Birth: <input type="date" name="userDob" value="<%=LocalDate.parse(user.getUserDob())%>"><br>
		Date of Joining: <input type="date" name="userJoiningDate" value="<%=LocalDate.parse(user.getUserJoiningDate())%>"><br>
		User Salary: <input type="text" name="userSalary" value="<%=user.getUserSalary()%>"><br> 
			Address: <input type="text" name="userAddress" value="<%=user.getUserAddress()%>"><br>
		Password: <input type="password" name="userPassword"
			value="<%=user.getUserPassword()%>"><br>


		<%-- Completed: <input type="checkbox" name="completed" value="<%= task.getCompleted()%>"> --%>
		<br> <input type="submit" name="sbt" value="edit">
	</form>
</body>
</html>