import org.junit.Test;
import static org.junit.Assert.*;

public class SquareCutoutTest {
	
	@Test(timeout=2000000)
	public void test0() {
		String[] cutout = new String[] {".....",
 "..##.",
 "..##.",
 ".....",
 "....."};
		assertEquals(2, new SquareCutout().verify(cutout));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] cutout = new String[] {"...",
 "..."};
		assertEquals(1, new SquareCutout().verify(cutout));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] cutout = new String[] {".....",
 ".###.",
 ".#.#.",
 ".###.",
 "....."};
		assertEquals(0, new SquareCutout().verify(cutout));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] cutout = new String[] {"..####",
 "..####",
 "......"};
		assertEquals(4, new SquareCutout().verify(cutout));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String[] cutout = new String[] {"..#..",
 ".###.",
 "#####",
 ".###.",
 "..#.."};
		assertEquals(0, new SquareCutout().verify(cutout));
	}

	@Test(timeout=2000)
	public void test5() {
		String[] cutout = new String[] {"...#####",
				"...#####",
				"........",
				"........",
				"........"};
		assertEquals(5, new SquareCutout().verify(cutout));
	}
}
