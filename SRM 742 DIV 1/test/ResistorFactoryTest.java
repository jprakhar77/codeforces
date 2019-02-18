import org.junit.Test;
import static org.junit.Assert.*;

public class ResistorFactoryTest {
	
	@Test(timeout=2000)
	public void test0() {
		long nanoOhms = 3000000000L;
		assertArrayEquals(new int[] {0, 0, 0, 0, 1, 0 }, new ResistorFactory().construct(nanoOhms));
	}
	
	@Test(timeout=2000)
	public void test1() {
		long nanoOhms = 1200000000L;
		assertArrayEquals(new int[] {0, 0, 0, 1, 0, 0, 1, 2, 1 }, new ResistorFactory().construct(nanoOhms));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long nanoOhms = 1428571428L;
		assertArrayEquals(new int[] {0, 0, 0, 0, 1, 0, 1, 2, 0, 3, 1, 1 }, new ResistorFactory().construct(nanoOhms));
	}
	
	@Test(timeout=2000)
	public void test3() {
		long nanoOhms = 12000000001L;
		assertArrayEquals(new int[] {0, 0, 0, 0, 1, 0, 2, 2, 0, 3, 3, 0 }, new ResistorFactory().construct(nanoOhms));
	}
	
	@Test(timeout=2000)
	public void test4() {
		long nanoOhms = 333333333L;
		assertArrayEquals(new int[] {0, 0, 1, 0, 1, 1 }, new ResistorFactory().construct(nanoOhms));
	}
}
