package algorithms.DivideAndConquer;

import interfaces.Algorithm;

import java.util.Scanner;

public class StrassensMatrixMultiplication implements Algorithm {
    private int[][] A;
    private int[][] B;
    private int[][] result;

    // Default constructor with an empty or default matrix
    public StrassensMatrixMultiplication() {
        this.A = new int[0][0]; // Initialize with an empty matrix
        this.B = new int[0][0]; // Initialize with an empty matrix
        this.result = new int[0][0]; // Initialize with an empty matrix
    }

    public StrassensMatrixMultiplication(int[][] A, int[][] B) {
        this.A = A;
        this.B = B;
        this.result = new int[A.length][B[0].length];
    }

    @Override
    public void execute() {
        if (A.length == 0 || B.length == 0 || A[0].length == 0 || B[0].length == 0) {
            System.out.println("Matrix A or B is empty or invalid.");
            return;
        }
        if (A[0].length != B.length) {
            System.out.println("Matrix dimensions are invalid for multiplication.");
            return;
        }
        result = strassenMultiply(A, B);
    }

    private int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] R = new int[n][n];

        if (n == 1) {
            R[0][0] = A[0][0] * B[0][0];
        } else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);

            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

            int[][] M1 = strassenMultiply(add(A11, A22), add(B11, B22));
            int[][] M2 = strassenMultiply(add(A21, A22), B11);
            int[][] M3 = strassenMultiply(A11, subtract(B12, B22));
            int[][] M4 = strassenMultiply(A22, subtract(B21, B11));
            int[][] M5 = strassenMultiply(add(A11, A12), B22);
            int[][] M6 = strassenMultiply(subtract(A21, A11), add(B11, B12));
            int[][] M7 = strassenMultiply(subtract(A12, A22), add(B21, B22));

            int[][] C11 = add(subtract(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(subtract(add(M1, M3), M2), M6);

            join(C11, R, 0, 0);
            join(C12, R, 0, n / 2);
            join(C21, R, n / 2, 0);
            join(C22, R, n / 2, n / 2);
        }
        return R;
    }

    private void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i = 0, i2 = iB; i < C.length; i++, i2++) {
            for (int j = 0, j2 = jB; j < C.length; j++, j2++) {
                C[i][j] = P[i2][j2];
            }
        }
    }

    private void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i = 0, i2 = iB; i < C.length; i++, i2++) {
            for (int j = 0, j2 = jB; j < C.length; j++, j2++) {
                P[i2][j2] = C[i][j];
            }
        }
    }

    private int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    @Override
    public void displayResult() {
        System.out.println("Resultant matrix:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void strassenInteractive(Scanner scanner) {
        // Step 1: Read dimensions and elements of matrix A
        System.out.println("Enter dimensions of matrix A (m x n):");
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] A = new int[m][n];

        System.out.println("Enter elements of matrix A:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        // Step 2: Read dimensions and elements of matrix B
        System.out.println("Enter dimensions of matrix B (n x p):");
        n = scanner.nextInt(); // Reuse 'n' from matrix A's column count
        int p = scanner.nextInt();
        int[][] B = new int[n][p];

        System.out.println("Enter elements of matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        // Step 3: Perform matrix multiplication using Strassen's algorithm
        StrassensMatrixMultiplication strassen = new StrassensMatrixMultiplication(A, B);
        strassen.execute();

        // Step 4: Display the resultant matrix
        strassen.displayResult();
    }
}
