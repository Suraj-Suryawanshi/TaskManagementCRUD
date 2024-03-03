<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">a:link, a:visited {
  background-color: #f44336;
  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}
body{
	display: flex;
	flex-direction:column;
	align-items: center;
	
}
</style>
</head>

<body>
	<h1>Welcome Admin</h1>
	<form method="get" action="userServlet">
		<a href="adminAddUser.jsp" >Add User</a><br><br>
		<a href="adminDispTask.jsp" >Assign Task</a>		
	</form>

</body>
</html>