package com.yobibyte.nbody;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
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
//		Random rand = new Random();
//		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
//			Body newBody = new Body(new Point2D.Double(rand.nextDouble() * 500, rand.nextDouble() * 500), new Point2D.Double(rand.nextDouble() / 3, rand.nextDouble() / 3), rand.nextDouble() * 500);
//			bodies.add(newBody);
//		}

		Body earth = new Body(new Point2D.Double(-10, 0), new Point2D.Double(0, -0.1), 500d);
		Body moon = new Body(new Point2D.Double(10, 0), new Point2D.Double(0, 0.1), 500d);

		bodies.add(earth);
		bodies.add(moon);

		// Body earth = new Body(new Point2D.Double(100, 100), new Point2D.Double(0,
		// 0), 1500d);
		// Body moon = new Body(new Point2D.Double(200, 200), new Point2D.Double(0,
		// 0), 500d);
		// bodies.add(earth);
		// bodies.add(moon);

		solver = new Solver(bodies);
	}

	public void calcCollisions() {
		Iterator<Body> it = bodies.iterator();
		List<Body> newBodies = new ArrayList<Body>();
		while (it.hasNext()) {
			Body bodyToCheck = it.next();
			Iterator<Body> innerIt = bodies.iterator();
			while (innerIt.hasNext()) {
				Body currBody = it.next();
				if (bodyToCheck != currBody) {
					double deltaX = bodyToCheck.coord.getX() - currBody.coord.getX();
					double deltaY = bodyToCheck.coord.getY() - currBody.coord.getY();
					double r = deltaX * deltaX + deltaY * deltaY;
					if (r < Constants.EPS) {
						Point2D newCoords = new Point2D.Double((currBody.coord.getX() + bodyToCheck.coord.getX()) / 2, (currBody.coord.getY() + bodyToCheck.coord.getY()) / 2);
						Point2D newVelocity = new Point2D.Double((currBody.velocity.getX() + bodyToCheck.velocity.getX()) / 2, (currBody.velocity.getY() + bodyToCheck.velocity.getY()) / 2);
						double newMass = currBody.mass + bodyToCheck.mass;
						Body newBody = new Body(newCoords, newVelocity, newMass);
						newBodies.add(newBody);
						innerIt.remove();
						it.remove();
					}
				}
			}
		}
		bodies.addAll(newBodies);
		solver.updateBodies(bodies);
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
