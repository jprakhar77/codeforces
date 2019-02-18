import org.junit.Test;
import static org.junit.Assert.*;

public class TheRoundCityDiv1Test {
	
	@Test(timeout=2000)
	public void test0() {
		int r = 1;
		assertEquals(4L, new TheRoundCityDiv1().find(r));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int r = 2;
		assertEquals(8L, new TheRoundCityDiv1().find(r));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int r = 3;
		assertEquals(16L, new TheRoundCityDiv1().find(r));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int r = 47;
		assertEquals(4176L, new TheRoundCityDiv1().find(r));
	}
}
