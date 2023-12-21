package org.example.part3;

import org.example.TypeClasses.CatName;
import org.example.TypeClasses.Coordinates;
import org.example.TypeClasses.PreyType;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CatsHauntingRoute {
    private final Random random = new Random();
    private final Coordinates mapSize = new Coordinates(5000,5000);
    private final Coordinates homePoint = new Coordinates(2500,2500); //TODO: delete if its not useful
    private Coordinates currentPoint = new Coordinates(2500,2500);
    private final int timeLimit = 7200;
    private int currentTime;
    private int space = 5; // amount of free slots for the prey
    private int currentCat;
    private HashMap<CatName,List<Coordinates>> catsRoute = new HashMap<>();
    private HashMap<CatName, List<PreyType>> catsTrophies = new HashMap<>();
    private HashMap<Coordinates, PreyType> preyCoords = new HashMap<>();
    public void catHauntingRoute(){
        System.out.println("The map is a 5000x5000 board. In order to simplify the task we assume the home is a single coordinate - 2500;2500");
        System.out.println("For the exercise purposes we will commit a crime on a mathematics and assume that Pythagoras was wrong.");
        RandomizePreys randomizePreys = new RandomizePreys();
        preyCoords = randomizePreys.randomPreys(mapSize);
        Coordinates lastDirection = getRandomDirection();
        for (int i = 0; i < CatName.values().length; i++) {
            currentCat = i;
            while( /*TODO: while the time is bigger than haunt*/ ){
                while (getTimeLeft() || space > 2) {
                    /** There is 1/3 chance the next step will be the same as the previous one, so the route seems to be a bt more natural and straightforward */
                    if (getDirection() > 0)
                        currentPoint = addStep(getRandomDirection());
                    else
                        currentPoint = addStep(lastDirection);
                }
                while (currentPoint.getX() != homePoint.getX() && currentPoint.getY() != homePoint.getY()) {
                    // TODO: Make route to the home
                }
            }
        }
    }

    private boolean getTimeLeft(){
        int returnTime = getReturnTime();
        /** The cat may have enough time to make 1 step and then return but if it catches a prey the time will not be enough to return anymore */
        int fixedCurrentTime = currentTime + 1 + 60*3;
        return timeLimit - fixedCurrentTime <= returnTime;
    }

    private int getReturnTime() {
        int xDistance = Math.abs(currentPoint.getX() - homePoint.getX());
        int yDistanace = Math.abs((currentPoint.getY() - homePoint.getY()));
        /** Because cats may cross the map diagonally, which means for the distance = 2 (eg: up and right) the diagonal distance = 1 (eg: upper right) */
        /** We simply don't care coordinates are actually centimeters */
        int distance = Math.max(xDistance,yDistanace);
        return (distance+1)/10 + 60*3*2;
    }

    private Coordinates getRandomDirection(){
        Coordinates coords;
        do {
            coords = new Coordinates((random.nextInt(3) - 1),(random.nextInt(3) - 1));
        } while (coords.getX()==0 && coords.getY()==0);
        return coords;
    }
    private int getDirection(){
        return random.nextInt(2);
    }
    private Coordinates addStep(Coordinates step){
        Coordinates newPos = getNewPos(step);
        if(crossedRoute(newPos))
            return currentPoint;
        else
            return newPos;
    }
    private Coordinates getNewPos(Coordinates step){
        return new Coordinates(currentPoint.getX()+step.getX(),currentPoint.getY()+step.getY());
    }
    private boolean crossedRoute(Coordinates pos){
        for (int i = 0; i < currentCat; i++) {
            if(duplicatedCoords(pos,catsRoute.get(CatName.values()[i])))
                return false;
        }
        return true;
    }
    private boolean duplicatedCoords(Coordinates coord, List<Coordinates> route){
        for (Coordinates coords: route) {
            if(coords.getX()==coord.getX() && coords.getY()==coord.getY())
                return true;
        }
        return false;
    }
}
