package com.yobibyte.rendering.engine;

import org.lwjgl.opengl.Display;

import com.yobibyte.nbody.NBodyProblemModel;
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
			// nBodyProblemModel.input();
			if (stepCounter < Constants.NUM_OF_STEPS) {
				nBodyProblemModel.calculations(stepCounter);
				stepCounter++;
			}
			nBodyProblemModel.render();
			Window.update();

			Thread b = new Thread();
			try {
				b.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
