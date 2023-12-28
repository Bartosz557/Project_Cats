package org.example.TypeClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AreaScore {
    private int mouse, snail, leaf, rock;

    @Override
    public String toString() {
        return String.format("Mouse: %-5dSnail: %-5dLeaf: %-5dRock: %-5d", mouse, snail, leaf, rock);
    }


}
