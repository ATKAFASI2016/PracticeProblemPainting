package atkafasi.solutions;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.instructions.Instructions;
import atkafasi.model.instructions.Square;

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

        // find horizontal lines

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
                    float squareRatio = numberOfLoadedCell / cellNumberOfIdentityMatrix;

                    if (Float.compare(squareRatio, this.ratio) >= 0) {
                        // add as an instruction
                        squareInsList.add(new Square(row, col, radius));
                    }

                }
            }
        }

        return squareInsList;
    }


    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
