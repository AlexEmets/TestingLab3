package com.example;

public class ArrayMerger implements Merger {

    @Override
    public void merge(
            int[] a, int[] l, int[] r, int left, int right) {
        if (a == null) {
            throw new RuntimeException("Input array cannot be null.");
        }

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
