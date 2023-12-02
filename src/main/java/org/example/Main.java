package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){
            System.out.println("Hello! Choose the mode");
            System.out.println("1. Cat GPS Tracker\n2. Cat's haunt score\n3. Cat's haunting route optimizer\n");
            Scanner scanner = new Scanner(System.in);
            String mode = scanner.nextLine();
            switch (mode) {
                case "1":
                    CatGPS catGPS = new CatGPS();
                    catGPS.generateCatsRoute();
                    break;
                case "2":
                    CatHauntScore catHauntScore = new CatHauntScore();
                    break;
                case "3":
                    CatHauntOptimizer catHauntOptimizer = new CatHauntOptimizer();
                    break;
                default:
                    System.out.println("Wrong number, write 1,2 or 3");
            }
        }
    }
}