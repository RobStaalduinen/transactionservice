package controllers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;

import datastore.TransactionStore;
import models.Status;
import models.Transaction;


@Path("/transaction")
public class TransactionController {
	
	private TransactionStore transactionStore = TransactionStore.getInstance();
		
	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response getTransaction(@PathParam("id") long id) throws JSONException 
	{
		Transaction returnedTransaction = transactionStore.getTransaction(id);
		if(returnedTransaction != null)
		{
			return Response.status(200).entity(returnedTransaction).build();
		}
		else{
			return Response.status(404).entity(new Status("Transaction not Found", false)).build();
		}
	}
	
	@Path("{id}")
	@PUT
	@Produces("application/json")
	public Response createTransaction(@PathParam("id") long id, Transaction incomingTransaction) throws JSONException
	{
		Status creationStatus = transactionStore.createTransaction(id, incomingTransaction);
		return Response.status(200).entity(creationStatus).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces("application/json")
	public Response deleteTransaction(@PathParam("id") long id)
	{
		Status deleteStatus = transactionStore.deleteTransaction(id);
		int statusCode = 200;
		if(!deleteStatus.isSuccess)
		{
			statusCode = 404;
		}
		
		return Response.status(statusCode).entity(deleteStatus).build();
	}

}
