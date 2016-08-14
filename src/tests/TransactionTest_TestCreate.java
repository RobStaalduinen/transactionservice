package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datastore.TransactionStore;
import models.Status;
import models.Transaction;



public class TransactionTest_TestCreate{

	@Test
	public void test() {
		long transactionId = 1;
		Transaction t = new Transaction();
		t.setType("TestType");
		t.setAmount(2000);
		
		Status creationStatus = TransactionStore.getInstance().createTransaction(transactionId, t);
		
		assertEquals(creationStatus.getStatus(), "OK");
	}

}
