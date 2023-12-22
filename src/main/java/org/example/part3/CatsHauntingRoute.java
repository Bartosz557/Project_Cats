package org.example.part3;

import org.example.TypeClasses.CatName;
import org.example.TypeClasses.Coordinates;
import org.example.TypeClasses.PreyType;

import java.util.*;

public class CatsHauntingRoute {

    public CatsHauntingRoute(){
        setHauntTime();
    }
    Scanner scanner = new Scanner(System.in);

    private final Random random = new Random();
    private final Coordinates mapSize = new Coordinates(5000,5000);
    private final Coordinates homePoint = new Coordinates(2500,2500); //TODO: delete if its not useful
    private Coordinates currentPoint;
    private final int timeLimit = 7200;
    private double currentTime;
    private int space = 5; // amount of free slots for the prey
    private int currentCat;

    private HashMap<PreyType,Double> hauntTime = new HashMap<>();
    List<Coordinates> steps;
    List<PreyType> preys;
    private HashMap<CatName,List<Coordinates>> catsRoute = new HashMap<>();
    private HashMap<CatName, List<PreyType>> catsTrophies = new HashMap<>();
    private HashMap<Coordinates, PreyType> preyCoords = new HashMap<>();
    public void catHauntingRoute(){
        System.out.println("The map is a 5000x5000 board. In order to simplify the task we assume the home is a single coordinate - 2500;2500");
        System.out.println("For the exercise purposes we will commit a crime on a mathematics and assume that Pythagoras was wrong.\n\n\n");
        RandomizePreys randomizePreys = new RandomizePreys();
        preyCoords = randomizePreys.randomPreys(mapSize);
        for (int i = 0; i < CatName.values().length; i++) {
            Coordinates lastDirection = getRandomDirection();
            steps = new ArrayList<>();
            preys = new ArrayList<>();
            currentCat = i;
            currentTime = 0;
            space = 5;
            currentPoint = new Coordinates(2500,2500);
            while(getTimeLeft()){
                while (getTimeLeft() && space > 2) {
                    /** There is 1/3 chance the next step will be the same as the previous one, so the route seems to be a bit more natural and straightforward */
                    if (getDirection() > 0) {
                        lastDirection = getRandomDirection();
                        currentPoint = addStep(lastDirection);
                    }
                    else
                        currentPoint = addStep(lastDirection);
                    checkForPrey();
                }
                while (currentPoint.getX() != homePoint.getX() && currentPoint.getY() != homePoint.getY()) {
                    int xDistance =  homePoint.getX() - currentPoint.getX();  // if negative needs to go down
                    int yDistanace = homePoint.getY() - currentPoint.getY();
                    Coordinates nextStep;
                    for (int k = 0; k < Math.min(Math.abs(xDistance),Math.abs(yDistanace)); k++) {
                        nextStep = addStep(new Coordinates(getHomeDirection(xDistance),getHomeDirection(yDistanace)));
                        if(currentPoint.getX()==nextStep.getX() && currentPoint.getY()==nextStep.getY())
                            k--;
                        currentPoint = nextStep;
                        if(space>0)
                            checkForPrey();
                    }
                    for (int j = 0; j < (Math.abs(Math.abs(xDistance)-Math.abs(yDistanace))); j++) {
                        int xDistance2 =  homePoint.getX() - currentPoint.getX();  // if negative needs to go down
                        int yDistance2 = homePoint.getY() - currentPoint.getY();
                        nextStep = addStep(new Coordinates(getHomeDirection(xDistance2),getHomeDirection(yDistance2)));
                        if(currentPoint.getX()==nextStep.getX() && currentPoint.getY()==nextStep.getY())
                            j--;
                        currentPoint = nextStep;
                        if(space>0)
                            checkForPrey();
                    }
                }
            }
            if(currentPoint.getX() == homePoint.getX() && currentPoint.getY() == homePoint.getY())
                System.out.println(CatName.values()[i]+" successfully returned to the home");
            else
                System.out.println(CatName.values()[i]+" somehow did not return to the home");
            catsRoute.put(CatName.values()[i],steps);
            catsTrophies.put(CatName.values()[i],preys);
        }

        for (int i = 0; i < CatName.values().length; i++) {
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            System.out.println("\n\nThe route of the cat: "+CatName.values()[i]+"\n");
            for( Coordinates step : catsRoute.get(CatName.values()[i]))
                System.out.println(step);
            System.out.println("\nThe preys of the cat: "+CatName.values()[i]+"\n");
            for( PreyType prey : catsTrophies.get(CatName.values()[i]))
                System.out.println(prey);
        }
    }



    private int getHomeDirection(int distance) {
        int result;
        if (distance <= 0) {
            if(distance==0)
                result = 0;
            else
                result = -1;
        } else
            result = 1;
        return result;
    }

    private void checkForPrey() {
        Iterator<HashMap.Entry<Coordinates, PreyType>> iterator = preyCoords.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry<Coordinates, PreyType> entry = iterator.next();
            if (entry.getKey().getX() == currentPoint.getX() && entry.getKey().getY() == currentPoint.getY()) {
                preys.add(entry.getValue());
                space--;
                currentTime += hauntTime.get(entry.getValue());
                iterator.remove();
            }
        }
    }

    private boolean getTimeLeft(){
        double returnTime = getReturnTime();
        /** The cat may have enough time to make 1 step and then return but if it catches a prey the time will not be enough to return anymore */
        double fixedCurrentTime = currentTime + 1 + 60*3;
        return returnTime <= timeLimit - fixedCurrentTime;
//        return true;
    }

    private double getReturnTime() {
        int xDistance = Math.abs(currentPoint.getX() - homePoint.getX());
        int yDistanace = Math.abs((currentPoint.getY() - homePoint.getY()));
        /** Because cats may cross the map diagonally, which means for the distance = 2 (eg: up and right) the diagonal distance = 1 (eg: upper right) */
        /** We simply don't care the coordinates are actually centimeters */
        int distance = Math.max(xDistance,yDistanace);
        return (double) (distance + 1) /10 + 60*3*2 + 600;
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
        if(crossedRoute(newPos) || !validCoords(newPos))
            return currentPoint;
        currentTime+=0.1;
        steps.add(newPos);
        return newPos;
    }

    private boolean validCoords(Coordinates coords){
        if(coords.getX()>5000 || coords.getY()>5000 || coords.getX()<0 || coords.getY()<0)
            return false;
        return true;
    }
    private Coordinates getNewPos(Coordinates step){
        return new Coordinates(currentPoint.getX()+step.getX(),currentPoint.getY()+step.getY());
    }
    private boolean crossedRoute(Coordinates pos){
//        for (int i = 0; i < currentCat; i++) {
//            if(duplicatedCoords(pos,catsRoute.get(CatName.values()[i])))
//                return true;
//        }
        return false;
    }
    private boolean duplicatedCoords(Coordinates coord, List<Coordinates> route){
        for (Coordinates coords: route) {
            if(coords.getX()==coord.getX() && coords.getY()==coord.getY())
                return true;
        }
        return false;
    }

    private void setHauntTime(){
        hauntTime.put(PreyType.FIELD_MOUSE,3.0*60.0);
        hauntTime.put(PreyType.HOUSE_MOUSE,2.0*60.0);
        hauntTime.put(PreyType.SNAIL,1.5*60.0);
        hauntTime.put(PreyType.LEAF,1*60.0);
        hauntTime.put(PreyType.ROCK,0.5*60.0);
    }
}
