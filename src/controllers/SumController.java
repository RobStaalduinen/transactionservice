package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import datastore.TransactionStore;
import models.Sum;

@Path("/sum")
public class SumController {
	
	private TransactionStore transactionStore = TransactionStore.getInstance();
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response getTransactionsForType(@PathParam("id") long id) throws JSONException 
	{
		double sum = transactionStore.getSumForTransaction(id);
		Sum sumReturn = new Sum(sum);
		return Response.status(200).entity(sumReturn).build();
	}


}
