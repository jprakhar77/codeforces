import org.junit.Test;
import static org.junit.Assert.*;

public class PalindromeSubsequenceTest {
	
	@Test(timeout=2000)
	public void test0() {
		String s = "bababba";
		assertArrayEquals(new int[] {1, 2, 2, 1, 2, 1, 2 }, new PalindromeSubsequence().optimalPartition(s));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String s = "abba";
		assertArrayEquals(new int[] {1, 1, 1, 1 }, new PalindromeSubsequence().optimalPartition(s));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String s = "b";
		assertArrayEquals(new int[] {1 }, new PalindromeSubsequence().optimalPartition(s));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String s = "babb";
		assertArrayEquals(new int[] {1, 1, 1, 2 }, new PalindromeSubsequence().optimalPartition(s));
	}
}
