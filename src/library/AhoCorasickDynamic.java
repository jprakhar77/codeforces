package library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AhoCorasickDynamic {

    List<AhoCorasick> ahoCorasicks;
    int alphabetSize;
    int size;

    public AhoCorasickDynamic(int alphabetSize) {
        this.alphabetSize = alphabetSize;
        this.ahoCorasicks = new ArrayList<>();
    }

    void addString(String str) {
        AhoCorasick ahoCorasick = new AhoCorasick(new String[]{str}, 1, alphabetSize, str.length());
        ahoCorasick.precompute();
        ahoCorasicks.add(ahoCorasick);
        size++;
        while (size >= 2 && sameSizeLast2States()) {
            AhoCorasick ahoCorasick1 = ahoCorasicks.get(size - 1);
            AhoCorasick ahoCorasick2 = ahoCorasicks.get(size - 2);

            ahoCorasick = merge(ahoCorasick1, ahoCorasick2);

            ahoCorasicks.remove(size - 1);
            size--;
            ahoCorasicks.remove(size - 1);

            ahoCorasicks.add(ahoCorasick);
        }
    }

    AhoCorasick merge(AhoCorasick ahoCorasick1, AhoCorasick ahoCorasick2) {
        int mergesSize = ahoCorasick1.m + ahoCorasick2.m;

        String[] strings = Stream.concat(Arrays.stream(ahoCorasick1.str), Arrays.stream(ahoCorasick2.str))
                .toArray(String[]::new);

        int maxStates = ahoCorasick1.maxStates + ahoCorasick2.maxStates;

        AhoCorasick ahoCorasick = new AhoCorasick(strings, mergesSize, alphabetSize, maxStates);
        ahoCorasick.precompute();

        return ahoCorasick;
    }

    boolean sameSizeLast2States() {
        int lastSize = ahoCorasicks.get(size - 1).m;
        int secLastSize = ahoCorasicks.get(size - 2).m;

        return lastSize == secLastSize;
    }

    public int findNumOfMatchingWords(String text) {
        int matchingWords = 0;

        for (int i = 0; i < size; i++) {
            matchingWords += ahoCorasicks.get(i).findNumOfMatchingWords(text);
        }

        return matchingWords;
    }

    public List<String> findMatchingWords(String text) {
        List<String> matchingWords = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            matchingWords.addAll(ahoCorasicks.get(i).findMatchingWords(text));
        }

        return matchingWords;
    }
}