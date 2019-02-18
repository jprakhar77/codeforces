import org.junit.Test;
import static org.junit.Assert.*;

public class Chains0Test {
	
	@Test(timeout=2000)
	public void test0() {
		int x = 10;
		int y = 47;
		assertArrayEquals(new int[] {23, 29 }, new Chains0().getProperSubset(x, y));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int x = 12;
		int y = 15;
		assertArrayEquals(new int[] {13, 14 }, new Chains0().getProperSubset(x, y));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int x = 0;
		int y = 50;
		assertArrayEquals(new int[] {23, 29 }, new Chains0().getProperSubset(x, y));
	}
}
