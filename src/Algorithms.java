import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

interface Sorting{void sort(int[] array);}


public class Algorithms {
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    public static void quickSort(int[] array){ quicksort(array, 0, array.length - 1);}
    public static void quicksort(int[] array, int low, int high) {
        int stackSize = high - low + 1;
        int[] stack = new int[stackSize];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];
            int pivot = partition(array, low, high);
            if (pivot - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivot - 1;
            }
            if (pivot + 1 < high) {
                stack[++top] = pivot + 1;
                stack[++top] = high;
            }
        }
    }
    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
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

    public static void bucketSort(int[] A) {
        int n = A.length;
        int numberOfBuckets = (int) Math.ceil(Math.sqrt(n));
        List<Integer>[] buckets = new List[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<Integer>();
        }
        int max = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        for (int i = 0; i < n; i++) {
            int index = hash(A[i], max, numberOfBuckets);
            buckets[index].add(A[i]);
        }
        for (int i = 0; i < numberOfBuckets; i++) {
            Collections.sort(buckets[i]);
        }
        int index = 0;
        for (int i = 0; i < numberOfBuckets; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                A[index++] = buckets[i].get(j);
            }
        }
    }
    public static int hash(int i, int max, int numberOfBuckets) {
        return (int) Math.floor((double) i / max * (numberOfBuckets - 1));
    }

    public static int linearSearch(int[] A, int x) {
        int size = A.length;
        for (int i = 0; i < size - 1; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] A, int x) {
        int low = 0;
        int high = A.length - 1;
        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (A[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (A[low] == x) {
            return low;
        } else if (A[high] == x) {
            return high;
        } else {
            return -1;
        }
    }

    public static double[][] sort(int[] numbers, Sorting sorting, String sort_name) {
        int index = -1;
        double[][] result = new double[3][10];
        for (int i = 500; i < 260000; i = i * 2) {
            if (i > 250000) {i = 250000;}
            int random = 0;
            int sorted = 0;
            int reverse = 0;
            index++;
            int[] array = new int[i];
            int[] reverse_array = new int[i];

            for (int j = 0; j < 10; j++) {
                System.arraycopy(numbers, 0, array, 0, i);
                long start = System.currentTimeMillis();
                sorting.sort(array);
                long end = System.currentTimeMillis();
                random += end - start;
            }

            random = random / 10;
            System.out.println(sort_name + "\tInput Size: " + i + "\tData: Random\tTime: " + random + " ms.");

            for (int k = 0; k < 10; k++) {
                long start = System.currentTimeMillis();
                sorting.sort(array);
                long end = System.currentTimeMillis();
                sorted += end - start;
            }

            sorted = sorted / 10;
            System.out.println(sort_name + "\tInput Size: " + i + "\tData: Sorted\tTime: " + sorted + " ms.");

            int middle = i / 2;
            for (int l = 0; l < middle; l++) {
                int temp = array[l];
                array[l] = array[i-l-1];
                array[i-l-1] = temp;
            }

            for (int m = 0; m < 10; m++) {
                System.arraycopy(array, 0, reverse_array, 0, i);
                long start = System.currentTimeMillis();
                sorting.sort(reverse_array);
                long end = System.currentTimeMillis();
                reverse += end - start;
            }

            reverse = reverse / 10;
            System.out.println(sort_name + "\tInput Size: " + i + "\tData: Reversed\tTime: " + reverse + " ms.");

            result[0][index] = random;
            result[1][index] = sorted;
            result[2][index] = reverse;
        }
        System.out.println("---------------------------------------------------------");
        return result;
    }

    public static double[][] linear_search(int[] numbers, String search_name) {
        int index = -1;
        double[][] result = new double[2][10];
        for (int i = 500; i < 260000; i = i * 2) {
            if (i > 250000) {i = 250000;}
            int random = 0;
            int sorted = 0;
            index++;
            int[] array = new int[i];
            int x = numbers[new Random().nextInt(i)];

            for (int j = 0; j < 1000; j++) {
                System.arraycopy(numbers, 0, array, 0, i);
                long start = System.nanoTime();
                linearSearch(array, x);
                long end = System.nanoTime();
                random += end - start;
            }

            random = random / 1000;
            System.out.println(search_name + "\tInput Size: " + i + "\tData: Random\tTime: " + random + " ns.");

            for (int k = 0; k < 10; k++) {
                long start = System.nanoTime();
                linearSearch(array, x);
                long end = System.nanoTime();
                sorted += end - start;
            }

            sorted = sorted / 1000;
            System.out.println(search_name + "\tInput Size: " + i + "\tData: Sorted\tTime: " + sorted + " ns.");

            result[0][index] = random;
            result[1][index] = sorted;
        }
        System.out.println("---------------------------------------------------------");
        return result;
    }

    public static double[][] binary_search(int[] numbers, String search_name) {
        int index = -1;
        double[][] result = new double[1][10];

        for (int i = 500; i < 260000; i = i * 2) {
            if (i > 250000) {i = 250000;}
            int sorted = 0;
            index++;
            int[] array = new int[i];
            int x = numbers[new Random().nextInt(i)];

            for (int k = 0; k < 1000; k++) {
                long start = System.nanoTime();
                binarySearch(array, x);
                long end = System.nanoTime();
                sorted += end - start;
            }

            sorted = sorted / 1000;
            System.out.println(search_name + "\tInput Size: " + i + "\tData: Sorted\tTime: " + sorted + " ns.");;

            result[0][index] = sorted;

        } System.out.println("---------------------------------------------------------");
        return result;
    }

}
