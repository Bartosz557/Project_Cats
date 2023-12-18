package org.example.part2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Measurements {

    private int x,y,z;
    @Override
    public String toString() {
        return String.format("X : %-2d  Y : %-2d   Z : %d", x, y, z);
    }
}
