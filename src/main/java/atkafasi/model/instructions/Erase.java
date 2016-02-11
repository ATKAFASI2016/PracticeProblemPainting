package atkafasi.model.instructions;

public class Erase extends Instructions {

    private static String commandName = "ERASE_CELL";

    private int r;

    private int c;

    public Erase(int row, int col) {

	this.r = row;
	this.c = col;
    }

    public int getR() {

	return r;
    }

    public int getC() {

	return c;
    }

    @Override public String toInstructionString() {

	return commandName + " " + r + " " + c;
    }

}
