package com.yobibyte.solving.core;

import java.awt.geom.Point2D;

public class Constants {

	public static Integer NUM_OF_STEPS = 1000000;
	
	public static final Point2D INITIAL_COORD = new Point2D.Double();
	public static final Point2D INITIAL_VELOCITY = new Point2D.Double(2,2);
	public static final Point2D INITIAL_ACCELERATION = new Point2D.Double(1,1);
	public static final double V0 = 1f;
	public static final double GRAVITY = 9.81f;

	public static final double CONSTANT_OF_FRICTION = 0.8f;
	public static final double ALPHA = 40;
	public static final double MASS = 1;
	public static final Double RUNGE_STEP = 0.1d;

	public static final double SIN_ALPHA = Math.sin(Math.toRadians(ALPHA));
	public static final double COS_ALPHA = Math.cos(Math.toRadians(ALPHA));
	public static final double VX0 = V0 * COS_ALPHA;
	public static final double VY0 = V0 * SIN_ALPHA;
	
	public static Integer NUM_OF_BODIES = 2;

	
	
	//Rendering stuff
	public static String TITLE = "Yobibyte n-problem implementation";
	public static final int TILE_SIZE = 32;
	public static final int BG_SIZE = 64*64;
	public static final Integer DISPLAY_WIDTH = 800;
	public static final Integer DISPLAY_HEIGHT = 600;
	public static final Integer FPS = 60;
	public static final boolean isFullScreen = true;
}
