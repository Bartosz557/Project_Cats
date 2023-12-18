package org.example.part2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Setter
@Getter
@AllArgsConstructor
public class Animal {

    private PreyType name;
    private double value;
    private Measurements measurements;
    private final DecimalFormat df = new DecimalFormat("0.000");
    @Override
    public String toString() {
        return String.format("  Name: %s\n  Value: %s\n  Measurements:  %s\n", name, df.format(value), measurements);
    }
}
