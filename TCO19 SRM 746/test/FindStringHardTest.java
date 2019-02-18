import org.junit.Test;
import static org.junit.Assert.*;

public class FindStringHardTest {
	
	@Test(timeout=2000000000)
	public void test0() {
		int n = 3;
		assertEquals("bbaab", new FindStringHard().withAntipalindromicSubstrings(n));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 8;
		assertEquals("ababbaab", new FindStringHard().withAntipalindromicSubstrings(n));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 139;
		assertEquals("ababaabbaabbaaabbbaaabbbaaaabbbbaaaabbbbaaaaabbbbbaaaaabbbbbaaaaaabbbbbbaaaaaabbbbbb", new FindStringHard().withAntipalindromicSubstrings(n));
	}

	@Test(timeout=2000)
	public void test3() {
		int n = 663;
		assertEquals("0", new FindStringHard().withAntipalindromicSubstrings(n));
	}

	@Test(timeout=2000)
	public void test4() {
		int n = 5;
		assertEquals("0", new FindStringHard().withAntipalindromicSubstrings(n));
	}
}