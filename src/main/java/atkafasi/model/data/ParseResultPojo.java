package atkafasi.model.data;

import java.util.Arrays;

/**
 * Created by gilmour on 09.02.2016.
 */
public class ParseResultPojo {

    int rowNumber;

    int columnNumber;

    boolean[][] asciiData;

    public ParseResultPojo(int rowNumber, int columnNumber, boolean[][] asciiData) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.asciiData = asciiData;
    }


    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public boolean[][] getAsciiData() {
        return asciiData;
    }

    public void setAsciiData(boolean[][] asciiData) {
        this.asciiData = asciiData;
    }

    @Override
    public String toString() {
        return "ParseResultPojo{" +
                "rowNumber=" + rowNumber +
                ", columnNumber=" + columnNumber +
                ", asciiData=" + Arrays.toString(asciiData) +
                '}';
    }
}
