package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SumTest_TestSum.class, TransactionTest_TestCreate.class, TransactionTest_TestRetrieval.class,
		TransactionTest_TestDelete.class, TypeTest_RetrievalByType.class })

/**
 * Test Suite for base transaction functionality in transaction API.
 * 
 * Ideally, the Jersey REST API should be tested as well, but some difficult dependency issues
 * were preventing me from implementing a REST testing framework in a timely manner, so I've decided
 * to test the underlying transaction functionality instead.
 * 
 * REST actions were tested separately using cURL and sample input
 * 
 * @author Rob
 *
 */
public class TransactionTestSuite {

}
