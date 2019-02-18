import org.junit.Test;
import static org.junit.Assert.*;

public class MinDegreeSubgraphTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 3;
		long m = 3L;
		int k = 2;
		assertEquals("ALL", new MinDegreeSubgraph().exists(n, m, k));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 4;
		long m = 3L;
		int k = 2;
		assertEquals("SOME", new MinDegreeSubgraph().exists(n, m, k));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 6;
		long m = 10L;
		int k = 3;
		assertEquals("ALL", new MinDegreeSubgraph().exists(n, m, k));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int n = 6;
		long m = 15L;
		int k = 4;
		assertEquals("ALL", new MinDegreeSubgraph().exists(n, m, k));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int n = 17;
		long m = 53L;
		int k = 11;
		assertEquals("NONE", new MinDegreeSubgraph().exists(n, m, k));
	}
}
