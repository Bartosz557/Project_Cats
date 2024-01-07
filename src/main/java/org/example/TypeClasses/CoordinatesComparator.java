package org.example.TypeClasses;

import java.util.Comparator;

public class CoordinatesComparator implements Comparator<Coordinates> {
    @Override
    public int compare(Coordinates c1, Coordinates c2) {
        int xComparison = Integer.compare(c1.getX(), c2.getX());
        if (xComparison != 0) {
            return xComparison;
        }
        return Integer.compare(c1.getY(), c2.getY());
    }
}
