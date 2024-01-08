package org.example.HuntAreaOrganizer_Part5;

import org.example.TypeModels.CatName;
import org.example.TypeModels.Coordinates;

import java.util.HashMap;

public class AssignTerritories {
    private int assignCount;
    private final HashMap<Coordinates, CatName> catsTerritory;
    private final int areaAmount;
    private final Coordinates[] catLastArea;

    public AssignTerritories(int assignCount, HashMap<Coordinates, CatName> catsTerritory, int areaAmount, Coordinates[] catLastArea) {
        this.assignCount = assignCount;
        this.catsTerritory = catsTerritory;
        this.areaAmount = areaAmount;
        this.catLastArea = catLastArea;
    }

    public void assignTerritory() {
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
    // It optimizes a little bit the loop, it breaks the loop while all coordinates are going to be out of bound
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
