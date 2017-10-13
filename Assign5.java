package com.assign5;

import java.util.ArrayList;
import java.util.List;

public class Assign5 {

    //Extra: spiral matrix
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            //Traverse Right
            for (int i = colBegin; i <= colEnd; i++) { //will miss some value if use < replaces of <=
                result.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            //Traverse Down
            for (int i = rowBegin; i <= rowEnd; i++) {
                result.add(matrix[i][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                //Traverse Left
                for (int i = colEnd; i >= colBegin; i--) {
                    result.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                //Traverse Up
                for (int i = rowEnd; i >= rowBegin; i--) {
                    result.add(matrix[i][colBegin]);
                }
            }
            colBegin++;
        }
        return result;

    }

    public static void main(String[] args) {
        Assign5 a = new Assign5();
        int[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        System.out.println(a.spiralOrder(matrix));
    }
}
