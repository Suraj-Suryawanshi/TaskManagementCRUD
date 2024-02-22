<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.surya.model.User"%>
<%@ page import="com.surya.dao.UserDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="register.css">
<title>Admin User Panel</title>
</head>
<body>
    <div class="container">
	<h1>User List</h1>
	<a href="addUser.jsp">Add User</a>
	<div>
    	<a href="adminDisp1.jsp" >Admin Home</a><br><br>
    </div>
	<form method="post" action="userServlet">
		<!-- <input type="hidden" name="action" value="getAll">  --><%-- <input
			type="hidden" name="userId" value="<%=user.getUserId()%>"> --%>
		<input type="submit" name="sbt" value="getAll">
	</form>
	<table border="1">
		<tr>
			<!-- <th>ID</th> -->
			<th>UserName</th>
			<th>Email</th>
			<th>Date of Birth</th>
			<th>Role</th>
			<th>Joining Date</th>
			<th>Salary</th>
			<th>Address</th>
			<th>Password</th>
			<th>Actions</th>
		</tr>
		<%
		List<User> users = (ArrayList<User>) request.getAttribute("users");
		System.out.println("jsp----------" + users);
		if (users != null && !users.isEmpty()) {
		for (User user : users) {
		%>
		<tr>
			<td><%=user.getUserId()%></td> 
			<td><%=user.getUserName()%></td>
			<td><%=user.getUserEmail()%></td>
			<td><%=user.getUserDob()%></td>
			<td><%=user.getUserRole()%></td>
			<td><%=user.getUserJoiningDate() %></td>
			<td><%=user.getUserSalary() %></td>
			<td><%=user.getUserAddress()%></td>
			<td><%=user.getUserPassword()%></td>
 	 		<td><a href="editUser.jsp?userId=<%= user.getUserId() %>">Edit</a>
      
 			<%-- <form method="post" action="userServlet">
					<input type="hidden" name="sbt" value="getUserById"> <input
						type="hidden" name="userId" value="<%=user.getUserId()%>">
					<input type="submit"  value="Edit">
			</form>  --%>
			<form method="post" action="userServlet">
					<input type="hidden" name="sbt" value="delete"> <input
						type="hidden" name="userId" value="<%=user.getUserId()%>">
					<input type="submit"  value="Delete">
			</form></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="8">No users found.</td>
		</tr>
		<%
		}
		%>
	</table>
        <p><a href="index.jsp">Back to Login</a></p>
    
        <%-- Display error message if registration fails --%>
        <%  String error = request.getParameter("error");
           if (error != null && error.equals("1")) { %>
            <p style="color: red;">Registration failed. Please try again.</p>
        <% } %> 
        
      </div>
      
</body>
 
</html>