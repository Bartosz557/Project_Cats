package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatGPS {
    int amount;
    boolean wrongInput=true;
    List<Coordinates> coordinates = new ArrayList<>();
    public void generateCatsRoute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide the amount of coordinates:\n");
        while(true) {
            try {
                amount = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input, provide a number less than 5000");
            }
        }
            System.out.println("Do you want to provide coordinates? Otherwise they will be random generated. Y/N\n");
        String input = scanner.nextLine().toLowerCase();
        while(wrongInput){
            switch (input) {
                case "y":
                    for (int i = 0; i < amount; i++) {
                        int x,y;
                        while(true) {
                            try {
                                System.out.println("Provide coordinates:\n");
                                System.out.println("X:\n");
                                x = Integer.parseInt(scanner.nextLine());
                                System.out.println("Y:\n");
                                y = Integer.parseInt(scanner.nextLine());
                                if(x > 5000 || y > 5000 || x < 1 || y < 1)
                                    throw new NumberFormatException();
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Coordinates must be a number between 1 and 5000.");
                            }
                        }
                        Coordinates coordinate = new Coordinates(x,y);
                        coordinates.add(coordinate);
                    }
                    wrongInput = false;
                    break;
                case "n":
                    GetRandomCoordinates getRandomCoordinates = new GetRandomCoordinates();
                    coordinates = getRandomCoordinates.getPoints(amount);
                    wrongInput = false;
                    break;
                default:
                    System.out.println("Wrong input, write Y or N");
            }
        }
        CatsRoute catsRoute = new CatsRoute(coordinates);
        for (Coordinates x : coordinates) {
            System.out.println(x);

        }

    }


}
