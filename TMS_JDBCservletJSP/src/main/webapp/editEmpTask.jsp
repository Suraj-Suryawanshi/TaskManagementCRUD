<%@page import="com.surya.model.Task"%>
<%@page import="com.surya.dao.UserDaoImpl"%>
<%@page import="com.surya.model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.surya.model.EmpTask"%>
<%@page import="com.surya.dao.EmpTaskDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.surya.model.Task" %>
<%@ page import="com.surya.dao.TaskDao"%>
<%@ page import="com.surya.dao.UserDao"%>
<%@ page import="com.surya.dao.EmpTaskDao"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.surya.dao.TaskDaoImpl"%>

<%
UserDao userDao= new UserDaoImpl();
TaskDao taskDao = new TaskDaoImpl();
EmpTaskDao empTaskDao = new EmpTaskDaoImpl();
System.out.println();
List<User> employeeList = userDao.employeeUserList();
List<Task> taskIdList = taskDao.selectAllTask();
//request.setAttribute("employeeList", employeeList);
//request.setAttribute("taskIdList", taskIdList);


EmpTaskDaoImpl empTaskDaoImpl = new EmpTaskDaoImpl();
int empTaskId = Integer.parseInt(request.getParameter("empTaskId"));
EmpTask empTask = empTaskDaoImpl.getEmpTaskById(empTaskId);
//User user = (User) request.getAttribute("user");
System.out.println(empTask);

//UserDaoImpl userDao = new UserDaoImpl();
//List<User> employeeList = userDao.employeeUserList();
//request.setAttribute("managerList", managerList);
System.out.println("Jsp=======managerList"+employeeList);

%>


<html>
<head>
<title>Edit Task</title>
</head>
<body>
	<h1>Edit Task</h1>
	<button type="button" name="back" onclick="history.back()">Back</button>
	<form method="post" action="ManagerEmpServlet">
		<input type="hidden" name="sbt" value="edit">
	
		EmpTaskID: <input type="text" name="empTaskId" value="<%= empTask.getEmpTaskId() %>" readonly><br>
		Task Assign-Employee: 
        <select name="taskAssignManager">
            <% 
            //List<User> employeeList = (ArrayList<User>) request.getAttribute("employeeList");
            if (employeeList != null && !employeeList.isEmpty()) {
                for (User employee : employeeList) {
            %>
            <option value="<%= employee.getUserEmail() %>"><%= employee.getUserName()+":->"+employee.getUserEmail()%></option>
            <%-- <option value="<%=task.getTaskAssignManager() %>"
				<%=task.getTaskAssignManager().equals("<%=task.get%>") ? "selected" : ""%>><%= manager.getUserName()+":->"+manager.getUserEmail()%></option>
 --%>            <% 
                }
            }
            %>
        </select><br>
        
        Task Assign-Id: 
            Assigned Task Id: 
        <select name="taskId_fKey">
            <% 
			session = request.getSession();
            String email=(String)session.getAttribute("userEmail");
            String userEmail=email;
            //String userEmail = (String)request.getAttribute("email");
            System.out.println("userEmail===========JSP"+userEmail);
            //List<Task> taskIdList = (ArrayList<Task>) request.getAttribute("taskIdList");
            System.out.println("taskIdList:::JSP======="+taskIdList);
            if (taskIdList != null && !taskIdList.isEmpty()) {
                for (Task idList : taskIdList) {
                	if(idList.getTaskAssignManager().equals(userEmail)){
            %>
            <option value="<%= idList.getTaskId() %>"><%= "TaskId:"+idList.getTaskId()+"->TaskName:"+idList.getTaskName()%></option>
            <% 
                	}
                }               
            }
            %>
        </select><br>
 
        
		<br> <input type="submit" value="Submit">
	</form>
</body>
</html>