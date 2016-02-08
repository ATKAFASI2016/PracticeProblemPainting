package atkafasi.model.instructions;

public class Square implements Instructions {

	private static String commandName = "PAINT_SQUARE";
	private int r;
	private int c;
	private int s;
	
	public  Square(int xCenter,int yCenter,int radius) {
		this.r=yCenter;
		this.c=xCenter;
		this.s=radius;
	}
	
	@Override
	public String toInstructionString() {
	
		return commandName+" "+r+" "+c+" "+s;
	}

}
