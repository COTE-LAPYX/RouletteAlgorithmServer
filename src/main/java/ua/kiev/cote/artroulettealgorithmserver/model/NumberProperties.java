package ua.kiev.cote.artroulettealgorithmserver.model;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class NumberProperties {
    private int number;
    private String color;
    private boolean isEven;

    public NumberProperties(int number) {
        this.number = number;
        this.isEven = (number % 2 == 0);
        this.color = isEven ? "Красное" : "Чёрное";
    }
}