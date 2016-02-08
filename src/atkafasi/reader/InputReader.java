package atkafasi.reader;

import java.io.BufferedReader;
import java.io.FileReader;

public class InputReader {

	/**
	 * #=true .=false
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean[][] read(String fileName) throws Exception {
		boolean[][] result = null;
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String sCurrentLine;

		int lineNumber = -1;
		int yMax = -1;
		int xMax = -1;
		while ((sCurrentLine = br.readLine()) != null) {
			if (lineNumber == -1) {

				String[] strings = sCurrentLine.trim().split(" ");
				yMax = Integer.parseInt(strings[0]);
				xMax = Integer.parseInt(strings[1]);
				result = new boolean[yMax][xMax];
			} else {
				if (yMax > lineNumber) {
					for (int i = 0; i < xMax; i++) {
						char charAtX = sCurrentLine.charAt(i);
						if (charAtX == '#') {
							result[lineNumber][i] = true;
						} else {
							result[lineNumber][i] = false;
						}

					}

				}
			}
			lineNumber++;
		}

		if (result == null) {
			throw new NullPointerException();
		}
		return result;
	}
}
