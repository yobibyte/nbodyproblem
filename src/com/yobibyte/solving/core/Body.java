package com.yobibyte.solving.core;

import java.awt.geom.Point2D;

public class Body {
	public double mass;
	public Point2D coord;
	public Point2D velocity;
	public Point2D acceleration;
	Point2D[] velCoefs;
	Point2D[] posCoefs;

	public Body() {
		mass = Constants.MASS;
		coord = Constants.INITIAL_COORD;
		velocity = Constants.INITIAL_VELOCITY;
		acceleration = Constants.INITIAL_ACCELERATION;
	// Runge-Kutta of a 4th order
		velCoefs = new Point2D[4];
		posCoefs = new Point2D[4];
	}
	
	public Body(Double xCoord, Double yCoord) {
		mass = Constants.MASS;
		coord  = new Point2D.Double(xCoord, yCoord);
		velocity = Constants.INITIAL_VELOCITY;
		acceleration = Constants.INITIAL_ACCELERATION;
	// Runge-Kutta of a 4th order
		velCoefs = new Point2D[4];
		posCoefs = new Point2D[4];
	}
}
