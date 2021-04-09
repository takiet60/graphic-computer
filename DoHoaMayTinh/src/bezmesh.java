/*
 *  bezmesh.java
 *  This program renders a lighted, filled Bezier surface,
 *  using two-dimensional evaluators.
 */

import java.awt.Frame;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import jgl.GL;
import jgl.GLCanvas;

public class bezmesh extends GLCanvas {
	
	private int rotateX = 0, rotateY = 0, rotateZ = 0;

    private static final float ctrlpoints [][][] ={
			{{-2.5f, -.2f,  3.0f},{-.5f, -2.f,  2.0f},{ .5f, -2.f, 2.0f},{ 2.5f, -.2f,  3.0f}},
			{{-2.5f, -0.1f,  2.0f},{-0.5f, -1.5f,  -1.0f},{ 0.5f, -0.5f,  -1.0f},{ 2.5f, -0.1f, 2.0f}},
			{{-2.5f,  0.1f,  2.0f},{-0.5f,  1.5f,  -1.0f},{ 0.5f,  0.5f,  -1.0f},{ 2.5f, 0.1f,  2.0f}},
			{{-2.5f,  0.2f, 3.0f},{-0.5f,  2.f, 2.0f},{ 0.5f,  2.f,  2.0f},{ 2.5f,  0.2f, 3.0f}}};
    
    private void initlights () {
	float ambient[] = {0.f, 0.2f, 0.2f, .0f};
	float position[] = {0.0f, 1.0f, 2.0f, 1.0f};
	float mat_diffuse[] = {0.3f, 0.6f, 0.6f, 1.0f};
	float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_shininess[] = {50.0f};

        myGL.glEnable (GL.GL_LIGHTING);
        myGL.glEnable (GL.GL_LIGHT0);

        myGL.glLightfv (GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
        myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, position);

        myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
        myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
        myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, mat_shininess);
    }

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	myGL.glPushMatrix ();
	myGL.glRotatef ((float) rotateZ, .0f, .0f, 1.0f);
	myGL.glRotatef ((float) rotateY, .0f, 1.0f, .0f);
	myGL.glRotatef ((float) rotateX, 1.0f, .0f, .0f);
	myGL.glEvalMesh2 (GL.GL_FILL, 0, 20, 0, 20);
	myGL.glPopMatrix ();
	myGL.glFlush ();
    }

    private void myinit () {
	myGL.glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
	myGL.glEnable (GL.GL_DEPTH_TEST);
	myGL.glMap2f (GL.GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
		      0.0f, 1.0f, 12, 4, ctrlpoints);
	myGL.glEnable (GL.GL_MAP2_VERTEX_3);
	myGL.glEnable (GL.GL_AUTO_NORMAL);
	myGL.glEnable (GL.GL_NORMALIZE);
	myGL.glMapGrid2f (20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
	initlights ();		/* for lighted version only */
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
	bezmesh mainCanvas = new bezmesh ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}
