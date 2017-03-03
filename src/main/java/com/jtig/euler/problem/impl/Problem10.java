package com.jtig.euler.problem.impl;

import com.jtig.euler.problem.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * <p>
 * Find the sum of all the primes below two million.
 */
public class Problem10 implements Problem {

    @Override
    public String getName() {
        return "Summation of primes";
    }

    @Override
    public String getDescription() {
        return "The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.\n" +
                "\n" +
                "Find the sum of all the primes below two million.";
    }

    @Override
    public Number getResult() {
        final Set<Integer> set = new HashSet<>();

        final int bigNumber = 2000000;
        for (int i = 1; i < bigNumber; i++) {
            set.add(i);
        }

        for (int i = 2; i < bigNumber / 2; i++) {
            int mult = 2;
            while (true) {
                final int num = i * mult;
                if (num > bigNumber) {
                    break;
                }
                set.remove(num);
                mult++;
            }
        }

        long sum = 0;
        for (final Integer i : set) {
            sum += i;
        }

        System.out.println(sum);
        System.out.println(Long.MAX_VALUE);
        return sum;
    }

    @Override
    public boolean getSolved() {
        return true;
    }
}
