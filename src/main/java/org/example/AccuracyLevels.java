package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccuracyLevels {
    /**  genSize: 5%, 10% ,15%, 20%, 25% */
    private double genSize;
    /** successRatio: 60%, 50%, 40%, 30%, 20% */
    private double successRatio;
}
