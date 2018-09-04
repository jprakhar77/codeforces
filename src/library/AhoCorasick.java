package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AhoCorasick {
    String[] str;
    int m;
    int alphabetSize;
    int maxStates;

    int numOfStates;

    int[][] nextState;
    int[] failure;
    List[] endingStrs;

    public AhoCorasick(String[] str, int m, int alphabetSize, int maxStates) {
        this.str = str;
        this.m = m;
        this.alphabetSize = alphabetSize;
        this.maxStates = maxStates;

        this.nextState = new int[maxStates + 1][alphabetSize];

        for (int i = 0; i <= maxStates; i++) {
            for (int j = 0; j < alphabetSize; j++) {
                nextState[i][j] = -1;
            }
        }

        this.failure = new int[maxStates + 1];
        this.endingStrs = new List[maxStates + 1];
    }

    public void precompute() {
        computeNextState();

        computeFailure();
    }

    private void computeFailure() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < alphabetSize; i++) {
            if (nextState[0][i] > 0) {
                dq.addLast(nextState[0][i]);
            }
        }

        while (!dq.isEmpty()) {
            int curState = dq.removeFirst();

            for (int i = 0; i < alphabetSize; i++) {
                if (nextState[curState][i] != -1) {

                    int curFailure = failure[curState];

                    while (nextState[curFailure][i] == -1) {
                        curFailure = failure[curFailure];
                    }

                    curFailure = nextState[curFailure][i];
                    failure[nextState[curState][i]] = curFailure;

                    dq.addLast(nextState[curState][i]);

                    if (!Objects.isNull(endingStrs[curFailure])) {
                        if (endingStrs[nextState[curState][i]] == null)
                            endingStrs[nextState[curState][i]] = new ArrayList<Integer>();
                        endingStrs[nextState[curState][i]].addAll(endingStrs[curFailure]);
                    }
                }
            }
        }
    }

    private void computeNextState() {
        numOfStates = 1;
        for (int i = 0; i < m; i++) {
            int curState = 0;

            for (int j = 0; j < str[i].length(); j++) {
                char ch = str[i].charAt(j);

                if (nextState[curState][ch - 'a'] == -1) {
                    nextState[curState][ch - 'a'] = numOfStates++;
                }

                curState = nextState[curState][ch - 'a'];
            }

            if (endingStrs[curState] == null)
                endingStrs[curState] = new ArrayList<Integer>();

            endingStrs[curState].add(i);
        }

        for (int i = 0; i < alphabetSize; i++) {
            if (nextState[0][i] == -1)
                nextState[0][i] = 0;
        }
    }

    private int findNextState(int curState, char ch) {
        int chi = ch - 'a';

        while (nextState[curState][chi] == -1) {
            curState = failure[curState];
        }

        return nextState[curState][chi];
    }

    public int findNumOfMatchingWords(String text) {

        int curState = 0;

        int matchingWords = 0;

        for (int i = 0; i < text.length(); i++) {
            curState = findNextState(curState, text.charAt(i));

            if (!Objects.isNull(endingStrs[curState]))
                matchingWords += endingStrs[curState].size();
        }

        return matchingWords;
    }

    public List<String> findMatchingWords(String text) {
        int curState = 0;

        List<String> matchingWords = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            curState = findNextState(curState, text.charAt(i));

            if (!Objects.isNull(endingStrs[curState]))
                matchingWords.addAll(endingStrs[curState]);
        }

        return matchingWords;
    }

}