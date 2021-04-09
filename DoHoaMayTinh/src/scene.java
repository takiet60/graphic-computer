/*
 *  scene.java
 *  This program demonstrates the use of the GL lighting model.
 *  Objects are drawn using a grey material characteristic. 
 *  A single light source illuminates the objects.
 */

import java.awt.Frame;
import java.io.IOException;
import java.lang.String;
import java.lang.System;

import jgl.GL;
import jgl.GLCanvas;

public class scene extends GLCanvas {

    /* Initialize material property and light source. */
    private void myinit () {
	float light_ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	float light_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	float light_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	/* light_position is NOT default value */
	float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };

	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient);
	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse);
	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular);
	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, light_position);
    
	myGL.glEnable (GL.GL_LIGHTING);
	myGL.glEnable (GL.GL_LIGHT0);
	myGL.glEnable (GL.GL_DEPTH_TEST);
    }

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

	myGL.glPushMatrix ();
	myGL.glRotatef (20.0f, 1.0f, 0.0f, 0.0f);

//	myGL.glPushMatrix ();
//	myGL.glTranslatef (-0.75f, 0.5f, 0.0f);
//	myGL.glRotatef (90.0f, 1.0f, 0.0f, 0.0f);
////	myUT.glutSolidCube(arg0); (0.275, 0.85, 15, 15);
//	myUT.glutSolidCube(1.0);
//	myGL.glPopMatrix ();

	myGL.glPushMatrix ();
	myGL.glTranslatef (-0.75f, -0.5f, 0.0f);
	myGL.glRotatef (90.0f, 1.0f, 0.0f, 0.0f);
//	myUT.glutSolidCone (1.0, 2.0, 15, 15);
	myUT.glutSolidTorus(1.0, 1, 2, 3);
	myGL.glPopMatrix ();

//	myGL.glPushMatrix ();
//	myGL.glTranslatef (0.75f, 0.0f, -1.0f);
//	myUT.glutSolidSphere (1.0, 15, 15);
//	myGL.glPopMatrix ();

	myGL.glPopMatrix ();
	myGL.glFlush();
    }

    public void myReshape (int w, int h) {
        myGL.glViewport (0, 0, w, h);
        myGL.glMatrixMode (GL.GL_PROJECTION);
        myGL.glLoadIdentity ();
        if (w <= h) {
            myGL.glOrtho (-2.5f, 2.5f,
                          -2.5f*(float)h/(float)w, 
                           2.5f*(float)h/(float)w, 
                         -10.0f, 10.0f);
        } else {
            myGL.glOrtho (-2.5f*(float)w/(float)h, 
                           2.5f*(float)w/(float)h, 
                          -2.5f,  2.5f,
                         -10.0f, 10.0f);
        }
        myGL.glMatrixMode (GL.GL_MODELVIEW);
        myGL.glLoadIdentity ();
    }

    public void keyboard (char key, int x, int y) {
	switch (key) {
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
	scene mainCanvas = new scene ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}
