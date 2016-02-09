package atkafasi.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {

    /**
     * #=true .=false
     *
     * @param fileName
     * @return
     */
    public static boolean[][] readFileInto2DArr(String fileName) throws AtKafasiReaderException, IOException {

        boolean[][] result = null;

        String sCurrentLine;

        int lineNumber = -1;
        int rowMax = -1;
        int columnMax = -1;

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                if (lineNumber == -1) {

                    String[] strings = sCurrentLine.trim().split(" ");
                    // May throw numberFormatException while parsing integers !
                    rowMax = Integer.parseInt(strings[0]);
                    columnMax = Integer.parseInt(strings[1]);
                    result = new boolean[rowMax][columnMax];

                } else {
                    if (rowMax > lineNumber) {

                        if (sCurrentLine.length() > columnMax) {
                            throw new InconsistentFileFormatException("Inconsistent column index at row" + lineNumber + " Expected length: " + columnMax);
                        }

                        for (int i = 0; i < columnMax; i++) {
                            char charAtX = sCurrentLine.charAt(i);
                            if (charAtX == '#') {
                                result[lineNumber][i] = true;
                            } else if (charAtX == '.') {
                                result[lineNumber][i] = false;
                            } else {
                                throw new InconsistentFileFormatException("Unexpected character (" + charAtX + ") found at line:" + lineNumber + " column: " + i);
                            }
                        }
                    } else {
                        throw new InconsistentFileFormatException("File row index overflow! Row index cannot be equal or greater than " + rowMax);
                    }
                }
                lineNumber++;
            }

        }

        if (result == null) {
            throw new ReaderResultNullException("Somehow resulting array returned null!");
        }
        return result;
    }
}
