package atkafasi.launch;

import java.util.List;

import atkafasi.model.enums.SolutionStrategy;
import atkafasi.model.instructions.Instructions;
import atkafasi.reader.InputReader;
import atkafasi.solutions.Solution;
import atkafasi.solutions.SolutionFactory;
import atkafasi.writer.OutputWriter;

public class Launcher {

	/**
	 * Program starts here! ------ fileName should be changed
	 */

	public static void main(String[] args) {
		String fileName = "right_angle";
		try {

			boolean[][] data = InputReader.read(fileName + ".in");

			Solution solution = SolutionFactory.getSolution(SolutionStrategy.SinglePoint);

			List<Instructions> intructionList = solution.solve(data);

			OutputWriter.write(intructionList, fileName + ".out");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
