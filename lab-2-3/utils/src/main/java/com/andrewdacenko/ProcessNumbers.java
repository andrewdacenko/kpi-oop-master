package com.andrewdacenko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessNumbers {
    public List<Integer> processNumbers(String postNumbersString) {
        List<Integer> numbers = new ArrayList<Integer>();

        if (postNumbersString != null && !postNumbersString.isEmpty()) {
            postNumbersString = postNumbersString.replaceAll("[^0-9]+", ",");
            String[] postNumbers = postNumbersString.split(",");

            for (String number : postNumbers) {
                if (!number.isEmpty()) {
                    numbers.add(Integer.parseInt(number));
                }
            }

            System.out.println(numbers);

            Collections.sort(numbers);
        }

        return numbers;
    }
}
