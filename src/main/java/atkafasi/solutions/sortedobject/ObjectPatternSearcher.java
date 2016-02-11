package atkafasi.solutions.sortedobject;

import java.util.List;

import atkafasi.model.geoobjects.GeoObjects;

public interface ObjectPatternSearcher {

	public List<GeoObjects> getRelatedObjects(boolean [][] matrix);
}
