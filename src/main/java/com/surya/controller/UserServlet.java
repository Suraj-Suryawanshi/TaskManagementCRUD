package com.surya.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.surya.model.User;
import com.surya.dao.UserDaoImpl;
import com.surya.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	User user = new User();
	UserDao userDao= new UserDaoImpl();
    public UserServlet() {
        super();
    }
    
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
	    			else {
	    				response.sendRedirect("adminAddUser.jsp");
	    			}
	    		} 
//	    		else {
//	    			response.sendRedirect("adminAddUser.jsp"); // Redirect to index.jsp if no action parameter is provided
//	    		}
	            
	        }
	
	    }

	    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        User user = new User();
	        user.setUserRole(request.getParameter("userRole"));
	        user.setUserName(request.getParameter("userName"));
	        user.setUserEmail(request.getParameter("userEmail"));
	        user.setUserDob(request.getParameter("userDob"));
	        user.setUserJoiningDate(request.getParameter("userJoiningDate"));
	        user.setUserSalary(Integer.parseInt(request.getParameter("userSalary")));
	        user.setUserAddress(request.getParameter("userAddress"));
	        user.setUserPassword(request.getParameter("userPassword"));

	        userDao.addUser(user);
	        System.out.println("addUser--------------"+user);
	        getAll(request, response);
//	        response.sendRedirect("adminAddUser.jsp");
	    }

	    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int userId = Integer.parseInt(request.getParameter("userId"));
//	        User user = null;

	        // Find the user with the given userId
	        List<User> userList = userDao.selectAllUsers();
	        for (User u : userList) {
	            if (u.getUserId() == userId) 
	            {
	                user = u;
	                break;
	            }
	        }

	        if (user != null) {
	            // Update user details
	        
		        user.setUserRole(request.getParameter("userRole"));
		        user.setUserName(request.getParameter("userName"));
		        user.setUserEmail(request.getParameter("userEmail"));
		        user.setUserDob(request.getParameter("userDob"));
		        user.setUserJoiningDate(request.getParameter("userJoiningDate"));
		        user.setUserSalary(Integer.parseInt(request.getParameter("userSalary")));
		        user.setUserAddress(request.getParameter("userAddress"));
		        user.setUserPassword(request.getParameter("userPassword"));

	            // Update user in the userService
	            userDao.updateUser(user);
	        }

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


