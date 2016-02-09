package atkafasi.model.instructions;

public class Erase implements Instructions{

	private static String commandName = "ERASE_CELL";
	private int r;
	private int c;
	
	public Erase(int x,int y) {
		this.r=y;
		this.c=x;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

	@Override
	public String toInstructionString() {
		return commandName+" "+r+" "+c;
	}

}
