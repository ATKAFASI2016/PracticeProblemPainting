package main;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.enums.SolutionStrategy;
import atkafasi.model.instructions.Instructions;
import atkafasi.reader.AtKafasiReaderException;
import atkafasi.reader.InputReader;
import atkafasi.solutions.IterativeSearch;
import atkafasi.solutions.Solution;
import atkafasi.solutions.SolutionFactory;
import atkafasi.writer.IllegalInstructionFound;
import atkafasi.writer.OutputWriter;

import java.io.IOException;
import java.util.List;

public class Launcher {

    /**
     * Program starts here! ------ fileName should be changed
     */

    public static void main(String[] args) {

	SolutionStrategy solutionStrategy = SolutionStrategy.IterativeSearch;

	String[] resourceFileNames = { "learn_and_teach", "logo", "right_angle", "test_1" };

	for (int fIndex = 0; fIndex < resourceFileNames.length; fIndex++) {

	    String fileName = resourceFileNames[fIndex];

	    System.out.println("------------------------------------------------------");
	    System.out.println("Starting instruction generation for file = " + fileName);

	    String file = Launcher.class.getResource("/inputFiles/" + fileName + ".in").getFile();

	    if (file == null || file.isEmpty()) {
		System.err.println("Check file path: " + fileName);
	    }

	    ParseResultPojo result;
	    try {
		result = InputReader.readFileInto2DArr(file);
	    } catch (AtKafasiReaderException | IOException e) {
		System.err.println("Exception occurred, while reading file : " + file + "\n" + e.getMessage());
		return;
	    }

	    assert result != null;

	    // Heuristic ratio lower value is .8f
	    for (float ratio = .8f; Float.compare(ratio, 1f) <= 0; ratio += .1f) {

		Solution solution = SolutionFactory.getSolution(solutionStrategy);
		((IterativeSearch) solution).setRatio(ratio);
		List<Instructions> instructionsList = solution.solve(result);

		System.out.println("- There is " + instructionsList.size() + " instructions for ratio " + ratio);

		if (instructionsList != null && !instructionsList.isEmpty()) {
		    try {
			OutputWriter.instructionWriter(instructionsList, fileName + "_" + solutionStrategy.toString() + "_" + ratio + ".out");
			OutputWriter.generateAsciiImage(fileName + "_" + solutionStrategy.toString() + "_" + ratio + ".io", result, instructionsList);
		    } catch (IOException | IllegalInstructionFound e) {
			e.printStackTrace();
		    }
		} else {
		    System.err.println("Seems there is no instruction set generated. Output file is not created!");
		}

	    }

	    // new line
	    System.out.println();
	}
    }
}
