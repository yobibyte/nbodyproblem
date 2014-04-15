package com.yobibyte.solving.core;

import java.awt.geom.Point2D;

public class RungeKuttaMethod implements DiffEquSolver {

	public static final Double STEP = Constants.RUNGE_STEP;

	@Override
	// first element is velocity, second is position
	public void solve(Body body) {
		calcCoeff(body);
		Point2D[] velCoefs = body.velCoefs;
		Point2D[] posCoefs = body.posCoefs;
		//TODO simplify, may be a bug
		body.velocity.setLocation(body.velocity.getX() + (velCoefs[0].getX()+2*velCoefs[1].getX()+2*velCoefs[2].getX()+velCoefs[3].getX())*STEP/6, body.velocity.getY() + (velCoefs[0].getY()+(2*velCoefs[1].getY()+2*velCoefs[2].getY()+velCoefs[3].getY())*STEP/6));
		body.coord.setLocation(body.coord.getX() + (posCoefs[0].getX()+2*posCoefs[1].getX()+2*posCoefs[2].getX()+posCoefs[3].getX())*STEP/6, body.coord.getY() + (posCoefs[0].getY()+(2*posCoefs[1].getY()+2*posCoefs[2].getY()+posCoefs[3].getY())*STEP/6));
	}


	private void calcCoeff(Body body) {
		Point2D accel = body.acceleration;
		Point2D coords = body.coord;
		Point2D velocity = body.velocity;

		Point2D k1v, k2v, k3v, k4v, k1p, k2p, k3p, k4p;
		k1v = k2v = k3v = k4v = k1p = k2p = k3p = k4p = new Point2D.Double();

		k1v.setLocation(accel.getX() * coords.getX(), accel.getY() * coords.getY());
		k1p.setLocation(velocity);

		k2v.setLocation(accel.getX() * (coords.getX() + k1p.getX() * STEP / 2), coords.getY() + k1p.getY() * STEP / 2);
		k2p.setLocation(velocity.getX() * k1v.getX() * STEP / 2, velocity.getY() * k1v.getY() * STEP / 2);

		k3v.setLocation(accel.getX() * (coords.getX() + k2p.getX() * STEP / 2), coords.getY() + k2p.getY() * STEP / 2);
		k3p.setLocation(velocity.getX() * k2v.getX() * STEP / 2, velocity.getY() * k2v.getY() * STEP / 2);

		k4v.setLocation(accel.getX() * (coords.getX() + k3p.getX() * STEP), coords.getY() + k3p.getY() * STEP);
		k4p.setLocation(velocity.getX() * k2v.getX() * STEP, velocity.getY() * k2v.getY() * STEP);
		
		body.velCoefs = new Point2D[]{k1v,k2v,k3v,k4v};
		body.posCoefs = new Point2D[]{k1p,k2p,k3p,k4p};
	}

}
