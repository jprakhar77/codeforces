import org.junit.Test;
import static org.junit.Assert.*;

public class FightMonsterDiv1Test {
	
	@Test(timeout=2000)
	public void test0() {
		long hp = 201L;
		long attack = 100L;
		int level = 10;
		long duration = 10L;
		assertEquals(2L, new FightMonsterDiv1().fightTime(hp, attack, level, duration));
	}
	
	@Test(timeout=2000000000)
	public void test1() {
		long hp = 74900L;
		long attack = 100L;
		int level = 0;
		long duration = 2L;
		assertEquals(742L, new FightMonsterDiv1().fightTime(hp, attack, level, duration));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long hp = 1000000000000L;
		long attack = 1000000000000L;
		int level = 100;
		long duration = 1000000000000L;
		assertEquals(1L, new FightMonsterDiv1().fightTime(hp, attack, level, duration));
	}

	@Test(timeout=2000000000)
	public void test3() {
		long hp = 1338L;
		long attack = 100L;
		int level = 1;
		long duration = 2L;
		assertEquals(6L, new FightMonsterDiv1().fightTime(hp, attack, level, duration));
	}

	@Test(timeout=2000000000)
	public void test4() {

		long hp = 999999594215l;
		long attack = 1000L;
		int level = 52;
		long duration = 649L;
		assertEquals(6L, new FightMonsterDiv1().fightTime(hp, attack, level, duration));
	}
}
