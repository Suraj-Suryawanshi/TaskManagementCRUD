package com.surya.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.surya.model.EmpTask;
import com.surya.model.Employee;

import jakarta.servlet.ServletException;

public interface EmpTaskDao {
//	public List<Task> selectAllEmpTask() throws SQLException;
	public List<EmpTask> selectAllEmpTask() throws ServletException, IOException, SQLException;
	public boolean addEmpTask(EmpTask empTask) throws SQLException;
	public List<Employee> dispAllTask() throws ServletException, IOException;
	boolean editEmpTask(EmpTask empTask) throws SQLException;
	EmpTask getEmpTaskById(int empTaskId) throws SQLException;
	boolean deleteEmpTask(int empTaskId) throws SQLException;
}
