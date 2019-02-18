import org.junit.Test;
import static org.junit.Assert.*;

public class PurpleSubsequencesTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] A = new int[] {4,7,4,7};
		int L = 1;
		assertEquals(11L, new PurpleSubsequences().count(A, L));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] A = new int[] {1,1,2,2,3,3};
		int L = 3;
		assertEquals(8L, new PurpleSubsequences().count(A, L));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] A = new int[] {1,2,3,1,2,3};
		int L = 3;
		assertEquals(17L, new PurpleSubsequences().count(A, L));
	}
}
