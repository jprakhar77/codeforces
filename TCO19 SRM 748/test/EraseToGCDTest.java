import org.junit.Test;
import static org.junit.Assert.*;

public class EraseToGCDTest {
	
	@Test(timeout=200000000)
	public void test0() {
		int[] S = new int[] {6, 4, 30, 90, 66};
		int goal = 2;
		assertEquals(15, new EraseToGCD().countWays(S, goal));
	}

	@Test(timeout=200000000)
	public void test10() {
		int[] S = new int[] {6, 6, 15, 6, 6};
		int goal = 15;
		assertEquals(1, new EraseToGCD().countWays(S, goal));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] S = new int[] {8, 8, 8};
		int goal = 4;
		assertEquals(0, new EraseToGCD().countWays(S, goal));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] S = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int goal = 1;
		assertEquals(983, new EraseToGCD().countWays(S, goal));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] S = new int[] {2, 2, 2, 2, 2};
		int goal = 2;
		assertEquals(31, new EraseToGCD().countWays(S, goal));
	}
}
