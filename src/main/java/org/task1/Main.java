package org.task1;

public class Main {
    public static void main(String[] args) {
        int[][] A = {{1, 3, 1}, {1, 0, 0},};

        int[][] B = {{0, 0, 5}, {7, 5, 0}};

        int[][] C = {{1, 3, 1}, {1, 0, 0}};

        int[][] D = {{0, 0}, {7, 5}, {5, 0}};

        Matrix matrix1 = new Matrix(A);
        Matrix matrix2 = new Matrix(B);
        Matrix matrix3 = new Matrix(C);
        Matrix matrix4 = new Matrix(D);


       Matrix result = matrix1.matrixSum(matrix2);

       int[][] resultData = result.getData();

        for (int i = 0; i < resultData.length; i++) {
            for (int j = 0; j < resultData[0].length; j++) {

                System.out.print(resultData[i][j] + " ");

            }

            System.out.println();
        }

        Matrix scalarResult = matrix1.scalarMultiplication(2);

        int[][] scalarData = scalarResult.getData();

        for (int i = 0; i < scalarData.length; i++) {
            for (int j = 0; j < scalarData[0].length; j++) {

                System.out.print(scalarData[i][j] + " ");

            }
            System.out.println();
        }


        Matrix transposeResult = matrix1.transposeMatrix();
        int [][] transposeData = transposeResult.getData();

        for (int i = 0; i < transposeData.length; i++) {
            for (int j = 0; j < transposeData[0].length; j++) {
                System.out.print(transposeData[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Diagonal Matrix:");

       Matrix diagonalResult = matrix1.diagonalMatrix();
        int[][] diagonalData = diagonalResult.getData();

        for (int i = 0; i < diagonalData.length; i++) {
            for (int j = 0; j < diagonalData[0].length; j++) {
                System.out.print(diagonalData[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Lower Triangular:");

        Matrix lowerResult = matrix1.lowerTriangle();
        int[][] lowerData = lowerResult.getData();


        for (int i = 0; i < lowerData.length; i++) {
            for (int j = 0; j < lowerData[0].length; j++) {
                System.out.print(lowerData[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Upper Triangular:");

        Matrix upperResult = matrix1.upperTriangular();
        int[][] upperData = upperResult.getData();


        for (int i = 0; i < upperData.length; i++) {
            for (int j = 0; j < upperData[0].length; j++) {
                System.out.print(upperData[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Matrix Multiplication:");

        Matrix multiplicationResult = matrix3.matrixMultiplication(matrix4);

        int[][] multiplicationData = multiplicationResult.getData();

        for (int i = 0; i < multiplicationData.length; i++) {
            for (int j = 0; j < multiplicationData[0].length; j++) {

                System.out.print(multiplicationData[i][j] + " ");

            }
            System.out.println();
        }

        System.out.println("Sub Matrix:");

        Matrix subResult = matrix1.subMatrix(0,0);
        int[][] subData = subResult.getData();
        for (int i = 0; i < subData.length; i++) {
            for (int j = 0; j < subData[0].length; j++) {

                System.out.print(subData[i][j] + " ");

            }
            System.out.println();
        }

        int [][] E = {
                {1,8,3},
                {9,5,6},
                {7,8,6},
        };

        Matrix determinantMatrix = new Matrix(E);

        System.out.println("Determinant:");

        int determinantResult = determinantMatrix.determinant();

        System.out.println(determinantResult);
    }
}

