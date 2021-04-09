import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Sun2 extends GLCanvas {
	public void display() {
		/* clear all pixels */
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);

		/*
		 * draw white polygon (rectangle) with corners at (0.25, 0.25, 0.0) and
		 * (0.75, 0.75, 0.0)
		 */
		int count = 1;

		myGL.glColor3f(1f, 1f, 1f);
		myGL.glBegin(GL.GL_POLYGON);
		for (int i = 0; i < 360; i++) {
			float x = (float) (Math.cos(i) * 0.4);
			float y = (float) (Math.sin(i) * 0.4);
			myGL.glVertex3f(x, y, 0);
		}
		myGL.glEnd();

		myGL.glColor3f(.5f, .5f, .5f);
		myGL.glBegin(GL.GL_POLYGON);
		for (int i = 30; i <= 390; i += 60) {
			float x1 = (float) (Math.cos(i * Math.PI / 180) * 0.2);
			float y1 = (float) (Math.sin(i * Math.PI / 180) * 0.2);
			myGL.glVertex3f(x1, y1, 0);
			float x2 = (float) (Math.cos(count * 60 * Math.PI / 180) * 0.4);
			float y2 = (float) (Math.sin(count * 60 * Math.PI / 180) * 0.4);
			myGL.glVertex3f(x2, y2, 0);
			count++;
		}
		myGL.glEnd();
		
		myGL.glColor3f(0f, 0f, 0f);
		myGL.glBegin(GL.GL_POLYGON);
		for (int i = 0; i < 360; i++) {
			float x = (float) (Math.cos(i) * 0.2);
			float y = (float) (Math.sin(i) * 0.2);
			myGL.glVertex3f(x, y, 0);
		}
		myGL.glEnd();
		/*
		 * don't wait! start processing buffered OpenGL routines
		 */
		myGL.glFlush();
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
		default:
			break;
		}

	}

	private void myinit() {
		/* select clearing color */
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		/* initialize viewing values */
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
	}

	/*
	 * Declare initial window size, position, and display mode (single buffer
	 * and RGBA). Open window with "hello" in its title bar. Call initialization
	 * routines. Register callback function to display graphics. Enter main loop
	 * and process events.
	 */
	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		Sun2 mainCanvas = new Sun2();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}
}
