import org.junit.Test;
import static org.junit.Assert.*;

public class OrAndSumTest {
	
	@Test(timeout=2000)
	public void test0() {
		long[] pairOr = new long[] {7};
		long[] pairSum = new long[] {11};
		assertEquals("Possible", new OrAndSum().isPossible(pairOr, pairSum));
	}
	
	@Test(timeout=2000)
	public void test1() {
		long[] pairOr = new long[] {11};
		long[] pairSum = new long[] {7};
		assertEquals("Impossible", new OrAndSum().isPossible(pairOr, pairSum));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long[] pairOr = new long[] {3,3,7,5,7};
		long[] pairSum = new long[] {3,5,7,9,11};
		assertEquals("Possible", new OrAndSum().isPossible(pairOr, pairSum));
	}
	
	@Test(timeout=2000)
	public void test3() {
		long[] pairOr = new long[] {1,100,1000};
		long[] pairSum = new long[] {100,1000,10000};
		assertEquals("Impossible", new OrAndSum().isPossible(pairOr, pairSum));
	}
	
	@Test(timeout=2000)
	public void test4() {
		long[] pairOr = new long[] {261208776456074191l,261208776456074191l,261208776456074191l};
		long[] pairSum = new long[] {333333333333333333l,333333333333333333l,333333333333333333l};
		assertEquals("Possible", new OrAndSum().isPossible(pairOr, pairSum));
	}
	
	@Test(timeout=2000)
	public void test5() {
		long[] pairOr = new long[] {0};
		long[] pairSum = new long[] {0};
		assertEquals("Possible", new OrAndSum().isPossible(pairOr, pairSum));
	}
}
