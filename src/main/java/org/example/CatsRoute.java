package org.example;
import lombok.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Setter
@Getter
public class CatsRoute {

    Random random = new Random();
    AccuracyLevels accuracyLevel;
    private int routeStepsNumber;
    private HashMap< Integer, Coordinates> routeSteps;
    private List<Coordinates> coordinates;
    private Coordinates startPoint;
    private Coordinates endPoint;
    private int accuracy;
    private String text = "(The smaller accuracy is significant only for larger numbers)\n";

    public CatsRoute(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
        setStartPoint();
        setEndPoint();
        setRouteStepsNumber();
        setAccuracyLevel();
    }

    /** SETTERS **/
    private void setStartPoint() {
        this.startPoint = coordinates.get(random.nextInt(coordinates.size()));
    }
    private void setEndPoint() {
        do {
            this.endPoint = coordinates.get(random.nextInt(coordinates.size()));
        } while (this.endPoint == this.startPoint);
    }
    private void setRouteStepsNumber() {
        this.routeStepsNumber = random.nextInt(coordinates.size()-2)+1; // -2 because already 2 points are assigned to start and end.
    }
    private void setAccuracyLevel() {
            this.accuracyLevel = new AccuracyLevels((double) (accuracy)*0.05, (double) (7-accuracy)/10);
    }
    /** SETTERS **/

    public HashMap< Integer, Coordinates> getRoute(){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Provide a parameter of the algorithm accuracy (1 - 5)\n"+text+"\n");
            text="";
            accuracy = Integer.parseInt(scanner.nextLine());
        } while (accuracy > 5 || accuracy < 1);
        return calculateBestRoute();
    }

    private HashMap< Integer, Coordinates> calculateBestRoute(){
        routeSteps.put(1,startPoint);
        // TODO calcute the most efficient route from the coordinates
        return routeSteps;
    }

    private int getDistance(int currentStep, int newStep){
        return Math.abs(newStep - currentStep);
    }

}
