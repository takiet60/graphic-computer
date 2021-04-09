/*
 * robot.java
 * This program shows how to composite modeling transformations
 * to draw translated and rotated hierarchical models.
 * Interaction:  pressing the s and e keys (shoulder and elbow)
 * alters the rotation of the robot arm.
 */

import java.awt.Frame;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import jgl.GL;
import jgl.GLCanvas;

public class Robot_3 extends GLCanvas {

    private static int shoulder = 0, elbow = 0, body = 0, neck = 0, head = 0, feet = 0, leg = 0, rotate = 0;
    
    private static final float ctrlpoints [][][] ={
			{{-3.5f, -1.5f,  4.0f},{-.5f, -1.5f,  2.0f},{ .5f, -1.5f, 2.0f},{ 3.5f, -1.5f,  4.0f}},
			{{-3.5f, -0.5f,  2.0f},{-0.5f, -0.5f,  .0f},{ 0.5f, -0.5f,  0.0f},{ 3.5f, -0.5f, 2.0f}},
			{{-3.5f,  0.5f,  2.0f},{-0.5f,  0.5f,  0.0f},{ 0.5f,  0.5f,  .0f},{ 3.5f,  0.5f,  2.0f}},
			{{-3.5f,  1.5f, 4.0f},{-0.5f,  1.5f, 2.0f},{ 0.5f,  1.5f,  2.0f},{ 3.5f,  1.5f, 4.0f}}};


    private void myinit () {
    	myGL.glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
    	myGL.glMap2f (GL.GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
    		      0.0f, 1.0f, 12, 4, ctrlpoints);
    	myGL.glEnable (GL.GL_MAP2_VERTEX_3);
    	myGL.glEnable (GL.GL_AUTO_NORMAL);
    	myGL.glEnable (GL.GL_NORMALIZE);
    	myGL.glMapGrid2f (20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
//    	initlights ();		/* for lighted version only */
    }

    public void display () {
    	int i, j;

    	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    	myGL.glColor3f (1.0f, 1.0f, 1.0f);
    	myGL.glPushMatrix ();
    	myGL.glRotatef ((float)(rotate), 1.0f, 1.0f, 1.0f);
    	for (j = 0; j <= 8; j++) {
    	    myGL.glBegin (GL.GL_LINE_STRIP);
    		for (i = 0; i <= 30; i++) {
    	    	    myGL.glEvalCoord2f ((float)i/30.0f, (float)j/8.0f);
    		}
    	    myGL.glEnd ();
    	    myGL.glBegin (GL.GL_LINE_STRIP);
    		for (i = 0; i <= 30; i++) {
    	    	    myGL.glEvalCoord2f ((float)j/8.0f, (float)i/30.0f);
    		}
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

    /* ARGSUSED1 */
    public void keyboard (char key, int x, int y) {
	switch (key) {
	    case 's':
			rotate = (rotate + 5) % 360;
			myUT.glutPostRedisplay ();
		break;
	    case 27:
		System.exit(0);
	    default:
		break;
	}
    }

    public void init () {
	myUT.glutInitWindowSize (400, 400);
	myUT.glutInitWindowPosition (0, 0);
	myUT.glutCreateWindow (this);
	myinit ();
	myUT.glutDisplayFunc ("display");
	myUT.glutReshapeFunc ("myReshape");
	myUT.glutKeyboardFunc ("keyboard");
	myUT.glutMainLoop ();
    }

    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (408, 427);
	Robot_3 mainCanvas = new Robot_3 ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}
