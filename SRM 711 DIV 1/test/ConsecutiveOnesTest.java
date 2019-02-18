import org.junit.Test;
import static org.junit.Assert.*;

public class ConsecutiveOnesTest {
	
	@Test(timeout=2000)
	public void test0() {
		long n = 1L;
		int k = 2;
		assertEquals(3L, new ConsecutiveOnes().get(n, k));
	}
	
	@Test(timeout=2000)
	public void test1() {
		long n = 5L;
		int k = 2;
		assertEquals(6L, new ConsecutiveOnes().get(n, k));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long n = 7L;
		int k = 2;
		assertEquals(7L, new ConsecutiveOnes().get(n, k));
	}
	
	@Test(timeout=2000)
	public void test3() {
		long n = 364269800189924L;
		int k = 33;
		assertEquals(364273356242943L, new ConsecutiveOnes().get(n, k));
	}
}
