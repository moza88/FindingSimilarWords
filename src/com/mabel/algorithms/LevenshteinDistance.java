package com.mabel.algorithms;

/*
In the Levenshtein algorithm it calculates the least amount of edits needed to get to the compared word
There are 3 operations in this algorithm:
- Delete       D(i - 1, j) + 1
- Insert       D(i, j - 1) + 1
- Substitution D(i - 1, j - 1) + 2

Levenshtein is an example of dynamic programming*
Dynamic Programming: Solving problems by combining solutions of sub-problem

Below is a visual representation of the algorithm. One word is at the top of the matrix and other on the side,
and we progress through it left to right and up to down.

Example:
There is a 0 when for C at the top and C at the side because they are the same characters
There is a 1 when for CH at the top and C at the side because there would have to be one insert to the side, H
There is a 2 when for CHAT at the top and CA at the side because there would have to 2 inserts
There is a 3 when for CH at the top and CAT at the side because there would have to be 2 deletes and one insert

    C H A T
  0 1 2 3 4
C 1 0 1 2 3
A 2 1 2 1 2
T 3 2 3 2 1
          -
1 is the total distance to get from the word Cat to Chat
 */
public class LevenshteinDistance {
    public int LevenshteinDistance(String word1, String word2){
        int distance = 0;
        int wordLength1 = word1.length();
        int wordLength2 = word2.length();

        int[][] wordCompareMatrix = new int[wordLength1 + 1][wordLength2 + 1];

        //Filling in the top row, this is before the comparisons
        for(int i=0; i < wordLength1; i++){
            wordCompareMatrix[i][0] = i;
        }

        //Filling in the side column, this is before the comparisons
        for(int j=0; j <= wordLength2; j++){
            wordCompareMatrix[0][j] = j;
        }

        for(int i=0; i < wordLength1; i++){
            //Turning the word into a character list
            char[] wordCharacterList1 = word1.toCharArray();

            for(int j=0; j < wordLength2; j++){
                //Turning the word into a character list
                char[] wordCharacterList2 = word2.toCharArray();

                //Checking if the characters are equal to each other
                if(wordCharacterList1 == wordCharacterList2){
                    wordCompareMatrix[i + 1][j + 1] = wordCompareMatrix[i][j];
                }else{
                    int substitute = wordCompareMatrix[i][j] + 1;
                    int insert = wordCompareMatrix[i][j + 1] + 1;
                    int delete = wordCompareMatrix[i + 1][j] + 1;

                    int minimum = substitute>insert ? insert : substitute;
                    minimum = delete > minimum ? minimum : delete;
                    wordCompareMatrix[i + 1][j + 1] = minimum;
                }
            }
        }
        return distance;
    }
}
