package atkafasi.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import atkafasi.model.instructions.Instructions;

public class OutputWriter {

    public static void instructionWriter(List<Instructions> instructionsList, String fileName) throws IOException {

        File file = new File(fileName);
        boolean result = file.createNewFile();

        if (!result) {
            System.out.println(fileName + " already exists, will be overwritten");
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (Instructions anInstructionsList : instructionsList) {
            bw.write(anInstructionsList.toInstructionString() + "\n");
        }

        bw.close();
    }

    public static void asciiWriter(List<Instructions> instructionsList) {

    }

}
