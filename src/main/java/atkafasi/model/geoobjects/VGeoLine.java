package atkafasi.model.geoobjects;

public class VGeoLine extends GeoObjects {
	private int startX;
	private int startY;
	private int length;

	public VGeoLine(int startX, int startY, int length) {
		super();
		this.startX = startX;
		this.startY = startY;
		this.length = length;
		setValue(length);
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
