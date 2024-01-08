package org.example.RouteCalculator_Part1_Part2;

import org.example.TypeModels.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class GetClosestPoints extends GetDistance {
    private final int routeStepsNumber;
    private final List<Coordinates> coordinates;
    private final Coordinates centerPoint;

    public GetClosestPoints(int routeStepsNumber, List<Coordinates> coordinates, Coordinates centerPoint) {
        this.routeStepsNumber = routeStepsNumber;
        this.coordinates = coordinates;
        this.centerPoint = centerPoint;
    }
    public List<Coordinates> calculateClosestPoints() {
        int amount = Math.max(routeStepsNumber-2, coordinates.size() / 2);
        coordinates.sort((c1, c2) ->
                Double.compare(calculateDistance(centerPoint, c1), calculateDistance(centerPoint, c2)));
        return new ArrayList<>(coordinates.subList(0, amount));
    }
}
