package main;

import atkafasi.model.data.DataPart;
import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.enums.SolutionStrategy;
import atkafasi.model.instructions.Instructions;
import atkafasi.reader.AtKafasiReaderException;
import atkafasi.reader.InputReader;
import atkafasi.solutions.Solution;
import atkafasi.solutions.SolutionFactory;
import atkafasi.split.DataSplitter;
import atkafasi.writer.OutputWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {

    /**
     * Program starts here! ------ fileName should be changed
     */

    public static void main(String[] args) {

        String fileName = "logo";

        SolutionStrategy solutionStrategy = SolutionStrategy.SinglePoint;

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
        List<DataPart> dataParts = DataSplitter.split(result.getAsciiData());

        Solution solution = SolutionFactory.getSolution(solutionStrategy);

        List<Instructions> instructionsList = new ArrayList<>();

        for (DataPart dataPart : dataParts) {
            for (Instructions instruction : solution.solve(dataPart)) {
                instructionsList.add(instruction);
            }
        }

        try {
            OutputWriter.instructionWriter(instructionsList, fileName + "_" + solutionStrategy.toString() + ".out");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
