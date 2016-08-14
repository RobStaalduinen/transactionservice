package datastore;

import java.util.ArrayList;
import java.util.HashMap;

import models.Status;
import models.Transaction;

/* 
 * Singleton Class for storing and retrieving transactions
 * Keeps separate index for viewing by type
 */
public class TransactionStore {
	
	private HashMap<Long, Transaction> transactionHashMap;
	private HashMap<String, ArrayList<Long>> typeIndex;
	
	private static TransactionStore instance = new TransactionStore();
	
	private TransactionStore()
	{
		transactionHashMap = new HashMap<Long, Transaction>();
		typeIndex = new HashMap<String, ArrayList<Long>>();
	}
	
	public static TransactionStore getInstance()
	{
		return instance;
	}
	
	public Status createTransaction(long id, Transaction t)
	{
		//Check parent for existence
		if(t.hasParent && !transactionHashMap.containsKey(t.getParent_id()))
		{
			return new Status("Parent does not exist", false);
		}
		
		t.setId(id);
		transactionHashMap.put(id, t);
		addToTypeIndex(t.getType(), id);
		if(t.hasParent)
		{
			Transaction parent = transactionHashMap.get(t.getParent_id());
			parent.addChild(id);
		}
		
		return new Status("OK", true);
	}
	
	public Transaction getTransaction(long id)
	{
		if(transactionHashMap.containsKey(id))
		{
			return transactionHashMap.get(id);
		}
		else
		{
			return null;
		}
	}
	
	public Status deleteTransaction(long id)
	{
		if(!transactionHashMap.containsKey(id))
		{
			return new Status("Transaction not Found", false);
		}
		else
		{
			Transaction t = transactionHashMap.get(id);
			//Remove from type index
			ArrayList<Long> transactionTypeList = typeIndex.get(t.getType());
			if(transactionTypeList.size() > 0 && transactionTypeList.contains(id))
			{
				transactionTypeList.remove(id);
			}
			
			//Remove references from parent
			if(t.hasParent)
			{
				Transaction parentTransaction = transactionHashMap.get(t.getParent_id());
				parentTransaction.removeChild(id);
			}
			
			//Remove references from children
			for(Long childId : t.getChildren())
			{
				Transaction child = transactionHashMap.get(childId);
				child.hasParent = false;
			}
			
			transactionHashMap.remove(id);
			
			return new Status("OK", true);
		}
	}
	
	public Long[] getTransactionsForType(String type)
	{
		//Default 0 length array
		Long[] transactionArray = new Long[0];
		if(typeIndex.containsKey(type))
		{
			ArrayList<Long> transactionList = typeIndex.get(type);
			transactionArray = new Long[transactionList.size()];
			transactionArray = transactionList.toArray(transactionArray);
		}
		
		return transactionArray;
	}
	
	public double getSumForTransaction(long id)
	{
		Transaction t = transactionHashMap.get(id);
		
		double sum = t.getAmount();
		
		ArrayList<Long> children = t.getChildren();
		
		if(children.size() > 0)
		{
			for(Long childId : children)
			{
				sum += getSumForTransaction(childId);
			}
		}
		return sum;
	}
	
	private void addToTypeIndex(String type, long id)
	{
		if(typeIndex.containsKey(type))
		{
			typeIndex.get(type).add(id);
		}
		else{
			ArrayList<Long> newListOfIds = new ArrayList<Long>();
			newListOfIds.add(id);
			typeIndex.put(type, newListOfIds);
		}
	}

}
