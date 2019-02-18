import org.junit.Test;
import static org.junit.Assert.*;

public class CoprimeMatrixTest {
	
	@Test(timeout=2000000000)
	public void test0() {
		String[] coprime = new String[] {"NYNYN",
 "YNYYN",
 "NYNYN",
 "YYYNY",
 "NNNYN"};
		assertEquals("Possible", new CoprimeMatrix().isPossible(coprime));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] coprime = new String[] {"NY",
 "NN"};
		assertEquals("Impossible", new CoprimeMatrix().isPossible(coprime));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] coprime = new String[] {"NYYYYN",
 "YNYNNN",
 "YYNYYY",
 "YNYNYN",
 "YNYYNY",
 "NNYNYN"};
		assertEquals("Possible", new CoprimeMatrix().isPossible(coprime));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] coprime = new String[] {"NN",
 "NN"};
		assertEquals("Impossible", new CoprimeMatrix().isPossible(coprime));
	}
}
