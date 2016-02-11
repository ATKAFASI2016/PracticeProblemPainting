package atkafasi.solutions.sortedobject;

import java.util.ArrayList;
import java.util.List;

import atkafasi.model.geoobjects.DirtySquareException;
import atkafasi.model.geoobjects.GeoObjects;
import atkafasi.model.geoobjects.GeoSquare;
import atkafasi.model.instructions.Erase;

public class SquareFinder implements ObjectPatternSearcher {

	private boolean tryBigger;
	private List<Erase> erasePoints;

	@Override
	public List<GeoObjects> getRelatedObjects(boolean[][] matrix) {
		List<GeoObjects> result = new ArrayList<GeoObjects>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				tryBigger = true;
				for (int s = 0; tryBigger && (((2 * s) + i) < matrix.length) && (((2 * s) + j) < matrix[i].length); s++) {
					erasePoints = new ArrayList<Erase>();
					for (int k = 0; k < (2 * s + 1); k++) {
						for (int l = 0; l < (2 * s + 1); l++) {
							if (!matrix[i + k][j + l]) {
								erasePoints.add(new Erase(j + l, i + k));
							}
						}
					}
					try {
						GeoSquare square = new GeoSquare(j, i, s, erasePoints);
						result.add(square);
					} catch (DirtySquareException e) {
						// tryBigger = false;
					}

				}
			}
		}
		return result;
	}
}
