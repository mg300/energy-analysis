package org.example;

public interface InputManager {
        double readCell(int indexCol, int indexRow);
        String readCellTypeString(int indexCol, int indexRow);
        int getCountOfRowsWithData(int indexCol);
        int getCountOfRowsWithString(int indexCol);
        int getNumOfFirstRowWithData(int indexCol);

}
