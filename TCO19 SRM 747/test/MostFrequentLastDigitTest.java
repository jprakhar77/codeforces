import org.junit.Test;
import static org.junit.Assert.*;

public class MostFrequentLastDigitTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 4;
		int d = 7;
		assertArrayEquals(new int[] {1, 6, 13, 4 }, new MostFrequentLastDigit().generate(n, d));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 2;
		int d = 8;
		assertArrayEquals(new int[] {44, 444 }, new MostFrequentLastDigit().generate(n, d));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 7;
		int d = 2;
		assertArrayEquals(new int[] {109, 22, 74, 23, 29, 9, 1003 }, new MostFrequentLastDigit().generate(n, d));
	}

	@Test(timeout=20000000)
	public void test3() {
		int n = 200;
		int d = 5;
		assertArrayEquals(new int[] {109, 22, 74, 23, 29, 9, 1003 }, new MostFrequentLastDigit().generate(n, d));

	}
}
