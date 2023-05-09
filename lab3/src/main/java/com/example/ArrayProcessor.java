package com.example;

public class ArrayProcessor {

    private MergeSort mergeSort;

    public ArrayProcessor(MergeSort mergeSort) {
        this.mergeSort = mergeSort;
    }

    public int[] processArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int[] result = arr.clone();
        mergeSort.mergeSort(result,0);
        return result;
    }
}
