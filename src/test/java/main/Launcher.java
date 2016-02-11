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

	String fileName = "learn_and_teach";

	SolutionStrategy solutionStrategy = SolutionStrategy.IterativeSearch;

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

	Solution solution = SolutionFactory.getSolution(solutionStrategy);

	((IterativeSearch) solution).setRatio(.7f);

	List<Instructions> instructionsList = solution.solve(result);

	if (instructionsList != null && !instructionsList.isEmpty()) {
	    try {

		OutputWriter.instructionWriter(instructionsList, fileName + "_" + solutionStrategy.toString() + ".out");
		OutputWriter.generateAsciiImage(fileName + "_" + solutionStrategy.toString() + ".io", result, instructionsList);

	    } catch (IOException | IllegalInstructionFound e) {
		e.printStackTrace();
	    }
	} else {
	    System.err.println("Seems there is no instruction set generated. Output file is not created!");
	}

    }

}
