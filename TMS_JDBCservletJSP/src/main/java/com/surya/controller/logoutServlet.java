package com.surya.controller;

import java.io.IOException;

import com.surya.dao.UserDao;
import com.surya.dao.UserDaoImpl;
import com.surya.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logoutServlet
 */

@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	User user = new User();
	UserDao userDao= new UserDaoImpl();
    public logoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
//	 HttpSession session = request.getSession();
////	 session.removeAttribute("");
//	 if (session != null) {
//		 System.out.println("session"+session.getAttribute("Role"));
//	 session.invalidate(); // Invalidate the session }
//	 System.out.println("session"+session.getAttribute("Role"));
//	 System.out.println("session Invalidated Check"+session.toString());

     response.sendRedirect("index.jsp"); // Redirect to the login page

//	 }
	 }

}
