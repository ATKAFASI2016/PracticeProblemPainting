package atkafasi.solutions;

import atkafasi.model.enums.SolutionStrategy;

public class SolutionFactory {
	/**
	 * Every new Solution Implementation should be added to
	 * SolutionStrategy(enum) and SolutionFactory
	 * 
	 * @param strategy
	 * @return
	 * @throws Exception
	 */
	public static Solution getSolution(SolutionStrategy strategy) throws Exception {
		switch (strategy) {
		case HorizontalLines:
			return new HorizontalLines();
		case VerticalLines:
			return new VerticalLines();
		case SinglePoint:
			return new PaintEachPoint();
		default:
			throw new NullPointerException();
		}

	}
}
