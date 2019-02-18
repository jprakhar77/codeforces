import org.junit.Test;
import static org.junit.Assert.*;

public class IdenticalBagsTest {
	
	@Test(timeout=2000)
	public void test0() {
		long[] candy = new long[] {10, 11, 12};
		long bagSize = 3L;
		assertEquals(10L, new IdenticalBags().makeBags(candy, bagSize));
	}
	
	@Test(timeout=2000)
	public void test1() {
		long[] candy = new long[] {10, 11, 12, 1, 2, 3};
		long bagSize = 3L;
		assertEquals(10L, new IdenticalBags().makeBags(candy, bagSize));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long[] candy = new long[] {100};
		long bagSize = 7L;
		assertEquals(14L, new IdenticalBags().makeBags(candy, bagSize));
	}
	
//	@Test(timeout=2000)
//	public void test3() {
//		long[] candy = new long[] {10000000000, 20000000000, 30000000000};
//		long bagSize = 6L;
//		assertEquals(10000000000L, new IdenticalBags().makeBags(candy, bagSize));
//	}
}
