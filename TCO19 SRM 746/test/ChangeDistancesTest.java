import org.junit.Test;
import static org.junit.Assert.*;

public class ChangeDistancesTest {
	
	@Test(timeout=2000)
	public void test0() {
		String[] g = new String[] {"011","100","100"};
		assertArrayEquals(new String[] {"000", "001", "010" }, new ChangeDistances().findGraph(g));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] g = new String[] {"000","000","000"};
		assertArrayEquals(new String[] {"011", "100", "100" }, new ChangeDistances().findGraph(g));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] g = new String[] {"0100","1000","0001","0010"};
		assertArrayEquals(new String[] {"0011", "0010", "1100", "1000" }, new ChangeDistances().findGraph(g));
	}
}
