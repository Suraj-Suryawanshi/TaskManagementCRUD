<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
    <h1>Add User</h1>
    <form method="post" action="userServlet">
        <input type="hidden" name="sbt" value="add">
           
        Role: 
        <select name="userRole">
            <option value="Admin">Admin</option>
            <option value="Manager">Manager</option>
            <option value="Employee">Employee</option>
        </select><br>
        UserName: <input type="text" name="userName"><br>
        Email: <input type="email" name="userEmail"><br>
        Date of Birth: <input type="date" name="userDob"><br>
        Date of Joining: <input type="date" name="userJoiningDate"><br>
        User Salary: <input type="text" name="userSalary"><br>
        Address: <input type="text" name="userAddress"><br>
        Password: <input type="password" name="userPassword"><br>
        <input type="submit"  value="Submit">
    </form>
</body>
</html>
