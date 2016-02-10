package atkafasi.solutions;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.instructions.HLine;
import atkafasi.model.instructions.Instructions;
import atkafasi.model.instructions.Square;
import atkafasi.model.instructions.VLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilmour on 09.02.2016.
 */
public class IterativeSearch implements Solution {

    float ratio;

    @Override
    public List<Instructions> solve(final ParseResultPojo frameData) {

        // find squares

        List<Instructions> squareInstructions = findSquareShapes(frameData);

        // find vertical lines
        List<Instructions> horizontalLineInstructions = findHorizontalLineShapes(frameData);

        // find horizontal lines

        List<Instructions> verticalLineInstructions = findVerticalLineShapes(frameData);

        // erase if necessary

        // combine results

        return squareInstructions;
    }

    private List<Instructions> findSquareShapes(ParseResultPojo frameData) {

        List<Instructions> squareInsList = new ArrayList<>();

        int minSide = Math.min(frameData.getRowNumber(), frameData.getColumnNumber());

        int maxSquareRadius = (minSide - 1) / 2;

        for (int radius = 1; radius < maxSquareRadius; radius++) {

            int cellNumberOfIdentityMatrix = (int) Math.pow((2 * radius) + 1, 2);

            for (int row = radius; row < frameData.getRowNumber() - radius; row++) {
                for (int col = radius; col < frameData.getColumnNumber() - radius; col++) {

                    // TODO inefficient :(
                    // sub-matrix comparison

                    int numberOfLoadedCell = 0;

                    for (int tempRow = row - radius; tempRow <= row + radius; tempRow++) {
                        for (int tempCol = col - radius; tempCol <= col + radius; tempCol++) {

                            if (frameData.getAsciiData()[tempRow][tempCol])
                                numberOfLoadedCell++;
                        }
                    }

                    // check square load ratio
                    float squareRatio = 1f * numberOfLoadedCell / cellNumberOfIdentityMatrix;

                    if (Float.compare(squareRatio, this.ratio) >= 0) {
                        // add as an instruction
                        squareInsList.add(new Square(row, col, radius));
                    }

                }
            }
        }

        return squareInsList;
    }

    private List<Instructions> findHorizontalLineShapes(ParseResultPojo frameData) {


        List<Instructions> horizontalInsList = new ArrayList<>();

        // R1 == R2
        for (int row = 0; row < frameData.getRowNumber(); row++) {
            for (int col = 0; col < frameData.getColumnNumber(); col++) {
                if (frameData.getAsciiData()[row][col]) {

                    int offset = 0;

                    while (col + offset < frameData.getColumnNumber() && frameData.getAsciiData()[row][col + offset]) {
                        offset += 1;
                    }

                    if (offset > 0) {
                        horizontalInsList.add(new HLine(row, col, col + offset - 1));
                    }

                    col += offset;
                }

            }
        }


        return horizontalInsList;
    }

    private List<Instructions> findVerticalLineShapes(ParseResultPojo frameData) {

        List<Instructions> verticalInsList = new ArrayList<>();


        // C1 == C2
        for (int col = 0; col < frameData.getColumnNumber(); col++) {
            for (int row = 0; row < frameData.getRowNumber(); row++) {
                if (frameData.getAsciiData()[row][col]) {
                    int offset = 0;

                    while (row + offset < frameData.getRowNumber() && frameData.getAsciiData()[row + offset][col]) {
                        offset += 1;
                    }

                    if (offset > 0) {
                        verticalInsList.add(new VLine(row, row + offset - 1, col));
                    }

                    row += offset;
                }
            }
        }


        return verticalInsList;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
