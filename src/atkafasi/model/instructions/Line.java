package atkafasi.model.instructions;

public class Line implements Instructions {
	private static String commandName = "PAINT_LINE";
	private int r1;
	private int c1;
	private int r2;
	private int c2;
	
	public Line(int x1,int y1,int x2,int y2) {
		 this.r1=y1;
		 this.c1=x1;
		 this.r2=y2;
		 this.c2=x2;
	}
	
	@Override
	public String toInstructionString() {
		return commandName+" "+r1+" "+c1+" "+r2+" "+c2;
	}

	protected void setR1(int r1) {
		this.r1 = r1;
	}

	protected void setC1(int c1) {
		this.c1 = c1;
	}

	protected void setR2(int r2) {
		this.r2 = r2;
	}

	protected void setC2(int c2) {
		this.c2 = c2;
	}

}
