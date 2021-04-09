import java.awt.Frame;
import java.io.IOException;

import javax.swing.text.Utilities;

import jgl.GL;
import jgl.GLAUX;
import jgl.GLCanvas;

public class Test extends GLCanvas{
	 public void display () {
			/* clear all pixels */
			myGL.glClear (GL.GL_COLOR_BUFFER_BIT);

			/*
			 * draw white polygon (rectangle) with corners at
			 * (0.25, 0.25, 0.0) and (0.75, 0.75, 0.0)  
			 */
			// Main circle
			myGL.glColor3f (1f, 1f, 1f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (0 + Math.cos(i)*0.725); 
				float y = (float) (0 + Math.sin(i)*0.725); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();
			
			// Left circle
			myGL.glColor3f (0f, 0f, 0f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (-0.3125 + Math.cos(i)*0.125); 
				float y = (float) (0.3125 + Math.sin(i)*0.125); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();
			
			// Right circle
			myGL.glColor3f (0f, 0f, 0f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (0.3125 + Math.cos(i)*0.125); 
				float y = (float) (0.3125 + Math.sin(i)*0.125); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();
			
			// Center circle
			myGL.glColor3f (0f, 0f, 0f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (0 + Math.cos(i)*0.125); 
				float y = (float) (0 + Math.sin(i)*0.125); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();
			
			// Bottom circle
			myGL.glColor3f (0f, 0f, 0f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (0 + Math.cos(i)*0.4); 
				float y = (float) (-0.4 + Math.sin(i)*0.25); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();
			
			// Bottom circle
			myGL.glColor3f (1f, 1f, 1f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (0 + Math.cos(i)*0.4); 
				float y = (float) (-0.255 + Math.sin(i)*0.25); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();
			
			// Top - Left circle
			myGL.glColor3f (1f, 1f, 1f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (-0.55 + Math.cos(i)*0.2); 
				float y = (float) (0.55 + Math.sin(i)*0.2); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();
			
			// Top - Right circle
			myGL.glColor3f (1f, 1f, 1f);
			myGL.glBegin (GL.GL_POLYGON);
			for(int i = 0; i < 360; i++){
				float x = (float) (0.55 + Math.cos(i)*0.2); 
				float y = (float) (0.55 + Math.sin(i)*0.2); 
				myGL.glVertex3f(x, y, 0);
			}
			myGL.glEnd();

			myGL.glColor3f(0f, 0f, 0f);
			myGL.glBegin(GL.GL_POLYGON);
			myGL.glVertex3f(-0.05f, -0.15f, 0f);
			myGL.glVertex3f(-0.05f, -0.3f, 0f);
			myGL.glEnd();
			
			myGL.glColor3f(0f, 0f, 0f);
			myGL.glBegin(GL.GL_POLYGON);
			myGL.glVertex3f(0.05f, -0.15f, 0f);
			myGL.glVertex3f(0.05f, -0.3f, 0f);
			myGL.glEnd();
			
			myGL.glColor3f(0f, 0f, 0f);
			myGL.glBegin(GL.GL_POLYGON);
			myGL.glVertex3f(-0.45f, 0.475f, 0f);
			myGL.glVertex3f(-0.2f, 0.475f, 0f);
			myGL.glVertex3f(-0.45f, 0.5f, 0f);
			myGL.glVertex3f(-0.2f, 0.5f, 0f);
			myGL.glEnd();
			
			myGL.glColor3f(0f, 0f, 0f);
			myGL.glBegin(GL.GL_POLYGON);
			myGL.glVertex3f(0.45f, 0.475f, 0f);
			myGL.glVertex3f(0.2f, 0.475f, 0f);
			myGL.glVertex3f(0.45f, 0.5f, 0f);
			myGL.glVertex3f(0.2f, 0.5f, 0f);
			myGL.glEnd();
			
			myGL.glColor3f(0f, 0f, 0f);
			myGL.glBegin(GL.GL_POLYGON);
			
			myGL.glEnd();
			
			/*
			 * don't wait!  
			 * start processing buffered OpenGL routines 
			 */
			myGL.glFlush ();
		    }

		    public void keyboard (char key, int x, int y) {
			switch (key) {
			    case 27:
				System.exit(0);
			    default:
				break;
			}
			
		    }

		    private void myinit () {
			/* select clearing color */
			myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);

			/* initialize viewing values */
			myGL.glMatrixMode (GL.GL_PROJECTION);
			myGL.glLoadIdentity ();
			myGL.glOrtho (-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
		    }

		    /* 
		     * Declare initial window size, position, and display mode
		     * (single buffer and RGBA).  Open window with "hello"
		     * in its title bar.  Call initialization routines.
		     * Register callback function to display graphics.
		     * Enter main loop and process events.
		     */
		    public void init () {
			myUT.glutInitWindowSize (500, 500);
			myUT.glutInitWindowPosition (0, 0);
			myUT.glutCreateWindow (this);
			myinit ();
			myUT.glutDisplayFunc ("display");
			myUT.glutKeyboardFunc ("keyboard");
			myUT.glutMainLoop ();
		    }

		    static public void main (String args[]) throws IOException {
			Frame mainFrame = new Frame ();
			mainFrame.setSize (508, 508);
			Test mainCanvas = new Test ();
			mainCanvas.init();
			mainFrame.add (mainCanvas);
			mainFrame.setVisible (true);
		    }

}

