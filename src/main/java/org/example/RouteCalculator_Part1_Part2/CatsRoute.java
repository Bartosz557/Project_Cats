package org.example.RouteCalculator_Part1_Part2;
import lombok.*;
import org.example.TypeModels.Coordinates;

import java.util.*;

@Setter
@Getter
public class CatsRoute extends GetDistance{

    Random random = new Random();
    private int routeStepsNumber;
    private HashMap< Integer, Coordinates> routeSteps = new HashMap<>();
    private List<Coordinates> coordinates;
    private Coordinates startPoint, endPoint;

    public CatsRoute(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
        setRouteStepsNumber();
        setStartPoint();
        setEndPoint();
    }
    public void getRoute() {
        GetClosestPoints getClosestPoints = new GetClosestPoints(routeStepsNumber, coordinates, getCenterPoint());
        coordinates = getClosestPoints.calculateClosestPoints();
        HashMap<Integer, Coordinates> bestRoute = findShortestRoute(coordinates, routeStepsNumber, startPoint, endPoint);
        System.out.println("Shortest Route:");
        for (Map.Entry<Integer, Coordinates> entry : bestRoute.entrySet()) {
            Integer key = entry.getKey();
            Coordinates value = entry.getValue();
            System.out.printf("Step: %-4s Coordinates: %-4s%n", key, value);
        }
        int totalDistance = calculateTotalDistance(bestRoute);
        System.out.println("Total Distance: " + totalDistance/100 + "m");
    }
    private HashMap<Integer, Coordinates> findShortestRoute(List<Coordinates> coordinates, int numSteps, Coordinates start, Coordinates end) {
        HashMap<Integer, Coordinates> bestRoute = nearestNeighbor(coordinates);
        bestRoute.put(1, start);
        bestRoute.put(numSteps, end);
        return bestRoute;
    }
    private HashMap<Integer, Coordinates> nearestNeighbor(List<Coordinates> coordinates) {
        HashMap<Integer, Coordinates> route = new HashMap<>();
        Coordinates lastCoordinate;
        for (int i = 2; i < routeStepsNumber; i++) {
            if(i==2)
                lastCoordinate = startPoint;
            else
                lastCoordinate = route.get(i-1);
            Coordinates nearestNeighbor = findNearestNeighbor(lastCoordinate, coordinates);
            route.put(i,nearestNeighbor);
            coordinates.remove(nearestNeighbor);
        }
        return route;
    }
    private Coordinates findNearestNeighbor(Coordinates source, List<Coordinates> candidates) {
        double minDistance = Double.MAX_VALUE;
        Coordinates nearestNeighbor = null;

        for (Coordinates candidate : candidates) {
            double distance = calculateDistance(source, candidate);
            if (distance < minDistance) {
                minDistance = distance;
                nearestNeighbor = candidate;
            }
        }
        return nearestNeighbor;
    }
    private int calculateTotalDistance(HashMap<Integer, Coordinates> route) {
        double totalDistance = 0;
        List<Map.Entry<Integer, Coordinates>> entryList = new ArrayList<>(route.entrySet());
        entryList.sort(Comparator.comparingInt(Map.Entry::getKey));
        for (int i = 0; i < entryList.size() - 1; i++) {
            Coordinates currentCoordinate = entryList.get(i).getValue();
            Coordinates nextCoordinate = entryList.get(i + 1).getValue();
            totalDistance += calculateDistance(currentCoordinate, nextCoordinate);
        }
        return (int)Math.round(totalDistance);
    }
    private Coordinates getCenterPoint(){
        int newX = (startPoint.getX() + startPoint.getX()) / 2;
        int newY = (endPoint.getY() + endPoint.getY()) / 2;
        return new Coordinates(newX, newY);
    }
    /* SETTERS */
    private void setRouteStepsNumber() {
        this.routeStepsNumber = random.nextInt(coordinates.size()-2)+3;
    } // 3 is the minimum
    private void setStartPoint() {
        this.startPoint = coordinates.get(random.nextInt(coordinates.size()));
        coordinates.remove(this.startPoint);
    }
    private void setEndPoint() {
        this.endPoint = coordinates.get(random.nextInt(coordinates.size()));
        coordinates.remove(this.endPoint);
    }
    /* SETTERS */
}
