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
	<% //String email=(String)session.getAttribute("userEmail"); 
		//System.out.println("\n\nJSP======="+email+"\n\n");
	%>
    <div class="container">
	<h1>Assigned Task By Admin</h1>
	<!-- 
	<a href="taskServlet">Add Task & Assign Task</a> -->
	<div>
    	<a href="managerDisp1.jsp" >Return Home</a><br><br>
    </div>
	<form method="post" action="ManagerServlet">
		<input type="hidden" name="sbt" value="getAll"> 
		<input type="submit"  value="View My Task">
	</form>
	<table border="1">
		<tr>
			<th>Task-ID</th> 
			<th>TaskName</th>
			<th>Task Description</th>
			<th>Task-Assign Manager-Name</th>
			<th>Task Assign Date</th>
			<th>Task Due Date</th>
			<th>Task Status</th>
			<th>Actions</th>
		</tr>
		<%
		Task task = (Task) request.getAttribute("task");
		System.out.println("jsp::viewAssignTask----------" + task);
		if (task != null ) {
		//for (Task task : tasks) {
			//if(email.equals((String)task.getTaskAssignManager())){
			
				%>
		<tr>
			<td><%=task.getTaskId()%></td>
			<td><%=task.getTaskName()%></td>
			<td><%=task.getTaskDescription()%></td>
			<td><%=task.getTaskAssignManager() %></td>
			<td><%=task.getTaskAssignDate()%></td>
			<td><%=task.getTaskDueDate()%></td>
			<td><%=task.getTaskStatus()%></td>
 			<td><a href="editTaskManager.jsp?taskId=<%=task.getTaskId()%>">Edit</a> 
 			<%-- <form method="post" action="userSevlet">
					<input type="hidden" name="sbt" value="editGetById"> 
					<input
						type="hidden" name="userId" value="<%=user.getUserId()%>">
					<input type="submit"  value="Edit">
			</form>  --%>
		<%-- 	<form method="post" action="taskServlet">
					<input type="hidden" name="sbt" value="delete"> <input
						type="hidden" name="taskId" value="<%=task.getTaskId()%>">
					<input type="submit"  value="Delete">
			</form></td> --%>
		</tr>
		<%
		
		} 
		
		else {
		%>
		<tr>
			<td colspan="8">No Task Assigned Yet...</td>
		</tr>
		<%
		}
		%>
	</table>
        <p><a href="index.jsp">Back To Login</a></p>
    
        <%-- Display error message if registration fails --%>
      <%--   <%  String error = request.getParameter("error");
           if (error != null && error.equals("1")) { %>
            <p style="color: red;">Registration failed. Please try again.</p>
        <% } %>  --%>
        
      </div>
      
</body>
 
</html>