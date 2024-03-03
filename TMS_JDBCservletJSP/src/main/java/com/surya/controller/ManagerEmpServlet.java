package com.surya.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.surya.dao.EmpTaskDao;
import com.surya.dao.EmpTaskDaoImpl;
import com.surya.dao.TaskDao;
import com.surya.dao.TaskDaoImpl;
import com.surya.dao.UserDao;
import com.surya.dao.UserDaoImpl;
import com.surya.model.EmpTask;
import com.surya.model.Task;
import com.surya.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ManagerEmpServlet")
public class ManagerEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao = new UserDaoImpl();
	TaskDao taskDao = new TaskDaoImpl();
	EmpTaskDao empTaskDao = new EmpTaskDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			String email = (String) request.getAttribute("userEmail");
			System.out.println();
			request.setAttribute("email", email);
			List<User> employeeList = userDao.employeeUserList();
			List<Task> taskIdList = taskDao.selectAllTask();
			request.setAttribute("employeeList", employeeList);
			request.setAttribute("taskIdList", taskIdList);
			System.out.println("doGet>>>>>>employeeList" + employeeList);
			System.out.println("doGet>>>>>>taskIdList" + taskIdList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("addEmpTask.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("sbt");
		System.out.println("action>>>>>" + action);
		if (action != null) {
			System.out.println("action call" + action);

			if ("getAll".equals(action)) {

				try {
					getAllEmpTask(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if ("addEmpTask".equals(action)) {

				try {
					addTask(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("ExceptionCaought in add ManagerEmp>>>>");
					e.printStackTrace();
					response.sendRedirect("AssignEmpTask.jsp?error=add");
				}

			} else if ("edit".equals(action)) {
				try {
					editTask(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Caught in edit>>>>");
					response.sendRedirect("AssignEmpTask.jsp?error=edit");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("AssignEmpTask.jsp?error=other");

				}
			}

			else if ("delete".equals(action)) {
				System.out.println("methodChecked Delete");
			try {
				deleteTask(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}

		else {
			response.sendRedirect("ViewAssignTask.jsp");
		}

	}

	private void getAllEmpTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<EmpTask> empTasks = empTaskDao.selectAllEmpTask();
			System.out.println("taskServlet>>>>>>>" + empTasks);
			request.setAttribute("empTasks", empTasks);
			RequestDispatcher rd = request.getRequestDispatcher("AssignEmpTask.jsp");
			rd.forward(request, response);
//		        response.sendRedirect("admin/adminDispTask.jsp");
		} catch (Exception e) {
			System.out.println("catch>>>manEmpSer");
			e.printStackTrace();
			response.sendRedirect("AssignEmpTask.jsp");
		}

	}

	private void editTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("taskServlet::editTask>>>>call");

		int empTaskId = Integer.parseInt(request.getParameter("empTaskId"));
		String empUserEmail = request.getParameter("empUserEmail");
		int taskId_fKey = Integer.parseInt(request.getParameter("taskId_fKey"));

		EmpTask empTask = new EmpTask(empTaskId, empUserEmail, taskId_fKey);
		System.out.println("servlet>>>>>" + empTask);
		// Update task in the taskService

			empTaskDao.editEmpTask(empTask);
			response.sendRedirect("AssignEmpTask.jsp");
//       }

	}

	private void deleteTask(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, NumberFormatException {
		int empTaskId = Integer.parseInt(request.getParameter("empTaskId"));
		try {
			empTaskDao.deleteEmpTask(empTaskId);
			getAllEmpTask(request, response);
		} catch (Exception e) {
			System.out.println("Exception caught in >>>> deleteEmpTask");
			e.printStackTrace();
		}

	}

	private void addTask(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		EmpTask empTask = new EmpTask();

			System.out.println("Inside try");
			empTask.setEmpUserEmail(request.getParameter("empUserEmail"));
			empTask.setTaskId_fKey(Integer.parseInt(request.getParameter("taskId_fKey")));

			empTaskDao.addEmpTask(empTask);
			System.out.println("tasksServlet::addTask>>>>>>>>>>>>" + empTask);
			RequestDispatcher rd = request.getRequestDispatcher("AssignEmpTask.jsp");
			rd.forward(request, response);





	}

}
