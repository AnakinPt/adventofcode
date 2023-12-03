package org.example;

import java.util.Objects;

public class Pair {
    private int i, j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static Pair of(int i, int j){
        return new Pair(i, j);
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return i == pair.i && j == pair.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
