import org.junit.Test;
import static org.junit.Assert.*;

public class GravityPuzzleTest {
	
	@Test(timeout=2000)
	public void test0() {
		String[] board = new String[] {"#...",
 "....",
 "...."};
		assertEquals(12, new GravityPuzzle().count(board));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] board = new String[] {".#."};
		assertEquals(1, new GravityPuzzle().count(board));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] board = new String[] {"#.",
 "##"};
		assertEquals(4, new GravityPuzzle().count(board));
	}
	
	@Test(timeout=200000000)
	public void test3() {
		String[] board = new String[] {".##",
 "..#",
 "..."};
		assertEquals(72, new GravityPuzzle().count(board));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String[] board = new String[] {".##########.",
 "............",
 "............",
 "............",
 "............",
 "............",
 "............",
 "............",
 "............",
 "............"};
		assertEquals(999999937, new GravityPuzzle().count(board));
	}
	
	@Test(timeout=2000)
	public void test5() {
		String[] board = new String[] {"."};
		assertEquals(1, new GravityPuzzle().count(board));
	}
}
