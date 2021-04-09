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

public class Robot_2 extends GLCanvas {

    private static int shoulder = 0, elbow = 0, body = 0, neck = 0, head = 0, feetL = 0, legL = 0, 
    		legR = 0, feetR = 0, lefR = 0;

    private void myinit () {
    	myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
	myGL.glShadeModel (GL.GL_FLAT);
    }

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT);
	myGL.glPushMatrix ();
		// body 
		myGL.glTranslatef(.0f, -0.5f, 0.0f);
		myGL.glRotatef((float) body, 0.0f, 1.0f, .0f);
		myGL.glTranslatef(.0f, .75f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef (1.0f, 1.5f, .5f);
		myUT.glutWireCube (1.0);
	    myGL.glPopMatrix ();
	    
	    myGL.glPushMatrix();
	 // neck 
	  		myGL.glTranslatef(.0f, .75f, 0.0f);
	  		myGL.glRotatef((float) neck, 1.0f, 0.0f, .0f);
	  		myGL.glTranslatef(.0f, .0f, 0.0f);
	  		myGL.glPushMatrix();
	  		myGL.glScalef (.25f, .25f, .25f);
	  		myUT.glutWireCube (1.0);
	  	    myGL.glPopMatrix ();
	  	 myGL.glPopMatrix();
 	    
 	    myGL.glPushMatrix();
 	    	// head
 	 		myGL.glTranslatef(.0f, .5f, 0.0f);
 	 		myGL.glRotatef((float) head, 1.0f, 0.0f, .0f);
 	 		myGL.glTranslatef(.0f, 1.f, 0.0f);
 	 		myGL.glPushMatrix();
 	 		myGL.glScalef (1.f, 1.f, .25f);
 	 		myUT.glutWireCube (1.0);
 	 	    myGL.glPopMatrix ();
 	    myGL.glPopMatrix();
 	    
 	    myGL.glPushMatrix();
 	// left shoulder
 	 		myGL.glTranslatef(-.5f, .50f, 0.0f);
 	 		myGL.glRotatef((float) shoulder, .0f, 1.f, .0f);
 	 		myGL.glTranslatef(-.5f, .0f, 0.0f);
 	 		myGL.glPushMatrix();
 	 		myGL.glScalef (1.f, .4f, .25f);
 	 		myUT.glutWireCube (1.0);
 	 	    myGL.glPopMatrix ();
 	 	    
 	 	    // left elbow
 	 		myGL.glTranslatef(-.5f, .0f, 0.0f);
 	 		myGL.glRotatef((float) elbow, .0f, 1.0f, .0f);
 	 		myGL.glTranslatef(-.5f, .0f, 0.0f);
 	 		myGL.glPushMatrix();
 	 		myGL.glScalef (1.f, .4f, .25f);
 	 		myUT.glutWireCube (1.0);
 	 	    myGL.glPopMatrix ();
 	 	 myGL.glPopMatrix();
 	    
 	    
 	 	myGL.glPushMatrix();
 	 	 // right shoulder
	  	    myGL.glTranslatef(0.5f, .5f, 0.f);
	  	    myGL.glRotatef((float) shoulder, 0.f, -1.f, .0f);
	  	    myGL.glTranslatef(.5f, 0.f, 0.f);
	  	    myGL.glPushMatrix();
	 		myGL.glScalef (1.f, .4f, .25f);
	 		myUT.glutWireCube (1.0);
	 	    myGL.glPopMatrix ();
	 	    
	 	    // right elbow
	  	    myGL.glTranslatef(0.5f, .0f, 0.f);
	  	    myGL.glRotatef((float) elbow, 0.f, -1.f, .0f);
	  	    myGL.glTranslatef(0.5f, 0.f, 0.f);
	  	    myGL.glPushMatrix();
	 		myGL.glScalef (1.f, .4f, .25f);
	 		myUT.glutWireCube (1.0);
	 	    myGL.glPopMatrix ();
	 	myGL.glPopMatrix();
 	 	 
	    
	    
	    myGL.glPushMatrix();
	    	// left feet
	    	myGL.glTranslatef(-.25f, -.75f, .0f);
	    	myGL.glRotatef((float) feetL, 1.f, 0.f, 0.f);
	    	myGL.glTranslatef(-.0f, -.5f, 0.f);
		    myGL.glPushMatrix();
			myGL.glScalef (.5f, 1.f, .25f);
			myUT.glutWireCube (1.0);
		    myGL.glPopMatrix ();
		    
		 // left leg
	    	myGL.glTranslatef(-.0f, -.50f, .0f);
	    	myGL.glRotatef((float) legL, 1.f, 0.f, 0.f);
	    	myGL.glTranslatef(-.0f, -.50f, 0.f);
		    myGL.glPushMatrix();
			myGL.glScalef (.5f, 1.f, .25f);
			myUT.glutWireCube (1.0);
		    myGL.glPopMatrix ();
	    myGL.glPopMatrix();
	    
	    myGL.glPushMatrix();
    	// right feet
    	myGL.glTranslatef(.25f, -.75f, .0f);
    	myGL.glRotatef((float) feetR, 1.f, 0.f, 0.f);
    	myGL.glTranslatef(-.0f, -.5f, 0.f);
	    myGL.glPushMatrix();
		myGL.glScalef (.5f, 1.f, .25f);
		myUT.glutWireCube (1.0);
	    myGL.glPopMatrix ();
	    
	 // right leg
    	myGL.glTranslatef(-.0f, -.50f, .0f);
    	myGL.glRotatef((float) legR, 1.f, 0.f, 0.f);
    	myGL.glTranslatef(-.0f, -.50f, 0.f);
	    myGL.glPushMatrix();
		myGL.glScalef (.5f, 1.f, .25f);
		myUT.glutWireCube (1.0);
	    myGL.glPopMatrix ();
    myGL.glPopMatrix();

	myGL.glPopMatrix ();
	myGL.glFlush ();
    }

    public void myReshape (int w, int h) {
	myGL.glViewport (0, 0, w, h);
	myGL.glMatrixMode (GL.GL_PROJECTION);
	myGL.glLoadIdentity ();
	myGLU.gluPerspective (60.0, (double)w/(double)h, 1.0, 20.0);
	myGL.glMatrixMode (GL.GL_MODELVIEW);
	myGL.glLoadIdentity ();
	myGL.glTranslatef (0.0f, 0.0f, -5.0f);
    }

    /* ARGSUSED1 */
    public void keyboard (char key, int x, int y) {
	switch (key) {
	    case 's':
			shoulder = (shoulder + 5) % 360;
			myUT.glutPostRedisplay ();
		break;
	    case 'S':
			shoulder = (shoulder - 5) % 360;
			myUT.glutPostRedisplay ();
		break;
	    case 'e':
			elbow = (elbow + 5) % 360;
			myUT.glutPostRedisplay ();
		break;
	    case 'E':
			elbow = (elbow - 5) % 360;
			myUT.glutPostRedisplay ();
		break;
	    case 'b':
		    body = (body + 5) % 360;
		    myUT.glutPostRedisplay ();
	    break;
	    case 'B':
		    body = (body - 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'h':
		    head = (head + 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'H':
		    head = (head - 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'n':
		    neck = (neck + 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'N':
		    neck = (neck - 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'f':
		    feetL = (feetL + 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'F':
	    	feetL = (feetL - 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'l':
		    legL = (legL + 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'y':
	    case 'Y':
		myGL.glRotatef (30.0f, 0.0f, 1.0f, 0.0f);
		myUT.glutPostRedisplay ();
		break;
	    case 'L':
	    	legL = (legL - 5) % 360;
		    myUT.glutPostRedisplay ();
		    break;
	    case 'i':
	    case 'I':
		myGL.glLoadIdentity ();
		myGLU.gluLookAt (2.0, 2.0, 10.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
		myUT.glutPostRedisplay ();
		break;
	    case 'q':
	    case 'Q':
	    	myGL.glLoadIdentity ();
			myGLU.gluLookAt (0.0, 0.0, 10.0, 0.0, .0, 2.0, 0.0, 1.0, 0.0);
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
	Robot_2 mainCanvas = new Robot_2 ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}
