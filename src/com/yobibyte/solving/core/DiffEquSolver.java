package com.yobibyte.solving.core;

import java.util.List;

public interface DiffEquSolver {
	public void makeAStep(Body body, List<Body> bodies);
}
