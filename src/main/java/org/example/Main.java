package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InputManager patternFileManager = new FileHandlerInput("2023.12.27-roszkow-powercurve - wzorcowa.ods");
        InputManager actualFileManager = new FileHandlerInput("2023.12.27-roszkow-trend.ods");
        DataProcessor patternProcessor = new DataProcessor(patternFileManager);
        DataProcessor actualProcessor = new DataProcessor(actualFileManager);
//        List<Double> windSpeedPattern = new ArrayList<>();
//        List<Double> KWPattern=new ArrayList<>();
//        List<Double> windSpeedActtual=new ArrayList<>();
//        List<Double> windKWActual=new ArrayList<>();
        double[] windSpeedPattern = patternProcessor.getColumnData(1);
        double[] KWPattern = patternProcessor.getColumnData(2);
        double[] windSpeedActual= actualProcessor.getColumnData(1);
        double[] KWActual = actualProcessor.getColumnData(2);

        Interpolator interpolator = new Interpolator();
        double[] patternX=interpolator.getNewPrecision();
        double[] patternY = interpolator.interpolate(windSpeedPattern,KWPattern);
        double[] actualX = windSpeedActual;
        double[] actualY = KWActual;

        System.out.println(Arrays.toString(patternX));
        System.out.println(Arrays.toString(patternY));






    }
}