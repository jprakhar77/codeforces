import org.junit.Test;
import static org.junit.Assert.*;

public class NAddOddTest {
	
	@Test(timeout=2000)
	public void test0() {
		long L = 5L;
		long R = 5L;
		int K = 1;
		assertEquals(5L, new NAddOdd().solve(L, R, K));
	}
	
	@Test(timeout=2000)
	public void test1() {
		long L = 1L;
		long R = 99999L;
		int K = 99999;
		assertEquals(0L, new NAddOdd().solve(L, R, K));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long L = 16L;
		long R = 17L;
		int K = 3;
		assertEquals(9L, new NAddOdd().solve(L, R, K));
	}
	
	@Test(timeout=2000)
	public void test3() {
		long L = 3L;
		long R = 7L;
		int K = 5;
		assertEquals(4L, new NAddOdd().solve(L, R, K));
	}
	
	@Test(timeout=2000000000)
	public void test4() {
		long L = 1645805087361625L;
		long R = 9060129311830846L;
		int K = 74935;
		assertEquals(421014795656548226L, new NAddOdd().solve(L, R, K));
	}
}
