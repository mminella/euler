package com.jtig.euler.problem;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProblemRegistry {
    public static ProblemRegistry instance = new ProblemRegistry();

    private ConcurrentMap<Integer, Problem> problems;

    private ProblemRegistry() {
        this.problems = new ConcurrentHashMap<>();
    }

    public Problem get(int index) {
        return problems.computeIfAbsent(index, i -> {
            try {
                Class<?> clazz = Class.forName("com.jtig.euler.problem.impl.Problem" + i);
                return (Problem) clazz.newInstance();
            } catch (Exception e) {
                return null;
            }
        });
    }
}
