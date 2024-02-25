package com.surya.model;

public class Task {

	private int taskId;
	private String taskName;
	private String taskDescription;
	private String taskAssignManager;
//	private int taskManagerId;
	private String taskAssignDate;
	private String taskDueDate;
	private String taskStatus;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

//	public int getTaskManagerId() {
//		return taskManagerId;
//	}
//	public void setTaskManagerId(int taskManagerId) {
//		this.taskManagerId = taskManagerId;
//	}
	public String getTaskAssignManager() {
		return taskAssignManager;
	}
	public void setTaskAssignManager(String taskAssignManager) {
		this.taskAssignManager = taskAssignManager;
	}
	public String getTaskAssignDate() {
		return taskAssignDate;
	}
	public void setTaskAssignDate(String taskAssignDate) {
		this.taskAssignDate = taskAssignDate;
	}
	public String getTaskDueDate() {
		return taskDueDate;
	}
	public void setTaskDueDate(String taskDueDate) {
		this.taskDueDate = taskDueDate;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	

	public Task() {

	}
	public Task(int taskId, String taskName, String taskDescription, String taskAssignManager, String taskAssignDate,
			String taskDueDate, String taskStatus) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskAssignManager = taskAssignManager;
		this.taskAssignDate = taskAssignDate;
		this.taskDueDate = taskDueDate;
		this.taskStatus = taskStatus;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskAssignManager=" + taskAssignManager + ", taskAssignDate=" + taskAssignDate + ", taskDueDate="
				+ taskDueDate + ", taskStatus=" + taskStatus + "]";
	}
	
	

}
