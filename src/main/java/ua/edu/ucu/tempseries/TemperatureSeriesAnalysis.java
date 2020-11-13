package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final double ABSOLUTEZERO = -273.0;
    private static final double DELTA = 0.00001;
    private double[] temperatureSeries = new double[1];
    private int size = 0;
    private int buffer = 1;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] tempSeries) {
        addTemps(tempSeries);
    }

    public double average() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += temperatureSeries[i];
        }
        return (sum/size);
    }

    public double deviation() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double ave = average();
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += (ave - temperatureSeries[i]) * (ave - temperatureSeries[i]);
        }
        return Math.sqrt(sum/size);
    }

    public double min() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double minTemp = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            if (minTemp > temperatureSeries[i]) {
                minTemp = temperatureSeries[i];
            }
        }
        return minTemp;
    }

    public double max() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double maxTemp = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            if (maxTemp < temperatureSeries[i]) {
                maxTemp = temperatureSeries[i];
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double closest = temperatureSeries[0];
        double min = Math.abs(closest - tempValue);
        for (int i = 1; i < size; i++) {
            double temp = temperatureSeries[i];
            if ((min > Math.abs(temp - tempValue))
                    || ((Math.abs(min - Math.abs(temp - tempValue)) < DELTA)
                    && (temp > tempValue))) {
                closest = temp;
                min = Math.abs(closest - tempValue);
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] tempResults = new double[size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            double temp = temperatureSeries[i];
            if (temp < tempValue) {
                tempResults[counter] = temp;
                ++counter;
            }
        }
        double[] results = new double[counter];
        System.arraycopy(tempResults, 0, results, 0, counter);
        return results;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] tempResults = new double[size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            double temp = temperatureSeries[i];
            if (temp > tempValue) {
                tempResults[counter] = temp;
                ++counter;
            }
        }
        double[] results = new double[counter];
        System.arraycopy(tempResults, 0, results, 0, counter);
        return results;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        TempSummaryStatistics statistics = new TempSummaryStatistics(this);
        return statistics;
    }

    public int addTemps(double... temps) {
        double[] temporary = temperatureSeries;
        if (temps.length + size > buffer) {
            while (temps.length + size > buffer) {
                buffer *= 2;
            }
            double[] newList = new double[buffer];
//            for (int i = 0; i < size; i++) {
//                newList[i] = temperatureSeries[i];
//            }
            System.arraycopy(temperatureSeries, 0, newList, 0, size);
            temporary = newList;
        }
        int tempSize = size;
        for (double temp : temps) {
            if (temp < ABSOLUTEZERO) {
                throw new InputMismatchException();
            }
            temporary[tempSize] = temp;
            ++tempSize;
        }
        temperatureSeries = temporary;
        size = tempSize;
        return size;
    }
}
