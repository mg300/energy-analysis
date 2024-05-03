package org.example;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class SheetGenerator {
    private final double[] patternX;
    private final double[] patternY;
    private final double[] actualX;
    private final double[] actualY;
    private Row row;

    private XSSFSheet sheet;

    private final int NUM_OF_ROWS;

    public SheetGenerator(double[] patternX, double[] patternY, double[] actualX, double[] actualY) {
        this.patternX = patternX;
        this.patternY = patternY;
        this.actualX = actualX;
        this.actualY = actualY;
        this.NUM_OF_ROWS=patternX.length;
    }
    public void generate(String fileName) throws
            IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            sheet = wb.createSheet("linechart");
            writeColNames();
            writeColValues();
            generateChart();

            try (FileOutputStream fileOut = new FileOutputStream(fileName+".xlsx")) {
                wb.write(fileOut);
                Workbook wkb = new com.aspose.cells.Workbook(fileName+".xlsx");
                wkb.save(fileName+".ods", SaveFormat.ODS);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}

    private void writeColValues(){
        int index=1;
        for (double pattern : patternX) {
            row = sheet.createRow((short) index);
            Cell cell = row.createCell((short) 0);
            cell.setCellValue(pattern);
            cell = row.createCell((short) 1);
            cell.setCellValue(patternY[index-1]);
            int[] ind = Utils.findIndex(actualX,pattern);
            double actualValue ;
            if(ind.length>0){
                double sum=0;
                for(int indx : ind){
                    sum+=actualY[indx];
                }
                actualValue = Math.round(sum/ind.length*100)/100.00;
                cell = row.createCell((short) 2);
                cell.setCellValue(actualValue);
                cell = row.createCell((short) 3);
                cell.setCellValue(patternY[index-1]-actualValue);
            }
            index++;
        }
    }
    private void writeColNames(){
        row = sheet.createRow((short) 0);
        row.createCell((short) 0).setCellValue("Wiatr [m/s]");
        row.createCell((short) 1).setCellValue("Moc wzorcowa [KW]");
        row.createCell((short) 2).setCellValue("Moc rzeczywista [KW]");
        row.createCell((short) 3).setCellValue("Różnica (wzorcowa-rzeczywista) [KW]");
    }
    private void generateChart(){
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        int col2 = patternX.length > 100 ? 150 : 30;
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 5, col2, 50);

        XSSFChart chart = drawing.createChart(anchor);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("wiatr [m/s]");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("moc [KW]");
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, NUM_OF_ROWS, 0, 0));
        XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, NUM_OF_ROWS, 1, 1));
        XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, NUM_OF_ROWS, 2, 2));
        XDDFNumericalDataSource<Double> ys3 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, NUM_OF_ROWS, 3, 3));

        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
        XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(xs, ys1);

        series1.setTitle("Moc wzorcowa [KW]", null);
        series1.setSmooth(true);
        series1.setMarkerStyle(MarkerStyle.STAR);
        series1.setMarkerStyle(MarkerStyle.NONE);

        XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(xs, ys2);
        series2.setTitle("Moc rzeczywista [KW]", null);
        series2.setSmooth(true);
        series2.setMarkerStyle(MarkerStyle.NONE);

        XDDFLineChartData.Series series3 = (XDDFLineChartData.Series) data.addSeries(xs, ys3);
        series3.setTitle("Różnica mocy [KW]", null);
        series3.setSmooth(true);
        series3.setMarkerStyle(MarkerStyle.NONE);

        chart.plot(data);
    }
}
