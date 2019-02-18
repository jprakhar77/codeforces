import org.junit.Test;
import static org.junit.Assert.*;

public class ModularQuadrantTest {
	
	@Test(timeout=2000000000)
	public void test0() {
		int r1 = 0;
		int r2 = 2;
		int c1 = 1;
		int c2 = 4;
		assertEquals(13L, new ModularQuadrant().sum(r1, r2, c1, c2));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int r1 = 2;
		int r2 = 2;
		int c1 = 0;
		int c2 = 7;
		assertEquals(10L, new ModularQuadrant().sum(r1, r2, c1, c2));
	}
	
	@Test(timeout=2000000000)
	public void test2() {
		int r1 = 4;
		int r2 = 8;
		int c1 = 0;
		int c2 = 5;
		assertEquals(37L, new ModularQuadrant().sum(r1, r2, c1, c2));
	}
}
