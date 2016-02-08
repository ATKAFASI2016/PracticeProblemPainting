package atkafasi.model.instructions;

public class VLine extends Line {

	
	public VLine(int x1, int x2, int y) {
		super(x1, y, x2, y);
		if(x1>x2){
			setC1(x2);
			setC2(x1);
		}
	}

}
