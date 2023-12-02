package org.example;
import lombok.*;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
public class CatsRoute {

    public CatsRoute(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    private HashMap< Integer, Coordinates> routeSteps;
    private List<Coordinates> coordinates;
    private int startPoint;
    private int endPoint;

}
