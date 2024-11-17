import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        int[] inputSizes = {4000, 5000,6000, 7000,8000,9000, 10000,11000, 12000,13000 };
        List<Long> durations1 = new ArrayList<>();
        List<Long> durations2 = new ArrayList<>();

        for (int size : inputSizes) {
            PriorityQueueBinaryHeap heapQueue = new PriorityQueueBinaryHeap();
            PriorityQueueLinkedList linkedListQueue = new PriorityQueueLinkedList();

            heapQueue.fillRandom(size);
            linkedListQueue.fillRandom(size);

            long duration1 = 0;
            long duration2 = 0;
            for (int i = 0; i < 1000; i++) {
                long startTime1 = System.nanoTime();
                heapQueue.extractMax();
                long endTime1 = System.nanoTime();
                duration1 += endTime1 - startTime1;

                long startTime2 = System.nanoTime();
                linkedListQueue.extractMax();
                long endTime2 = System.nanoTime();
                duration2 += endTime2 - startTime2;
            }

            durations1.add(duration1 / 1000);
            durations2.add(duration2 / 1000);
        }

        SwingUtilities.invokeLater(() -> createAndShowGraph(inputSizes, durations1, durations2));
    }

    private static void createAndShowGraph(int[] inputSizes, List<Long> durations1, List<Long> durations2) {
        XYSeries series1 = new XYSeries("Binary Heap");
        for (int i = 0; i < inputSizes.length; i++) {
            series1.add(inputSizes[i], durations1.get(i));
        }

        XYSeries series2 = new XYSeries("Linked List");
        for (int i = 0; i < inputSizes.length; i++) {
            series2.add(inputSizes[i], durations2.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Priority Queue Performance",
                "Input Size",
                "Time (ns)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Priority Queue Performance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}