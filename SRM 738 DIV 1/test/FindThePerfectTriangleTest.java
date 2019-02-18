import org.junit.Test;
import static org.junit.Assert.*;

public class FindThePerfectTriangleTest {
	
	@Test(timeout=2000)
	public void test0() {
		int area = 6;
		int perimeter = 11;
		assertArrayEquals(new int[] { }, new FindThePerfectTriangle().constructTriangle(area, perimeter));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int area = 6;
		int perimeter = 12;
		assertArrayEquals(new int[] {1, 1, 1, 4, 5, 4 }, new FindThePerfectTriangle().constructTriangle(area, perimeter));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int area = 37128;
		int perimeter = 882;
		assertArrayEquals(new int[] {137, 137, 273, 410, 1, 410 }, new FindThePerfectTriangle().constructTriangle(area, perimeter));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int area = 12;
		int perimeter = 18;
		assertArrayEquals(new int[] {1, 1, 4, 5, 1, 9 }, new FindThePerfectTriangle().constructTriangle(area, perimeter));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int area = 18096;
		int perimeter = 928;
		assertArrayEquals(new int[] {1, 1, 1, 88, 417, 88 }, new FindThePerfectTriangle().constructTriangle(area, perimeter));
	}
	
	@Test(timeout=2000)
	public void test5() {
		int area = 1000000;
		int perimeter = 1000;
		assertArrayEquals(new int[] { }, new FindThePerfectTriangle().constructTriangle(area, perimeter));
	}
}
