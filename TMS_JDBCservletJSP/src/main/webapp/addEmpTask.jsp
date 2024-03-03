<%@page import="com.surya.model.Task"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.surya.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Add EmpTask</title>
</head>
<body>
    <h1>Add Task for Employee</h1>
    <button type="button" name="back" onclick="history.back()">back</button>
    <form method="post" action="ManagerEmpServlet">
        <input type="hidden" name="sbt" value="addEmpTask">

        Task-Assign Employee User Email: 
        <select name="empUserEmail">
            <% 
            List<User> employeeList = (ArrayList<User>) request.getAttribute("employeeList");
            if (employeeList != null && !employeeList.isEmpty()) {
                for (User employee : employeeList) {
            %>
            <option value="<%= employee.getUserEmail() %>"><%= employee.getUserName()+":->"+ employee.getUserEmail()%></option>
            <% 
                }
            }
            %>
        </select><br>

        Assigned Task Id: 
        <select name="taskId_fKey">
            <% 
			session = request.getSession();
            String email=(String)session.getAttribute("userEmail");
            String userEmail=email;
            //String userEmail = (String)request.getAttribute("email");
            System.out.println("userEmail===========JSP"+userEmail);
            List<Task> taskIdList = (ArrayList<Task>) request.getAttribute("taskIdList");
            if (taskIdList != null && !taskIdList.isEmpty()) {
                for (Task idList : taskIdList) {
                	if(idList.getTaskAssignManager().equals(userEmail)){
            %>
            <option value="<%= idList.getTaskId() %>"><%= idList.getTaskId()+"->"+idList.getTaskName()%></option>
            <% 
                	}
                }               
            }
            %>
        </select><br>
        <input type="submit" value="Assign Task Emp">
    </form>
    <%
		String error = request.getParameter("error");
		if (error != null && error.equals("add")) {
		%>
		<p style="color: red;">Already Assigned Task to the user! Try to assign another user...</p>
		<%
		}%>
</body>
</html>
