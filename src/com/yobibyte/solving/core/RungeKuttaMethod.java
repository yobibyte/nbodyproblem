package com.yobibyte.solving.core;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Iterator;
import java.util.List;

public class RungeKuttaMethod implements DiffEquSolver {

	public static final java.lang.Double STEP = Constants.RUNGE_STEP;

	@Override
	public void solve(Body body, List<Body> bodies) {
		Point2D coords = body.coord;
		Point2D velocity = body.velocity;

		Point2D varToCalc = new Point2D.Double();
		//
		Point2D k1v, k2v, k3v, k4v, k1p, k2p, k3p, k4p;
		k1v = k2v = k3v = k4v = k1p = k2p = k3p = k4p = new Point2D.Double();

		k1v = getAccel(bodies, body, coords);
		k1p = velocity;

		varToCalc.setLocation(coords.getX() + k1p.getX() * STEP / 2, coords.getY() + k1p.getY() * STEP / 2);
		k2v = getAccel(bodies, body, varToCalc);
		k2p.setLocation(velocity.getX() * k1v.getX() * STEP / 2, velocity.getY() * k1v.getY() * STEP / 2);

		varToCalc.setLocation((coords.getX() + k2p.getX() * STEP / 2), coords.getY() + k2p.getY() * STEP / 2);
		k3v = getAccel(bodies, body, varToCalc);
		k3p.setLocation(velocity.getX() * k2v.getX() * STEP / 2, velocity.getY() * k2v.getY() * STEP / 2);

		varToCalc.setLocation(coords.getX() + k3p.getX() * STEP, coords.getY() + k3p.getY() * STEP);
		k4v = getAccel(bodies, body, varToCalc);
		k4p.setLocation(velocity.getX() * k3v.getX() * STEP, velocity.getY() * k3v.getY() * STEP);

		body.velocity.setLocation(velocity.getX() + (k1v.getX() + 2 * k2v.getX() + 2 * k3v.getX() + k4v.getX()) * STEP / 6, velocity.getY() + (k1v.getY() + 2 * k2v.getY() + 2 * k3v.getY() + k4v.getY()) * STEP / 6);
		body.coord.setLocation(coords.getX() + (k1p.getX() + 2 * k2p.getX() + 2 * k3p.getX() + k4p.getX()) * STEP / 6, coords.getY() + (k1p.getY() + 2 * k2p.getY() + 2 * k3p.getY() + k4p.getY()) * STEP / 6);

		// alxdon solver
		// Iterator<Body> it = bodies.iterator();
		//
		// double fx = 0d;
		// double fy = 0d;
		//
		// if (it.hasNext()) {
		// Body currBody = it.next();
		// if (currBody != body) {
		// double deltaX = currBody.coord.getX() - coords.getX();
		// double deltaY = currBody.coord.getY() - coords.getY();
		// double deltaR = deltaX * deltaX + deltaY * deltaY;
		// double f = Constants.GRAVITY * body.mass * currBody.mass / deltaR;
		// deltaR = Math.sqrt(deltaR);
		// fx += f * deltaX / deltaR;
		// fy += f * deltaY / deltaR;
		// }
		// body.velocity.setLocation(velocity.getX() + STEP * fx / body.mass,
		// velocity.getY() + STEP * fy / body.mass);
		// body.coord.setLocation(coords.getX() + velocity.getX() * STEP,
		// coords.getY() + velocity.getY() * STEP);
		// }

	}

	public Point2D getAccel(List<Body> bodies, Body body, Point2D varToCalc) {
		Iterator<Body> it = bodies.iterator();

		double ax = 0d;
		double ay = 0d;

		if (it.hasNext()) {
			Body currBody = it.next();
			if (currBody != body) {
				double deltaX = currBody.coord.getX() - varToCalc.getX();
				double deltaY = currBody.coord.getY() - varToCalc.getY();
				double deltaR = deltaX * deltaX + deltaY * deltaY;
				double a = Constants.GRAVITY * currBody.mass / deltaR;
				deltaR = Math.sqrt(deltaR);
				ax += a * deltaX / deltaR;
				ay += a * deltaY / deltaR;
			}
		}
		return new Point2D.Double(ax, ay);
	}

}
