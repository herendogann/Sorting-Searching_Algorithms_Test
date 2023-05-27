import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String args[]) throws IOException {
        Scanner file = new Scanner(new File("TrafficFlowDataset.csv"));
        String line = file.nextLine();

        int[] numbers = new int[251281];
        int index = 0;
        while (file.hasNext()) {
            line = file.nextLine();
            numbers[index++] = Integer.parseInt(line.split(",")[6]);
        }

        Sorting selection = Algorithms::selectionSort;
        Sorting quick = Algorithms::quickSort;
        Sorting bucket = Algorithms::bucketSort;


        double[][] selection_result = Algorithms.sort(numbers, selection, "Selection sort");
        double[][] quick_result = Algorithms.sort(numbers, quick, "Quick sort");
        double[][] bucket_result = Algorithms.sort(numbers, bucket, "Bucket sort");
        double[][] linear_result = Algorithms.linear_search(numbers, "Linear search");
        double[][] binary_result = Algorithms.binary_search(numbers, "Binary search");

        int[] xAxis = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        double[][] yAxis_random = new double[3][10];
        double[][] yAxis_sorted = new double[3][10];
        double[][] yAxis_reversed = new double[3][10];
        double[][] yAxis_search = new double[3][10];
        double[][] yAxis_random_quickbucket = new double[2][10];
        double[][] yAxis_search_sorted = new double[2][10];

        yAxis_random[0] = selection_result[0];
        yAxis_random[1] = quick_result[0];
        yAxis_random[2] = bucket_result[0];

        yAxis_sorted[0] = selection_result[1];
        yAxis_sorted[1] = quick_result[1];
        yAxis_sorted[2] = bucket_result[1];

        yAxis_reversed[0] = selection_result[2];
        yAxis_reversed[1] = quick_result[2];
        yAxis_reversed[2] = bucket_result[2];

        yAxis_search[0] = linear_result[0];
        yAxis_search[1] = linear_result[1];
        yAxis_search[2] = binary_result[0];

        yAxis_random_quickbucket[0] = quick_result[0];
        yAxis_random_quickbucket[1] = bucket_result[0];

        yAxis_search_sorted[0] = linear_result[1];
        yAxis_search_sorted[1] = binary_result[0];

        sortingChart("Random Data Test", xAxis, yAxis_random);
        sortingChart("Sorted Data Test", xAxis, yAxis_sorted);
        sortingChart("Reversed Data Test", xAxis, yAxis_reversed);
        searchingChart("Searching Algorithms Test", xAxis, yAxis_search);

        sortingChart2("Random Data Test(Quick & Bucket Sort)", xAxis, yAxis_random_quickbucket);
        searchingChart2("Searching Algorithms Test (Sorted data)", xAxis, yAxis_search_sorted);

    }
    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static void sortingChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        XYChart chart = new XYChartBuilder().width(1200).height(900).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("Selection Sort", doubleX, yAxis[0]);
        chart.addSeries("Quick Sort", doubleX, yAxis[1]);
        chart.addSeries("Bucket Sort", doubleX, yAxis[2]);

        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart).displayChart();
    }
    public static void searchingChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        XYChart chart = new XYChartBuilder().width(1200).height(900).title(title)
                .yAxisTitle("Time in Nanoseconds").xAxisTitle("Input Size").build();

        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("Linear Search (random data)", doubleX, yAxis[0]);
        chart.addSeries("Linear Search (sorted data)", doubleX, yAxis[1]);
        chart.addSeries("Binary Search (sorted data)", doubleX, yAxis[2]);

        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart).displayChart();
    }

    public static void sortingChart2(String title, int[] xAxis, double[][] yAxis) throws IOException {
        XYChart chart = new XYChartBuilder().width(1200).height(900).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("Quick Sort", doubleX, yAxis[0]);
        chart.addSeries("Bucket Sort", doubleX, yAxis[1]);

        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart).displayChart();
    }
    public static void searchingChart2(String title, int[] xAxis, double[][] yAxis) throws IOException {
        XYChart chart = new XYChartBuilder().width(1200).height(900).title(title)
                .yAxisTitle("Time in Nanoseconds").xAxisTitle("Input Size").build();

        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("Linear Search (sorted data)", doubleX, yAxis[0]);
        chart.addSeries("Binary Search (sorted data)", doubleX, yAxis[1]);

        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        new SwingWrapper(chart).displayChart();
    }
}
