import org.junit.Test;
import static org.junit.Assert.*;

public class TransformBoardDiv1Test {
	
	@Test(timeout=2000)
	public void test0() {
		String[] start = new String[] {"#.",".."};
		String[] target = new String[] {"..",".#"};
		assertArrayEquals(new int[] {1, 10101 }, new TransformBoardDiv1().getOperations(start, target));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] start = new String[] {"..",".#"};
		String[] target = new String[] {"#.",".."};
		assertArrayEquals(new int[] {-1 }, new TransformBoardDiv1().getOperations(start, target));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] start = new String[] {"#..#","#..."};
		String[] target = new String[] {"....","...#"};
		assertArrayEquals(new int[] {3, 1000103 }, new TransformBoardDiv1().getOperations(start, target));
	}
}
