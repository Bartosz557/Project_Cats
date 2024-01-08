package org.example;

import org.example.RouteCalculator_Part1_Part2.CatGPS;

import org.example.HauntRouteOptimizer_Part4.CatsHauntingRoute;
import org.example.StorageLoader_Part3.CatHauntScore;
import org.example.HuntAreaOrganizer_Part5.AssignAreasForCats;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){
            System.out.println("Hello! Choose the mode");
            System.out.println("1. Cat GPS Tracker\n2. Cat's haunt score\n3. Cat's haunting route optimizer\n4. Cat's haunting area calculator");
            Scanner scanner = new Scanner(System.in);
            String mode = scanner.nextLine();
            switch (mode) {
                case "1":
                    CatGPS catGPS = new CatGPS();
                    catGPS.generateCatsRoute();
                    break;
                case "2":
                    CatHauntScore catHauntScore = new CatHauntScore();
                    catHauntScore.optimizeHaunting();
                    System.out.println("\nThe best prey for allocation the free space is always the rock\nbecause even for wage 0.0 the (amount/2 = 250) is bigger than anything else\nxd\n");
                    break;
                case "3":
                    CatsHauntingRoute catsHauntingRoute = new CatsHauntingRoute();
                    catsHauntingRoute.catHauntingRoute();
                    break;
                case "4":
                    AssignAreasForCats assignAreasForCats = new AssignAreasForCats();
                    assignAreasForCats.divideMap();
                    break;
                default:
                    System.out.println("Wrong number, write 1,2, 3 or 4");
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
        }
    }
}