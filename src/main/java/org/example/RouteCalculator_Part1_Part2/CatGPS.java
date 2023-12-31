package org.example.RouteCalculator_Part1_Part2;

import org.example.TypeModels.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatGPS {
    private boolean wrongInput=true;
    private List<Coordinates> coordinates = new ArrayList<>();
    public void generateCatsRoute() {
        int amount;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide the amount of coordinates:\n");
        while(true) {
            try {
                amount = Integer.parseInt(scanner.nextLine());
                if(amount<3)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input, provide a number more than 2 and less than 5000");
            }
        }
            System.out.println("Do you want to autogenerate the coordinates?. Y/N\n");
        String input = scanner.nextLine().toLowerCase();
        while(wrongInput){
            switch (input) {
                case "n":
                    for (int i = 0; i < amount; i++) {
                        int x,y;
                        while(true) {
                            try {
                                System.out.println("\nProvide coordinates:\n");
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
                case "y":
                    GetRandomCoordinates getRandomCoordinates = new GetRandomCoordinates();
                    coordinates = getRandomCoordinates.getPoints(amount);
                    wrongInput = false;
                    break;
                default:
                    System.out.println("Wrong input, write Y or N");
            }
        }
        CatsRoute catsRoute = new CatsRoute(coordinates);
        catsRoute.getRoute();
    }
}
