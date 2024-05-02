package org.example;

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
        for (double item : windSpeedPattern){
            System.out.print(item+", ");
        }



    }
}