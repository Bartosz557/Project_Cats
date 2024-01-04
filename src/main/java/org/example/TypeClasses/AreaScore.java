package org.example.TypeClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class AreaScore {
    private int mouse, snail, mulch;
    @Override
    public String toString() {
        return String.format("Mouse: %-5dSnail: %-5dLeaves and rocks: %-5d", mouse, snail, mulch);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AreaScore that = (AreaScore) obj;
        return mouse == that.mouse && snail == that.snail && mulch == that.mulch;
    }
    @Override
    public int hashCode() {
        return Objects.hash(mouse, snail, mulch);
    }

}
