import org.junit.Test;
import static org.junit.Assert.*;

public class RainForecastTest {
	
	@Test(timeout=2000)
	public void test0() {
		int ilkoProb = 7;
		int[] deliverProbs = new int[] {};
		assertEquals(0.93, new RainForecast().predictRain(ilkoProb, deliverProbs), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test1() {
		int ilkoProb = 93;
		int[] deliverProbs = new int[] {50};
		assertEquals(0.5, new RainForecast().predictRain(ilkoProb, deliverProbs), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test2() {
		int ilkoProb = 0;
		int[] deliverProbs = new int[] {90,90};
		assertEquals(0.82, new RainForecast().predictRain(ilkoProb, deliverProbs), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test3() {
		int ilkoProb = 11;
		int[] deliverProbs = new int[] {13, 92, 7};
		assertEquals(0.7084846399999999, new RainForecast().predictRain(ilkoProb, deliverProbs), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test4() {
		int ilkoProb = 50;
		int[] deliverProbs = new int[] {3, 17, 92, 34, 2, 14};
		assertEquals(0.5, new RainForecast().predictRain(ilkoProb, deliverProbs), 1e-9);
	}
}
