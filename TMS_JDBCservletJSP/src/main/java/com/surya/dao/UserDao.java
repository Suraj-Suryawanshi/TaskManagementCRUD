package com.surya.dao;

import java.sql.SQLException;
import java.util.List;

import com.surya.model.User;

public interface UserDao {
	boolean isValidUser(String role, String email, String password);
	boolean updateUser(User user) throws SQLException;
	User getUserById(int userId);
	List<User> selectAllUsers() ;
	boolean addUser(User user);
	boolean deleteUser(int id) throws SQLException ;
	public List<User> managerUserList();
	public List<User> employeeUserList();
	public List<User> UsersByMonthYear(String startDate, String endDate);

}