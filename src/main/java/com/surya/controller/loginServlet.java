package com.surya.controller;

import java.io.IOException;

import com.surya.dao.UserDao;
import com.surya.dao.UserDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	/****/
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate(); // Invalidate the session
	        }
	        response.sendRedirect("index.jsp"); // Redirect to the login page
	    
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String userRole = request.getParameter("userRole");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");

		UserDao userDao = new UserDaoImpl();

		System.out.println("outCheck>>>>>" + userRole+userEmail+userPassword);
		System.out.println("logCheck>>>>"+userDao.isValidUser(userEmail, userRole, userPassword));
		if (userDao.isValidUser( userEmail, userRole, userPassword)) {
			
			 HttpSession session = request.getSession();
			 session.setAttribute("userRole", userRole); 
			
			 System.out.println("inCheck>>>>>");
			if (userRole.equals("Admin")) {
				request.getRequestDispatcher("adminDisp1.jsp").forward(request, response);
			}
			else if (userRole.equals("Manager")) {
				request.getRequestDispatcher("managerDisp1.jsp").forward(request, response);
			}
			else if(userRole.equals("Employee")){
				request.getRequestDispatcher("employeeDisp.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("index.jsp?error=1");
				System.out.println("Something went wrong!");

			}
//		            HttpSession session = request.getSession();
//		            session.setAttribute("userRole", userRole);
//		            response.sendRedirect("addEmp.jsp");
			// System.out.println("Hi - "+username);
		}
		else {
			response.sendRedirect("index.jsp?error=1&name=2");
			System.out.println("Something went wrong!");
		}
	}
}