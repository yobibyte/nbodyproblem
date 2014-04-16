//package com.yobibyte.solving.core;
//
//import static org.lwjgl.opengl.GL11.GL_FLOAT;
//import static org.lwjgl.opengl.GL11.GL_NEAREST;
//import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
//import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
//import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
//import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
//import static org.lwjgl.opengl.GL11.glBindTexture;
//import static org.lwjgl.opengl.GL11.glDisableClientState;
//import static org.lwjgl.opengl.GL11.glDrawArrays;
//import static org.lwjgl.opengl.GL11.glEnableClientState;
//import static org.lwjgl.opengl.GL11.glPopMatrix;
//import static org.lwjgl.opengl.GL11.glPushMatrix;
//import static org.lwjgl.opengl.GL11.glTexCoordPointer;
//import static org.lwjgl.opengl.GL11.glTranslatef;
//import static org.lwjgl.opengl.GL11.glVertexPointer;
//import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
//import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
//import static org.lwjgl.opengl.GL15.glBindBuffer;
//import static org.lwjgl.opengl.GL15.glBufferData;
//import static org.lwjgl.opengl.GL15.glDeleteBuffers;
//import static org.lwjgl.opengl.GL15.glGenBuffers;
//
//import java.awt.geom.Point2D;
//import java.io.IOException;
//import java.nio.FloatBuffer;
//
//import org.lwjgl.BufferUtils;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;
//
//public class Body {
//
//	public static int OFFSET_X = Constants.TILE_SIZE / 2;
//	public static int OFFSET_Y = Constants.TILE_SIZE / 2;
//
//	public double mass;
//	public Point2D coord;
//	public Point2D velocity;
//	public Point2D acceleration;
//	Point2D[] velCoefs;
//	Point2D[] posCoefs;
//
//	private final int NUM_OF_VERTICES = 6;
//	private final int DIMENSION = 3; // 2 actually, but it's easier in that way
//	private final int TEX_COORDS = 2;
//
//	// vbo stands for vertex data object
//	private int vboVertexHandle;
//	private int vboTexCoordHandle;
//
//	private Texture texture;
//
//	public float x;
//	public float y;
//
//	public void setRenderCoords() {
//		x = (float) coord.getX() + OFFSET_X;
//		y = (float) coord.getY() + OFFSET_Y;
//	}
//
//	public Body(Double xCoord, Double yCoord) {
//		mass = Constants.MASS;
//		coord = new Point2D.Double(xCoord, yCoord);
//		setRenderCoords();
//		velocity = Constants.INITIAL_VELOCITY;
//		//acceleration = Constants.INITIAL_ACCELERATION;
//		// Runge-Kutta of a 4th order
//		velCoefs = new Point2D[4];
//		posCoefs = new Point2D[4];
//
//		try {
//			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/dt.png"), GL_NEAREST);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		FloatBuffer vertexData = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * DIMENSION);
//		// TODO simplify this
//		// Two triangles here because of 2D
//		vertexData.put(new float[] { x, y, 0, x + Constants.TILE_SIZE, y, 0, x, y + Constants.TILE_SIZE, 0, x + Constants.TILE_SIZE, y + Constants.TILE_SIZE, 0, x, y + Constants.TILE_SIZE, 0, x + Constants.TILE_SIZE, y, 0 });
//		vertexData.flip();
//
//		FloatBuffer textureCoordData = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * TEX_COORDS);
//		textureCoordData.put(new float[] { 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 });
//		textureCoordData.flip();
//
//		vboVertexHandle = glGenBuffers();
//		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
//		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
//		glBindBuffer(GL_ARRAY_BUFFER, 0);
//
//		vboTexCoordHandle = glGenBuffers();
//		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
//		glBufferData(GL_ARRAY_BUFFER, textureCoordData, GL_STATIC_DRAW);
//		glBindBuffer(GL_ARRAY_BUFFER, 0);
//
//	}
//	
//	public Body(Point2D initCoord, Point2D initVelocity, Point2D initAcceleration) {
//		mass = Constants.MASS;
//		coord = initCoord;
//		setRenderCoords();
//		velocity = initVelocity;
//		//acceleration = initAcceleration;
//		// Runge-Kutta of a 4th order
//		velCoefs = new Point2D[4];
//		posCoefs = new Point2D[4];
//
//		try {
//			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/dt.png"), GL_NEAREST);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		FloatBuffer vertexData = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * DIMENSION);
//		// TODO simplify this
//		// Two triangles here because of 2D
//		vertexData.put(new float[] { x, y, 0, x + Constants.TILE_SIZE, y, 0, x, y + Constants.TILE_SIZE, 0, x + Constants.TILE_SIZE, y + Constants.TILE_SIZE, 0, x, y + Constants.TILE_SIZE, 0, x + Constants.TILE_SIZE, y, 0 });
//		vertexData.flip();
//
//		FloatBuffer textureCoordData = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * TEX_COORDS);
//		textureCoordData.put(new float[] { 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 });
//		textureCoordData.flip();
//
//		vboVertexHandle = glGenBuffers();
//		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
//		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
//		glBindBuffer(GL_ARRAY_BUFFER, 0);
//
//		vboTexCoordHandle = glGenBuffers();
//		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
//		glBufferData(GL_ARRAY_BUFFER, textureCoordData, GL_STATIC_DRAW);
//		glBindBuffer(GL_ARRAY_BUFFER, 0);
//
//	}
//
//	public void render() {
//		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
//
//		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
//		glVertexPointer(DIMENSION, GL_FLOAT, 0, 0L);
//
//		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
//		glTexCoordPointer(TEX_COORDS, GL_FLOAT, 0, 0L);
//
//		glEnableClientState(GL_VERTEX_ARRAY);
//		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
//		glPushMatrix();
//		glTranslatef(x, y, 0);
//		glDrawArrays(GL_TRIANGLES, 0, NUM_OF_VERTICES);
//		glPopMatrix();
//		glDisableClientState(GL_VERTEX_ARRAY);
//		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
//	}
//
//	public void dispose() {
//		glDeleteBuffers(vboVertexHandle);
//		glDeleteBuffers(vboTexCoordHandle);
//	}
//}

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
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Body {

	public static int OFFSET_X = Constants.TILE_SIZE / 2;
	public static int OFFSET_Y = Constants.TILE_SIZE / 2;

	public double mass;
	public Point2D coord;
	public Point2D velocity;

	private final int NUM_OF_VERTICES = 6;
	private final int DIMENSION = 3; // 2 actually, but it's easier in that way
	private final int TEX_COORDS = 2;

	// vbo stands for vertex data object
	private int vboVertexHandle;
	private int vboTexCoordHandle;

	private Texture texture;

	public float x;
	public float y;

	public void setRenderCoords() {
		x = (float) coord.getX() + OFFSET_X + 200;
		y = (float) coord.getY() + OFFSET_Y + 200;
	};

	public Body(Point2D coords, Point2D vel) {
		mass = Constants.MASS;
		coord = coords;
		velocity = vel;
		
		setRenderCoords();
		
		// Runge-Kutta of a 4th order

		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/dt.png"), GL_NEAREST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		FloatBuffer vertexData = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * DIMENSION);
		// TODO simplify this
		// Two triangles here because of 2D
		vertexData.put(new float[] { x, y, 0, x + Constants.TILE_SIZE, y, 0, x, y + Constants.TILE_SIZE, 0, x + Constants.TILE_SIZE, y + Constants.TILE_SIZE, 0, x, y + Constants.TILE_SIZE, 0, x + Constants.TILE_SIZE, y, 0 });
		vertexData.flip();

		FloatBuffer textureCoordData = BufferUtils.createFloatBuffer(NUM_OF_VERTICES * TEX_COORDS);
		textureCoordData.put(new float[] { 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 });
		textureCoordData.flip();

		vboVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		vboTexCoordHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
		glBufferData(GL_ARRAY_BUFFER, textureCoordData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

	}

	public void render() {
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());

		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glVertexPointer(DIMENSION, GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
		glTexCoordPointer(TEX_COORDS, GL_FLOAT, 0, 0L);

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glDrawArrays(GL_TRIANGLES, 0, NUM_OF_VERTICES);
		glPopMatrix();
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	}

	public void dispose() {
		glDeleteBuffers(vboVertexHandle);
		glDeleteBuffers(vboTexCoordHandle);
	}
}
