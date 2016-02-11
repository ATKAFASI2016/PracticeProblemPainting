package atkafasi.solutions.sortedobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.geoobjects.GeoObjects;
import atkafasi.model.geoobjects.HGeoLine;
import atkafasi.model.geoobjects.GeoSquare;
import atkafasi.model.geoobjects.VGeoLine;
import atkafasi.model.instructions.Instructions;
import atkafasi.solutions.Solution;

public class SortedObjectsSolutionImpl implements Solution {

	long mBase, vFinished, hFinished, sFinished, elemination, convertion;

	@Override
	public List<Instructions> solve(ParseResultPojo frameData) {
		ObjectPatternSearcher vertLineFinder = new VLineFinder();
		ObjectPatternSearcher horiLineFinder = new HLineFinder();
		ObjectPatternSearcher squareFinder = new SquareFinder();

		mBase = System.currentTimeMillis();
		yMax = frameData.getRowNumber();
		xMax = frameData.getColumnNumber();

		List<GeoObjects> vLines = vertLineFinder.getRelatedObjects(frameData.getAsciiData());

		vFinished = System.currentTimeMillis();

		List<GeoObjects> hLines = horiLineFinder.getRelatedObjects(frameData.getAsciiData());

		hFinished = System.currentTimeMillis();
		List<GeoObjects> squares = squareFinder.getRelatedObjects(frameData.getAsciiData());

		sFinished = System.currentTimeMillis();

		List<GeoObjects> allGeoObjs = new ArrayList<GeoObjects>();
		for (GeoObjects geoObjects : vLines) {
			allGeoObjs.add(geoObjects);
		}
		for (GeoObjects geoObjects : hLines) {
			allGeoObjs.add(geoObjects);
		}
		for (GeoObjects geoObjects : squares) {
			allGeoObjs.add(geoObjects);
		}
		Collections.sort(allGeoObjs);

		demoPaintCache = new boolean[yMax][xMax];
		for (int i = 0; i < yMax; i++) {
			for (int j = 0; j < xMax; j++) {
				demoPaintCache[i][j] = false;
			}
		}
		List<GeoObjects> printedGeoObjs = new ArrayList<GeoObjects>();
		for (GeoObjects geoObjects : allGeoObjs) {

			boolean paintable = isPaintable(geoObjects);
			geoObjects.setPrint(paintable);
			if (paintable) {
				printedGeoObjs.add(geoObjects);
			}

		}
		elemination = System.currentTimeMillis();
		List<Instructions> result = new ArrayList<Instructions>();
		for (GeoObjects geoObj : printedGeoObjs) {
			for (Instructions ins : ObjectToInstructerConverter.convert(geoObj)) {
				result.add(ins);
			}
		}

		convertion = System.currentTimeMillis();
		System.err.println("***SortedObjectsSolutionImpl***");
		System.err.println("squares:" + squares.size());
		System.err.println("vLines:" + vLines.size());
		System.err.println("hLines:" + hLines.size());
		System.err.println("------------------------------");
		System.err.println("----TIMES----  (Start : " + mBase + ")");
		System.err.println("- Verticals :\t" + (vFinished - mBase));
		System.err.println("- Horizontal :\t" + (hFinished - vFinished));
		System.err.println("- Squares :\t" + (sFinished - hFinished));
		System.err.println("- Elemination :\t" + (elemination - sFinished));
		System.err.println("- Convertion :\t" + (convertion - elemination));
		System.err.println("--- End Of the Story -----");
		System.err.println("------------------------------");
		return result;
	}

	// AFTER THIS LINE
	// CODE: CHECKS IF THE GEO OBJECT
	// NEED TO SET PRINTED? OR NOT?
	boolean[][] demoPaintCache;
	int yMax;
	int xMax;

	private boolean isPaintable(GeoObjects geoObjects) {
		if (geoObjects instanceof HGeoLine) {
			HGeoLine hLine = (HGeoLine) geoObjects;
			return isPaintableHLine(hLine);
		} else if (geoObjects instanceof VGeoLine) {
			VGeoLine vLine = (VGeoLine) geoObjects;
			return isPaintableVLine(vLine);
		} else if (geoObjects instanceof GeoSquare) {
			GeoSquare square = (GeoSquare) geoObjects;
			return isPaintableSquare(square);
		} else {
			return false;
		}

	}

	private boolean isPaintableSquare(GeoSquare square) {
		boolean result = false;
		int r = square.getSemiradius();
		int lenght = (2 * r) + 1;
		for (int i = 0; i < lenght; i++) {
			for (int j = 0; j < lenght; j++) {
				if (!demoPaintCache[square.getStartY() + i][square.getStartX() + j]) {
					demoPaintCache[square.getStartY() + i][square.getStartX() + j] = true;
					result = true;
				}
			}
		}
		return result;
	}

	private boolean isPaintableVLine(VGeoLine vLine) {
		boolean result = false;
		for (int i = 0; i < vLine.getLength(); i++) {
			if (!demoPaintCache[vLine.getStartY()][vLine.getStartX() + i]) {
				demoPaintCache[vLine.getStartY()][vLine.getStartX() + i] = true;
				result = true;
			}
		}
		return result;
	}

	private boolean isPaintableHLine(HGeoLine hLine) {
		boolean result = false;
		for (int i = 0; i < hLine.getLength(); i++) {
			if (!demoPaintCache[hLine.getStartY() + i][hLine.getStartX()]) {
				demoPaintCache[hLine.getStartY() + i][hLine.getStartX()] = true;
				result = true;
			}
		}
		return result;
	}
}
