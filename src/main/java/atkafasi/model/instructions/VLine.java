package atkafasi.model.instructions;

public class VLine extends Line {

	
	public VLine(int y1, int y2, int x) {
		super(y1, x, y2, x);
		if(y1>y2){
			setC1(y2);
			setC2(y1);
		}
	}

}
