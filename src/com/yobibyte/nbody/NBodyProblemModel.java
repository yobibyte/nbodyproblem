package com.yobibyte.nbody;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yobibyte.solving.core.Background;
import com.yobibyte.solving.core.Body;
import com.yobibyte.solving.core.Constants;
import com.yobibyte.solving.core.RungeKuttaMethod;
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
		// Random rand = new Random();
		// for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
		// bodies.add(new Body(rand.nextDouble() * Constants.DISPLAY_WIDTH / 2,
		// rand.nextDouble() * Constants.DISPLAY_HEIGHT / 2));
		// }
		//Body sun = new Body(new Point2D.Float(100, 100), new Point2D.Float(0, 0), new Point2D.Float(0, 0));
//		Body sun = new Body(0, 30, 100);
//		sun.mass = 10000;
//		bodies.add(sun);
		//Body earth = new Body(new Point2D.Float(300, 300), new Point2D.Float(10, 10), new Point2D.Float(-100, -100));
		Body earth = new Body(-10, 20, 100);
		Body moon = new Body(20, 30, 200);
		earth.mass = 100;
		moon.mass = 10;
		bodies.add(earth);
		bodies.add(moon);
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
