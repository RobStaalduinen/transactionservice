package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastore.TransactionStore;
import models.Transaction;

public class TransactionTest_TestDelete {
	
	private final long TRANSACTION_ID = 1;

	@Before
	public void setUp() throws Exception {
		long transactionId = TRANSACTION_ID;
		Transaction t = new Transaction();
		t.setType("TEST");
		t.setAmount(2000);
		TransactionStore.getInstance().createTransaction(transactionId, t);
	}

	@Test
	public void test() {
		TransactionStore.getInstance().deleteTransaction(TRANSACTION_ID);
		Transaction oldTransaction = TransactionStore.getInstance().getTransaction(TRANSACTION_ID);
		assertNull(oldTransaction);
	}

}
