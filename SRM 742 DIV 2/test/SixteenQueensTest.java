import org.junit.Test;
import static org.junit.Assert.*;

public class SixteenQueensTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] row = new int[] {3};
		int[] col = new int[] {5};
		int add = 1;
		assertArrayEquals(new int[] {0, 0 }, new SixteenQueens().addQueens(row, col, add));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] row = new int[] {0};
		int[] col = new int[] {1};
		int add = 1;
		assertArrayEquals(new int[] {4, 7 }, new SixteenQueens().addQueens(row, col, add));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] row = new int[] {0};
		int[] col = new int[] {1};
		int add = 3;
		assertArrayEquals(new int[] {4, 7, 15, 0, 49, 49 }, new SixteenQueens().addQueens(row, col, add));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] row = new int[] {14, 19};
		int[] col = new int[] {3, 47};
		int add = 0;
		assertArrayEquals(new int[] { }, new SixteenQueens().addQueens(row, col, add));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] row = new int[] {};
		int[] col = new int[] {};
		int add = 2;
		assertArrayEquals(new int[] {0, 0, 1, 2 }, new SixteenQueens().addQueens(row, col, add));
	}
	
	@Test(timeout=2000)
	public void test5() {
		int[] row = new int[] {1,2,3};
		int[] col = new int[] {7,2,19};
		int add = 1;
		assertArrayEquals(new int[] {0, 1 }, new SixteenQueens().addQueens(row, col, add));
	}
}
