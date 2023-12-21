package org.example.TypeClasses;

import org.example.TypeClasses.Animal;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomTreeMap<K, V> extends TreeMap<K, V> {

    private final DecimalFormat df = new DecimalFormat("0.00");
    public CustomTreeMap(Comparator<? super K> comparator) {
        super(comparator);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<K, V> entry : entrySet()) {
            
            result.append("\nPotential score for ").append(getValueAsString(entry.getValue())).append(":\n  ").append(df.format(entry.getKey())).append("\n");
        }

        return result.toString();
    }
    private String getValueAsString(Object value) {
        return (value instanceof Animal) ? ((Animal) value).getName().name() : value.toString();
    }
}