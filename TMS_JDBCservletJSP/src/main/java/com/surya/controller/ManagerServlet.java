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

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao= new UserDaoImpl();
	TaskDao taskDao = new TaskDaoImpl();
	EmpTaskDao empTaskDao = new EmpTaskDaoImpl();


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			String email = (String)request.getAttribute("userEmail");
			System.out.println();
			request.setAttribute("email", email);
			List<User> employeeList = userDao.employeeUserList();
			List<Task> taskIdList = taskDao.selectAllTask();
			request.setAttribute("employeeList", employeeList);
			request.setAttribute("taskIdList", taskIdList);
			System.out.println("doGet>>>>>>employeeList"+employeeList);
			System.out.println("doGet>>>>>>taskIdList"+taskIdList);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("editEmpTask.jsp");
	        dispatcher.forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("AssignEmpTask.jsp");
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	       String action = request.getParameter("sbt");
	       System.out.println("action>>>>>"+action);
	        if (action != null) {
	        	System.out.println("action call" + action);
	    		if (action != null) {
	    			if ("add".equals(action)) {

							addTask(request, response);

	    			} else if ("delete".equals(action)) {

							deleteTask(request, response);

	    			} else if ("editStatus".equals(action)) {
	    				try {
							editTaskStatus(request, response);
						} catch (ServletException | IOException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			}
	    			else if("edit".equals(action)) {
	    				try {
							editTask(request,response);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			}
	    			else if("getAll".equals(action)) {
	    				getManagerTask(request, response);
	    			}
	    			else {
	    				response.sendRedirect("ViewAssignTask.jsp");
	    			}
	    		}

	        }

	    }


    private void editTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("taskServlet::editTask>>>>call");

        int empTaskId = Integer.parseInt(request.getParameter("empTaskId"));
        String empUserEmail=request.getParameter("empUserEmail");
        int taskId_fKey = Integer.parseInt(request.getParameter("taskId_fKey"));
    	EmpTask empTask=new EmpTask(empTaskId, empUserEmail, taskId_fKey)  ;
    	System.out.println("servlet>>>>>"+empTask);
        // Update task in the taskService
        empTaskDao.editEmpTask(empTask);
//    }

    response.sendRedirect("AssignEmpTask.jsp");


	}

	private void deleteTask(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void addTask(HttpServletRequest request, HttpServletResponse response) {


	}

	private void getManagerTask(HttpServletRequest request,HttpServletResponse response ) throws IOException, ServletException {

        try {
        	HttpSession session = request.getSession();
    		String userEmail=(String)session.getAttribute("userEmail");
    		String email=userEmail;
//    		request.setAttribute("email", email);
    		System.out.println("\n\nJSP======="+email+"\n\n");
    		Task task = taskDao.getTaskByManagerName(email);
    		System.out.println("taskByManagerName::ManagerServlet"+task);
    		request.setAttribute("task", task);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAssignTask.jsp");
    		dispatcher.forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }



	    private void editTaskStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {


	        System.out.println("taskServlet::editTask>>>>call");

		        int taskId = Integer.parseInt(request.getParameter("taskId"));
		        String taskName=request.getParameter("taskName");
		        String taskDescription=request.getParameter("taskDescription");
		        String taskAssignManager=request.getParameter("taskAssignManager");
		        String taskAssignDate=request.getParameter("taskAssignDate");
		        String taskDueDate=request.getParameter("taskDueDate");
		        String taskStatus=request.getParameter("taskStatus");
	        	Task task=new Task(taskId, taskName, taskDescription, taskAssignManager, taskAssignDate, taskDueDate, taskStatus )  ;
	        	System.out.println("servlet>>>>>"+task);
	            // Update task in the taskService
	            taskDao.updateTask(task);
//	        }

	        response.sendRedirect("ViewAssignTask.jsp");

	}
}
