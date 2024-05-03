package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        InputManager patternFileManager = new FileHandlerInput("2023.12.27-roszkow-powercurve - wzorcowa.ods");
        InputManager actualFileManager = new FileHandlerInput("2023.12.27-roszkow-trend.ods");
        DataProcessor patternProcessor = new DataProcessor(patternFileManager);
        DataProcessor actualProcessor = new DataProcessor(actualFileManager);

        double[] patternWindSpeed = patternProcessor.getColumnData(1);
        double[] patternKW = patternProcessor.getColumnData(2);
        double[] actualWindSpeed = actualProcessor.getColumnData(2);
        double[] actualKW = actualProcessor.getColumnData(3);

        SheetGenerator sheetGenerator = new SheetGenerator(patternWindSpeed,patternKW, actualWindSpeed,actualKW);
        sheetGenerator.generate("Raport");

        Interpolator interpolator = new Interpolator();

        double[] patternX = interpolator.getNewPrecision();
        double[] patternY = interpolator.interpolate(patternWindSpeed, patternKW);
        sheetGenerator = new SheetGenerator(patternX,patternY, actualWindSpeed,actualKW);
        sheetGenerator.generate("Raport2");

    }
}
