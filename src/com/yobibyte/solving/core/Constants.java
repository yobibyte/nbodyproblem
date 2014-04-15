package com.yobibyte.solving.core;

import java.awt.geom.Point2D;

public class Constants {

	public static Integer NUM_OF_STEPS = 10;
	
	public static final Point2D INITIAL_COORD = new Point2D.Double();
	public static final Point2D INITIAL_VELOCITY = new Point2D.Double(2,2);
	public static final Point2D INITIAL_ACCELERATION = new Point2D.Double(1,1);
	public static final double V0 = 1f;
	public static final double GRAVITY = 9.81f;

	public static final double CONSTANT_OF_FRICTION = 0.8f;
	public static final double ALPHA = 40;
	public static final double MASS = 1;
	public static final Double RUNGE_STEP = 0.01d;

	public static final double SIN_ALPHA = Math.sin(Math.toRadians(ALPHA));
	public static final double COS_ALPHA = Math.cos(Math.toRadians(ALPHA));
	public static final double VX0 = V0 * COS_ALPHA;
	public static final double VY0 = V0 * SIN_ALPHA;
	
	public static Integer NUM_OF_BODIES = 10;

}
