package atkafasi.solutions.sortedobject;

import java.util.ArrayList;
import java.util.List;

import atkafasi.model.geoobjects.GeoObjects;
import atkafasi.model.geoobjects.VGeoLine;

public class VLineFinder implements ObjectPatternSearcher {

	@Override
	public List<GeoObjects> getRelatedObjects(boolean[][] matrix) {
		List<GeoObjects> result = new ArrayList<GeoObjects>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j]) {
					int k = j + 1;
					for (; k < matrix[i].length; k++) {
						if (!matrix[i][k]) {
							break;
						}
					}
					result.add(new VGeoLine(j, i, k - j));
					j = k;
				}
			}
		}
		return result;
	}
}
