<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="index.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<form action="loginServlet" method="post">
			 
 			<label for="email">Email:</label>
 			<input type="text" id="email" name="userEmail" required><br>
 			<label for="role:">Select Role:
			<select name="userRole" >
				<option value="Employee">Employee</option>
				<option value="Manager">Manager</option>
				<option value="Admin">Admin</option>
			</select>
			</label>
			<label for="password">Password:</label> <input type="password"
				id="password" name="userPassword" required><br>
			<button type="submit">Login</button>
		</form>

		<!-- <p>
			Don't Registered Yet ? &nbsp; <a href="register.jsp"><b>Register
					Now</b></a>
		</p> -->
		<%-- <%
		System.out.println("parameter2 check");
		String name = request.getParameter("name");
		if (name != null && name.equals("2")) {
		%>
		<p style="color: red;">Paramenter 2. Please try again.</p>
		<%
		}
		%> --%>
		<%-- Display error message if login fails --%>
		<%
		String error = request.getParameter("error");
		if (error != null && error.equals("1")) {
		%>
		<p style="color: red;">Invalid userName or password. Please try
			again.</p>
		<%
		}
		else if(error !=null && error.equals("2")){
		%>
		<p style="color: red;">Invalid userRole... Please try
			again.</p>
		<%} %>
		<%-- Display error message if Register Successful --%>
		<%-- <%
		String rs = request.getParameter("registration");
		if (rs != null && rs.equals("success")) {
		%>
		<p style="color: green;">Your Registration is Successful. Please
			Login.</p>
		<%
		}
		%> --%>
	</div>
</body>
</html>