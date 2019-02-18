import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DieDesignTest {
	
	@Test(timeout=200000000)
	public void test0() {
		int[] pips = new int[] {1,2,3,4,5,6};
		int[] ans = new DieDesign().design(pips);
		System.out.println(Arrays.toString(ans));
		assertArrayEquals(new int[] {0, 0, 0, 7, 7, 7 }, ans);
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] pips = new int[] {0,0,0,2};
		assertArrayEquals(new int[] {0, 0, 1, 1 }, new DieDesign().design(pips));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] pips = new int[] {1,1,13,1};
		assertArrayEquals(new int[] {4, 4, 4, 4 }, new DieDesign().design(pips));
	}
}
