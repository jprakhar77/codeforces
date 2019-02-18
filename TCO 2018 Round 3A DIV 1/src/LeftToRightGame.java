public class LeftToRightGame {

    String alice = "Alice";
    String bob = "Bob";

    public String whoWins(int length, int divisor) {
        if (divisor == 1) {
            return bob;
        }
        if (length == 1)
            return alice;
        if (length % 2 != 0) {
            return alice;
        } else {
            if (divisor <= 11)
                return bob;
            else
                return alice;
        }
    }
}
