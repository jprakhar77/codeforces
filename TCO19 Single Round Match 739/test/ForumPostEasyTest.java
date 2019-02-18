import org.junit.Test;
import static org.junit.Assert.*;

public class ForumPostEasyTest {
	
	@Test(timeout=2000)
	public void test0() {
		String[] exactPostTime = new String[] {"12:12:12"};
		String[] showPostTime = new String[] {"few seconds ago"};
		assertEquals("12:12:12", new ForumPostEasy().getCurrentTime(exactPostTime, showPostTime));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] exactPostTime = new String[] {"23:23:23","23:23:23"};
		String[] showPostTime = new String[] {"59 minutes ago","59 minutes ago"};
		assertEquals("00:22:23", new ForumPostEasy().getCurrentTime(exactPostTime, showPostTime));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] exactPostTime = new String[] {"00:10:10","00:10:10"};
		String[] showPostTime = new String[] {"59 minutes ago","1 hours ago"};
		assertEquals("impossible", new ForumPostEasy().getCurrentTime(exactPostTime, showPostTime));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] exactPostTime = new String[] {"11:59:13","11:13:23","12:25:15"};
		String[] showPostTime = new String[] {"few seconds ago","46 minutes ago","23 hours ago"};
		assertEquals("11:59:23", new ForumPostEasy().getCurrentTime(exactPostTime, showPostTime));
	}
}
