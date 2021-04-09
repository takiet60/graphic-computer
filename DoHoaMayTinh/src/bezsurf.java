/*
 *  bezsurf.java			
 *  This program uses evaluators to draw a Bezier surface.
 */

import java.awt.Frame;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import jgl.GL;
import jgl.GLCanvas;

public class bezsurf extends GLCanvas {
	private int rotateX, rotateY, rotateZ;

    private static final float ctrlpoints [][][] = {
			{{-3.5f, -.2f,  4.0f},{-.5f, -3.f,  2.0f},{ .5f, -2.5f, 2.0f},{ 3.5f, -.2f,  4.0f}},
			{{-3.5f, -0.1f,  2.0f},{-0.5f, -1.5f,  -1.0f},{ 0.5f, -0.5f,  -1.0f},{ 3.5f, -0.1f, 2.0f}},
			{{-3.5f,  0.1f,  2.0f},{-0.5f,  1.5f,  -1.0f},{ 0.5f,  0.5f,  -1.0f},{ 3.5f, 0.1f,  2.0f}},
			{{-3.5f,  0.2f, 4.0f},{-0.5f,  3.f, 2.0f},{ 0.5f,  2.5f,  2.0f},{ 3.5f,  0.2f, 4.0f}}};
    private void myinit () {
	myGL.glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
	myGL.glShadeModel (GL.GL_FLAT);
	myGL.glEnable (GL.GL_DEPTH_TEST);
	myGL.glMap2f (GL.GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
		      0.0f, 1.0f, 12, 4, ctrlpoints);
	myGL.glEnable (GL.GL_MAP2_VERTEX_3);
    }

    public void display () {
    	int i, j;

	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	myGL.glColor3f (1.f, 1.0f, 1.0f);
	myGL.glPushMatrix ();
	myGL.glRotatef ((float) rotateZ, .0f, .0f, 1.0f);
	myGL.glRotatef ((float) rotateY, .0f, 1.0f, .0f);
	myGL.glRotatef ((float) rotateX, 1.0f, .0f, .0f);
	for (j = 0; j <= 8; j++) {
	    myGL.glBegin (GL.GL_LINE_STRIP);
		for (i = 0; i <= 30; i++)
	    	    myGL.glEvalCoord2f ((float)i/30.0f, (float)j/8.0f);
	    myGL.glEnd ();
	    myGL.glBegin (GL.GL_LINE_STRIP);
		for (i = 0; i <= 30; i++)
	    	    myGL.glEvalCoord2f ((float)j/8.0f, (float)i/30.0f);
	    myGL.glEnd ();
	}
	myGL.glPopMatrix ();
	myGL.glFlush ();
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
    }

    public void keyboard (char key, int x, int y) {
	switch (key) {
	case 'x':
		rotateX = (rotateX + 10) % 360;
		myUT.glutPostRedisplay ();
		break;
	case 'y':
		rotateY = (rotateY + 10) % 360;
		myUT.glutPostRedisplay ();
		break;
	case 'z':
		rotateZ = (rotateZ + 10) % 360;
		myUT.glutPostRedisplay ();
	case 'X':
		rotateX = (rotateX - 10) % 360;
		myUT.glutPostRedisplay ();
		break;
	case 'Y':
		rotateY = (rotateY - 10) % 360;
		myUT.glutPostRedisplay ();
		break;
	case 'Z':
		rotateZ = (rotateZ - 10) % 360;
		myUT.glutPostRedisplay ();
		break;
	    case 27:
		System.exit(0);
	    default:
		break;
	}
    }

    public void init () {
	myUT.glutInitWindowSize (500, 500);
	myUT.glutInitWindowPosition (0, 0);
	myUT.glutCreateWindow (this);
	myinit ();
	myUT.glutReshapeFunc ("myReshape");
	myUT.glutDisplayFunc ("display");
	myUT.glutKeyboardFunc ("keyboard");
	myUT.glutMainLoop ();
    }

    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (508, 527);
	bezsurf mainCanvas = new bezsurf ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}
