import org.junit.Test;
import static org.junit.Assert.*;

public class CubesOnATableTest {
	
	@Test(timeout=2000)
	public void test0() {
		int surface = 5;
		assertArrayEquals(new int[] {0, 0, 0 }, new CubesOnATable().placeCubes(surface));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int surface = 20;
		assertArrayEquals(new int[] {5, 5, 0, 5, 5, 1, 5, 6, 0, 5, 6, 1, 6, 5, 0, 6, 5, 1, 6, 6, 0, 6, 6, 1 }, new CubesOnATable().placeCubes(surface));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int surface = 25;
		assertArrayEquals(new int[] {0, 0, 0, 2, 2, 0, 4, 4, 0, 6, 6, 0, 8, 8, 0 }, new CubesOnATable().placeCubes(surface));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int surface = 32;
		assertArrayEquals(new int[] {1, 3, 0, 2, 3, 0, 2, 3, 1, 3, 3, 0, 3, 3, 1, 3, 3, 2, 4, 3, 0, 4, 3, 1, 4, 3, 2, 4, 3, 3 }, new CubesOnATable().placeCubes(surface));
	}
}
