import org.junit.Test;
import static org.junit.Assert.*;

public class CliquePartyTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] a = new int[] {1,2,3};
		int k = 2;
		assertEquals(3, new CliqueParty().maxsize(a, k));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] a = new int[] {1,2,3};
		int k = 1;
		assertEquals(2, new CliqueParty().maxsize(a, k));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] a = new int[] {4,10,5,6};
		int k = 2;
		assertEquals(3, new CliqueParty().maxsize(a, k));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] a = new int[] {1,2,3,4,5,6};
		int k = 3;
		assertEquals(4, new CliqueParty().maxsize(a, k));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] a = new int[] {10,9,25,24,23,30};
		int k = 7;
		assertEquals(4, new CliqueParty().maxsize(a, k));
	}
}
