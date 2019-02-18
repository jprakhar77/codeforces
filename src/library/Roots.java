package library;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Roots {
    long cubeRoot(long num) {
        long cubeRoot = (long) Math.cbrt(num);

        while (cubeRoot * cubeRoot * cubeRoot <= num) {
            if (cubeRoot * cubeRoot * cubeRoot == num) {
                return cubeRoot;
            } else {
                cubeRoot++;
            }
        }

        return -1;
    }

    long squareRoot(long num) {
        long squareRoot = (long) Math.sqrt(num);

        while (squareRoot * squareRoot <= num) {
            if (squareRoot * squareRoot == num) {
                return squareRoot;
            } else {
                squareRoot++;
            }
        }

        return -1;
    }
}
