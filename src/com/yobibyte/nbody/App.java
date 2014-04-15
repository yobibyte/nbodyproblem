package com.yobibyte.nbody;

import com.yobibyte.rendering.engine.Engine;
import com.yobibyte.solving.implementation.Solver;

public class App {

	public static void main(String[] args) {
		//Computational stuff
		//Solver solver = new Solver();
		//solver.solve();
		
		//Rendering stuff
		Engine renderer = new Engine();
		renderer.startRendering();
	}
}
