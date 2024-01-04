package org.example.part5;

import org.example.TypeClasses.AreaScore;
import org.example.TypeClasses.CatName;
import org.example.TypeClasses.Coordinates;

import java.util.HashMap;

public class AssignArea {
    HashMap<Coordinates, AreaScore> areas;
    HashMap<Coordinates, CatName> catsTerritory = new HashMap<>();
    private final int massCenterSize = 10; // The amount of areas creating the mass center size.
    private final int areaSize = 100;
    private final int mapSize = 5000;
    private final int areaNum = (int)Math.pow((double)mapSize/areaSize,2)/massCenterSize;

    private final int iterationNum = 50 - (massCenterSize - 1);

    private HashMap<Coordinates,CatName> massCenter = new HashMap<>();

    public AssignArea(HashMap<Coordinates, AreaScore> areas) { this.areas = areas; }
    public void assignAreasToCats(){
        findMassCenter();
    }

    public void findMassCenter(){
        int max;
        for (int i = 0; i < CatName.values().length; i++) {
            max=0;
            for (int j = 0; j < iterationNum; j++) {
                for (int k = 0; k < iterationNum; k++) {
                    int value=0;
                    for (int q = 0; q < 10; q++) {
                        for (int l = 0; l < 10; l++) {
                            value+=getValue(i,j,k,q,l);
                        }
                    }
                    max+=value;
                }
            }
        }
    }

    private int getValue(int currentCat, int x, int y, int areaInBoxX, int areaInBoxY){
        switch (currentCat){
            case 0:
                return areas.get(new Coordinates((x+areaInBoxX)*areaSize,(y+areaInBoxY)*areaSize)).getMouse();
            case 1:
                return areas.get(new Coordinates((x+areaInBoxX)*areaSize,(y+areaInBoxY)*areaSize)).getSnail();
            case 2:
                return areas.get(new Coordinates((x+areaInBoxX)*areaSize,(y+areaInBoxY)*areaSize)).getMulch();
        }
        return 0;
    }
}