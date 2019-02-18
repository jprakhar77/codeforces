import org.junit.Test;
import static org.junit.Assert.*;

public class LeftToRightGameTest {
	
	@Test(timeout=2000)
	public void test0() {
		int length = 4;
		int divisor = 10;
		assertEquals("Bob", new LeftToRightGame().whoWins(length, divisor));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int length = 3;
		int divisor = 1000;
		assertEquals("Alice", new LeftToRightGame().whoWins(length, divisor));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int length = 2;
		int divisor = 3;
		assertEquals("Bob", new LeftToRightGame().whoWins(length, divisor));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int length = 147;
		int divisor = 47;
		assertEquals("Alice", new LeftToRightGame().whoWins(length, divisor));
	}
}
