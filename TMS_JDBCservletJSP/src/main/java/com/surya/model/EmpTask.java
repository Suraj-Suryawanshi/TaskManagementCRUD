package com.surya.model;

public class EmpTask {
	private int EmpTaskId;
	private String EmpUserEmail;
	private int TaskId_fKey;

	public int getEmpTaskId() {
		return EmpTaskId;
	}
	public void setEmpTaskId(int empTaskId) {
		EmpTaskId = empTaskId;
	}
	public String getEmpUserEmail() {
		return EmpUserEmail;
	}
	public void setEmpUserEmail(String empUserEmail) {
		EmpUserEmail = empUserEmail;
	}
	public int getTaskId_fKey() {
		return TaskId_fKey;
	}
	public void setTaskId_fKey(int taskId_fKey) {
		TaskId_fKey = taskId_fKey;
	}
	public EmpTask(int empTaskId, String empUserEmail, int taskId_fKey) {
		super();
		EmpTaskId = empTaskId;
		EmpUserEmail = empUserEmail;
		TaskId_fKey = taskId_fKey;
	}

	public EmpTask() {
//		super();
	}
	@Override
	public String toString() {
		return "EmpTask [EmpTaskId=" + EmpTaskId + ", EmpUserEmail=" + EmpUserEmail + ", TaskId_fKey=" + TaskId_fKey + "]";
	}




}
