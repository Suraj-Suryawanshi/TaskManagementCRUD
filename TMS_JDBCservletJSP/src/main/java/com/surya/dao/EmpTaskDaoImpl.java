package com.surya.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.surya.model.EmpTask;
import com.surya.model.Employee;
import com.surya.util.DButil;

import jakarta.servlet.ServletException;

public class EmpTaskDaoImpl implements EmpTaskDao {

	//for adding in EmpTask table by manager
	@Override
	public boolean addEmpTask(EmpTask empTask) throws SQLException {
		String query = "INSERT INTO \"I1292\".empTask (empUserEmail, taskId_fKey ) VALUES (?, ?)";
		System.out.println("task add::daoImpl----");
		try (Connection connection = DButil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			System.out.println("insideTry" + query);
			preparedStatement.setString(1, empTask.getEmpUserEmail());
			preparedStatement.setInt(2, empTask.getTaskId_fKey());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("addEmpTask::EmpTaskDaoImpl-----------" + rowsAffected);
			return rowsAffected > 0;
//		} catch (SQLException e) {
//			e.printStackTrace();

//			return false;
		}

	}

	@Override
	public boolean deleteEmpTask(int empTaskId) throws SQLException {
		 boolean rowDeleted;
	        String query = "delete from \"I1292\".empTask where empTaskId=?";
	        Connection connection = DButil.getConnection();
	        try (
	        	PreparedStatement statement = connection.prepareStatement(query);) {
	            statement.setInt(1, empTaskId);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	}


	@Override
	public boolean editEmpTask(EmpTask empTask) throws SQLException{
		String query = "update \"I1292\".empTask set empUserEmail= ?,taskId_fKey=? where empTaskId=?";
		boolean rowUpdated;
        try (
        	Connection connection = DButil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        	preparedStatement.setString(1, empTask.getEmpUserEmail());
        	preparedStatement.setInt(2, empTask.getTaskId_fKey());

            preparedStatement.setInt(3, empTask.getEmpTaskId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
            return rowUpdated;
        }
//        catch(SQLException e) {
//        	e.printStackTrace();
//        	return false;
//        }

	}

	@Override
	public EmpTask getEmpTaskById(int empTaskId) throws SQLException {
		 	EmpTask empTask = null;
		    String query = "SELECT * FROM \"I1292\".empTask WHERE empTaskId=?";
		    Connection connection = DButil.getConnection();
		    try (
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        System.out.println("UserId-------"+empTaskId);
		        preparedStatement.setInt(1, empTaskId);
		        ResultSet rs = preparedStatement.executeQuery();
		        System.out.println("ResultSEt-----"+rs.toString());
		        if (rs.next()) {
		        	int empTaskId1 = rs.getInt("empTaskId");
	                String empUserEmail = rs.getString("empUserEmail");
	                int taskId_fKey = rs.getInt("taskId_fKey");
	                 empTask = new EmpTask(empTaskId1, empUserEmail, taskId_fKey);
	                System.out.println("\nsGetById::userDaoImpl-------"+empTask);
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
		    System.out.println("-----------"+empTask);

		    return empTask;
	}

	//for fetch all assigned task to employees by manager with taskDetails for employeeDisp (Join result of two tables)
	@Override
	public List<Employee> dispAllTask() throws ServletException, IOException {
		String query = "SELECT  task.taskId, task.taskName, task.taskDescription, task.taskAssignManager, task.taskAssignDate, task.taskDueDate, task.taskStatus, empTask.empUserEmail FROM \"I1292\".task  INNER JOIN \"I1292\".empTask ON task.taskId = empTask.taskId_fKey";
//		String query = "SELECT empTask.empUserEmail, task.taskId, task.taskName, task.taskDescription, task.taskAssignManager, task.taskAssignDate, task.taskDueDate, task.taskStatus"
//				+ "FROM \"I1292\"task" + "INNER JOIN \"1292\"empTask" + "ON task.taskId = empTask.taskId_fKey";

		List<Employee> employee = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = DButil.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("selectAllDispTask::ResultSet--------" + rs);
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String empUserEmail = rs.getString("empUserEmail");
				int TaskId = rs.getInt("TaskId");
				String TaskName = rs.getString("TaskName");
				String TaskDescription = rs.getString("TaskDescription");
				String TaskAssignManager = rs.getString("TaskAssignManager");
				String TaskAssignDate = rs.getString("TaskAssignDate");
				String TaskDueDate = rs.getString("TaskDueDate");
				String TaskStatus = rs.getString("TaskStatus");

				employee.add(new Employee(empUserEmail, TaskId, TaskName, TaskDescription, TaskAssignManager,
						TaskAssignDate, TaskDueDate, TaskStatus));
				System.out.println("\n\ndispAllEmpTask::userDaoImpl-------" + employee);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
//		        finally {
//					connection.close();
//				}
		return employee;
	}


	//for fetching the data at manager side of assigned task to the employee containing reference relation
	@Override
	public List<EmpTask> selectAllEmpTask() throws ServletException, IOException, SQLException {
		System.out.println("selectAllTask::Call----------");
		String query = "select * from \"I1292\".empTask";
		List<EmpTask> empTasks = new ArrayList<>();
		// Step 1: Establishing a Connection
		Connection connection = DButil.getConnection();
		try (
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("selectAllEmpTask::ResultSet--------" + rs);
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int empTaskId = rs.getInt("empTaskId");
				String taskUserEmail = rs.getString("empUserEmail");
				int taskId_fKey = rs.getInt("taskId_fKey");

				empTasks.add(new EmpTask(empTaskId, taskUserEmail, taskId_fKey));
				System.out.println("\nselectAllEmpTask::userDaoImpl-------" + empTasks);

			}
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
//		        finally {
//					connection.close();
//				}
		return empTasks;

	}

}
