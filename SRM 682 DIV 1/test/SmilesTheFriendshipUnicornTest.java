import org.junit.Test;
import static org.junit.Assert.*;

public class SmilesTheFriendshipUnicornTest {
	
	@Test(timeout=2000)
	public void test0() {
		int N = 5;
		int[] A = new int[] {0, 1, 2, 3};
		int[] B = new int[] {1, 2, 3, 4};
		assertEquals("Yay!", new SmilesTheFriendshipUnicorn().hasFriendshipChain(N, A, B));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int N = 5;
		int[] A = new int[] {0, 1, 2, 3, 1};
		int[] B = new int[] {1, 2, 3, 0, 4};
		assertEquals("Yay!", new SmilesTheFriendshipUnicorn().hasFriendshipChain(N, A, B));
	}
	
	@Test(timeout=200000000)
	public void test2() {
		int N = 5;
		int[] A = new int[] {0, 0, 0, 0, 1, 3};
		int[] B = new int[] {1, 2, 3, 4, 4, 2};
		assertEquals("Yay!", new SmilesTheFriendshipUnicorn().hasFriendshipChain(N, A, B));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int N = 8;
		int[] A = new int[] {1, 3, 4, 3, 4, 3, 0, 2};
		int[] B = new int[] {7, 7, 7, 4, 6, 5, 4, 7};
		assertEquals("Yay!", new SmilesTheFriendshipUnicorn().hasFriendshipChain(N, A, B));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int N = 7;
		int[] A = new int[] {0, 1, 1, 5, 4, 5};
		int[] B = new int[] {5, 2, 3, 6, 1, 1};
		assertEquals(":(", new SmilesTheFriendshipUnicorn().hasFriendshipChain(N, A, B));
	}
	
	@Test(timeout=2000)
	public void test5() {
		int N = 42;
		int[] A = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41}
;
		int[] B = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 0};
		assertEquals("Yay!", new SmilesTheFriendshipUnicorn().hasFriendshipChain(N, A, B));
	}
}
