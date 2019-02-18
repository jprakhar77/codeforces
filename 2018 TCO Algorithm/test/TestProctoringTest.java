import org.junit.Test;
import static org.junit.Assert.*;

public class TestProctoringTest {
	
	@Test(timeout=4000)
	public void test0() {
		int[] p = new int[] {2};
		int[] q = new int[] {4};
		assertEquals(2.0, new TestProctoring().expectedTime(p, q), 1e-9);
	}
	
	@Test(timeout=4000)
	public void test1() {
		int[] p = new int[] {1,2};
		int[] q = new int[] {2,4};
		assertEquals(2.6666666666666665, new TestProctoring().expectedTime(p, q), 1e-9);
	}
	
	@Test(timeout=4000)
	public void test2() {
		int[] p = new int[] {3,1,2,4,2,5};
		int[] q = new int[] {3,1,2,4,2,5};
		assertEquals(1.0, new TestProctoring().expectedTime(p, q), 1e-9);
	}
	
	@Test(timeout=4000)
	public void test3() {
		int[] p = new int[] {1,1,1,1,1,1,1,1};
		int[] q = new int[] {1,2,3,4,5,6,7,8};
		assertEquals(13.604834665120991, new TestProctoring().expectedTime(p, q), 1e-9);
	}
	
	@Test(timeout=4000)
	public void test4() {
		int[] p = new int[] {2,3,5,7,11,13,17};
		int[] q = new int[] {3,5,7,11,13,17,19};
		assertEquals(2.6299445065924276, new TestProctoring().expectedTime(p, q), 1e-9);
	}
	
	@Test(timeout=4000)
	public void test5() {
		int[] p = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		int[] q = new int[] {1000000000,1000000000,1000000000,1000000000,1000000000,
1000000000,1000000000,1000000000,1000000000,1000000000,
1000000000,1000000000,1000000000,1000000000,1000000000,
1000000000,1000000000,1000000000,1000000000,1000000000};
		assertEquals(3.597740924491638E9, new TestProctoring().expectedTime(p, q), 1e-9);
	}
}
