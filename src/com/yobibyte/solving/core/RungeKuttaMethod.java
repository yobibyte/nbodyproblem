package com.yobibyte.solving.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RungeKuttaMethod implements DiffEquSolver {

	public static final java.lang.Double STEP = Constants.RUNGE_STEP;

	@Override
	public void solve(Body body, List<Body> bodies) {
//		Point2D coords = body.coord;
//		Point2D velocity = body.velocity;
		
//		double accel = 0d;
//		Point2D varToCalc = new Point2D.Double();
//
		double k1v, k2v, k3v, k4v, k1p, k2p, k3p, k4p;
		k1v = k2v = k3v = k4v = k1p = k2p = k3p = k4p = 0d;
//		
//
//		varToCalc = coords;
//		k1v = getAccel(bodies, body, varToCalc);
//		k1p = Math.sqrt(velocity.getX()*velocity.getX()+velocity.getY()*velocity.getY());
//
//		varToCalc.setLocation((coords.getX() + k1p.getX() * STEP / 2), coords.getY() + k1p.getY() * STEP / 2);
//		k2v.setLocation(getAccel(bodies, body, varToCalc));
//		k2p.setLocation(velocity.getX() * k1v.getX() * STEP / 2, velocity.getY() * k1v.getY() * STEP / 2);
//
//		varToCalc.setLocation(coords.getX() + k2p.getX() * STEP / 2, coords.getY() + k2p.getY() * STEP / 2);
//		k3v.setLocation(getAccel(bodies, body, varToCalc));
//		k3p.setLocation(velocity.getX() * k2v.getX() * STEP / 2, velocity.getY() * k2v.getY() * STEP / 2);
//
//		varToCalc.setLocation(coords.getX() + k3p.getX() * STEP, coords.getY() + k3p.getY() * STEP);
//		k4v.setLocation(getAccel(bodies, body, varToCalc));
//		k4p.setLocation(velocity.getX() * k3v.getX() * STEP, velocity.getY() * k3v.getY() * STEP);
//
//		body.velocity.setLocation(body.velocity.getX() + (k1v.getX() + 2 * k2v.getX() + 2 * k3v.getX() + k4v.getX()) * STEP / 6, body.velocity.getY() + (k1v.getY() + 2 * k2v.getY() + 2 * k3v.getY() + k4v.getY()) * STEP / 6);
//		body.coord.setLocation(body.coord.getX() + (k1p.getX() + 2 * k2p.getX() + 2 * k3p.getX() + k4p.getX()) * STEP / 6, body.coord.getY() + (k1p.getY() + 2 * k2p.getY() + 2 * k3p.getY() + k4p.getY()) * STEP / 6);

		
		
//alxdon solver
		Iterator<Body> it = bodies.iterator();

		double fx = 0d;
		double fy = 0d;
		
		if (it.hasNext()) {
			Body currBody = it.next();
			if (currBody != body) {
				double deltaX = currBody.coord.getX() - body.coord.getX();
				double deltaY = currBody.coord.getY() - body.coord.getY();
				double deltaR = deltaX*deltaX+deltaY*deltaY; 
				double f = Constants.GRAVITY*body.mass*currBody.mass/deltaR;
				deltaR = Math.sqrt(deltaR);
				fx += f*deltaX/deltaR;
				fy += f*deltaY/deltaR;
			}
			body.velocity.setLocation(body.velocity.getX()+STEP*fx/body.mass, body.velocity.getY() + STEP*fy/body.mass);
			body.coord.setLocation(body.coord.getX()+body.velocity.getX()*STEP, body.coord.getY()+body.velocity.getY()*STEP);
		}
	}

//	public void getF(List<Body> bodies, Body body, Point2D varToCalc) {
//
//		Iterator<Body> it = bodies.iterator();
//
//		double fx = 0d;
//		double fy = 0d;
//		
//		if (it.hasNext()) {
//			Body currBody = it.next();
//			if (currBody != body) {
//				double deltaX = currBody.coord.getX() - body.coord.getX();
//				double deltaY = currBody.coord.getY() - body.coord.getY();
//				double deltaR = deltaX*deltaX+deltaY*deltaY; 
//				double f = Constants.GRAVITY*body.mass*currBody.mass/deltaR;
//				deltaR = Math.sqrt(deltaR);
//				fx += f*deltaX/deltaR;
//				fy += f*deltaY/deltaR;
//			}
//			body.velocity.setLocation(body.coord.getX()+fx/body.mass*STEP, body.coord.getY() + fy/body.mass*STEP);
//			body.coord.setLocation(body.coord.getX() + body.velocity.getX()*STEP, body.coord.getY() + body.velocity.getY()*STEP);
//		}
//	}

}
