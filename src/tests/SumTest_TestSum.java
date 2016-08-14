package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastore.TransactionStore;
import models.Transaction;

public class SumTest_TestSum {

	@Before
	public void setUp() throws Exception {
		
		//Set up 5 different transactions, 4 linked, 1 not
		
		Transaction t1 = new Transaction();
		t1.setType("TEST");
		t1.setAmount(1000);
		TransactionStore.getInstance().createTransaction(1, t1);
		
		Transaction t2 = new Transaction();
		t2.setType("TEST");
		t2.setAmount(2000);
		t2.setParent_id(1);
		TransactionStore.getInstance().createTransaction(2, t2);
		
		Transaction t3 = new Transaction();
		t3.setType("TEST");
		t3.setAmount(10000);
		t3.setParent_id(2);
		TransactionStore.getInstance().createTransaction(3, t3);
		
		Transaction t4 = new Transaction();
		t4.setType("TEST");
		t4.setAmount(10000);
		t4.setParent_id(2);
		TransactionStore.getInstance().createTransaction(4, t4);
		
		Transaction t5 = new Transaction();
		t5.setType("TEST");
		t5.setAmount(20000);
		TransactionStore.getInstance().createTransaction(5, t5);
	}

	@Test
	public void test() {
		double sum = TransactionStore.getInstance().getSumForTransaction(1);
		assertEquals(sum, 23000.0, 0.01);
	}

}
