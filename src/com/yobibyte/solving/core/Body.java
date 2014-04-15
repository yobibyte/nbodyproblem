package com.yobibyte.solving.core;

import java.awt.geom.Point2D;

public class Body {
	public double mass;
	public Point2D currCoord;

	public Body() {
		mass = Constants.MASS;
		currCoord =Constants.INITIAL_COORD; 
				}
}
