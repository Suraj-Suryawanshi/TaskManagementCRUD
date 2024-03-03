package com.surya.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.surya.dao.TaskDao;
import com.surya.dao.TaskDaoImpl;
import com.surya.dao.UserDaoImpl;
import com.surya.model.Task;
import com.surya.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/taskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	User user = new User();
	UserDaoImpl userDao = new UserDaoImpl();
	Task task = new Task();
	TaskDao taskDao= new TaskDaoImpl();
	public TaskServlet() {
		super();
	}
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	List<User> managerList = userDao.managerUserList();
        request.setAttribute("managerList", managerList);
        System.out.println("doGet>>>>>>managerList"+managerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addTask.jsp");
        dispatcher.forward(request, response);



    	//        int taskId = Integer.parseInt(request.getParameter("taskId")); // Get task ID from request parameter
//        Task task=null;
//
//		try {
//			task = taskDao.getTaskById(taskId);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// Get task details from database
//        request.setAttribute("task", task); // Set task object as request attribute
//        System.out.println("servletGet>>>>>>>>"+task);
//        // Forward to edit.jsp
//        RequestDispatcher dispatcher = request.getRequestDispatcher("editTask.jsp");
//        dispatcher.forward(request, response);
    }


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	       String action = request.getParameter("sbt");
	       System.out.println("action>>>>>"+action);
	        if (action != null) {
	        	System.out.println("action call" + action);
	    		if (action != null) {
	    			if ("add".equals(action)) {
	    				try {
							addTask(request, response);
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
	    			} else if ("delete".equals(action)) {
	    				try {
							deleteTask(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							response.sendRedirect("adminDispTask.jsp?error=delete");

						}
	    			} else if ("edit".equals(action)) {
	    				try {
							editTask(request, response);
						} catch (ServletException | IOException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			}
	    			else if("getAll".equals(action)) {
	    				getAll(request, response);
	    			}
	    			else {
	    				response.sendRedirect("adminAddTask.jsp");
	    			}
	    		}

	        }

	    }

	    private void addTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {


	    	Task task = new Task();
	        task.setTaskName(request.getParameter("taskName"));
	        task.setTaskDescription(request.getParameter("taskDescription"));
	        task.setTaskAssignManager(request.getParameter("taskAssignManager"));
	        task.setTaskAssignDate(request.getParameter("taskAssignDate"));
	        task.setTaskDueDate(request.getParameter("taskDueDate"));
	        task.setTaskStatus(request.getParameter("taskStatus"));

	        taskDao.addTask(task);
	        System.out.println("tasksServlet::addTask>>>>>>>>>>>>"+task);
	        getAll(request, response);
//	        response.sendRedirect("adminAddTask.jsp");
	    }

	    private void editTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {


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

	        response.sendRedirect("adminDispTask.jsp");
	    }
	    private void getAll(HttpServletRequest request,HttpServletResponse response ) throws IOException, ServletException {

	        try {
		        List<Task> tasks = taskDao.selectAllTask();
		        System.out.println("taskServlet>>>>>>>"+tasks);
		        request.setAttribute("tasks", tasks);
		        RequestDispatcher rd=request.getRequestDispatcher("adminDispTask.jsp");
		        rd.forward(request, response);
//		        response.sendRedirect("admin/adminDispTask.jsp");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

//	    	response.sendRedirect("adminAddTask.jsp");
	    }

	    @SuppressWarnings("unused")
		private void getTaskById(HttpServletRequest request,HttpServletResponse response ) throws IOException, ServletException {

	        try {
	        	int taskId = Integer.parseInt(request.getParameter("taskId"));
		        Task task = taskDao.getTaskById(taskId);
		        System.out.println("taskServlet>>>>>>>"+task);
		        request.setAttribute("task", task);
		        RequestDispatcher rd=request.getRequestDispatcher("editTask.jsp");
		        rd.forward(request, response);
//		        response.sendRedirect("admin/adminDispTask.jsp");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

//	    	response.sendRedirect("adminAddTask.jsp");
	    }

	    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int taskId = Integer.parseInt(request.getParameter("taskId"));
	        taskDao.deleteTask(taskId);
	        getAll(request, response);


	    }

}
