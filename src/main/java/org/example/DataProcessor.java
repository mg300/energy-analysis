package org.example;

public class DataProcessor {
    final InputManager managerIO;
    public DataProcessor(InputManager managerIO) {
        this.managerIO = managerIO;
    }
    public double[] getColumnData(int col){
        int rowCount = managerIO.getCountOfRowsWithData(col);
        int rowStart = managerIO.getNumOfFirstRowWithData(col);

        double[] result = new double[rowCount];
        int index = 0;
        for(int row=rowStart; row<rowCount+rowStart;row++){
            result[index] = managerIO.readCell(col,row);
            index++;
        }
        return result;
    }



}
