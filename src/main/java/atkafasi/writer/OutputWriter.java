package atkafasi.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import atkafasi.model.instructions.Instructions;

public class OutputWriter {

	public static void write(List<Instructions> intructionList, String fileName) throws IOException {

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < intructionList.size(); i++) {
			bw.write(intructionList.get(i).toInstructionString() + "\n");
		}

		bw.close();
	}

}
