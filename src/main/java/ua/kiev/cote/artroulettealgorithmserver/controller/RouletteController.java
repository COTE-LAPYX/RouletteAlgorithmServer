package ua.kiev.cote.artroulettealgorithmserver.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kiev.cote.artroulettealgorithmserver.dto.ErrorResponse;
import ua.kiev.cote.artroulettealgorithmserver.dto.NumberPropertiesDTO;
import ua.kiev.cote.artroulettealgorithmserver.dto.NumberPropertiesMapDTO;
import ua.kiev.cote.artroulettealgorithmserver.dto.NumberPropertiesResponse;
import ua.kiev.cote.artroulettealgorithmserver.model.NumberProperties;
import ua.kiev.cote.artroulettealgorithmserver.service.NumberPropertiesService;
import ua.kiev.cote.artroulettealgorithmserver.util.exception.NumberPropertiesException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

    private final ModelMapper modelMapper;
    private final NumberPropertiesService numberPropertiesService;

    public RouletteController(ModelMapper modelMapper, NumberPropertiesService numberPropertiesService) {
        this.modelMapper = modelMapper;
        this.numberPropertiesService = numberPropertiesService;
    }

    public NumberPropertiesMapDTO convertToNumberPropertiesMapDTO(Map<NumberProperties, Integer> numberPropertiesMap) {
        Map<Map<String, Object>, Integer> serializedMap = new TreeMap<>(Comparator.comparingInt(m -> (int) m.get("number")));

        numberPropertiesMap.forEach((key, value) -> {
            Map<String, Object> dtoFields = new HashMap<>();
            NumberPropertiesDTO dto = convertToNumberPropertiesDTO(key);

            dtoFields.put("number", dto.getNumber());
            dtoFields.put("color", dto.getColor());
            dtoFields.put("isEven", dto.isEven());

            serializedMap.put(dtoFields, value);
        });

        NumberPropertiesMapDTO numberPropertiesMapDTO = new NumberPropertiesMapDTO();
        numberPropertiesMapDTO.setMap(serializedMap);

        return numberPropertiesMapDTO;
    }

    public NumberPropertiesDTO convertToNumberPropertiesDTO(NumberProperties numberProperties) {
        return modelMapper.map(numberProperties, NumberPropertiesDTO.class);
    }

    @GetMapping("/spin")
    public NumberPropertiesResponse getSpinResults(@RequestParam() int min, @RequestParam() int max, @RequestParam() int length){
        return new NumberPropertiesResponse(convertToNumberPropertiesMapDTO(numberPropertiesService.countOccurrences(min, max, length)));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NumberPropertiesException numberPropertiesException){
        ErrorResponse response = new ErrorResponse(numberPropertiesException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}