package atkafasi.solutions;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.instructions.*;
import atkafasi.model.instructions.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by gilmour on 09.02.2016.
 */
public class IterativeSearch implements Solution {

    float ratio;

    PriorityQueue<Instructions> instList = new PriorityQueue<>();

    @Override public List<Instructions> solve(final ParseResultPojo frameData) {

	// find squares
	findSquareShapes(frameData);

	// find vertical lines
	findHorizontalLineShapes(frameData);

	// find horizontal lines
	findVerticalLineShapes(frameData);

	// draw functions

	List<Instructions> resultingList = eliminate(frameData);

	// erase if necessary

	// combine results

	return resultingList;
    }

    private List<Instructions> eliminate(ParseResultPojo frameData) {

	List<Instructions> resultInstList = new ArrayList<>();

	Object[] arr = instList.toArray();
	Arrays.sort(arr);

	boolean[][] resultArr = new boolean[frameData.getRowNumber()][frameData.getColumnNumber()];

	for (int index = 0; index < arr.length; index++) {
	    Instructions instruction = (Instructions) arr[index];

	    // check if drawn already ?
	    if (instruction instanceof Point) {

		Point p = (Point) instruction;
		if (resultArr[p.getRow()][p.getColumn()]) {
		    instList.remove(instruction);
		    //		    System.out.println("POINT instruction removed");
		} else {
		    resultArr[p.getRow()][p.getColumn()] = true;
		    resultInstList.add(instruction);
		}

	    } else if (instruction instanceof Square) {

		int startingRow = ((Square) instruction).getRow() - ((Square) instruction).getS();
		int startingColumn = ((Square) instruction).getColumn() - ((Square) instruction).getS();

		int squareDimension = 2 * ((Square) instruction).getS() + 1;

		int numberOfTotal = (int) Math.pow(squareDimension, 2);

		int loadedCells = 0;
		for (int row = startingRow; row < startingRow + squareDimension; row++) {
		    for (int col = startingColumn; col < startingColumn + squareDimension; col++) {
			if (resultArr[row][col]) {
			    loadedCells++;
			}
		    }
		}

		if (loadedCells == numberOfTotal) {
		    instList.remove(instruction);
		    //		    System.out.println("SQUARE instruction removed");
		} else {
		    for (int row = startingRow; row < startingRow + squareDimension; row++) {
			for (int col = startingColumn; col < startingColumn + squareDimension; col++) {
			    resultArr[row][col] = true;
			}
		    }
		    resultInstList.add(instruction);
		}

	    } else if (instruction instanceof HLine) {

		int r1 = ((HLine) instruction).getR1();
		int c1 = ((HLine) instruction).getC1();
		int r2 = ((HLine) instruction).getR2();
		int c2 = ((HLine) instruction).getC2();

		int expectedLoadedCells = (c2 - c1) + 1;

		int loadedCells = 0;
		for (int col = c1; col <= (c1 + (c2 - c1)); col++) {
		    if (resultArr[r1][col]) {
			loadedCells++;
		    }
		}

		if (loadedCells == expectedLoadedCells) {
		    instList.remove(instruction);
		    //		    System.out.println("HLINE instruction removed");
		} else {
		    for (int col = c1; col <= (c1 + (c2 - c1)); col++) {
			resultArr[r1][col] = true;
		    }
		    resultInstList.add(instruction);
		}

	    } else if (instruction instanceof VLine) {

		int r1 = ((VLine) instruction).getR1();
		int c1 = ((VLine) instruction).getC1();
		int r2 = ((VLine) instruction).getR2();
		int c2 = ((VLine) instruction).getC2();

		int expectedLoadedCells = (r2 - r1) + 1;

		int loadedCells = 0;
		for (int row = r1; row <= (r1 + (r2 - r1)); row++) {
		    if (resultArr[row][c1]) {
			loadedCells++;
		    }
		}

		if (expectedLoadedCells == loadedCells) {
		    instList.remove(instruction);
		    //		    System.out.println("VLINE instruction removed");
		} else {
		    for (int row = r1; row <= (r1 + (r2 - r1)); row++) {
			resultArr[row][c1] = true;
		    }
		    resultInstList.add(instruction);
		}
	    }
	}

	for (int i = 0; i < frameData.getRowNumber(); i++) {
	    for (int j = 0; j < frameData.getColumnNumber(); j++) {

		if (frameData.getAsciiData()[i][j] != resultArr[i][j]) {
		    if (resultArr[i][j]) {
			//			System.out.println("erased");
			resultInstList.add(new Erase(i, j));
		    } else {
			//			System.out.println("added");
			resultInstList.add(new Point(i, j));
		    }
		}
	    }
	}
	return resultInstList;
    }

    private void findSquareShapes(ParseResultPojo frameData) {

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
			Instructions ins = new Square(row, col, radius);
			ins.setInstruction_value(((cellNumberOfIdentityMatrix - numberOfLoadedCell) / 2) + cellNumberOfIdentityMatrix * .1f);
			instList.offer(new Square(row, col, radius));
		    }

		}
	    }
	}

    }

    private void findHorizontalLineShapes(ParseResultPojo frameData) {

	// R1 == R2
	for (int row = 0; row < frameData.getRowNumber(); row++) {
	    for (int col = 0; col < frameData.getColumnNumber(); col++) {
		if (frameData.getAsciiData()[row][col]) {

		    int offset = 0;

		    while (col + offset < frameData.getColumnNumber() && frameData.getAsciiData()[row][col + offset]) {
			offset += 1;
		    }

		    if (offset > 0) {
			if (offset != 1) {
			    Instructions inst = new HLine(row, col, col + offset - 1);
			    inst.setInstruction_value(offset);

			    instList.offer(inst);
			} else {
			    Instructions inst = new Point(row, col);
			    inst.setInstruction_value(1);
			    instList.offer(inst);
			}
		    }
		    col += offset;
		}

	    }
	}
    }

    private void findVerticalLineShapes(ParseResultPojo frameData) {

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
			if (offset != 1) {
			    Instructions inst = new VLine(row, row + offset - 1, col);
			    inst.setInstruction_value(offset);
			    instList.offer(inst);
			} else {
			    Instructions inst = new Point(row, col);
			    inst.setInstruction_value(1);
			    instList.offer(new Point(row, col));
			}
		    }
		    row += offset;
		}
	    }
	}
    }

    public float getRatio() {

	return ratio;
    }

    public void setRatio(float ratio) {

	this.ratio = ratio;
    }

}
