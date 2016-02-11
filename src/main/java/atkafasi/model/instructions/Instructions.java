package atkafasi.model.instructions;

import java.util.Comparator;

public abstract class Instructions implements Comparable<Instructions> {

    float instruction_value;

    public abstract String toInstructionString();

    public float getInstruction_value() {

	return instruction_value;
    }

    public void setInstruction_value(float instruction_value) {

	this.instruction_value = instruction_value;
    }

    @Override public int compareTo(Instructions o) {

	return Float.compare(o.getInstruction_value(), instruction_value);

    }
}
