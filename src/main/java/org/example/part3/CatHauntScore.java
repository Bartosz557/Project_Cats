package org.example.part3;
import org.example.part2.Animal;
import org.example.part2.CatName;
import org.example.part2.PreyType;
import org.example.part2.Measurements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CatHauntScore {

    public CatHauntScore(){
        setValues();
        setAnimals();
    }

    private HashMap<Measurements, PreyType> score = new HashMap<>(); // 3D map of the storage
    private final CatName[] catNames = {CatName.LUNA, CatName.ARIANA, CatName.DANTE};
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
    }

    /** SETTERS **/
    private void setValues(){
        double[][] preyValues = {
                {0.4, 0.4, 0.1, 0.1, 0.0},         // Luna
                {0.375, 0.375, 0.125, 0.125, 0.0}, // Ariana
                {0.5, 0.2, 0.2, 0.05, 0.05}        // Dante
        };
        for (int i = 0; i < 3; i++) {
            values.put(catNames[i],preyValues[i]);
        }
    }
    private void setAnimals(){
        for (int i = 0; i < 3; i++) {
            List<Animal> animals = new ArrayList<>();
            animals.add(new Animal(PreyType.FIELD_MOUSE, values.get(catNames[i])[0], new Measurements(5, 3, 3)));
            animals.add(new Animal(PreyType.HOUSE_MOUSE, values.get(catNames[i])[1], new Measurements(7, 2, 2)));
            animals.add(new Animal(PreyType.SNAIL, values.get(catNames[i])[2], new Measurements(3, 3, 3)));
            animals.add(new Animal(PreyType.LEAF, values.get(catNames[i])[3], new Measurements(3, 2, 1)));
            animals.add(new Animal(PreyType.ROCK, values.get(catNames[i])[4], new Measurements(2, 2, 1)));
            animals.add(new Animal(PreyType.EMPTY, values.get(catNames[i])[0], new Measurements(0, 0, 0)));
            cats.put(catNames[i],animals);
        }
    }
    /** SETTERS **/

}
