package atkafasi.model.geoobjects;

public class GeoObjects implements Comparable<GeoObjects> {

	private double value;
	private boolean print;

	protected void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public int compareTo(GeoObjects o) {
		return Double.compare(o.getValue(), this.getValue());
	}

	public boolean isPrint() {
		return print;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

}
