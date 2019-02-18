import org.junit.Test;
import static org.junit.Assert.*;

public class SumProductTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] amount = new int[] {0,2,1,1,0,0,0,0,0,0};
		int blank1 = 2;
		int blank2 = 2;
		assertEquals(4114, new SumProduct().findSum(amount, blank1, blank2));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] amount = new int[] {1,6,0,0,0,0,0,0,0,0};
		int blank1 = 1;
		int blank2 = 2;
		assertEquals(22, new SumProduct().findSum(amount, blank1, blank2));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] amount = new int[] {1,2,3,4,5,6,7,8,9,10};
		int blank1 = 15;
		int blank2 = 3;
		assertEquals(340242570, new SumProduct().findSum(amount, blank1, blank2));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] amount = new int[] {3,14,15,92,65,35,89,79,32,38};
		int blank1 = 46;
		int blank2 = 26;
		assertEquals(417461139, new SumProduct().findSum(amount, blank1, blank2));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] amount = new int[] {100,100,100,100,100,100,100,100,100,100};
		int blank1 = 100;
		int blank2 = 100;
		assertEquals(372980218, new SumProduct().findSum(amount, blank1, blank2));
	}
}
