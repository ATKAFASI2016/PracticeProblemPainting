package atkafasi.solutions;

import java.util.List;

import atkafasi.model.instructions.Instructions;

public interface Solution {
	public List< Instructions> solve(boolean[][] data);
}
