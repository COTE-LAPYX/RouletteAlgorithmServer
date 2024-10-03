package ua.kiev.cote.artroulettealgorithmserver.service;

import org.springframework.stereotype.Service;
import ua.kiev.cote.artroulettealgorithmserver.model.NumberProperties;

import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@Service
public class NumberPropertiesService {
    private final Random random = new Random();

    private int[] generateArray(int min, int max, int length) {
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        return array;
    }

    public Map<NumberProperties, Integer> countOccurrences(int min, int max, int length) {
        int[] array = generateArray(min, max, length);

        Map<NumberProperties, Integer> countMap = new TreeMap<>(Comparator.comparingInt(NumberProperties::getNumber));

        for (int num : array) {
            NumberProperties prop = new NumberProperties(num);
            countMap.put(prop, countMap.getOrDefault(prop, 0) + 1);
        }

        return countMap;
    }
}
