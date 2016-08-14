package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastore.TransactionStore;
import models.Transaction;

public class TransactionTest_TestRetrieval {
	
	private final long TRANSACTION_ID = 1;
	private final String TYPE = "TestType";

	@Before
	public void setUp() throws Exception {
		long transactionId = TRANSACTION_ID;
		Transaction t = new Transaction();
		t.setType(TYPE);
		t.setAmount(2000);
		TransactionStore.getInstance().createTransaction(transactionId, t);
	}

	@Test
	public void test() {
		Transaction t = TransactionStore.getInstance().getTransaction(TRANSACTION_ID);
		assertEquals(t.getType(), TYPE);
	}

}
