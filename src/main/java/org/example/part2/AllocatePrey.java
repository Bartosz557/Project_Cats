package org.example.part2;

import org.example.RouteCalculator.Coordinates;

import java.util.*;

public class AllocatePrey {
    private HashMap<Measurements, PreyType> score = new HashMap<>(); // 3D map of the storage

    private Measurements storageSize = new Measurements(10,10,20);

    private CustomTreeMap<Double, Animal> preyPrio;

    public void allocatePreys(){
        for (Map.Entry<Double, Animal> entry : preyPrio.entrySet()) {
            Animal animal = entry.getValue();
            Measurements animalSize = animal.getMeasurements();
            allocateStorage(animalSize);
        }
    }
    public void allocateStorage(Measurements size) {
        HashMap<Measurements, PreyType> tempMap = new HashMap<>();
        for (int i = 0; i < size.getVolume(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                for (int k = 0; k < size.getY(); k++) {
                    for (int l = 0; l < size.getZ(); l++) {
                    }
                }
            }
        }
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
