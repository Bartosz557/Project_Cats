package org.example;
import lombok.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
public class CatsRoute {

    Random random = new Random();

    public CatsRoute(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
        setStartPoint();
        setEndPoint();
    }

    private HashMap< Integer, Coordinates> routeSteps;
    private List<Coordinates> coordinates;
    private Coordinates startPoint;
    private Coordinates endPoint;

    private void setStartPoint() {
        this.startPoint = coordinates.get(random.nextInt(coordinates.size()-1) + 1);
    }

    private void setEndPoint() {
        while (true){
            this.endPoint =  coordinates.get(random.nextInt(coordinates.size()-1) + 1);
            if(this.endPoint != this.startPoint)
                break;
        }
    }

}
