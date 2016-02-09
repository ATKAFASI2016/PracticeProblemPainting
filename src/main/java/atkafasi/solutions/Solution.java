package atkafasi.solutions;

import java.util.List;

import atkafasi.model.data.DataPart;
import atkafasi.model.instructions.Instructions;

public interface Solution {

    List<Instructions> solve(DataPart dataPart);

}
