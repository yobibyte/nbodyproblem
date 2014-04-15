package com.yobibyte.solving.core;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.yobibyte.solving.core.Constants;

public class Background {


	private final int NUM_OF_VERTICES = 6;
	private final int DIMENSION = 3; // 2 actually, but it's easier in that way
	private final int TEX_COORDS = 2;

	// vbo stands for vertex data object
	private int vboVertexHandle1;
	private int vboTexCoordHandle1;
	
	private int vboVertexHandle2;
	private int vboTexCoordHandle2;

	private Texture texture;

//	public float x=0;
//	public float y=0;

	public Background(float x, float y) {
	

		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/bg.png"), GL_NEAREST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		FloatBuffer vertexData1 = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * DIMENSION);
		// TODO simplify this
		// Two triangles here because of 2D
		vertexData1.put(new float[] {x, y, 0, x + 1024, y, 0, x, y + 800, 0, x + 1024, y + 800, 0, x, y + 800, 0, x + 1024, y, 0 });
		//vertexData1.put(new float[] {x, y, 0, x + 1024, y, 0, x, y + 800, 0, x + 1024, y + 800, 0, x, y + 800, 0, x + 1024, y, 0 });
		
		vertexData1.flip();

		FloatBuffer textureCoordData1 = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * TEX_COORDS);
		textureCoordData1.put(new float[] { 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 });
		textureCoordData1.flip();

		vboVertexHandle1 = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle1);
		glBufferData(GL_ARRAY_BUFFER, vertexData1, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		vboTexCoordHandle1 = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle1);
		glBufferData(GL_ARRAY_BUFFER, textureCoordData1, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		

	}

	 public void render() {
	 glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
	
	 glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle1);
	 glVertexPointer(DIMENSION, GL_FLOAT, 0, 0L);
	
	 glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle1);
	 glTexCoordPointer(TEX_COORDS, GL_FLOAT, 0, 0L);
	
	 glEnableClientState(GL_VERTEX_ARRAY);
	 glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	

	 glDrawArrays(GL_TRIANGLES, 0, NUM_OF_VERTICES);
	 glDisableClientState(GL_VERTEX_ARRAY);
	 glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	 	
	 }



	public void dispose() {
		glDeleteBuffers(vboVertexHandle1);
		glDeleteBuffers(vboTexCoordHandle1);
	}
}
