import org.junit.Test;
import static org.junit.Assert.*;

public class MiniatureDachshundTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] mikan = new int[] {100, 100, 100, 100, 100};
		int weight = 4750;
		assertEquals(2, new MiniatureDachshund().maxMikan(mikan, weight));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] mikan = new int[] {100, 100, 100, 100, 50};
		int weight = 4750;
		assertEquals(3, new MiniatureDachshund().maxMikan(mikan, weight));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] mikan = new int[] {120, 90, 130, 100, 110, 80};
		int weight = 3000;
		assertEquals(6, new MiniatureDachshund().maxMikan(mikan, weight));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] mikan = new int[] {50};
		int weight = 5000;
		assertEquals(0, new MiniatureDachshund().maxMikan(mikan, weight));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] mikan = new int[] {200, 50, 200, 50, 200, 50, 200, 50};
		int weight = 4800;
		assertEquals(4, new MiniatureDachshund().maxMikan(mikan, weight));
	}
}
