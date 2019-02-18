import org.junit.Test;
import static org.junit.Assert.*;

public class A0PaperTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] A = new int[] {0,3};
		assertEquals("Possible", new A0Paper().canBuild(A));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] A = new int[] {0,1,2};
		assertEquals("Possible", new A0Paper().canBuild(A));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] A = new int[] {0,0,0,0,15};
		assertEquals("Impossible", new A0Paper().canBuild(A));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] A = new int[] {2,0,0,0,0,0,0,3,2,0,0,5,0,3,0,0,1,0,0,0,5};
		assertEquals("Possible", new A0Paper().canBuild(A));
	}
}
