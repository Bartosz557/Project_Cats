package org.example.TypeClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AnimalValue {
    private PreyType name;
    private double value;
    @Override
    public String toString() { return String.format("  Name: %s\n  Value: %s\n",name,value); }
}
