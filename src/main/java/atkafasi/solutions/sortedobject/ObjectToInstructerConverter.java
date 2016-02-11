package atkafasi.solutions.sortedobject;

import atkafasi.model.geoobjects.GeoObjects;
import atkafasi.model.geoobjects.GeoSquare;
import atkafasi.model.geoobjects.HGeoLine;
import atkafasi.model.geoobjects.VGeoLine;
import atkafasi.model.instructions.*;

import java.util.ArrayList;
import java.util.List;

public class ObjectToInstructerConverter {

	public static List<Instructions> convert(GeoObjects geoObjects) {
		List<Instructions> result = new ArrayList<Instructions>();
		if (geoObjects instanceof HGeoLine) {
			HGeoLine hLine = (HGeoLine) geoObjects;
			result.add(new HLine(
					hLine.getStartY(),
					hLine.getStartX(),
					(hLine.getStartX() + hLine.getLength()/* - 1*/)
			));
		} else if (geoObjects instanceof VGeoLine) {
			VGeoLine vLine = (VGeoLine) geoObjects;
			result.add(new VLine(
					vLine.getStartY(),
					(vLine.getStartY() + vLine.getLength() /*- 1*/),
					vLine.getStartX()
			));
		} else if (geoObjects instanceof GeoSquare) {
			GeoSquare geoSquare = (GeoSquare) geoObjects;
			result.add(new Square(geoSquare.getStartY() + geoSquare.getSemiradius(), geoSquare.getStartX()
					+ geoSquare.getSemiradius(), geoSquare.getSemiradius()));
			for (Erase erase : geoSquare.getErases()) {
				result.add(erase);
			}
		}
		return result;
	}
}
