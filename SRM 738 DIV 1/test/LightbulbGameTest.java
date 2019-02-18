import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LightbulbGameTest {

    @Test(timeout = 2000)
    public void test0() {
        String[] board = new String[]{"000",
                "000",
                "000"};
        assertEquals(0, new LightbulbGame().countWinningMoves(board));
    }

    @Test(timeout = 2000)
    public void test1() {
        String[] board = new String[]{"0000",
                "0100",
                "0000"};
        assertEquals(1, new LightbulbGame().countWinningMoves(board));
    }

    @Test(timeout = 2000)
    public void test2() {
        String[] board = new String[]{"00000",
                "01001",
                "00000"};
        assertEquals(1, new LightbulbGame().countWinningMoves(board));
    }

    @Test(timeout = 2000)
    public void test3() {
        String[] board = new String[]{"10",
                "01"};
        assertEquals(0, new LightbulbGame().countWinningMoves(board));
    }

    @Test(timeout = 2000)
    public void test4() {
        String[] board = new String[]{"1111",
                "1110"};
        assertEquals(3, new LightbulbGame().countWinningMoves(board));
    }

    @Test(timeout = 2000)
    public void test5() {
        String[] board = new String[]{"00000",
                "01001", "00000"};
        assertEquals(1, new LightbulbGame().countWinningMoves(board));
    }

}
