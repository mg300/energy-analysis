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

    public float readCell(int indexCol, int indexRow) {
        indexRow--;
        indexCol--;
        Cell<?> cell = sheet.getImmutableCellAt(indexCol, indexRow);
        BigDecimal cellValue = (BigDecimal) cell.getValue();
        return cellValue.floatValue();
    }
    public String readTariffName(int indexCol){
        indexCol--;
        Cell<?> cell = sheet.getImmutableCellAt(indexCol, 2);
        return cell.getValue().toString();
    }
    public String readTitle(){
        Cell<?> cell = sheet.getImmutableCellAt(0, 0);
        return cell.getValue().toString();
    }
    public String readPlace(){
        Cell<?> cell = sheet.getImmutableCellAt(1, 1);
        return cell.getValue().toString();
    }


    public int getCountOfRowsWithData(int indexCol) {
        int rowCount = 0;
        indexCol--;
        for (int indexRow = 0; indexRow < 4096; indexRow++) {
            Cell<?> cell = sheet.getImmutableCellAt(indexCol, indexRow);
            if(!cell.isEmpty()){
                if(Objects.equals(cell.getValueType().toString(), "FLOAT")){
                    rowCount++;
                }

            }

        }
        return rowCount;
    }
    public int getNumOfFirstRowWithData(int indexCol) {
        int rowStart = 1;
        indexCol--;
        for (int indexRow = 0; indexRow < 4096; indexRow++) {
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