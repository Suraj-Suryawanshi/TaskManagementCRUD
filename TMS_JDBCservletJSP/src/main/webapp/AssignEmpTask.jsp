<%@page import="com.surya.model.EmpTask"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.surya.model.Task"%>
<%@ page import="com.surya.dao.TaskDaoImpl"%>
<%@ page import="com.surya.dao.UserDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="register.css">
<title>Admin Task Panel</title>
</head>
<body>

	<div class="container">
		<h1>Employee Assign List</h1>
		<a href="ManagerEmpServlet">Add Task & Assign Task</a>
		<div>
			<a href="managerDisp1.jsp">Return Home</a><br>
			<br>
		</div>
		<form method="post" action="ManagerEmpServlet">
			 <input type="hidden" name="sbt" value="getAll"> 
			<%-- <input
			type="hidden" name="userId" value="<%=user.getUserId()%>"> --%>
			<input type="submit"  value="Display All Task">
		</form>
		<table border="1">
			<tr>
				<!-- <th>ID</th> -->
				<th>EmpTaskId</th>
				<th>Empployee User Email</th>
				<th>Assigned Task-Id</th>
				<th>Actions</th>
			</tr>
			<%
			session = request.getSession();
			String email = (String)session.getAttribute("userEmail");
			List<EmpTask> empTasks = (ArrayList<EmpTask>) request.getAttribute("empTasks");
			System.out.println("jsp----------" + empTasks);
			if (empTasks != null && !empTasks.isEmpty()) {
				for (EmpTask task : empTasks) {
					if(task.getEmpUserEmail().equals(email))
			%>
			<tr>
				<%-- 	<td><%=user.getUserId()%></td>  --%>
				<td><%=task.getEmpTaskId()%></td>
				<td><%=task.getEmpUserEmail()%></td>
				<td><%=task.getTaskId_fKey()%></td>
				<td>
				<a href="editEmpTask.jsp?empTaskId=<%=task.getEmpTaskId()%>">Edit</a>
					<%-- <form method="post" action="userSevlet">
					<input type="hidden" name="sbt" value="editGetById"> 
					<input
						type="hidden" name="userId" value="<%=user.getUserId()%>">
					<input type="submit"  value="Edit">
			</form>  --%>
				<form method="post" action="ManagerEmpServlet">
					<input type="hidden" name="sbt" value="delete"> <input
						   type="hidden" name="empTaskId" value="<%=task.getEmpTaskId()%>">
					<input type="submit" value="Delete">
				</form> </td>
			</tr>
			<%
				}
			} 
			else {
			%>
			<tr>
				<td colspan="8">No task found.</td>
			</tr>
			<%
			}
			%>
		</table>
		   
		<p>
			<a href="logoutServlet">Back To Login</a>
		</p>

		<%-- Display error message if registration fails --%>
		<%--   <%  String error = request.getParameter("error");
           if (error != null && error.equals("1")) { %>
            <p style="color: red;">Registration failed. Please try again.</p>
        <% } %>  --%>
	 <%		String error = request.getParameter("error");
            System.out.println("error====="+error);
		if (error != null && error.equals("add")) {
			System.out.print("Jsp Error 1 caugth=========ManEmpServ");
		%>
		<p style="color: red;">Already Assigned Task to the selected user! Try to assign other user...</p>
		<%
		}%>		
		
		<%		 error = request.getParameter("error");
            System.out.println("error====="+error);
		if (error != null && error.equals("edit")) {
			System.out.print("Jsp Error 1 caugth=========ManEmpServ");
		%>
		<p style="color: red;">Already Assigned Task to the selected user! Try to assign other user...</p>
		<%
		}%>		
		
			<%		 error = request.getParameter("error");
            System.out.println("error====="+error);
		if (error != null && error.equals("other")) {
			System.out.print("Jsp Error 1 caugth=========ManEmpServ");
		%>
		<p style="color: red;">Something went wrong! Try After Some time </p>
		<%
		}%>		
	</div>

</body>

</html>