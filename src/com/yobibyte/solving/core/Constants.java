package com.yobibyte.solving.core;

import java.awt.geom.Point2D;

public class Constants {

	
	public static final Point2D INITIAL_COORD = new Point2D.Double();
	public static final Point2D INITIAL_VELOCITY = new Point2D.Double(10,1);
	public static final Point2D INITIAL_ACCELERATION = new Point2D.Double(0,1);
	public static final double V0 = 1f;
	//public static final double GRAVITY = 6.67545/Math.pow(10, 11);
	public static final double GRAVITY = 100;
	public static final double ALPHA = 40;
	public static final double MASS = 200;
	public static final Double RUNGE_STEP = 1d;
	public static Integer NUM_OF_BODIES = 2;
	public static Integer NUM_OF_STEPS = 1000000;
	
	//Rendering stuff
	public static String TITLE = "Yobibyte n-problem implementation";
	public static final int TILE_SIZE = 32;
	public static final int BG_SIZE = 64*64;
	public static final Integer DISPLAY_WIDTH = 800;
	public static final Integer DISPLAY_HEIGHT = 600;
	public static final Integer FPS = 60;
	public static final boolean isFullScreen = true;
}
