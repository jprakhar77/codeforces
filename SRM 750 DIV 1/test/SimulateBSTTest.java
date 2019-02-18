import org.junit.Test;
import static org.junit.Assert.*;

public class SimulateBSTTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 3;
		int[] Sprefix = new int[] {10, 20, 30};
		int a = 0;
		int m = 100;
		assertEquals(99860, new SimulateBST().checksum(n, Sprefix, a, m));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 3;
		int[] Sprefix = new int[] {10, 10, 10};
		int a = 0;
		int m = 100;
		assertEquals(0, new SimulateBST().checksum(n, Sprefix, a, m));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 3;
		int[] Sprefix = new int[] {20, 10, 30};
		int a = 0;
		int m = 100;
		assertEquals(99930, new SimulateBST().checksum(n, Sprefix, a, m));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int n = 9;
		int[] Sprefix = new int[] {40, 20, 60, 70, 80, 30, 10, 30, 90};
		int a = 0;
		int m = 100;
		assertEquals(461469106, new SimulateBST().checksum(n, Sprefix, a, m));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int n = 15;
		int[] Sprefix = new int[] {10, 20, 30};
		int a = 100;
		int m = 1000000007;
		assertEquals(149719615, new SimulateBST().checksum(n, Sprefix, a, m));
	}
}
