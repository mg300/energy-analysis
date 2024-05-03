package org.example;

import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class FileHandlerInput implements InputManager {

    private Sheet sheet;

    public FileHandlerInput(String path) {
        try {
            File file = new File(path);
            this.sheet = SpreadSheet.createFromFile(file).getSheet(0);
        } catch (IOException exception) {
            System.out.println("Zła ścieżka do pliku");
        }
    }

    public double readCell(int indexCol, int indexRow) {
        indexRow--;
        indexCol--;
        Cell<?> cell = sheet.getImmutableCellAt(indexCol, indexRow);
        BigDecimal cellValue = (BigDecimal) cell.getValue();
        return cellValue.doubleValue();
    }
    public String readCellTypeString(int indexCol, int indexRow) {
        indexRow--;
        indexCol--;
        Cell<?> cell = sheet.getImmutableCellAt(indexCol, indexRow);
        Object cellValue = cell.getValue();
        return cellValue.toString();
    }

    public int getCountOfRowsWithData(int indexCol) {
        int rowCount = 0;
        indexCol--;
        for (int indexRow = 0; indexRow < 50000; indexRow++) {
            Cell<?> cell = sheet.getImmutableCellAt(indexCol, indexRow);
            if(!cell.isEmpty()){
                if(Objects.equals(cell.getValueType().toString(), "FLOAT")){
                    rowCount++;
                }

            }

        }
        return rowCount;
    }    public int getCountOfRowsWithString(int indexCol) {
        int rowCount = 0;
        indexCol--;
        for (int indexRow = 0; indexRow < 50000; indexRow++) {
            Cell<?> cell = sheet.getImmutableCellAt(indexCol, indexRow);
            if(!cell.isEmpty()){
                if(Objects.equals(cell.getValueType().toString(), "STRING")){
                    rowCount++;
                }
            }

        }
        return rowCount;
    }
    public int getNumOfFirstRowWithData(int indexCol) {
        int rowStart = 1;
        indexCol--;
        for (int indexRow = 0; indexRow < 50000; indexRow++) {
            Cell<?> cell = sheet.getImmutableCellAt(indexCol, indexRow);
            if(!cell.isEmpty()){
                if(Objects.equals(cell.getValueType().toString(), "FLOAT")){
                   return indexRow+1;
                }
            }

        }
        return rowStart;
    }
}