import org.junit.Test;
import static org.junit.Assert.*;

public class GangstersTest {
	
	@Test(timeout=2000)
	public void test0() {
		int people = 4;
		int alive = 2;
		assertEquals(12, new Gangsters().countOrderings(people, alive));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int people = 3;
		int alive = 1;
		assertEquals(6, new Gangsters().countOrderings(people, alive));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int people = 3;
		int alive = 0;
		assertEquals(0, new Gangsters().countOrderings(people, alive));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int people = 9;
		int alive = 3;
		assertEquals(203616, new Gangsters().countOrderings(people, alive));
	}
}
