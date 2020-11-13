package ua.edu.ucu.tempseries;

final public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(TemperatureSeriesAnalysis tempSeries) {
        avgTemp = tempSeries.average();
        devTemp = tempSeries.deviation();
        minTemp = tempSeries.min();
        maxTemp = tempSeries.max();
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }
}
