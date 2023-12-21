package org.example.RouteCalculator;

import org.example.TypeClasses.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetClosestPoints extends GetDistance {

    private int routeStepsNumber;
    private List<Coordinates> coordinates;
    private Coordinates centerPoint;

    public GetClosestPoints(int routeStepsNumber, List<Coordinates> coordinates, Coordinates centerPoint) {
        this.routeStepsNumber = routeStepsNumber;
        this.coordinates = coordinates;
        this.centerPoint = centerPoint;
    }

    public List<Coordinates> calculateClosestPoints() {

        int amount = Math.max(routeStepsNumber-2, coordinates.size() / 2);
        Collections.sort(coordinates, (c1, c2) ->
                Double.compare(calculateDistance(centerPoint, c1), calculateDistance(centerPoint, c2)));
        return new ArrayList<>(coordinates.subList(0, amount));
    }
}
