package org.example.RouteCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Coordinates {

    private int x,y;

    @Override
    public String toString() {
        return String.format("X : %-5d  Y : %d", x, y);
    }
}
