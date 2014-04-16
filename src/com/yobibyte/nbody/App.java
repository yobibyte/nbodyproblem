package com.yobibyte.nbody;

import com.yobibyte.rendering.engine.Engine;

public class App {

	public static void main(String[] args) {
    MP3Player soundToPlay = new MP3Player("res/sw.mp3");
    soundToPlay.play();
		Engine.startRendering();
	}
}
