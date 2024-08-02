package algorithms.DivideAndConquer;

import interfaces.Algorithm;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSort implements Algorithm {
    private int[] array;

    public MergeSort(int[] array) {
        this.array = array;
    }

    @Override
    public void execute() {
        if (array.length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
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

        // Step 3: Create an instance of MergeSort and execute the sorting algorithm
        MergeSort mergeSort = new MergeSort(array);
        mergeSort.execute();

        // Step 4: Display the sorted array
        mergeSort.displayResult();

        scanner.close();
    }
}
