import org.junit.Test;
import static org.junit.Assert.*;

public class TwoByTwoTest {
	
	@Test(timeout=5000)
	public void test0() {
		String[] board = new String[] {"..#",
 "...",
 "#.."};
		assertEquals(1, new TwoByTwo().minimal(board));
	}
	
	@Test(timeout=5000)
	public void test1() {
		String[] board = new String[] {"#..#.#",
 "##.##."};
		assertEquals(7, new TwoByTwo().minimal(board));
	}
	
	@Test(timeout=5000)
	public void test2() {
		String[] board = new String[] {"......",
 "......",
 "####..",
 "####..",
 "......",
 "......",
 "..####",
 "..####",
 "......",
 "......"};
		assertEquals(0, new TwoByTwo().minimal(board));
	}
	
	@Test(timeout=5000)
	public void test3() {
		String[] board = new String[] {".###.#.#",
 "#.#.#..#",
 "#..#..#.",
 "#..#.#.#",
 "#.#....#",
 "#.##.##.",
 ".#.##...",
 "#.####.#"};
		assertEquals(10, new TwoByTwo().minimal(board));
	}
	
	@Test(timeout=5000)
	public void test4() {
		String[] board = new String[] {"##",
 "##"};
		assertEquals(4, new TwoByTwo().minimal(board));
	}
}
