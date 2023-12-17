package org.example.RouteCalculator;
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
            do {
                x = random.nextInt(mapSize) + 1;
                y = random.nextInt(mapSize) + 1;
            } while (coordinateExists(rPoints, x, y));
            Coordinates coordinates = new Coordinates(x,y);
            rPoints.add(coordinates);
        }
        return rPoints;
    }

    private boolean coordinateExists(List<Coordinates> coordinatesList, int x, int y) {
        for (Coordinates coordinates : coordinatesList) {
            if (coordinates.getX() == x && coordinates.getY() == y) {
                return true;
            }
        }
        return false;
    }

}
