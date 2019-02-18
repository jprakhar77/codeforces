import org.junit.Test;
import static org.junit.Assert.*;

public class HarmoniousGardenTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 8;
		int k = 2;
		int L = 4;
		assertEquals("Possible", new HarmoniousGarden().isPossible(n, k, L));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 3;
		int k = 2;
		int L = 3;
		assertEquals("Impossible", new HarmoniousGarden().isPossible(n, k, L));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 5;
		int k = 2;
		int L = 3;
		assertEquals("Possible", new HarmoniousGarden().isPossible(n, k, L));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int n = 50;
		int k = 1;
		int L = 47;
		assertEquals("Possible", new HarmoniousGarden().isPossible(n, k, L));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int n = 50;
		int k = 2;
		int L = 47;
		assertEquals("Impossible", new HarmoniousGarden().isPossible(n, k, L));
	}
}
