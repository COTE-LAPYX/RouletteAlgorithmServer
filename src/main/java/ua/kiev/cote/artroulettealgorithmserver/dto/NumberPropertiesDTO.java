package ua.kiev.cote.artroulettealgorithmserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class NumberPropertiesDTO {
    private int number;
    private String color;
    private boolean isEven;
}
