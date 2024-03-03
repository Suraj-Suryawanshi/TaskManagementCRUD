<%@page import="com.surya.dao.UserDaoImpl"%>
<%@page import="com.surya.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.surya.model.Task"%>
<%@page import="com.surya.dao.TaskDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.surya.dao.TaskDao"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.surya.dao.TaskDaoImpl"%>

<%
TaskDaoImpl taskDaoImpl = new TaskDaoImpl();
int taskId = Integer.parseInt(request.getParameter("taskId"));
Task task = taskDaoImpl.getTaskById(taskId);
//User user = (User) request.getAttribute("user");
System.out.println(task);

UserDaoImpl userDao = new UserDaoImpl();
List<User> managerList = userDao.managerUserList();
//request.setAttribute("managerList", managerList);
System.out.println("Jsp=======managerList"+managerList);
/* 
    if (task == null) {
        // Handle task not found error
        response.sendRedirect("admin/admin1.jsp");
        return;
    }
 */
%>


<html>
<head>
<title>Edit Task</title>
</head>
<body>
	<h1>Edit Task</h1>
	 <button type="button" name="back" onclick="history.back()">Back</button>
	<form method="post" action="taskServlet">
	<input type="hidden" name="sbt" value="edit">
	
	
		TaskID: <input type="text" name="taskId" value="<%= task.getTaskId() %>" readonly><br>
	 
		<%--    <select name="myList">
<% for (String value : myValues) { %>
<option value="<%= value %>"><%= value %></option>
<% } %>
</select> --%>

<%-- 		UserRole:
		<select name="userRole">
			<option value="Admin"
				<%=user.getUserRole().equals("Admin") ? "selected" : ""%>>Admin</option>
			<option value="Manager"
				<%=user.getUserRole().equals("Manager") ? "selected" : ""%>>Manager</option>
			<option value="Employee"
				<%=user.getUserRole().equals("Employee") ? "selected" : ""%>>Employee</option>
		</select><br> --%> 
		Task-Name: <input type="text" name="taskName"
			value="<%=task.getTaskName()%>"><br> 
		Task-Description: <input type="text" name="taskDescription" value="<%=task.getTaskDescription()%>"><br>
		Task Assign-Manager: 
        <select name="taskAssignManager">
            <% 
             //managerList = (ArrayList<User>) request.getAttribute("managerList");
            if (managerList != null && !managerList.isEmpty()) {
                for (User manager : managerList) {
            %>
            <option value="<%= manager.getUserEmail() %>"><%= manager.getUserName()+":->"+manager.getUserEmail()%></option>
            <%-- <option value="<%=task.getTaskAssignManager() %>"
				<%=task.getTaskAssignManager().equals("<%=task.get%>") ? "selected" : ""%>><%= manager.getUserName()+":->"+manager.getUserEmail()%></option>
 --%>            <% 
                }
            }
            %>
        </select><br>
		Task Assign-Date: <input type="date" name="taskAssignDate" value="<%=LocalDate.parse(task.getTaskAssignDate())%>"><br>
		Task Due-Date: <input type="date" name="taskDueDate" value="<%=LocalDate.parse(task.getTaskDueDate())%>"><br>
		Task Status: <select name="taskStatus">
			<option value="InComplete"
				<%=task.getTaskStatus().equals("InComplete") ? "selected" : ""%>>InComplete</option>
			<option value="Complete"
				<%=task.getTaskStatus().equals("Complete") ? "selected" : ""%>>Complete</option>
			<option value="OnHold"
				<%=task.getTaskStatus().equals("OnHold") ? "selected" : ""%>>On-Hold</option>
		</select><br> 


		<%-- Completed: <input type="checkbox" name="completed" value="<%= task.getCompleted()%>"> --%>
		<br> <input type="submit" value="Submit">
	</form>
</body>
</html>