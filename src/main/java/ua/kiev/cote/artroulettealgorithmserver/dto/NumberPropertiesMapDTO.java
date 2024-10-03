package ua.kiev.cote.artroulettealgorithmserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class NumberPropertiesMapDTO {
    private Map<Map<String, Object>, Integer> map;
}
