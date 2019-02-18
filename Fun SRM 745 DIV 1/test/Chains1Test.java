import org.junit.Test;
import static org.junit.Assert.*;

public class Chains1Test {
	
	@Test(timeout=2000000000)
	public void test0() {
		int n = 2;
		assertEquals(2L, new Chains1().countMaximalChains(n));
	}
}
