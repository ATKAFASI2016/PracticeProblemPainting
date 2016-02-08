package atkafasi.model.instructions;

public class HLine extends Line {

	public HLine(int x, int y1, int y2) {
		super(x, y1, x, y2);
		if(y1>y2){
			setR1(y2);
			setR2(y1);
		}
	}

}
