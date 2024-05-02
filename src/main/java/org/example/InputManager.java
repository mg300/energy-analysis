package org.example;

public interface InputManager {
        float readCell(int indexCol, int indexRow);
        int getCountOfRowsWithData(int indexCol);
        int getNumOfFirstRowWithData(int indexCol);
        String readTariffName(int indexCol);
        String readTitle();
        String readPlace();
}
