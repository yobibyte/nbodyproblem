package com.yobibyte.rendering.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.yobibyte.solving.core.Constants;

import static org.lwjgl.opengl.GL11.*;

public class Window {
	public static void create(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(Constants.TITLE);
			// Display.setFullscreen(Constants.isFullScreen);
			Display.create();
			initGl();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	 // TODO read about this methods of GL
	 private static void initGl() {
	 glClearColor(0.5f, 0.5f, 1, 1);
	 glMatrixMode(GL_PROJECTION);
	 glLoadIdentity();
	 // Set 2D mode
	 glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
	 glMatrixMode(GL_MODELVIEW);
	 glEnable(GL_TEXTURE_2D);
	 glEnable(GL_BLEND);
	 glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
	 glLoadIdentity();
	 }

	public static void clear() {
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public static void update() {
		Display.update();
		Display.sync(Constants.FPS);
	}

	public static void destroy() {
		Display.destroy();
		System.exit(0);
	}
}
