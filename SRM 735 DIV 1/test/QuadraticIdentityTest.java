//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class QuadraticIdentityTest {
//
//	@Test(timeout=2000)
//	public void test0() {
//		long m = 20L;
//		assertArrayEquals(new long[] {0, 1, 5, 16 }, new QuadraticIdentity().getFixedPoints(m));
//	}
//
//	@Test(timeout=2000)
//	public void test1() {
//		long m = 42620_4660_2L;
//		assertArrayEquals(new long[] {0, 1, 27391046, 152613648, 180004693, 333799345, 361190390, 513804038, 541195083, 621181264, 648572309, 801185957, 828577002, 941255910, 968646955, 982371654, 1009762699, 1121260603, 1148651648, 1162376347, 1189767392, 1302446300, 1329837345, 1482450993, 1509842038, 1589828219, 1617219264, 1769832912, 1797223957, 1951018609, 1978409654, 2103632256, 2131023301, 2131023302, 2158414347, 2283636949, 2311027994, 2464822646, 2492213691, 2644827339, 2672218384, 2752204565, 2779595610, 2932209258, 2959600303, 3072279211, 3099670256, 3113394955, 3140786000, 3252283904, 3279674949, 3293399648, 3320790693, 3433469601, 3460860646, 3613474294, 3640865339, 3720851520, 3748242565, 3900856213, 3928247258, 4082041910, 4109432955, 4234655557 }, new QuadraticIdentity().getFixedPoints(m));
//	}
//
//	@Test(timeout=2000)
//	public void test2() {
//		long m = 7L;
//		assertArrayEquals(new long[] {0, 1 }, new QuadraticIdentity().getFixedPoints(m));
//	}
//}