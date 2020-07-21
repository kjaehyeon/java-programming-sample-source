package jungsuk_practice;

import java.math.BigInteger;
import java.util.*;

public class BigFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(calcFactorial(n));
    }
    public static String calcFactorial(int n){
        return factorial(BigInteger.valueOf(n)).toString();
    }
    public static BigInteger factorial(BigInteger n){
        if(n.equals(BigInteger.ZERO)) return BigInteger.ONE;
        else
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
}
