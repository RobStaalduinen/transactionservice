package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;

import datastore.TransactionStore;

@Path("/type")
public class TypeController {

	private TransactionStore transactionStore = TransactionStore.getInstance();
	
	@Path("{type}")
	@GET
	@Produces("application/json")
	public Response getTransactionsForType(@PathParam("type") String type) throws JSONException 
	{
		System.out.println("GET TYPE: " + type);
		Long[] transactionArray = transactionStore.getTransactionsForType(type);
		return Response.status(200).entity(transactionArray).build();
	}
}
