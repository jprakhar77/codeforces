import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleMathProblemDiv1Test {
	
	@Test(timeout=2000)
	public void test0() {
		long X = 1L;
		assertEquals(0L, new SimpleMathProblemDiv1().calculate(X));
	}
	
	@Test(timeout=2000)
	public void test1() {
		long X = 8L;
		assertEquals(36L, new SimpleMathProblemDiv1().calculate(X));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long X = 15L;
		assertEquals(128L, new SimpleMathProblemDiv1().calculate(X));
	}
	
	@Test(timeout=2000)
	public void test3() {
		long X = 777444111L;
		assertEquals(342683738130575177L, new SimpleMathProblemDiv1().calculate(X));
	}
}
