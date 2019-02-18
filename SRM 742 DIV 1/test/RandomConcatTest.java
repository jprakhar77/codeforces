import org.junit.Test;
import static org.junit.Assert.*;

public class RandomConcatTest {
	
	@Test(timeout=2000)
	public void test0() {
		String[] pieces = new String[] {"hahaha"};
		String needle = "aha";
		assertEquals(2.0, new RandomConcat().expectation(pieces, needle), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] pieces = new String[] {"hah","ah"};
		String needle = "aha";
		assertEquals(0.5, new RandomConcat().expectation(pieces, needle), 1e-9);
	}
	
	@Test(timeout=2000000000)
	public void test2() {
		String[] pieces = new String[] {"t","o","p","c","o","d","e","r"};
		String needle = "topcoder";
		assertEquals(4.9603174603174596E-5, new RandomConcat().expectation(pieces, needle), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] pieces = new String[] {"hellotopc","oderhellotop","coderbye"};
		String needle = "topcoder";
		assertEquals(0.6666666666666666, new RandomConcat().expectation(pieces, needle), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test4() {
		String[] pieces = new String[] {"aabaa","aabaaaaa","aba","goodluck","havefun"};
		String needle = "aaaa";
		assertEquals(2.8000000000000016, new RandomConcat().expectation(pieces, needle), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test5() {
		String[] pieces = new String[] {"a","a","aa","b"};
		String needle = "aba";
		assertEquals(0.5, new RandomConcat().expectation(pieces, needle), 1e-9);
	}
}
