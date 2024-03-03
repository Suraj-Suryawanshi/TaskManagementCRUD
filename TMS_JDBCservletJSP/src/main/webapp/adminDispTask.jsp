<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.surya.model.Task"%>
<%@ page import="com.surya.dao.TaskDaoImpl"%>
<%@ page import="com.surya.dao.UserDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="register.css">
<title>Admin Task Panel</title>
</head>
<body>

    <div class="container">
	<h1>Task List</h1>
	<a href="taskServlet">Add Task & Assign Task</a>
	<div>
    	<a href="adminDisp1.jsp" >Return Home</a><br><br>
    </div>
	<form method="post" action="taskServlet">
		<input type="hidden" name="sbt" value="getAll">
		<!-- <input type="hidden" name="action" value="getAll">  --><%-- <input
			type="hidden" name="userId" value="<%=user.getUserId()%>"> --%>
		<input type="submit"  value="Get All Task">
	</form>
	<table border="1">
		<tr>
			<!-- <th>ID</th> -->
			<th>TaskName</th>
			<th>Task Description</th>
			<th>Task-Assign Manager-Name</th>
			<th>Task Assign Date</th>
			<th>Task Due Date</th>
			<th>Task Status</th>
			<th>Actions</th>
		</tr>
		<%
		List<Task> tasks = (ArrayList<Task>) request.getAttribute("tasks");
		System.out.println("jsp----------" + tasks);
		if (tasks != null && !tasks.isEmpty()) {
		for (Task task : tasks) {
		%>
		<tr>
		<%-- 	<td><%=user.getUserId()%></td>  --%>
			<td><%=task.getTaskName()%></td>
			<td><%=task.getTaskDescription()%></td>
			<td><%=task.getTaskAssignManager() %>
			<td><%=task.getTaskAssignDate()%></td>
			<td><%=task.getTaskDueDate()%></td>
			<td><%=task.getTaskStatus()%></td>
 			<td><a href="editTask.jsp?taskId=<%=task.getTaskId()%>">Edit</a> 
 			<%-- <form method="post" action="userSevlet">
					<input type="hidden" name="sbt" value="editGetById"> 
					<input
						type="hidden" name="userId" value="<%=user.getUserId()%>">
					<input type="submit"  value="Edit">
			</form>  --%>
			<form method="post" action="taskServlet">
					<input type="hidden" name="sbt" value="delete"> <input
						type="hidden" name="taskId" value="<%=task.getTaskId()%>">
					<input type="submit"  value="Delete">
			</form></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="8">No task found.</td>
		</tr>
		<%
		}
		%>
	</table>
        <p><a href="logoutServlet">Back To Login</a></p>
    
        <%-- Display error message if registration fails --%>
      <%--   <%  String error = request.getParameter("error");
           if (error != null && error.equals("1")) { %>
            <p style="color: red;">Registration failed. Please try again.</p>
        <% } %>  --%>
         <%  String error = request.getParameter("error");
           if (error != null && error.equals("delete")) { %>
            <p style="color: red;">Unable to delete since feature not deleted by manager.</p>
        <% } %> 
      </div>
      
</body>
 
</html>