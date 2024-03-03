package com.surya.controller;

import java.io.IOException;
import java.util.List;

import com.surya.dao.EmpTaskDao;
import com.surya.dao.EmpTaskDaoImpl;
import com.surya.model.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    EmpTaskDao empTaskDao = new EmpTaskDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> dispEmpTask = empTaskDao.dispAllTask();
		request.setAttribute("dispEmpTask", dispEmpTask);
        System.out.println("doGet>>>>>>employeeList"+dispEmpTask);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employeeDisp1.jsp");
        dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
