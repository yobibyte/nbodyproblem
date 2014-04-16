package com.yobibyte.nbody;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yobibyte.solving.core.Background;
import com.yobibyte.solving.core.Body;
import com.yobibyte.solving.core.Constants;
import com.yobibyte.solving.implementation.Solver;

public class NBodyProblemModel {
	public static Background bg;
	public static Background bg1;
	public static List<Body> bodies = new ArrayList<Body>(Constants.NUM_OF_BODIES);
	Solver solver;

	public NBodyProblemModel() {
		// player = new Player();
		bg = new Background(0f, 0f);
		bg1 = new Background(0, -400);
		Random rand = new Random();
//		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
//			Body newBody = new Body(new Point2D.Double(rand.nextDouble() * 150, rand.nextDouble() * 150), new Point2D.Double(rand.nextDouble() / 5, rand.nextDouble() / 5));
//			newBody.mass = rand.nextFloat() * 10000;
//			bodies.add(newBody);
//		}

		 Body earth = new Body(new Point2D.Double(-10, 0), new Point2D.Double(0,
		 -0.1));
		 Body moon = new Body(new Point2D.Double(10, 0), new Point2D.Double(0,
		 0.1));
		 earth.mass = 500d;
		 moon.mass = 500d;
		 bodies.add(earth);
		 bodies.add(moon);
		
//		
//		 Body earth = new Body(new Point2D.Double(-50, 50), new Point2D.Double(0.5,
//		 -0.1));
//		 Body moon = new Body(new Point2D.Double(50, 0), new Point2D.Double(0,
//		 0.1));
//		 earth.mass = 1500d;
//		 moon.mass = 1500d;
//		 bodies.add(earth);
//		 bodies.add(moon);

		solver = new Solver(bodies);
	}

	public void calculations(int stepNum) {
		solver.makeAStep(stepNum);
	}

	public void render() {
		bg.render();
		bg1.render();
		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
			bodies.get(i).setRenderCoords();
			bodies.get(i).render();
		}
	}

	public void dispose() {

	}
}
