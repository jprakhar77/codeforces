import org.junit.Test;
import static org.junit.Assert.*;

public class FindStringEasyTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 3;
		assertEquals("aa", new FindStringEasy().withPalindromicSubstrings(n));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 9;
		assertEquals("aaaba", new FindStringEasy().withPalindromicSubstrings(n));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 12;
		assertEquals("aabbaba", new FindStringEasy().withPalindromicSubstrings(n));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int n = 20;
		assertEquals("bbbaabbaba", new FindStringEasy().withPalindromicSubstrings(n));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int n = 140;
		assertEquals("bbaababaabaabbbabbbabbbaabaaaabbbaabbabbabababbbaaa", new FindStringEasy().withPalindromicSubstrings(n));
	}
}
