/*
 *  texturesurf.java
 *  This program uses evaluators to generate a curved
 *  surface and automatically generated texture coordinates.
 */

import java.awt.Color;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import javax.imageio.ImageIO;

import jgl.GL;
import jgl.GLCanvas;

public class Boat_Material extends GLCanvas {
	private static final int width = 256;
	private static final int height = 256;
	private int texName[] = new int[5];

    private static final float ctrlpoints [][][] = {
			{{-2.5f, -.2f,  3.0f},{-.5f, -2.f,  2.0f},{ .5f, -2.f, 2.0f},{ 2.5f, -.2f,  3.0f}},
			{{-2.5f, -0.1f,  2.0f},{-0.5f, -1.5f,  -1.0f},{ 0.5f, -0.5f,  -1.0f},{ 2.5f, -0.1f, 2.0f}},
			{{-2.5f,  0.1f,  2.0f},{-0.5f,  1.5f,  -1.0f},{ 0.5f,  0.5f,  -1.0f},{ 2.5f, 0.1f,  2.0f}},
			{{-2.5f,  0.2f, 3.0f},{-0.5f,  2.f, 2.0f},{ 0.5f,  2.f,  2.0f},{ 2.5f,  0.2f, 3.0f}}};

    private static boolean direction = true;
	private byte image1[][][] = new byte[width][height][4];
	private byte image2[][][] = new byte[width][height][4];
	private byte image3[][][] = new byte[width][height][4];
	private byte image4[][][] = new byte[width][height][4];
	private byte image5[][][] = new byte[width][height][4];
    
    private static final float texpts [][][] = {
			{{ 0.0f,  0.0f},
			 { 0.0f,  1.0f}},
			{{ 1.0f,  0.0f},
			 { 1.0f,  1.0f}}};

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	myGL.glColor3f (1.0f, 1.0f, 1.0f);
	myGL.glEvalMesh2 (GL.GL_FILL, 0, 20, 0, 20);
	myGL.glFlush ();
    }

    private void makeImage () throws IOException {
    	File f = new File("src//BedRoom_Center.bmp");
		BufferedImage s1 = ImageIO.read(f);
		f = new File("src//BedRoom_Top.bmp");
		BufferedImage s2 = ImageIO.read(f);
		f = new File("src//BedRoom_Bottom.bmp");
		BufferedImage s3 = ImageIO.read(f);
		f = new File("src//BedRoom_Left.bmp");
		BufferedImage s4 = ImageIO.read(f);
		f = new File("src//BedRoom_Right.bmp");
		BufferedImage s5 = ImageIO.read(f);
		for (int k = 0; k < width; k++) {
			for (int k2 = 0; k2 < height; k2++) {
				Color co = new Color(s1.getRGB(k, k2));
				image1[k][k2][0] = (byte) co.getRed();
				image1[k][k2][1] = (byte) co.getGreen();
				image1[k][k2][2] = (byte) co.getBlue();
				image1[k][k2][3] = (byte) 255;
				co = new Color(s2.getRGB(k, k2));
				image2[k][k2][0] = (byte) co.getRed();
				image2[k][k2][1] = (byte) co.getGreen();
				image2[k][k2][2] = (byte) co.getBlue();
				image2[k][k2][3] = (byte) 255;
				co = new Color(s3.getRGB(k, k2));
				image3[k][k2][0] = (byte) co.getRed();
				image3[k][k2][1] = (byte) co.getGreen();
				image3[k][k2][2] = (byte) co.getBlue();
				image3[k][k2][3] = (byte) 255;
				co = new Color(s4.getRGB(k, k2));
				image4[k][k2][0] = (byte) co.getRed();
				image4[k][k2][1] = (byte) co.getGreen();
				image4[k][k2][2] = (byte) co.getBlue();
				image4[k][k2][3] = (byte) 255;
				co = new Color(s5.getRGB(k, k2));
				image5[k][k2][0] = (byte) co.getRed();
				image5[k][k2][1] = (byte) co.getGreen();
				image5[k][k2][2] = (byte) co.getBlue();
				image5[k][k2][3] = (byte) 255;
			}
		}
    }
    
    private void initlights() {

		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float position[] = { 5.0f, 5.0f, 1.0f, 0.0f };
		float local_view[] = { 0.0f };
		myGL.glEnable(GL.GL_DEPTH_TEST);
		myGL.glDepthFunc(GL.GL_LESS);

		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position);
		myGL.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, specular);
		myGL.glLightModelfv(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);

		myGL.glEnable(GL.GL_LIGHTING);
		myGL.glEnable(GL.GL_LIGHT0);
		myGL.glShadeModel(GL.GL_FLAT);
		myGL.glEnable(GL.GL_DEPTH_TEST);
	}

    private void myinit () throws IOException {
    	initlights();
		makeImage();
		myGL.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
		// Center
		myGL.glGenTextures(5, texName);
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image1);
		// Top
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image2);
		myGL.glEnable(GL.GL_TEXTURE_2D);
		// Bottom
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image3);
		myGL.glEnable(GL.GL_TEXTURE_2D);

		// Left
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image4);
		myGL.glEnable(GL.GL_TEXTURE_2D);
		// Right
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image5);
		myGL.glEnable(GL.GL_TEXTURE_2D);

		myGL.glClearColor(0.0f, 0.1f, 0.1f, 0.0f);
    }

    public void myReshape (int w, int h) {
        myGL.glViewport (0, 0, w, h);
        myGL.glMatrixMode (GL.GL_PROJECTION);
        myGL.glLoadIdentity ();
	if (w <= h) {
	    myGL.glOrtho (-4.0f, 4.0f,
	    		  -4.0f *(float)h/(float)w,
			   4.0f *(float)h/(float)w,
			  -4.0f, 4.0f);
	} else {
	    myGL.glOrtho (-4.0f *(float)w/(float)h,
	    		   4.0f *(float)w/(float)h,
			  -4.0f, 4.0f,
			  -4.0f, 4.0f);
	}
        myGL.glMatrixMode (GL.GL_MODELVIEW);
        myGL.glLoadIdentity ();
	myGL.glRotatef (85.0f, 1.0f, 1.0f, 1.0f);
    }

    public void keyboard (char key, int x, int y) {
	switch (key) {
	    case 27:
		System.exit(0);
	    default:
		break;
	}
    }

    public void init () throws IOException {
	myUT.glutInitWindowSize (500, 500);
	myUT.glutInitWindowPosition (0, 0);
	myUT.glutCreateWindow (this);
	try {
		myinit ();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	myUT.glutReshapeFunc ("myReshape");
	myUT.glutDisplayFunc ("display");
	myUT.glutKeyboardFunc ("keyboard");
	myUT.glutMainLoop ();
    }

    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (508, 527);
	Boat_Material mainCanvas = new Boat_Material ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}
