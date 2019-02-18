import org.junit.Test;
import static org.junit.Assert.*;

public class AliceAndBobEasyTest {
	
	@Test(timeout=2000)
	public void test0() {
		int N = 1;
		assertArrayEquals(new int[] {737 }, new AliceAndBobEasy().make(N));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int N = 2;
		assertArrayEquals(new int[] {737, 373 }, new AliceAndBobEasy().make(N));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int N = 3;
		assertArrayEquals(new int[] {3337, 3373, 3733 }, new AliceAndBobEasy().make(N));
	}
}
