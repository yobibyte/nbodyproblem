package com.yobibyte.rendering.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import com.yobibyte.solving.core.Constants;

public class Engine {

	public static void startRendering() {
		createWindow();
		renderingLoop();
	}

	public static void renderingLoop() {
		while (!isCloseRequested()) {
			Window.clear();
			Window.update();
		}
	}
	
	private static void createWindow() {
		Window.create(Constants.DISPLAY_WIDTH, Constants.DISPLAY_WIDTH);
	}

	private static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
}
