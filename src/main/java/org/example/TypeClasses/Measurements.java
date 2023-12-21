package org.example.TypeClasses;

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
        return String.format("X : %-2d  Y : %-2d   Z : %d   ", x, y, z);
    }
    public double getVolume(){ return x*y*z;}
}
