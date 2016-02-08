package atkafasi.solutions;

import java.util.ArrayList;
import java.util.List;

import atkafasi.model.data.DataPart;
import atkafasi.model.instructions.Instructions;
import atkafasi.model.instructions.Point;

public class PaintEachPoint implements Solution {

	@Override
	public List<Instructions> solve(DataPart dataPart) {
		List<Instructions> result = new ArrayList<Instructions>();

		for (int y = 0; y < dataPart.getyLen(); y++) {
			for (int x = 0; x < dataPart.getxLen(); x++) {
				if (dataPart.getData()[y][x]) {
					result.add(new Point(x + dataPart.getStartX(), y + dataPart.getStartY()));
				}
			}
		}
		return result;
	}

}
