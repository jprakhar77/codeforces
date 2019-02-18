import org.junit.Test;
import static org.junit.Assert.*;

public class TieForMaxTest {
	
	@Test(timeout=2000)
	public void test0() {
		int t = 7;
		int p = 1;
		assertEquals(0.0, new TieForMax().getProbability(t, p), 1e-9);
	}
	
	@Test(timeout=2000000000)
	public void test1() {
		int t = 2;
		int p = 2;
		assertEquals(0.5, new TieForMax().getProbability(t, p), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test2() {
		int t = 3;
		int p = 3;
		assertEquals(0.2222222222222222, new TieForMax().getProbability(t, p), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test3() {
		int t = 6;
		int p = 4;
		assertEquals(0.380859375, new TieForMax().getProbability(t, p), 1e-9);
	}
}
