package com.yobibyte.nbody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yobibyte.solving.core.Background;
import com.yobibyte.solving.core.Body;
import com.yobibyte.solving.core.Constants;
import com.yobibyte.solving.core.RungeKuttaMethod;
import com.yobibyte.solving.implementation.Solver;

public class NBodyProblemModel {
	// public static Player player;
	// public static Body body;
	public static Background bg;
	public static Background bg1;
	public static List<Body> bodies = new ArrayList<Body>(Constants.NUM_OF_BODIES);
	Solver solver;

	public NBodyProblemModel() {
		// player = new Player();
		bg = new Background(0f,0f);
		bg1 = new Background(0,-400);
		Random rand = new Random();
		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
			bodies.add(new Body(rand.nextDouble()*Constants.DISPLAY_WIDTH/2, rand.nextDouble()*Constants.DISPLAY_HEIGHT/2));
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
		bg.render();
		bg1.render();
		for (int i = 0; i < Constants.NUM_OF_BODIES; i++) {
			bodies.get(i).setRenderCoords();
			bodies.get(i).render();
			//bodies.get(i).dispose();
		}
	}

	public void dispose() {

	}
}
