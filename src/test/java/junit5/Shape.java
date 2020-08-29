package junit5;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Shape {
    private int numberOfSides;
    private String description = "Square";

    public Shape(int numberOfSides)  {
        if (numberOfSides < 3 || numberOfSides == Integer.MAX_VALUE)
            throw new IllegalArgumentException();
        this.numberOfSides = numberOfSides;
    }
}
