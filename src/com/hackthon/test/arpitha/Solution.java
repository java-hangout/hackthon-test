package com.hackthon.test.arpitha;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
    // Write your code here
        // Step 1: Remove duplicates from the ranked list
        List<Integer> uniqueRanks = new ArrayList<>();
        uniqueRanks.add(ranked.get(0));
        for (int i = 1; i < ranked.size(); i++) {
            if (!ranked.get(i).equals(ranked.get(i - 1))) {
                uniqueRanks.add(ranked.get(i));
            }
        }

        // Step 2: Determine the rank for each player score
        List<Integer> result = new ArrayList<>();
        int n = uniqueRanks.size();
        int index = n - 1;

        for (int score : player) {
            // Move the index to the right position
            while (index >= 0 && score >= uniqueRanks.get(index)) {
                index--;
            }
            result.add(index + 2); // +2 because index+1 gives the rank for the next position
        }

        return result;
    }

}

public class Solution {
    private static final String OUTPUT_PATH="F:/Hackthon/ClimbingLeaderboard.txt";
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_PATH));
        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());
        System.out.println("rankedCount ---->> "+rankedCount);
        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());
        System.out.println("ranked ---->> "+ranked);

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());
        System.out.println("playerCount ---->> "+playerCount);

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());
        System.out.println("player ---->> "+player);
        List<Integer> result = Result.climbingLeaderboard(ranked, player);
        System.out.println("result ---->> "+result);
        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}