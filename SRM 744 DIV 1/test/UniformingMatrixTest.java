import org.junit.Test;
import static org.junit.Assert.*;

public class UniformingMatrixTest {
	
	@Test(timeout=2000)
	public void test0() {
		String[] M = new String[] {"1101",
 "0010"};
		assertEquals("possible", new UniformingMatrix().isPossible(M));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] M = new String[] {"11111",
 "11111",
 "11111",
 "11111",
 "11111",
 "11111",
 "11111",
 "11111"};
		assertEquals("possible", new UniformingMatrix().isPossible(M));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] M = new String[] {"110",
 "001",
 "110"};
		assertEquals("possible", new UniformingMatrix().isPossible(M));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] M = new String[] {"01010",
 "10101",
 "01010",
 "01010"};
		assertEquals("possible", new UniformingMatrix().isPossible(M));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String[] M = new String[] {"0110011",
 "1011000",
 "0101000",
 "0010001",
 "1010011",
 "1011011",
 "0111100"};
		assertEquals("impossible", new UniformingMatrix().isPossible(M));
	}
}
