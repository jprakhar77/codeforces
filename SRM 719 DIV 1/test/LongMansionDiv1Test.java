import org.junit.Test;
import static org.junit.Assert.*;

public class LongMansionDiv1Test {
	
	@Test(timeout=2000)
	public void test0() {
		int[] t = new int[] {5, 3, 10};
		int sX = 2;
		int sY = 0;
		int eX = 2;
		int eY = 2;
		assertEquals(29L, new LongMansionDiv1().minimalTime(t, sX, sY, eX, eY));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] t = new int[] {5, 3, 10};
		int sX = 0;
		int sY = 2;
		int eX = 0;
		int eY = 0;
		assertEquals(15L, new LongMansionDiv1().minimalTime(t, sX, sY, eX, eY));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] t = new int[] {137, 200, 184, 243, 252, 113, 162};
		int sX = 0;
		int sY = 2;
		int eX = 4;
		int eY = 2;
		assertEquals(1016L, new LongMansionDiv1().minimalTime(t, sX, sY, eX, eY));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] t = new int[] {995, 996, 1000, 997, 999, 1000, 997, 996, 1000, 996, 1000, 997, 999, 996, 1000, 998, 999, 995, 995, 998, 995, 998, 995, 997, 998, 996, 998, 996, 997, 1000, 998, 997, 995, 1000, 996, 997, 1000, 997, 997, 999, 998, 995, 999, 999, 1000, 1000, 998, 997, 995, 999};
		int sX = 18;
		int sY = 433156521;
		int eX = 28;
		int eY = 138238863;
		assertEquals(293443080673L, new LongMansionDiv1().minimalTime(t, sX, sY, eX, eY));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] t = new int[] {1};
		int sX = 0;
		int sY = 0;
		int eX = 0;
		int eY = 0;
		assertEquals(1L, new LongMansionDiv1().minimalTime(t, sX, sY, eX, eY));
	}
}
