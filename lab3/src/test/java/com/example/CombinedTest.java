package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CombinedTest {

    @Mock
    private Merger merger;

    @InjectMocks
    private MergeSort mergeSort;

    @Mock
    private MergeSort mergeSortMock;

    private ArrayProcessor arrayProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        arrayProcessor = new ArrayProcessor(mergeSortMock);
    }


    // Test 2: Scenario with no merging
    @Test
    public void testNoMerging() {
        int[] arr = {1, 2, 3};
        System.out.println("Before sorting:");
        printArr(arr);  
        mergeSort.mergeSort(arr,3);
        System.out.println("Sorted:");
        printArr(arr);
        verify(merger, never()).merge(eq(arr), eq(arr), eq(arr), anyInt(), anyInt());
    }

    // Test 3: Scenario with exception handling                             //// (5) один сценарій за якого мок об’єкт має 
    @Test(expected = IllegalArgumentException.class)                        //       згенерувати виключення.
    public void testNullArrayThrowsException() {
        mergeSort.mergeSort(null,0);
    }

    // Test 4: Scenario with partial mock object                            /// (4)
    @Test
    public void testPartialMockObject() {
        Merger realMerger = new ArrayMerger();
        Merger spyMerger = spy(realMerger);

        MergeSort mergeSortWithSpy = new MergeSort(spyMerger);

        int[] arr = {3, 2, 1};
        System.out.println("Before sorting:");
        printArr(arr);
        mergeSortWithSpy.mergeSort(arr,3);
        System.out.println("Sorted:");
        printArr(arr);


        verify(spyMerger, times(1)).merge(eq(new int[]{1,2}),eq(new int[]{2}), eq(new int[]{1}), anyInt(), anyInt());
    }
//
    // Test 5: Scenario where mock object throws an exception
    @Test(expected = RuntimeException.class)
    public void testSwapperThrowsException() {
        int[] arr = null;
        doThrow(RuntimeException.class).when(merger).merge(eq(null), eq(null), eq(null), anyInt(), anyInt());

        mergeSort.mergeSort(arr,3);
    }

    private static void printArr(int[] arr)
    {
        if (arr.length != 0) {
            for (int j : arr) System.out.print(j + " ");
        System.out.println();
        }
        else {
        System.out.print("[ ]");
        System.out.println();
        }
    }
}
