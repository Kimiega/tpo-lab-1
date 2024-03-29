package com.example.tpolab1.task1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.lang.Math.*;

public class MaclaurinSeries {
    private final static double PI2 = PI * 2;

    public double sec(double x, int n) {
        if (Double.isNaN(x))
            throw new ArithmeticException("x is NaN");
        if (Double.isInfinite(x))
            throw new ArithmeticException("x is infinity");
        if (x >= 0) {
            while (x > PI) {
                x -=PI2;
            }
        } else {
            while (x < -PI) {
                x += PI2;
            }
        }
        if (abs(x) == PI/2)
            return Double.NaN;

        boolean negativeSign = false;

        if (abs(x) > PI/2) {
            negativeSign = true;
            x += x > 0 ? -PI : PI;
        }
        BigDecimal res = BigDecimal.ZERO;
        BigDecimal temp;
        var eulerNumbersList = eulerNumbers(n + 1);

        for (int k = 0; k <= n; ++k) {
            temp = BigDecimal.ONE;
            if (k % 2 != 0)
                temp = temp.negate();
            temp = temp.multiply(BigDecimal.valueOf(x).pow(2 * k)).multiply(new BigDecimal(eulerNumbersList.get(k))).divide(new BigDecimal(getFactorial(2*k)), n, RoundingMode.CEILING);
            res = res.add(temp);
        }
        if (negativeSign) {
            res = res.negate();
        }

        return res.doubleValue();

    }

    private static BigInteger getFactorial(int f) {
        if (f <= 1) {
            return BigInteger.ONE;
        }
        else {
            return IntStream.rangeClosed(2, f)
                    .mapToObj(BigInteger::valueOf)
                    .reduce(BigInteger::multiply)
                    .orElseThrow(() -> new ArithmeticException(String.format("Factorial of %d couldn't be calculated", f)));
        }
    }
    private static List<BigInteger> accumulate(List<BigInteger> list) {
        final BigInteger[] sum = {BigInteger.ZERO};
        return  list.stream().map((BigInteger x) -> {
            sum[0] = sum[0].add(x);
            return sum[0];
        }).collect(Collectors.toList());
    }

    private List<BigInteger> eulerNumbers(int n) {
        List<BigInteger> A000111_list = new ArrayList<>();
        A000111_list.add(BigInteger.ONE);
        A000111_list.add(BigInteger.ONE);
        List<BigInteger> blist = new ArrayList<>();
        blist.add(BigInteger.ONE);
        for (int i = 0; i < (n - 1) * 2; ++i) {
            if (i % 2 == 0) {
                blist = accumulate(blist);
                blist.add(0, BigInteger.ZERO);
            }
            else {
                Collections.reverse(blist);
                blist = accumulate(blist);
                Collections.reverse(blist);
                blist.add(BigInteger.ZERO);
            }
            A000111_list.add(blist.stream().reduce(BigInteger.ZERO, BigInteger::add));
        }
        return IntStream
                .range(0, A000111_list.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> i % 4 == 2 ? A000111_list.get(i).negate() : A000111_list.get(i))
                .collect(Collectors.toList());
    }
}
