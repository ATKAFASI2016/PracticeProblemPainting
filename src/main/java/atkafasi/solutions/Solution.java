package atkafasi.solutions;

import java.util.List;

import atkafasi.model.data.ParseResultPojo;
import atkafasi.model.instructions.Instructions;

public interface Solution {

	List<Instructions> solve(ParseResultPojo frameData);

}
