package com.yobibyte.engine.implementation;

import org.lwjgl.opengl.Display;

import com.yobibyte.nbody.NBodyProblemModel;
import com.yobibyte.rendering.engine.Window;
import com.yobibyte.solving.core.Constants;

public class Engine {
	
	public static NBodyProblemModel nBodyProblemModel;

	public static void startRendering() {
		createWindow();
		createModel();
		appLoop();
		cleanUp();
	}

	public static void appLoop() {
		int stepCounter = 0;
		while (!isCloseRequested()) {
			Window.clear();
			//nBodyProblemModel.input();
			if (stepCounter < Constants.NUM_OF_STEPS) {
				nBodyProblemModel.calculations(stepCounter);
				stepCounter++;
			}
			nBodyProblemModel.render();
			Window.update();
			

		}
	}

	private static void createWindow() {
		Window.create(Constants.DISPLAY_WIDTH, Constants.DISPLAY_WIDTH);
	}
	
	public static void createModel() {
		 nBodyProblemModel = new NBodyProblemModel();
	}

	private static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}

	private static void cleanUp() {
		Window.destroy();
	}
}
