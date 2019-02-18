import org.junit.Test;
import static org.junit.Assert.*;

public class UnreliableRoverTest {
	
	@Test(timeout=2000)
	public void test0() {
		String direction = "N";
		int[] minSteps = new int[] {3};
		int[] maxSteps = new int[] {5};
		assertEquals(3L, new UnreliableRover().getArea(direction, minSteps, maxSteps));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String direction = "NE";
		int[] minSteps = new int[] {3,3};
		int[] maxSteps = new int[] {5,5};
		assertEquals(9L, new UnreliableRover().getArea(direction, minSteps, maxSteps));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String direction = "?";
		int[] minSteps = new int[] {0};
		int[] maxSteps = new int[] {2};
		assertEquals(9L, new UnreliableRover().getArea(direction, minSteps, maxSteps));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String direction = "??N?";
		int[] minSteps = new int[] {0, 0, 0, 0};
		int[] maxSteps = new int[] {0, 0, 0, 0};
		assertEquals(1L, new UnreliableRover().getArea(direction, minSteps, maxSteps));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String direction = "??E?";
		int[] minSteps = new int[] {0, 0, 3, 0};
		int[] maxSteps = new int[] {2, 3, 4, 2};
		assertEquals(120L, new UnreliableRover().getArea(direction, minSteps, maxSteps));
	}
}
