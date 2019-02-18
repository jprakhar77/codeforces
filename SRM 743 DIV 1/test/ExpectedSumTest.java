import org.junit.Test;
import static org.junit.Assert.*;

public class ExpectedSumTest {
	
	@Test(timeout=500000000)
	public void test0() {
		int[] sequence = new int[] {10, 20, 30};
		int[] probMinus = new int[] {0, 0, 0};
		assertEquals(60.0, new ExpectedSum().solve(sequence, probMinus), 1e-9);
	}
	
	@Test(timeout=5000)
	public void test1() {
		int[] sequence = new int[] {10, 40, 20};
		int[] probMinus = new int[] {0, 100, 0};
		assertEquals(20.0, new ExpectedSum().solve(sequence, probMinus), 1e-9);
	}
	
	@Test(timeout=5000)
	public void test2() {
		int[] sequence = new int[] {1, 1, 1};
		int[] probMinus = new int[] {50, 50, 50};
		assertEquals(1.375, new ExpectedSum().solve(sequence, probMinus), 1e-9);
	}
	
	@Test(timeout=5000)
	public void test3() {
		int[] sequence = new int[] {1, 1, 1};
		int[] probMinus = new int[] {30, 27, 43};
		assertEquals(1.89227, new ExpectedSum().solve(sequence, probMinus), 1e-9);
	}
	
	@Test(timeout=5000)
	public void test4() {
		int[] sequence = new int[] {47};
		int[] probMinus = new int[] {100};
		assertEquals(0.0, new ExpectedSum().solve(sequence, probMinus), 1e-9);
	}
}
