package com.yobibyte.nbody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yobibyte.solving.core.Body;
import com.yobibyte.solving.core.Constants;
import com.yobibyte.solving.core.RungeKuttaMethod;
import com.yobibyte.solving.implementation.Solver;

public class NBodyProblemModel {
	// public static Player player;
	// public static Body body;

	public static List<Body> bodies = new ArrayList<Body>(Constants.NUM_OF_BODIES);
	Solver solver;

	public NBodyProblemModel() {
		// player = new Player();

		Random rand = new Random();
		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
			bodies.add(new Body(rand.nextDouble(), rand.nextDouble()));
		}
		solver = new Solver(bodies);

	}

	public void input() {

	}

	public void calculations(int stepNum) {
		solver.solve(stepNum);
	}

	public void render() {
		// player.render();

		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
			bodies.get(i).setRenderCoords();
			bodies.get(i).render();
			//bodies.get(i).dispose();
		}
	}

	public void dispose() {

	}
}
