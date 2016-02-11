package atkafasi.model.geoobjects;

import java.util.List;

import atkafasi.model.instructions.Erase;

public class GeoSquare extends GeoObjects {

	private static final double squareDirtinessTrashold = 0.23;

	private int startX;
	private int startY;
	private int semiradius;
	private List<Erase> erases;

	public GeoSquare(int startX, int startY, int s, List<Erase> erasePoints) throws DirtySquareException {
		super();
		this.startX = startX;
		this.startY = startY;
		this.semiradius = s;
		this.erases = erasePoints;
		double R = (s * 2) + 1;
		double dirtyness = (erasePoints.size() / (R * R));
		if (dirtyness >= squareDirtinessTrashold) {
			throw (new DirtySquareException());
		}
		setValue(((R * R) - erasePoints.size()) / (1 + erasePoints.size()));

	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getSemiradius() {
		return semiradius;
	}

	public void setSemiradius(int semiradius) {
		this.semiradius = semiradius;
	}

	public List<Erase> getErases() {
		return erases;
	}

	public void setErases(List<Erase> erases) {
		this.erases = erases;
	}

}
