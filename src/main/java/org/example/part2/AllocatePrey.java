package org.example.part2;

import org.example.TypeClasses.Animal;
import org.example.TypeClasses.CustomTreeMap;
import org.example.TypeClasses.Measurements;
import org.example.TypeClasses.PreyType;

import java.util.*;

public class AllocatePrey {
    private final HashMap<Measurements, PreyType> score = new HashMap<>(); // 3D map of the storage
    private final HashMap<Measurements, PreyType> potentialAllocation = new HashMap<>();
    private final Measurements storageSize = new Measurements(10,20,10);

    PreyType currentType;

    private CustomTreeMap<Double, Animal> preyPrio;

    public void allocatePreys() {
        for (Map.Entry<Double, Animal> entry : preyPrio.entrySet()) {
            currentType = entry.getValue().getName();
            allocateStorage(entry.getValue());
        }
        int a=0;
        for (Map.Entry<Measurements, PreyType> entry : score.entrySet()) {
            a++;
            System.out.println("Position: "+entry.getKey() + "  Prey: "+ entry.getValue());
        }
        System.out.println("Allocated coords: "+a);
    }

    private void allocateStorage(Animal animal) {
        for (int i = 0; i < storageSize.getX(); i++) {
            for (int j = 0; j < storageSize.getY(); j++) {
                for (int k = 0; k < storageSize.getZ(); k++) {
                    if(checkSpace(animal.getMeasurements(), new Measurements(i,j,k)))
                        putAllocation();
                }
            }
        }
    }

    private void putAllocation() {
        for (Map.Entry<Measurements, PreyType> entry : potentialAllocation.entrySet()) {
            Measurements key = entry.getKey();
            PreyType value = entry.getValue();
            score.put(key, value);
        }
        potentialAllocation.clear();
    }

    private boolean checkSpace(Measurements animalSize, Measurements currentPos){
        for (int i = currentPos.getX(); i < animalSize.getX()+currentPos.getX(); i++) {
            for (int j = currentPos.getY(); j < animalSize.getY()+currentPos.getY(); j++) {
                for (int k = currentPos.getZ(); k < animalSize.getZ()+currentPos.getZ(); k++) {
                    Measurements coords = new Measurements(i,j,k);
                    if(!isValid(coords))
                        return false;
                    if(duplicatedCoords(coords)) {
                        return false;
                    }
                    potentialAllocation.put(coords,currentType);
                }
            }
        }
        return true;
    }

    private boolean duplicatedCoords(Measurements coord){
        for (Map.Entry<Measurements, PreyType> entry : score.entrySet()) {
            if(entry.getKey().getX()==coord.getX() && entry.getKey().getY()==coord.getY() && entry.getKey().getZ()==coord.getZ()) {
                System.out.println("duplicated coords");
                return true;
            }
        }
        return false;
    }

    private boolean isValid(Measurements coords) {
        if (coords.getX() >= storageSize.getX() || coords.getY() >= storageSize.getY() || coords.getZ() >= storageSize.getZ()) {
            System.out.println("invalid coords");
            return false;
        }
        return true;
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
