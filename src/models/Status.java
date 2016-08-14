package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "isSuccess"  })
public class Status {
	
	public boolean isSuccess;
	
	private String status;
	
	public Status(String status, boolean success)
	{
		this.status = status;
		this.isSuccess = success;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String newStatus)
	{
		status = newStatus;
	}
}
