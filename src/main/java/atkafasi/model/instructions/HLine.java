package atkafasi.model.instructions;

public class HLine extends Line {

	public HLine(int y, int x1, int x2) {
		super(y, x1, y, x2);
		if(x1>x2){
			setR1(x2);
			setR2(x1);
		}
	}

}
