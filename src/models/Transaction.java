package models;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonIgnoreProperties(value = { "id", "hasParent", "children"  })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class Transaction {
	
	public boolean hasParent = false;
	
	private long id;
	
	private String type;
	private double amount;
	
	private long parent_id;
	
	private ArrayList<Long> children = new ArrayList<Long>();
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long newId)
	{
		id = newId;
	}
		
	public String getType()
	{
		return type;
	}
	
	public void setType(String newType){
		type = newType;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public void setAmount(double newAmount)
	{
		amount = newAmount;
	}
	
	public long getParent_id()
	{
		return parent_id;
	}
	

	public void setParent_id(long newParentId)
	{
		hasParent = true;
		parent_id = newParentId;
	}
	
	public void addChild(long childId)
	{
		children.add((Long)childId);
	}
	
	public void removeChild(long childId)
	{
		children.remove((Long)childId);
	}
	
	public ArrayList<Long> getChildren()
	{
		return children;
	}
}
