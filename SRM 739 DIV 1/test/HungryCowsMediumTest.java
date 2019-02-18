import org.junit.Test;
import static org.junit.Assert.*;

public class HungryCowsMediumTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] cowAppetites = new int[] {3};
		int[] barnPositions = new int[] {5};
		assertEquals(8L, new HungryCowsMedium().getWellFedTime(cowAppetites, barnPositions));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] cowAppetites = new int[] {1,1,1,1,1};
		int[] barnPositions = new int[] {2,3};
		assertEquals(5L, new HungryCowsMedium().getWellFedTime(cowAppetites, barnPositions));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] cowAppetites = new int[] {4,4,4};
		int[] barnPositions = new int[] {4,2};
		assertEquals(9L, new HungryCowsMedium().getWellFedTime(cowAppetites, barnPositions));
	}
}
