package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastore.TransactionStore;
import models.Transaction;

public class TypeTest_RetrievalByType {
	
	private final String MATCHING_TYPE = "MATCH";
	private final String NON_MATCHING_TYPE ="NOMATCH";

	@Before
	public void setUp() throws Exception {
		//Set up 3 different transactions
		
		Transaction t1 = new Transaction();
		t1.setType(MATCHING_TYPE);
		t1.setAmount(2000);
		TransactionStore.getInstance().createTransaction(1, t1);
		
		Transaction t2 = new Transaction();
		t2.setType(MATCHING_TYPE);
		t2.setAmount(2000);
		TransactionStore.getInstance().createTransaction(2, t2);
		
		Transaction t3 = new Transaction();
		t3.setType(NON_MATCHING_TYPE);
		t3.setAmount(2000);
		TransactionStore.getInstance().createTransaction(3, t3);
	}

	@Test
	public void test() {
		Long[] transactionList = TransactionStore.getInstance().getTransactionsForType(MATCHING_TYPE);
		assertEquals(transactionList.length, 2);
	}

}
