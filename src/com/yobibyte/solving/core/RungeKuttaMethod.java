package com.yobibyte.solving.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RungeKuttaMethod implements DiffEquSolver {

	public static final Double STEP = Constants.RUNGE_STEP;

	@Override
	// first element is velocity, second is position
	public void solve(Body body, List<Body> bodies) {
		calcCoeff(body, bodies);
		Point2D[] velCoefs = body.velCoefs;
		Point2D[] posCoefs = body.posCoefs;
		// TODO simplify, may be a bug
		body.velocity.setLocation(body.velocity.getX() + (velCoefs[0].getX() + 2 * velCoefs[1].getX() + 2 * velCoefs[2].getX() + velCoefs[3].getX()) * STEP / 6, body.velocity.getY() + (velCoefs[0].getY() + (2 * velCoefs[1].getY() + 2 * velCoefs[2].getY() + velCoefs[3].getY()) * STEP / 6));
		body.coord.setLocation(body.coord.getX() + (posCoefs[0].getX() + 2 * posCoefs[1].getX() + 2 * posCoefs[2].getX() + posCoefs[3].getX()) * STEP / 6, body.coord.getY() + (posCoefs[0].getY() + (2 * posCoefs[1].getY() + 2 * posCoefs[2].getY() + posCoefs[3].getY()) * STEP / 6));
	}

	private void calcCoeff(Body body, List<Body> bodies) {
		Point2D coords = body.coord;
		Point2D velocity = body.velocity;

		Point2D accel = new Point2D.Double();
		Point2D varToCalc = new Point2D.Double();

		Point2D k1v, k2v, k3v, k4v, k1p, k2p, k3p, k4p;
		k1v = k2v = k3v = k4v = k1p = k2p = k3p = k4p = new Point2D.Double();

		varToCalc = coords;
		accel.setLocation(getAccel(bodies, body, varToCalc));
		k1v.setLocation(accel.getX() * coords.getX(), accel.getY() * coords.getY());
		k1p.setLocation(velocity);

		varToCalc.setLocation((coords.getX() + k1p.getX() * STEP / 2), coords.getY() + k1p.getY() * STEP / 2);
		k2v.setLocation(getAccel(bodies, body, varToCalc));
		k2p.setLocation(velocity.getX() * k1v.getX() * STEP / 2, velocity.getY() * k1v.getY() * STEP / 2);

		
		varToCalc.setLocation(coords.getX() + k2p.getX() * STEP / 2, coords.getY() + k2p.getY() * STEP / 2);
		k3v.setLocation(getAccel(bodies, body, varToCalc));
		k3p.setLocation(velocity.getX() * k2v.getX() * STEP / 2, velocity.getY() * k2v.getY() * STEP / 2);

		varToCalc.setLocation(coords.getX() + k3p.getX() * STEP, coords.getY() + k3p.getY() * STEP);
		k4v.setLocation(getAccel(bodies, body, varToCalc));
		k4p.setLocation(velocity.getX() * k2v.getX() * STEP, velocity.getY() * k2v.getY() * STEP);

		body.velCoefs = new Point2D[] { k1v, k2v, k3v, k4v };
		body.posCoefs = new Point2D[] { k1p, k2p, k3p, k4p };
	}

	public Point2D getAccel(List<Body> bodies, Body currBody, Point2D varToCalc) {

		// TODO may be a bug due to references and so on
		List<Body> bodiesToCalc = new ArrayList<Body>();
		Iterator<Body> it = bodies.iterator();

		while (it.hasNext()) {
			Body body = it.next();
			// TODO may be a bug due to != of objects
			if (body != currBody) {
				bodiesToCalc.add(body);
			}
		}

		it = bodiesToCalc.iterator();
		double accelX = 0d;
		double accelY = 0d;

		while (it.hasNext()) {
			Body body = it.next();
			accelX += body.mass * (body.coord.getX() - varToCalc.getX()) / Math.pow(Math.abs(body.coord.getX() - varToCalc.getX()), 3);
			accelY += body.mass * (body.coord.getY() - varToCalc.getY()) / Math.pow(Math.abs(body.coord.getY() - varToCalc.getY()), 3);
		}
		accelX *= Constants.GRAVITY;
		accelY *= Constants.GRAVITY;

		return new Point2D.Double(accelX, accelY);
	}

}
