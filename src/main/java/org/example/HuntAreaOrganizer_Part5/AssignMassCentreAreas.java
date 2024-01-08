package org.example.HuntAreaOrganizer_Part5;

import org.example.TypeModels.AreaScore;
import org.example.TypeModels.CatName;
import org.example.TypeModels.Coordinates;
import org.example.TypeModels.CoordinatesComparator;

import java.util.HashMap;
import java.util.TreeMap;

public class AssignMassCentreAreas {
    private final HashMap<Coordinates, AreaScore> areas;
    private final HashMap<Coordinates, CatName> catsTerritory = new HashMap<>();
    private final int massCenterSize = 10; // The amount of areas creating the mass center size.
    private final int areaSize = 100;
    private final int mapSize = 5000;
    private final int areaAmount = (int)Math.pow((double) mapSize /areaSize,2);
    private final Coordinates[] catLastArea = new Coordinates[3];
    private int assignCount = 0;
    public AssignMassCentreAreas(HashMap<Coordinates, AreaScore> areas) { this.areas = areas; }
    public void assignAreasToCats(){
        findMassCenter();
        AssignTerritories assignTerritories = new AssignTerritories(assignCount, catsTerritory, areaAmount, catLastArea);
        assignTerritories.assignTerritory();
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
}