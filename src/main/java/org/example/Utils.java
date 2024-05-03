package org.example;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static int[] findIndex(double[] array, double target) {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                indexes.add(i);
            }
        }

        int[] result = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++) {
            result[i] = indexes.get(i);
        }

        return result;
    }

}
