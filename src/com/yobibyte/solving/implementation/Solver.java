package com.yobibyte.solving.implementation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.yobibyte.solving.core.Body;
import com.yobibyte.solving.core.Constants;
import com.yobibyte.solving.core.DiffEquSolver;
import com.yobibyte.solving.core.RungeKuttaMethod;

public class Solver {

	List<Body> bodies = new ArrayList<Body>(Constants.NUM_OF_BODIES);
	Integer stepsNum = Constants.NUM_OF_STEPS;
	DiffEquSolver solver = new RungeKuttaMethod();;

	
	public Solver(List<Body> bodies) {
		this.bodies = bodies;
	}

	public void makeAStep(int stepNum) {
	//	System.out.println("STEP " + (stepNum + 1) + " ----------------------");
		Iterator<Body> it = bodies.iterator();
		int ctr = 0;
		while (it.hasNext()) {
			Body currBody = it.next();
			solver.solve(currBody, bodies);
//			Point2D currCoord = currBody.coord;
			//System.out.println("* " + (ctr + 1) + " BODY:  X = " + currCoord.getX() + " ; Y = " + currCoord.getY() + " ;");
	//		System.out.println("* " + (ctr + 1) + " BODY:  X = " + currBody.x + " ; Y = " + currBody.y + " ;");
			ctr++;
		}
	}

}
