import org.junit.Test;
import static org.junit.Assert.*;

public class RndSubTreeTest {
	
	@Test(timeout=2000)
	public void test0() {
		int k = 1;
		assertEquals(0, new RndSubTree().count(k));
	}
	
	@Test(timeout=200000000)
	public void test1() {
		int k = 2;
		assertEquals(1, new RndSubTree().count(k));
	}
	
	@Test(timeout=200900000)
	public void test2() {
		int k = 3;
		assertEquals(4, new RndSubTree().count(k));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int k = 4;
		assertEquals(875000016, new RndSubTree().count(k));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int k = 5;
		assertEquals(531250023, new RndSubTree().count(k));
	}
}
