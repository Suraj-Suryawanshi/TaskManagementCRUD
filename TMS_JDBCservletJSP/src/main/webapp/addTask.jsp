<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.surya.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Add User</title>
</head>
<body>
    <h1>Add User</h1>
     <button type="button" name="back" onclick="history.back()">back</button>
    <form method="post" action="taskServlet">
        <input type="hidden" name="sbt" value="add">
        Task-Name: <input type="text" name="taskName"><br>
        Task-Description: <input type="text" name="taskDescription"><br>
        Task Assign-Manager: 
        <select name="taskAssignManager">
            <% 
            List<User> managerList = (ArrayList<User>) request.getAttribute("managerList");
            if (managerList != null && !managerList.isEmpty()) {
                for (User manager : managerList) {
            %>
            <option value="<%= manager.getUserEmail() %>"><%= manager.getUserName()+":->"+manager.getUserEmail()%></option>
            <% 
                }
            }
            %>
        </select><br>
        Task Assign-Date: <input type="date" name="taskAssignDate"><br>
        Task Due-Date: <input type="date" name="taskDueDate"><br>
        Task Status:  <select name="taskStatus">
            <option value="InComplete">InComplete</option>
            <option value="Complete">Complete</option>
            <option value="OnHold">OnHold</option>
        </select><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
