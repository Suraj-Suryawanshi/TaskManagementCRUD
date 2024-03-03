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

%>


<html>
<head>
<title>Edit Task</title>
</head>
<body>
	<h1>Edit Task</h1>
	 <button type="button" name="Back" onclick="history.back()">back</button>
	<form method="post" action="employeeServlet">
		taskID: <input type="text" name="taskId" value="<%= task.getTaskId() %>" readonly><br>
	 
	
		Task-Name: <input type="text" name="taskName"
			value="<%=task.getTaskName()%>" readonly><br> 
		Task-Description: <input type="text" name="taskDescription" value="<%=task.getTaskDescription()%>" readonly><br>
		Task Assign-Manager: 
		<input type="text" name="taskAssignManager" value="<%=task.getTaskAssignManager()%>" readonly><br>
        <%-- <select name="taskAssignManager" readonly>
            <% 
             //managerList = (ArrayList<User>) request.getAttribute("managerList");
            if (managerList != null && !managerList.isEmpty()) {
                for (User manager : managerList) {
            %>
            <option value="<%= manager.getUserEmail() %>" readonly><%= manager.getUserName()+":->"+manager.getUserEmail()%></option >
            <option value="<%=task.getTaskAssignManager() %>"
				<%=task.getTaskAssignManager().equals("<%=task.get%>") ? "selected" : ""%>><%= manager.getUserName()+":->"+manager.getUserEmail()%></option>
            <% 
                }
            }
            %>
        </select><br> --%>
		Task Assign-Date: <input type="date" name="taskAssignDate" value="<%=LocalDate.parse(task.getTaskAssignDate())%>" readonly><br>
		Task Due-Date: <input type="date" name="taskDueDate" value="<%=LocalDate.parse(task.getTaskDueDate())%>" readonly><br>
		Task Status: 
		<select name="taskStatus">
			<option value="InComplete"
				<%=task.getTaskStatus().equals("InComplete") ? "selected" : ""%>>InComplete</option>
			<option value="Complete"
				<%=task.getTaskStatus().equals("Complete") ? "selected" : ""%>>Completed</option>
			
		</select><br> 


		<%-- Completed: <input type="checkbox" name="completed" value="<%= task.getCompleted()%>"> --%>
		<br> <input type="submit" name="sbt" value="editStatus">
	</form>
</body>
</html>