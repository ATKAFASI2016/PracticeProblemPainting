package atkafasi.solutions.sortedobject;

import java.util.ArrayList;
import java.util.List;

import atkafasi.model.geoobjects.GeoObjects;
import atkafasi.model.geoobjects.HGeoLine;

public class HLineFinder implements ObjectPatternSearcher {

	@Override
	public List<GeoObjects> getRelatedObjects(boolean[][] matrix) {
		List<GeoObjects> result = new ArrayList<GeoObjects>();
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[j][i]) {
					int k = j + 1;
					for (; k < matrix.length; k++) {
						if (!matrix[k][i]) {
							break;
						}
					}
					result.add(new HGeoLine(i, j, k - j));
					j = k;
				}
			}
		}
		return result;
	}

}
