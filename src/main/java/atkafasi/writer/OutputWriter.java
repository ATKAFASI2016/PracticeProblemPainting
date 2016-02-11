package atkafasi.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.instructions.Erase;
import atkafasi.model.instructions.HLine;
import atkafasi.model.instructions.Instructions;
import atkafasi.model.instructions.Square;
import atkafasi.model.instructions.VLine;

public class OutputWriter {

	public static void instructionWriter(List<Instructions> instructionsList, String fileName) throws IOException {

		File file = new File(fileName);
		boolean result = file.createNewFile();

		if (!result) {
			System.out.println(fileName + " already exists, will be overwritten");
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		try (BufferedWriter bw = new BufferedWriter(fw)) {
			for (Instructions anInstructionsList : instructionsList) {
				bw.write(anInstructionsList.toInstructionString() + "\n");
			}
		}
	}

	public static void generateAsciiImage(String fileName, ParseResultPojo frameData,
			List<Instructions> instructionsList) throws IOException, IllegalInstructionFound {

		File file = new File(fileName);
		boolean result = file.createNewFile();

		if (!result) {
			System.out.println(fileName + " already exists, will be overwritten");
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());

		try (BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(frameData.getRowNumber() + " " + frameData.getColumnNumber());
			bw.newLine();

			char[][] outputArr = new char[frameData.getRowNumber()][frameData.getColumnNumber()];

			// fill output with initial fullstops
			for (int i = 0; i < frameData.getRowNumber(); i++) {
				for (int j = 0; j < frameData.getColumnNumber(); j++) {
					outputArr[i][j] = '.';
				}
			}

			for (Instructions instruction : instructionsList) {

				if (instruction instanceof Square) {

					int startingRow = ((Square) instruction).getRow() - ((Square) instruction).getS();
					int startingColumn = ((Square) instruction).getColumn() - ((Square) instruction).getS();

					// (2s+1)x(2s+1)
					int squareDimension = 2 * ((Square) instruction).getS() + 1;

					// check if we are still in cartesian plane ?
					boolean checkValidity = startingRow >= 0;
					checkValidity &= startingColumn >= 0;
					checkValidity &= startingRow + squareDimension - 1 <= frameData.getRowNumber();
					checkValidity &= startingColumn + squareDimension - 1 <= frameData.getColumnNumber();

					if (!checkValidity) {
						throw new IllegalInstructionFound("INVALID INSTRUCTION FOUND ! "
								+ instruction.toInstructionString());
					}

					for (int i = startingRow; i < startingRow + squareDimension; i++) {
						for (int j = startingColumn; j < startingColumn + +squareDimension; j++) {
							outputArr[i][j] = '#';
						}
					}

				} else if (instruction instanceof VLine) {

					int r1 = ((VLine) instruction).getR1();
					int c1 = ((VLine) instruction).getC1();
					int r2 = ((VLine) instruction).getR2();
					int c2 = ((VLine) instruction).getC2();

					// VLine -> columns must be equal
					boolean validityCheck = c1 == c2;
					validityCheck &= r1 <= r2;

					// if (!validityCheck) {
					// throw new
					// IllegalInstructionFound("INVALID INSTRUCTION FOUND ! " +
					// instruction.toInstructionString());
					// }

					for (int i = r1; i < (r1 + (r2 - r1)); i++) {
						outputArr[i][c1] = '#';
					}
					for (int i = c1; i < (c1 + (c2 - c1)); i++) {
						outputArr[r1][i] = '#';
					}
				} else if (instruction instanceof HLine) {

					int r1 = ((HLine) instruction).getR1();
					int c1 = ((HLine) instruction).getC1();
					int r2 = ((HLine) instruction).getR2();
					int c2 = ((HLine) instruction).getC2();

					// HLine -> columns must be equal
					boolean validityCheck = r1 == r2;
					validityCheck &= c1 <= c2;

					// if (!validityCheck) {
					// throw new
					// IllegalInstructionFound("INVALID INSTRUCTION FOUND ! "
					// + instruction.toInstructionString());
					// }

					for (int i = c1; i < (c1 + (c2 - c1)); i++) {
						outputArr[r1][i] = '#';
					}
					for (int i = r1; i < (r1 + (r2 - r1)); i++) {
						outputArr[i][c1] = '#';
					}
				} else if (instruction instanceof Erase) {

					int row = ((Erase) instruction).getR();
					int column = ((Erase) instruction).getC();

					// TODO add validity check if necessary
					// But I guess, we do not need for now :)

					outputArr[row][column] = '.';
				}
			}

			for (int i = 0; i < outputArr.length; i++) {
				for (int j = 0; j < outputArr[i].length; j++) {
					bw.write(new char[] { outputArr[i][j] });
				}
				bw.newLine();
			}

		}

	}
}
