import org.junit.Test;
import static org.junit.Assert.*;

public class OneDimensionalBallsTest {
	
	@Test(timeout=2000000000)
	public void test0() {
		int[] firstPicture = new int[] {12,11};
		int[] secondPicture = new int[] {10,11,13};
		assertEquals(3L, new OneDimensionalBalls().countValidGuesses(firstPicture, secondPicture));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] firstPicture = new int[] {1,2,3};
		int[] secondPicture = new int[] {1,2,3};
		assertEquals(0L, new OneDimensionalBalls().countValidGuesses(firstPicture, secondPicture));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] firstPicture = new int[] {1,3};
		int[] secondPicture = new int[] {1,3};
		assertEquals(1L, new OneDimensionalBalls().countValidGuesses(firstPicture, secondPicture));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] firstPicture = new int[] {7234};
		int[] secondPicture = new int[] {6316,689156,689160,689161,800000,1000001};
		assertEquals(6L, new OneDimensionalBalls().countValidGuesses(firstPicture, secondPicture));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] firstPicture = new int[] {6,2,4};
		int[] secondPicture = new int[] {1,2,3,4,5,7,8};
		assertEquals(7L, new OneDimensionalBalls().countValidGuesses(firstPicture, secondPicture));
	}
}
