package algorithms.DivideAndConquer;

import interfaces.Algorithm;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort implements Algorithm {
    private int[] array;

    public QuickSort(int[] array) {
        this.array = array;
    }

    @Override
    public void execute() {
        if (array.length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    @Override
    public void displayResult() {
        if (array.length > 0) {
            System.out.println("Sorted array: " + Arrays.toString(array));
        } else {
            System.out.println("Array is empty.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read the number of elements in the array
        System.out.println("Enter the number of elements:");
        int n = scanner.nextInt();
        int[] array = new int[n];

        // Step 2: Read the elements of the array
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // Step 3: Create an instance of QuickSort and execute the sorting algorithm
        QuickSort quickSort = new QuickSort(array);
        quickSort.execute();

        // Step 4: Display the sorted array
        quickSort.displayResult();

        scanner.close();
    }
}
