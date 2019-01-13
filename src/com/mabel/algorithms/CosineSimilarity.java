package com.mabel.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
 * The idea behind cosine similarity is about converting the words into numeric values
 * that will be stored in vectors. Once we have them in vectors we will use Cosine calculations to find
 * the percentage of how similar they are.
 *
 * This algorithm can be broken down to 2 sections:
 *  1. Find Term Frequency (Mark if a term is used in a word (letter) is used in a phrase (word)
 *  2. Use the values from the Term Frequency to calculate the Cosine Similarity, below is the formula used
 *      a . b / (||a||*||b||)
 *
 * Refresher: Cosine comes from our Trig days, remember Sohcahtoa
 * we would use cosine to find the ratio of adjacent sides of a triangle.
 * In this situation think of the words as adjacent sides of a triangle and the percentage difference as the ratio
 */
public class CosineSimilarity {

    //Create a map with unique values and it's frequency, this will be later used for Term Frequency Calculation
    public Map<String, Integer> getWordFrequency(String[] phrase) {
        //Combine the two strings together and find the unique terms

        //This hashmap contains the word and an integer specifying if the word is present
        // A: AT, B: CAT
        // Word | A | B |
        // C    | 0 | 1 |
        // A    | 1 | 1 |
        // T    | 1 | 1 |

        Map<String, Integer> wordFreqMap = new HashMap<String, Integer>();
        for (String word : phrase) {
            Integer count = wordFreqMap.get(phrase);
            count = (count == null) ? 1 : ++count;
            wordFreqMap.put(word, count);
        }
        return wordFreqMap;
    }

    public double cosineSimilarityPhrases(String phraseA, String phraseB) {
        double percentSimilar = 0;

        //Convert the Strings into arrays
        String[] phraseA_array = phraseA.split("//W+");
        String[] phraseB_array = phraseB.split("//W+");

        //Convert the arrays into Maps
        Map<String, Integer> phraseA_map = getWordFrequency(phraseA_array);
        Map<String, Integer> phraseB_map = getWordFrequency(phraseB_array);

        //Use Hashset datastructure to get unique values
        HashSet<String> uniqueWord = new HashSet<>(phraseA_map.keySet());
        uniqueWord.retainAll(phraseB_map.keySet());

        //Initialize values for the cosine calculations
        double dotProd = 0;
        double magA = 0;
        double magB = 0;

        //Calculate dot product A . B
        //A . B = (a1b1) + (a2b2) + (a3b3) + ...
        for (String item : uniqueWord) {
            dotProd += phraseA_map.get(item) * phraseB_map.get(item);
        }

        //Calculate Magnitude A
        // || A || = SquareRoot(a1^2 + a2^2 + a3^2 + ...)
        for (String k : phraseA_map.keySet()) {
            magA += Math.pow(phraseA_map.get(k), 2);
        }

        //Calculate Magnitude B
        for (String k : phraseB_map.keySet()) {
            magB += Math.pow(phraseB_map.get(k), 2);
        }

        //Calculate Cosine Similarity
        percentSimilar = dotProd / Math.sqrt(magA * magB);

        return percentSimilar;
    }
}
