package com.hackthon.test.velmurugan;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

class Result {

    private static final String NO = "NO";
    private static final String YES = "YES";


    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        // Step 1: Count the frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Count the frequency of these frequencies
        Map<Integer, Integer> freqCountMap = new HashMap<>();
        for (int freq : frequencyMap.values()) {
            freqCountMap.put(freq, freqCountMap.getOrDefault(freq, 0) + 1);
        }

        // Step 3: Analyze the frequency of frequencies
        if (freqCountMap.size() == 1) {
            // All characters have the same frequency
            return "YES";
        } else if (freqCountMap.size() == 2) {
            // There are two different frequencies
            int freq1 = (int) freqCountMap.keySet().toArray()[0];
            int freq2 = (int) freqCountMap.keySet().toArray()[1];

            // Get the count of each frequency
            int count1 = freqCountMap.get(freq1);
            int count2 = freqCountMap.get(freq2);

            // Case 1: One frequency occurs exactly once and is either 1 or can be reduced to the other frequency
            if ((count1 == 1 && (freq1 == 1 || freq1 - 1 == freq2)) ||
                    (count2 == 1 && (freq2 == 1 || freq2 - 1 == freq1))) {
                return "YES";
            }
        }

        // In all other cases, the string is not valid
        return "NO";

    }

}

public class Solution {
    private static final String OUTPUT_PATH="F:/Hackthon/StringValidation.txt";
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_PATH));

        String s = bufferedReader.readLine();
        System.out.println("s --> "+s);

        String result = Result.isValid(s);

        System.out.println("result --> "+result);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
