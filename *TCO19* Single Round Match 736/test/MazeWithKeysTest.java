import org.junit.Test;
import static org.junit.Assert.*;

public class MazeWithKeysTest {
	
	@Test(timeout=2000000000)
	public void test0() {
		String[] a = new String[] {"...#.A.",
 ".#B#.#.",
 ".#.#.#.",
 ".#.#.#.",
 ".#b#.#.",
 "a#...#*"}
;
		assertEquals(10, new MazeWithKeys().startingPoints(a));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] a = new String[] {"..*",
 ".a.",
 "A.."}
;
		assertEquals(0, new MazeWithKeys().startingPoints(a));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] a = new String[] {".A*C.",
 ".#B#.",
 ".#.#.",
 "a..b."}
;
		assertEquals(10, new MazeWithKeys().startingPoints(a));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] a = new String[] {"a#a#*",
 "#..#.",
 "a..A."}
;
		assertEquals(4, new MazeWithKeys().startingPoints(a));
	}
}
