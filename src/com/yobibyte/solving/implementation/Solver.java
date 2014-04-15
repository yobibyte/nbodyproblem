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

	public void solve() {
		DiffEquSolver solver = new RungeKuttaMethod();
		Integer stepsNum = Constants.NUM_OF_STEPS;

		Random rand = new Random();
		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
			bodies.add(new Body(rand.nextDouble(), rand.nextDouble()));
		}

		for (int i = 0; i < stepsNum; i++) {
			System.out.println("STEP " + (i+1) + " ----------------------");
			// Point2D currPt = solver.solve();
			Iterator<Body> it = bodies.iterator();
			int ctr = 0;
			while (it.hasNext()) {
				//print coords
				Point2D currCoord = it.next().coord;
				System.out.println("* " + (ctr+1) + " BODY:  X = " + currCoord.getX() + " ; Y = " + currCoord.getY() + " ;");
				ctr++;
			}

			System.out.println("---------------------------------");
		}
	}

}
