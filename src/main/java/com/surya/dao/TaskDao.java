package com.surya.dao;

import java.sql.SQLException;
import java.util.List;

import com.surya.model.Task;


public interface TaskDao {
	boolean updateTask(Task task) throws SQLException;
	Task getTaskById(int taskId) throws SQLException;
	List<Task> selectAllTask() throws SQLException ;
	boolean addTask(Task task) throws SQLException;
	boolean deleteTask(int taskId) throws SQLException ;
//	List<Task> selectAllTasks();
}
