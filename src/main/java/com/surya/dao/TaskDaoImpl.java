package com.surya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.surya.model.Task;
import com.surya.util.DButil;

public class TaskDaoImpl implements TaskDao {

	@Override
	public boolean updateTask(Task task) throws SQLException {
		boolean rowUpdated;
		 String query = "update task set taskName = ?, taskDescription=?, taskAssignManager=?, taskAssignDate=?, taskDueDate=? where taskId=?";
	       System.out.println("user update----");
	       Connection connection = DButil.getConnection();
	        try (
	            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        	preparedStatement.setString(1, task.getTaskName());
	        	preparedStatement.setString(2, task.getTaskDescription());
	        	preparedStatement.setString(3, task.getTaskAssignManager());
	            preparedStatement.setString(4, task.getTaskAssignDate());
	            preparedStatement.setString(5, task.getTaskDueDate());
	            preparedStatement.setString(6, task.getTaskStatus());

	            preparedStatement.setInt(7, task.getTaskId());
	            rowUpdated = preparedStatement.executeUpdate() > 0;
	            return rowUpdated;
	        }

	        catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        finally {
	        	connection.close();
	        }
	}

	@Override
	public Task getTaskById(int taskId) throws SQLException {
		 	Task task = null;
		    String query = "SELECT * FROM task WHERE taskId=?";
		    Connection connection = DButil.getConnection();
		    try (
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        System.out.println("UserId-------"+taskId);
		        preparedStatement.setInt(1, taskId);
		        ResultSet rs = preparedStatement.executeQuery();
		        System.out.println("ResultSEt-----"+rs.toString());
		        if (rs.next()) {
		        	int id = rs.getInt("taskId");
	                String taskName = rs.getString("taskName");
	                String taskDescription = rs.getString("taskDescription");
	                String taskAssignManager = rs.getString("taskAssignManager");
	                String taskAssignDate = rs.getString("taskAssignDate");
	                String taskDueDate = rs.getString("taskDueDate");
	                String taskStatus = rs.getString("taskStatus");
	                 task=new Task(id, taskName, taskDescription, taskAssignManager, taskAssignDate, taskDueDate, taskStatus);
	                System.out.println("\nsGetById::userDaoImpl-------"+task);
//	                return task;
		        }
		    }
		    catch (SQLException e) {
		        e.printStackTrace();
		        // Handle exception
		    }
		    finally {
		    	connection.close();
		    	}
		    System.out.println("-----------"+task);

		    return task;
	}

	@Override
	public List<Task> selectAllTask() throws SQLException {
		System.out.println("selectAllTask::Call----------");
    	String query= "select * from task";
        List < Task > tasks = new ArrayList < > ();
        // Step 1: Establishing a Connection
        Connection connection = DButil.getConnection();
        try (
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("selectAllTask::ResultSet--------"+rs);
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("taskId");
                String taskName = rs.getString("taskName");
                String taskDescription = rs.getString("taskDescription");
                String taskAssignManager = rs.getString("taskAssignManager");
                String taskAssignDate = rs.getString("taskAssignDate");
                String taskDueDate = rs.getString("taskDueDate");
                String taskStatus = rs.getString("taskStatus");
                tasks.add(new Task(id, taskName, taskDescription, taskAssignManager, taskAssignDate, taskDueDate, taskStatus));
                System.out.println("\nselectAll::userDaoImpl-------"+tasks);
                return tasks;
            }
        }
		        catch (SQLException e) {
		//            printSQLException(e);
		        	e.printStackTrace();

		        }
//		        finally {
//					connection.close();
//				}
				return tasks;

}

	@Override
	public boolean addTask(Task task) throws SQLException {
		   String query = "INSERT INTO task(taskName, taskDescription, taskAssignManager,taskAssignDate, taskDueDate, taskStatus ) VALUES (?, ?, ?, ?, ?, ?)";
	       System.out.println("task add----");
	       Connection connection = DButil.getConnection();
	        try (
	            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        	preparedStatement.setString(1, task.getTaskName());
	        	preparedStatement.setString(2, task.getTaskDescription());
	        	preparedStatement.setString(3, task.getTaskAssignManager());
	            preparedStatement.setString(4, task.getTaskAssignDate());
	            preparedStatement.setString(5, task.getTaskDueDate());
	            preparedStatement.setString(6, task.getTaskStatus());

	            int rowsAffected = preparedStatement.executeUpdate();
	            System.out.println("addUser-----------"+rowsAffected);
	            return rowsAffected > 0;
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        finally {
	        	connection.close();
	        }
	}



	@Override
	public boolean deleteTask(int taskId) throws SQLException {
		 boolean rowDeleted;
	        String query = "delete from task where taskId=?";
	        Connection connection = DButil.getConnection();
	        try (
	        	PreparedStatement statement = connection.prepareStatement(query);) {
	            statement.setInt(1, taskId);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	}

}
