package org.example.part5;

import org.example.TypeClasses.AreaScore;
import org.example.TypeClasses.CatName;
import org.example.TypeClasses.Coordinates;
import org.example.TypeClasses.PreyType;
import org.example.part3.RandomizePreys;

import java.util.HashMap;
import java.util.Scanner;

public class DivideArea {

    private HashMap<Coordinates, AreaScore> areas = new HashMap<>();
    private RandomizePreys randomizePreys = new RandomizePreys();
    private final Coordinates mapSize = new Coordinates(5000,5000);
    private final Coordinates areaSize = new Coordinates(100,100);
    private AreaScore areaScore;

    public void checkAreas(){
        HashMap<Coordinates, PreyType> preyCoords = randomizePreys.randomPreys(mapSize);
        for (int i = 0; i < mapSize.getX()/ areaSize.getX(); i++) {
            for (int l = 0; l < mapSize.getY()/ areaSize.getY(); l++) {
                areaScore = new AreaScore(0, 0, 0);
                for (int j = 0; j < areaSize.getX(); j++) {
                    for (int k = 0; k < areaSize.getY(); k++) {
                        Coordinates key = new Coordinates(areaSize.getX() * i + j +1, areaSize.getY() * l + k +1);
                        if (preyCoords.containsKey(key))
                            addValue(preyCoords.get(key), areaScore);
                    }
                }
                areas.put(new Coordinates(areaSize.getX() * i,areaSize.getY() * l),areaScore);
            }
        }
        AssignArea assignArea = new AssignArea(areas);
        assignArea.assignAreasToCats();
    }

    private void addValue(PreyType preyType, AreaScore areaScore){
        switch (preyType) {
            case FIELD_MOUSE:
            case HOUSE_MOUSE:
                areaScore.setMouse(areaScore.getMouse() + 1);
                break;
            case SNAIL:
                areaScore.setSnail(areaScore.getSnail() + 1);
                break;
            case LEAF:
            case ROCK:
                areaScore.setMulch(areaScore.getMulch() + 1);
                break;
        }
    }
}
