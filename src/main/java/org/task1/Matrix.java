package org.task1;

public class Matrix {

    private int[][] data;
    private int rows;
    private int cols;

    public Matrix(int[][] data) { //CONSTRUCTOR
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }


    public Matrix matrixSum(Matrix other) {
        int[][] matrixResult = new int[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j <this.cols; j++) {

                matrixResult[i][j] = this.data[i][j] + other.data[i][j];

            }
        }

        return new Matrix(matrixResult);
    }

    public int [][] getData(){
        return data;
    }

    public Matrix scalarMultiplication(int scalar){
        int[][] matrixResult = new int[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j <this.cols; j++){

                matrixResult[i][j] = this.data[i][j] * scalar;
            }
        }
        return new Matrix(matrixResult);
    }

    public Matrix transposeMatrix(){
        int[][] matrixResult = new int[this.cols][this.rows];

        for (int i = 0; i <this.rows; i++){
            for (int j = 0; j < this.cols; j++){

                matrixResult[j][i] = this.data[i][j];
            }
        }
        return new Matrix(matrixResult);

    }

    public Matrix diagonalMatrix(){
        int[][] matrixResult = new int[this.rows][this.cols];

        for (int i = 0; i <this.rows; i++){
            for (int j = 0; j <this.cols; j++){

                if (i!=j){
                    matrixResult[i][j] = 0;
                }
                else{
                    matrixResult[i][j] = this.data[i][j];
                }
            }
        }
        return new Matrix(matrixResult);
    }

    public Matrix lowerTriangle(){
        int[][] matrixResult = new int[this.rows][this.cols];

        for (int i = 0; i <this.rows; i++){
            for (int j = 0; j < this.cols; j++){

                if (i<j){
                    matrixResult[i][j] = 0;
                }
                else{
                    matrixResult[i][j] = this.data[i][j];
                }
            }
        }
        return new Matrix(matrixResult);
    }

    public Matrix upperTriangular(){
        int[][] matrixResult = new int[this.rows][this.cols];

        for (int i = 0; i <this.rows; i++){
            for (int j = 0; j < this.cols; j++){

                if (i>j){
                    matrixResult[i][j] = 0;
                }
                else{
                    matrixResult[i][j] = this.data[i][j];
                }
            }
        }
        return new Matrix(matrixResult);
    }

    public Matrix matrixMultiplication(Matrix other){
        int[][] matrixResult = new int[this.rows][other.cols];
        for (int i = 0; i < this.rows ; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k <this.cols; k++) {

                    matrixResult[i][j] += this.data[i][k] * other.data[k][j];

                }

            }

        }
        return new Matrix(matrixResult);

    }

    public Matrix subMatrix(int removeRow, int removeCol) {
        int[][] newMatrix = new int[this.rows - 1][this.cols - 1];
        int newRow = 0;
        for (int i = 0; i < this.rows; i++) {
            if (i == removeRow) {
                continue;
            }
            int newCol = 0;
            for (int j = 0; j <this.cols; j++) {
                if (j == removeCol) {
                    continue;
                }
                newMatrix[newRow][newCol] = this.data[i][j];
                newCol++;
            }
            newRow++;
        }
        return new Matrix(newMatrix);
    }

    public int determinant() {
        return determinant(this.data);
    }

    private int determinant(int[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Determinant only works for square matrices.");
        }

        if (matrix.length == 1) {
            return matrix[0][0];
        }

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int determinant = 0;

        for (int col = 0; col < matrix[0].length; col++) {
            int sign;

            if (col % 2 == 0) {
                sign = 1;
            } else {
                sign = -1;
            }

            int[][] subMatrix = createSubMatrix(matrix, 0, col);

            determinant += sign * matrix[0][col] * determinant(subMatrix);
        }

        return determinant;
    }

    private int[][] createSubMatrix(int[][] matrix, int removeRow, int removeCol) {
        int[][] newMatrix = new int[matrix.length - 1][matrix[0].length - 1];

        int newRow = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (i == removeRow) {
                continue;
            }

            int newCol = 0;

            for (int j = 0; j < matrix[0].length; j++) {
                if (j == removeCol) {
                    continue;
                }

                newMatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }

            newRow++;
        }

        return newMatrix;
    }
}