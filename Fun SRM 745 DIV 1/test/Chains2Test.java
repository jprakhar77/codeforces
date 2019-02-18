import org.junit.Test;
import static org.junit.Assert.*;

public class Chains2Test {
	
	@Test(timeout=2000)
	public void test0() {
		int[] x = new int[] {0, 0, 0};
		int[] y = new int[] {0, 1, 3};
		assertEquals(3, new Chains2().validate(x, y));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] x = new int[] {1, 4, 4};
		int[] y = new int[] {1, 7, 8};
		assertEquals(8, new Chains2().validate(x, y));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] x = new int[] {4, 4, 3};
		int[] y = new int[] {5, 7, 6};
		assertEquals(-3, new Chains2().validate(x, y));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] x = new int[] {4, 4};
		int[] y = new int[] {7, 7};
		assertEquals(-2, new Chains2().validate(x, y));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] x = new int[] {3};
		int[] y = new int[] {3};
		assertEquals(0, new Chains2().validate(x, y));
	}
}
