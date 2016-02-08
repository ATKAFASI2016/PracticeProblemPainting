package atkafasi.solutions;

import java.util.ArrayList;
import java.util.List;

import atkafasi.model.instructions.Instructions;
import atkafasi.model.instructions.Point;

public class PaintEachPoint implements Solution {

	@Override
	public List<Instructions> solve(boolean[][] data) {

		List<Instructions> result = new ArrayList<Instructions>();

		for (int y = 0; y < data.length; y++) {
			for (int x = 0; x < data[y].length; x++) {
				if (data[y][x]) {
					result.add(new Point(x, y));
				}
			}
		}
		return result;
	}

}
