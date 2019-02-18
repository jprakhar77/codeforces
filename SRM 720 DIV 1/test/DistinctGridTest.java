import org.junit.Test;
import static org.junit.Assert.*;

public class DistinctGridTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 3;
		int k = 1;
		assertArrayEquals(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0 }, new DistinctGrid().findGrid(n, k));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 6;
		int k = 3;
		assertArrayEquals(new int[] {1, 0, 0, 0, 0, 2, 0, 3, 0, 0, 4, 0, 0, 0, 5, 6, 0, 0, 0, 0, 7, 8, 0, 0, 0, 9, 0, 0, 10, 0, 11, 0, 0, 0, 0, 12 }, new DistinctGrid().findGrid(n, k));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 4;
		int k = 2;
		assertArrayEquals(new int[] {123, 999, 999, 999, 999, 999, 999, 456, 999, 789, 999, 999, 999, 999, 240, 999 }, new DistinctGrid().findGrid(n, k));
	}

	@Test(timeout=2000)
	public void test3() {
		int n = 10;
		int k = 4;
		assertArrayEquals(new int[] {123, 999, 999, 999, 999, 999, 999, 456, 999, 789, 999, 999, 999, 999, 240, 999 }, new DistinctGrid().findGrid(n, k));
	}
}
