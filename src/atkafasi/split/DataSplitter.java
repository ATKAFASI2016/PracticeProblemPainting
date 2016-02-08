package atkafasi.split;

import java.util.ArrayList;
import java.util.List;

import atkafasi.model.data.DataPart;

public class DataSplitter {

	public static List<DataPart> split(boolean[][] data) {
		List<DataPart> result = new ArrayList<>();
		result.add(new DataPart(0, 0, data, data[0].length, data.length));
		return result;
	}

}
