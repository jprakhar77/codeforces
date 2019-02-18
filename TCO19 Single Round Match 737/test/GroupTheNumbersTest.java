import org.junit.Test;
import static org.junit.Assert.*;

public class GroupTheNumbersTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] a = new int[] {737};
		assertEquals("737", new GroupTheNumbers().calculate(a));
	}
	
	@Test(timeout=2000000000)
	public void test1() {
		int[] a = new int[] {-3,-7,-37};
		assertEquals("256", new GroupTheNumbers().calculate(a));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] a = new int[] {0,1,-1,2,-2,3,-3,4,-4};
		assertEquals("577", new GroupTheNumbers().calculate(a));
	}

	@Test(timeout=2000)
	public void test4() {
		int[] a = new int[] {0,-1,-1};
		assertEquals("1", new GroupTheNumbers().calculate(a));
	}

    @Test(timeout=2000)
    public void test6() {
        int[] a = new int[] {-1};
        assertEquals("-1", new GroupTheNumbers().calculate(a));
    }

	@Test(timeout=20000000)
	public void test5() {
		int[] a = new int[] {0,1,-1};
		assertEquals("1", new GroupTheNumbers().calculate(a));
	}

    @Test(timeout=20000000)
    public void test7() {
        int[] a = new int[] {0,-1};
        assertEquals("0", new GroupTheNumbers().calculate(a));
    }

    @Test(timeout=20000000)
    public void test8() {
        int[] a = new int[] {1,1,1,1,3};
        assertEquals("7", new GroupTheNumbers().calculate(a));
    }

    @Test(timeout=20000000)
    public void test9() {
        int[] a = new int[] {0,-1,1,1,1,3};
        assertEquals("6", new GroupTheNumbers().calculate(a));
    }

	@Test(timeout=2000)
	public void test3() {
		int[] a = new int[] {305802003, 136316694, 621448948, -175990184, 52551547, -566608000, 141205973, -137352529, 226239209, 73136038,
 345723809, 420451378, 455689639, -228162827, 593253055, 280556479, -470339174, -606141985, 594940027, -71768243,
 -475458047, 145718435, 441912314, -622312734, -701346268, 514619489, -280198616, -499528618, 545548925, 
 -219590088, 607143343, 481228395, -208750264, 212639054, 59098345, 177587003, 456261200};
		assertEquals("9552708453136043699764519962574992589764841282194475522705022369294739910524918614928401864033984307...4376758929349063867091932531272019128627221634558094207678674023362344641051055230025728000000000000", new GroupTheNumbers().calculate(a));
	}
}
