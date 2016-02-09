package atkafasi.solutions;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.instructions.Instructions;
import atkafasi.model.instructions.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummiest instruction generator. Examines 2 dimensional array one-by-one. If there is a painting at specified cell, appends instruction.
 */
public class PaintEachPoint implements Solution {


    @Override
    public List<Instructions> solve(ParseResultPojo frameData) {

        List<Instructions> instructionList = new ArrayList<>();

        for (int i = 0; i < frameData.getRowNumber(); i++) {
            for (int j = 0; j < frameData.getColumnNumber(); j++) {
                if (frameData.getAsciiData()[i][j]) {
                    instructionList.add(new Point(i, j));
                }
            }
        }


        return instructionList;
    }
}
