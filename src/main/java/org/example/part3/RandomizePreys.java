package org.example.part3;

import lombok.Getter;
import org.example.TypeClasses.Coordinates;
import org.example.TypeClasses.Measurements;
import org.example.TypeClasses.PreyType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizePreys {
    private final Random random = new Random();
    Coordinates pos;

    HashMap<Coordinates, PreyType> preyCoords;

    private final int[] preyAmount = {
            150,  /**  FIELD_MOUSE  */
            80,   /**  HOUSE_MOUSE  */
            90,   /**  SNAIL        */
            300,  /**  LEAF         */
            200   /**  ROCK         */
    };
    public HashMap<Coordinates, PreyType> randomPreys(Coordinates mapSize){
        preyCoords = new HashMap<>();
        for (int i = 0; i < preyAmount.length; i++) {
            for (int j = 0; j < preyAmount[i]; j++) {
                do {
                    pos = new Coordinates(random.nextInt(mapSize.getX()) + 1, random.nextInt(mapSize.getY()) + 1);
                } while (duplicatedCoords(pos) || (pos.getX()==2500&&pos.getY()==2500));
                preyCoords.put(pos,PreyType.values()[i]);
            }
        }
        return preyCoords;
    }
    private boolean duplicatedCoords(Coordinates coords){
        for (Map.Entry<Coordinates, PreyType> entry : preyCoords.entrySet()) {
            if(entry.getKey().getX()==coords.getX() && entry.getKey().getY()==coords.getY())
                return true;
        }
        return false;
    }
}
