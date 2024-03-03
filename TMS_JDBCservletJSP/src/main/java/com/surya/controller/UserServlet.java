package com.surya.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.surya.dao.UserDao;
import com.surya.dao.UserDaoImpl;
import com.surya.model.User;
import com.surya.util.PasswordEncryptionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	User user = new User();
	UserDao userDao= new UserDaoImpl();
//    public UserServlet() {
//        super();
//    }





    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId")); // Get task ID from request parameter
        User user= userDao.getUserById(userId); // Get task details from database
        request.setAttribute("user", user); // Set task object as request attribute
        System.out.println("servletGet>>>>>>>>"+user);
        // Forward to edit.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
        dispatcher.forward(request, response);
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		response.getWriter().append("Served at: ").append(request.getContextPath());
//		 try {
//		        List<User> users = userDao.selectAllUsers();
//		        System.out.println("userServlet>>>>>>>"+users);
//		        request.setAttribute("users", users);
//		        RequestDispatcher rd=request.getRequestDispatcher("adminAddUser.jsp");
//		        rd.forward(request, response);
////		        response.sendRedirect("admin/adminDispTask.jsp");
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	       String action = request.getParameter("sbt");
	       System.out.println("action>>>>>"+action);
	        if (action != null) {
	        	System.out.println("action call" + action);
	    		if (action != null) {
	    			if ("add".equals(action)) {
	    				addUser(request, response);
	    			} else if ("delete".equals(action)) {
	    				try {
							deleteUser(request, response);
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			} else if ("edit".equals(action)) {
	    				try {
							editUser(request, response);
						} catch (ServletException | IOException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			}
	    			else if("getAll".equals(action)) {
	    				getAll(request, response);
	    			}
	    			else if("filterByDate".equals(action)) {
	    				getAllByDates(request, response);
	    			}
	    			else {
	    				response.sendRedirect("adminAddUser.jsp");
	    			}
	    		}
//	    		else {
//	    			response.sendRedirect("adminAddUser.jsp"); // Redirect to index.jsp if no action parameter is provided
//	    		}

	        }

	    }

	    private void getAllByDates(HttpServletRequest request, HttpServletResponse response) {

	        try {
	        	String startDate=request.getParameter("startDate");
	        	String endDate=request.getParameter("endDate");

		        List<User> users = userDao.UsersByMonthYear(startDate,endDate);
		        System.out.println("userServlet>>>>>>>"+users);
		        request.setAttribute("users", users);
		        RequestDispatcher rd=request.getRequestDispatcher("adminAddUser.jsp");
		        rd.forward(request, response);
//		        response.sendRedirect("admin/adminDispTask.jsp");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }


}

		private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        User user = new User();
			String plainPassword = request.getParameter("userPassword");
			String userPassword = PasswordEncryptionUtil.encrypt(plainPassword);
	        user.setUserRole(request.getParameter("userRole"));
	        user.setUserName(request.getParameter("userName"));
	        user.setUserEmail(request.getParameter("userEmail"));
	        user.setUserDob(request.getParameter("userDob"));
	        user.setUserJoiningDate(request.getParameter("userJoiningDate"));
	        user.setUserSalary(Integer.parseInt(request.getParameter("userSalary")));
	        user.setUserAddress(request.getParameter("userAddress"));
	        user.setUserPassword(userPassword);

	        userDao.addUser(user);
	        System.out.println("addUser--------------"+user);
	        getAll(request, response);
//	        response.sendRedirect("adminAddUser.jsp");
	    }

	    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

	        System.out.println("userServlet::editUser>>>>call");
//	        String plainPassword = request.getParameter("userPassword");

		        int userId = Integer.parseInt(request.getParameter("userId"));
		        String userRole=request.getParameter("userRole");
		        String userName=request.getParameter("userName");
		        String userEmail=request.getParameter("userEmail");
		        String userDob=request.getParameter("userDob");
		        String userJoiningDate=request.getParameter("userJoiningDate");
		        String userAddress=request.getParameter("userAddress");
//		        String userPassword = PasswordEncryptionUtil.encrypt(plainPassword);
		        String userPassword=request.getParameter("userPassword");
		        int userSalary = Integer.parseInt(request.getParameter("userSalary"));
	        	User user=new User(userId, userName, userPassword, userEmail, userDob, userRole,userJoiningDate, userSalary, userAddress)  ;
	        	System.out.println("servlet>>>>>"+user);
	            // Update user in the userService
	            userDao.updateUser(user);
//	        }

	        response.sendRedirect("adminAddUser.jsp");
	    }
	    private void getAll(HttpServletRequest request,HttpServletResponse response ) throws IOException, ServletException {

	        try {
		        List<User> users = userDao.selectAllUsers();
		        System.out.println("userServlet>>>>>>>"+users);
		        request.setAttribute("users", users);
		        RequestDispatcher rd=request.getRequestDispatcher("adminAddUser.jsp");
		        rd.forward(request, response);
//		        response.sendRedirect("admin/adminDispTask.jsp");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

//	    	response.sendRedirect("adminAddUser.jsp");
	    }

	    @SuppressWarnings("unused")
		private void getUserById(HttpServletRequest request,HttpServletResponse response ) throws IOException, ServletException {

	        try {
	        	int userId = Integer.parseInt(request.getParameter("userId"));
		        User user = userDao.getUserById(userId);
		        System.out.println("userServlet>>>>>>>"+user);
		        request.setAttribute("user", user);
		        RequestDispatcher rd=request.getRequestDispatcher("editUser.jsp");
		        rd.forward(request, response);
//		        response.sendRedirect("admin/adminDispTask.jsp");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

//	    	response.sendRedirect("adminAddUser.jsp");
	    }
//	    private User getUserById(HttpServletRequest request,HttpServletResponse response) {
//	    	return user
//	    }

	    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int userId = Integer.parseInt(request.getParameter("userId"));
	        userDao.deleteUser(userId);
	        getAll(request, response);

//	        try {
//		        List<User> users = userDao.selectAllUsers();
//		        System.out.println("userServlet>>>>>>>"+users);
//		        request.setAttribute("users", users);
//		        RequestDispatcher rd=request.getRequestDispatcher("adminAddUser.jsp");
//		        rd.forward(request, response);
////		        response.sendRedirect("admin/adminDispTask.jsp");
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//	        response.sendRedirect("adminAddUser.jsp");
	    }


}




