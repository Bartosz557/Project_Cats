package org.example.part2;

import java.util.*;

public class AllocatePrey {

    CustomTreeMap<Double, Animal> preyPrio;

    public void allocatePreys(){

    }

    public void valuePreys(List<Animal> animals){
        preyPrio = new CustomTreeMap<>(Comparator.reverseOrder());
        for (Animal animal : animals) {
            Measurements measurements = animal.getMeasurements();
            double amount = 2000/(measurements.getVolume());
            double wage = (amount*animal.getValue() + amount) * 0.5;
            preyPrio.put(wage,animal);
        }
        System.out.println(preyPrio);
    }

}
