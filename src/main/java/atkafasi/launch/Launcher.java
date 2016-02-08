package atkafasi.launch;

import java.util.ArrayList;
import java.util.List;

import atkafasi.model.data.DataPart;
import atkafasi.model.enums.SolutionStrategy;
import atkafasi.model.instructions.Instructions;
import atkafasi.reader.InputReader;
import atkafasi.solutions.Solution;
import atkafasi.solutions.SolutionFactory;
import atkafasi.split.DataSplitter;
import atkafasi.writer.OutputWriter;

public class Launcher {

	/**
	 * Program starts here! ------ fileName should be changed
	 */

	public static void main(String[] args) {
		String fileName = "logo";
		try {

			boolean[][] data = InputReader.read(fileName + ".in");

			List<DataPart> dataParts = DataSplitter.split(data);

			Solution solution = SolutionFactory.getSolution(SolutionStrategy.SinglePoint);

			List<Instructions> intructionList = new ArrayList<Instructions>();

			for (int i = 0; i < dataParts.size(); i++) {
				for (Instructions instruction : solution.solve(dataParts.get(i))) {
					intructionList.add(instruction);
				}
			}

			OutputWriter.write(intructionList, fileName + ".out");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
