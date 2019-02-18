import org.junit.Test;
import static org.junit.Assert.*;

public class MaximizingGCDTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] A = new int[] {5, 4, 13, 2};
		assertEquals(6, new MaximizingGCD().maximumGCDPairing(A));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] A = new int[] {26, 23};
		assertEquals(49, new MaximizingGCD().maximumGCDPairing(A));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] A = new int[] {100, 200, 300, 500, 1100, 700};
		assertEquals(100, new MaximizingGCD().maximumGCDPairing(A));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] A = new int[] {46, 78, 133, 92, 1, 23, 29, 67, 43, 111, 3908, 276, 13, 359, 20, 21};
		assertEquals(4, new MaximizingGCD().maximumGCDPairing(A));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] A = new int[] {16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 
65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608};
		assertEquals(16400, new MaximizingGCD().maximumGCDPairing(A));
	}
}
