package atkafasi.model.instructions;

public class Square extends Instructions {

    private static String commandName = "PAINT_SQUARE";
    private int row;
    private int column;
    private int s;

    public Square(int row, int column, int radius) {
        this.row = row;
        this.column = column;
        this.s = radius;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getS() {
        return s;
    }

    @Override
    public String toInstructionString() {

        return commandName + " " + row + " " + column + " " + s;
    }

}
