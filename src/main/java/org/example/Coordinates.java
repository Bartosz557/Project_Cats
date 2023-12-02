package org.example;

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
        return "X: "+x+"  Y: "+y;
    }
}
