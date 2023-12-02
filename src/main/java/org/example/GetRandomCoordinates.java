package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRandomCoordinates {

    Random random = new Random();
    final int mapSize = 5000;
    List<Coordinates> rPoints = new ArrayList<>();
    public List<Coordinates> getPoints(int amount){
        int x,y;
        for (int i = 0; i < amount; i++) {
            x=random.nextInt(mapSize) + 1;
            y=random.nextInt(mapSize) + 1;
            Coordinates coordinates = new Coordinates(x,y);
            rPoints.add(coordinates);
        }
        return rPoints;
    }

}
