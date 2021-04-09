import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Sun extends GLCanvas{
	public void display () {
		/* clear all pixels */
		myGL.glClear (GL.GL_COLOR_BUFFER_BIT);

		/*
		 * draw white polygon (rectangle) with corners at
		 * (0.25, 0.25, 0.0) and (0.75, 0.75, 0.0)  
		 */
		myGL.glColor3f (1f, 1f, 1f);
		myGL.glBegin (GL.GL_LINE_LOOP);
		float r1 = 0.2f;
		float r2 = 0.4f;
		for(int i = 0; i < 360; i++){
			for(int j = 0; j < 6; j++){
				if(i == j * 60){
					float x = (float) (Math.cos(Math.PI * (double) (i / 180)) * r1);
					float y = (float) (Math.sin(Math.PI * (double) (i / 180)) * r1);
					myGL.glVertex3f(x, y, 0);
					
					float a = (float) Math.cos(Math.PI * ((double)(i / 180) - Math.PI / 6)) * r2;
					float b = (float) Math.sin(Math.PI * ((double)(i / 180) - Math.PI / 6)) * r2;
					myGL.glVertex3f(a, b, 0);
				}
				
			}
			
		}
		
		myGL.glEnd ();
		

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
		mainFrame.setSize (508, 527);
		Sun mainCanvas = new Sun ();
		mainCanvas.init();
		mainFrame.add (mainCanvas);
		mainFrame.setVisible (true);
	    }
}
