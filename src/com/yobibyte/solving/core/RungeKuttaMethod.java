package com.yobibyte.solving.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RungeKuttaMethod implements DiffEquSolver {

	public static final Double STEP = Constants.RUNGE_STEP;

	// @Override
	// // first element is velocity, second is position
	// public void solve(Body body, List<Body> bodies) {
	// calcCoeff(body, bodies);
	// Point2D[] velCoefs = body.velCoefs;
	// Point2D[] posCoefs = body.posCoefs;
	// // TODO simplify, may be a bug
	// body.velocity.setLocation(body.velocity.getX() + (velCoefs[0].getX() + 2 *
	// velCoefs[1].getX() + 2 * velCoefs[2].getX() + velCoefs[3].getX()) * STEP /
	// 6, body.velocity.getY() + (velCoefs[0].getY() + 2 * velCoefs[1].getY() + 2
	// * velCoefs[2].getY() + velCoefs[3].getY()) * STEP / 6);
	// body.coord.setLocation(body.coord.getX() + (posCoefs[0].getX() + 2 *
	// posCoefs[1].getX() + 2 * posCoefs[2].getX() + posCoefs[3].getX()) * STEP /
	// 6, body.coord.getY() + (posCoefs[0].getY() + 2 * posCoefs[1].getY() + 2 *
	// posCoefs[2].getY() + posCoefs[3].getY()) * STEP / 6);
	// }

	// @Override
	// public void solve(Body body, List<Body> bodies) {
	// Point2D coords = body.coord;
	// Point2D velocity = body.velocity;
	//
	// Point2D accel = new Point2D.Double();
	// Point2D varToCalc = new Point2D.Double();
	//
	// Point2D k1v, k2v, k3v, k4v, k1p, k2p, k3p, k4p;
	// k1v = k2v = k3v = k4v = k1p = k2p = k3p = k4p = new Point2D.Double();
	//
	// varToCalc = coords;
	// accel.setLocation(getAccel(bodies, body, varToCalc));
	// k1v.setLocation(accel.getX() * coords.getX(), accel.getY() *
	// coords.getY());
	// k1p.setLocation(velocity);
	//
	// varToCalc.setLocation((coords.getX() + k1p.getX() * STEP / 2),
	// coords.getY() + k1p.getY() * STEP / 2);
	// k2v.setLocation(getAccel(bodies, body, varToCalc));
	// k2p.setLocation(velocity.getX() * k1v.getX() * STEP / 2, velocity.getY() *
	// k1v.getY() * STEP / 2);
	//
	// varToCalc.setLocation(coords.getX() + k2p.getX() * STEP / 2, coords.getY()
	// + k2p.getY() * STEP / 2);
	// k3v.setLocation(getAccel(bodies, body, varToCalc));
	// k3p.setLocation(velocity.getX() * k2v.getX() * STEP / 2, velocity.getY() *
	// k2v.getY() * STEP / 2);
	//
	// varToCalc.setLocation(coords.getX() + k3p.getX() * STEP, coords.getY() +
	// k3p.getY() * STEP);
	// k4v.setLocation(getAccel(bodies, body, varToCalc));
	// k4p.setLocation(velocity.getX() * k3v.getX() * STEP, velocity.getY() *
	// k3v.getY() * STEP);
	//
	// body.velocity.setLocation(body.velocity.getX() + (k1v.getX() + 2 *
	// k2v.getX() + 2 * k3v.getX() + k4v.getX()) * STEP / 6, body.velocity.getY()
	// + (k1v.getY() + 2 * k2v.getY() + 2 * k3v.getY() + k4v.getY()) * STEP / 6);
	// body.coord.setLocation(body.coord.getX() + (k1p.getX() + 2 * k2p.getX() + 2
	// * k3p.getX() + k4p.getX()) * STEP / 6, body.coord.getY() + (k1p.getY() + 2
	// * k2p.getY() + 2 * k3p.getY() + k4p.getY()) * STEP / 6);
	//
	// // body.velCoefs = new Point2D[] { k1v, k2v, k3v, k4v };
	// // body.posCoefs = new Point2D[] { k1p, k2p, k3p, k4p };
	// }

	// public Point2D getAccel(List<Body> bodies, Body currBody, Point2D
	// varToCalc) {
	//
	// Iterator<Body> it = bodies.iterator();
	//
	// double accelX = 0d;
	// double accelY = 0d;
	//
	// while (it.hasNext()) {
	// Body body = it.next();
	// if (body != currBody) {
	// // System.out.println(Math.pow(Math.abs(body.coord.getX() -
	// varToCalc.getX()), 3));
	// accelX = accelX + body.mass * (body.coord.getX() - varToCalc.getX()) /
	// Math.pow(Math.abs(body.coord.getX() - varToCalc.getX()), 3);
	// accelY = accelY + body.mass * (body.coord.getY() - varToCalc.getY()) /
	// Math.pow(Math.abs(body.coord.getY() - varToCalc.getY()), 3);
	// }
	//
	// }
	// accelX *= Constants.GRAVITY;
	// accelY *= Constants.GRAVITY;
	//
	//
	// return new Point2D.Double(accelX, accelY);
	// }

	@Override
	public void solve(Body body, List<Body> bodies) {
		double radius = body.radius;
		double velocity = body._velocity;
		double accel = 0d;
		double varToCalc = 0d;

		double k1v, k2v, k3v, k4v, k1p, k2p, k3p, k4p;
		k1v = k2v = k3v = k4v = k1p = k2p = k3p = k4p = 0d;

		varToCalc = radius;
		accel = getAccel(bodies, body, varToCalc);
		k1v = accel;
		k1p = velocity;

		varToCalc = radius + k1p*STEP/2;
		k2v = getAccel(bodies, body, varToCalc); 
		k2p = velocity*k1v*STEP/2;
		
		varToCalc = radius + k2p*STEP/2;
		k3v = getAccel(bodies, body, varToCalc); 
		k3p = velocity*k2v*STEP/2;
		
		varToCalc = radius + k3p*STEP;
		k4v = getAccel(bodies, body, varToCalc); 
		k4p = velocity*k3v*STEP;
		
		body._velocity += (k1v+2*k2v+2*k3v+k4v)*STEP/6;
		body.radius += (k1p+2*k2p+2*k3p+k4p)*STEP/6;
	}

	public double getAccel(List<Body> bodies, Body currBody, double varToCalc) {

		Iterator<Body> it = bodies.iterator();

		double accel = 0d;

		while (it.hasNext()) {
			Body body = it.next();
			if (body != currBody) {
				accel += body.mass * (body.radius - varToCalc) / Math.pow(Math.abs(body.radius - varToCalc), 3);
			}

		}
		accel *= Constants.GRAVITY;

		return accel;
	}

}
