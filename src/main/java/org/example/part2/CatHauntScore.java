package org.example.part2;
import org.example.TypeClasses.Animal;
import org.example.TypeClasses.CatName;
import org.example.TypeClasses.Measurements;
import org.example.TypeClasses.PreyType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CatHauntScore {

    public CatHauntScore(){
        setValues();
        setAnimals();
    }
    private final Scanner scanner = new Scanner(System.in);

    private final HashMap<CatName,List<Animal>> cats = new HashMap<>();
    private final HashMap<CatName,double[]> values = new HashMap<>();

    public void optimizeHaunting(){
        for (HashMap.Entry<CatName, List<Animal>> entry : cats.entrySet()) {
            System.out.println("Cat: " + entry.getKey());
            System.out.println("Animals:\n");
            for (Animal animal : entry.getValue()) {
                System.out.println(animal);
            }
            System.out.println();
        }
        AllocatePrey allocatePrey = new AllocatePrey();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println();
            }
            System.out.print("Priority for " + CatName.values()[i] + ":\n");
            allocatePrey.valuePreys(cats.get(CatName.values()[i]));
            System.out.print("Allocation for " + CatName.values()[i] + ":\n");
            allocatePrey.allocatePreys();
            if(i<2) {
                System.out.println("\nPress Enter to allocate next cat's storage...");
                scanner.nextLine();
            }
        }
    }

    /** SETTERS **/
    private void setValues(){
        double[][] preyValues = {
                {0.4, 0.4, 0.1, 0.0, 0.1},         // Luna
                {0.125, 0.125, 0.375, 0.375, 0.0}, // Ariana
                {0.2, 0.2, 0.05, 0.05, 0.5}        // Dante
        };
        for (int i = 0; i < 3; i++) {
            values.put(CatName.values()[i],preyValues[i]);
        }
    }
    private void setAnimals(){
        for (int i = 0; i < 3; i++) {
            List<Animal> animals = new ArrayList<>();
            animals.add(new Animal(PreyType.FIELD_MOUSE, values.get(CatName.values()[i])[0], new Measurements(5, 3, 3)));
            animals.add(new Animal(PreyType.HOUSE_MOUSE, values.get(CatName.values()[i])[1], new Measurements(7, 2, 2)));
            animals.add(new Animal(PreyType.SNAIL, values.get(CatName.values()[i])[2], new Measurements(3, 3, 3)));
            animals.add(new Animal(PreyType.LEAF, values.get(CatName.values()[i])[3], new Measurements(3, 2, 1)));
            animals.add(new Animal(PreyType.ROCK, values.get(CatName.values()[i])[4], new Measurements(2, 2, 1)));
            cats.put(CatName.values()[i],animals);
        }
    }
    /** SETTERS **/
}
