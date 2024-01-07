package org.example.part5;

import org.example.TypeClasses.AreaScore;
import org.example.TypeClasses.CatName;
import org.example.TypeClasses.Coordinates;
import org.example.TypeClasses.CoordinatesComparator;

import java.util.HashMap;
import java.util.TreeMap;

public class AssignArea {
    HashMap<Coordinates, AreaScore> areas;
    HashMap<Coordinates, CatName> catsTerritory = new HashMap<>();
    private final int massCenterSize = 10; // The amount of areas creating the mass center size.
    private final int areaSize = 100;
    private final int mapSize = 5000;
    private final int areaAmount = (int)Math.pow((double) mapSize /areaSize,2);
    private final Coordinates[] catLastArea = new Coordinates[3];
    private int assignCount = 0;
//    private HashMap<Coordinates,CatName> massCenter = new HashMap<>();
    public AssignArea(HashMap<Coordinates, AreaScore> areas) { this.areas = areas; }
    public void assignAreasToCats(){
        findMassCenter();
        assignTerritory();
        TreeMap<Coordinates, CatName> sortedMap = new TreeMap<>(new CoordinatesComparator());
        sortedMap.putAll(catsTerritory);
        for (HashMap.Entry<Coordinates, CatName> entry : sortedMap.entrySet()) {
            System.out.println("Coordinates: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
    public void findMassCenter(){
        int max;
        Coordinates massCenter;
        for (int i = 0; i < CatName.values().length; i++) {
            max=0;
            massCenter = new Coordinates(0,0);
            int iterationNum = 50 - (massCenterSize - 1);
            for (int j = 0; j < iterationNum; j++) {
                for (int k = 0; k < iterationNum; k++) {
                    int value=0;
                    if(i > 0 && !massCenterValid(j,k))
                        break;
                    for (int q = 0; q < massCenterSize; q++) {
                        for (int l = 0; l < massCenterSize; l++) {
                            value+=getValue(i,j,k,q,l);
                        }
                    }
                    if(value>max){
                        max=value;
                        massCenter = new Coordinates(j*areaSize,k*areaSize);
                    }
                }
            }
            assignMassCenter(i,massCenter);
            catLastArea[i] = new Coordinates(massCenter.getX()-100,massCenter.getY());
        }
    }
    // Checking if the new mass center area does not overlap the existing one
    private boolean massCenterValid(int x, int y) {
        for (int i = 0; i < massCenterSize; i++) {
            for (int j = 0; j < massCenterSize; j++) {
                if(catsTerritory.containsKey(new Coordinates((i+x)*areaSize,(j+y)*areaSize)))
                    return false;
            }
        }
        return true;
    }
    private void assignMassCenter(int catNum, Coordinates massCenter) {
        for (int i = 1; i <= massCenterSize; i++) {
            for (int j = 1; j <= massCenterSize; j++) {
                catsTerritory.put(new Coordinates(massCenter.getX()+(100*i) ,massCenter.getY()+(100*j)),CatName.values()[catNum]);
                assignCount++;
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

    private void assignTerritory() {
        final Coordinates[] directions = {
                new Coordinates(0, 100),
                new Coordinates(100, 0),
                new Coordinates(0, -100),
                new Coordinates(-100, 0)
        };
        int[] catDirection = {0,0,0};
        int[] edgeLength = {11,11,11};
        int[] directionMovesLeft = {edgeLength[0],edgeLength[1],edgeLength[2]};
        Coordinates newCatArea;
        boolean addCount = true;
        while(assignCount<areaAmount){
            for (int i = 0; i < CatName.values().length; i++) {
                do {
                    newCatArea = catLastArea[i].add(directions[catDirection[i]]);
                    if(!validateDirection(i,catDirection[i])){
                        for (int j = 0; j < directionMovesLeft[i]; j++) {
                            catLastArea[i] = catLastArea[i].add(directions[catDirection[i]]);
                        }
                        directionMovesLeft[i]=0;
                        addCount = false;
                    } else
                        directionMovesLeft[i]--;
                    if (directionMovesLeft[i] == 0) {
                        if (catDirection[i] == 3) {
                            catDirection[i] = 0;
                            edgeLength[i]++;
                        } else {
                            catDirection[i]++;
                        }
                        directionMovesLeft[i] = edgeLength[i];
                    }
                    catLastArea[i] = newCatArea;
                } while (!checkValidArea(newCatArea));
                if(addCount && areaInBound(newCatArea)) {
                    catsTerritory.put(newCatArea, CatName.values()[i]);
                    assignCount++;
                }
                addCount=true;
            }
        }
    }

    // It optimizes a little bit loop, it breaks the loop while all coordinates are going to be out of bound
    private boolean validateDirection(int catNum, int direction) {
        switch(direction){
            case 0:
                if(catLastArea[catNum].getY()>4900) return false;
            case 1:
                if(catLastArea[catNum].getX()>4900) return false;
            case 2:
                if(catLastArea[catNum].getY()<0) return false;
            case 3:
                if(catLastArea[catNum].getX()<0) return false;
        }
        return true;
    }

    private boolean checkValidArea(Coordinates newCatArea) {
        return !catsTerritory.containsKey(newCatArea);
    }
    private boolean areaInBound(Coordinates coords) {
        return coords.getX() >= 0 && coords.getX() <= 4900 && coords.getY() >= 0 && coords.getY() <= 4900;
    }
}