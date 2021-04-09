import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// must import jgl.GL....
import jgl.GL;
import jgl.GLU;
import jgl.GLAUX;

public class Robot_1  extends Applet
		   implements ComponentListener, KeyListener {
    // must use GL to use jGL.....
    // and use GLU to use the glu functions....
    // remember to give GL to initialize GLU
    // and use GLAUX to use the aux functions.....
    // remember to give GL to initialize GLAUX
    GL myGL = new GL ();
    GLU myGLU = new GLU (myGL);
    GLAUX myAUX = new GLAUX (myGL);

    private static int shoulder = 0, elbow = 0, head = 0, leg = 0, foot = 0;

    private void elbowAdd () { elbow = (elbow + 5) % 360; }
    private void elbowSubtract () { elbow = (elbow - 5) % 360; }
    private void shoulderAdd () { shoulder = (shoulder + 5) % 360; }
    private void shoulderSubtract () { shoulder = (shoulder - 5) % 360; }
    private void headAdd(){ head = (head + 5) % 360; }
    private void headSubstract(){ head = (head - 5) % 360; }
    private void legAdd(){leg = (leg + 5) % 360;}
    private void legSubtract(){leg = (leg - 5) % 360;}

    public void keyTyped (KeyEvent e) {}
    public void keyReleased (KeyEvent e) {}

    public void keyPressed (KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            shoulderSubtract ();
            elbowSubtract();
            display ();
            repaint ();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            shoulderAdd ();
            elbowAdd();
            display ();
            repaint ();
        } else if (keyCode == KeyEvent.VK_UP) {
            elbowAdd ();
            display ();
            repaint ();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            elbowSubtract ();
            display ();
            repaint ();
        }else if(keyCode == KeyEvent.VK_H){
        	headAdd();
        	display();
        	repaint();
        }else if(keyCode == KeyEvent.VK_J){
        	headSubstract();
        	display();
        	repaint();
        }else if(keyCode == KeyEvent.VK_L){
        	legAdd();
        	display();
        	repaint();
        }else if(keyCode == KeyEvent.VK_K){
	    	legSubtract();
	    	display();
	    	repaint();
	    }
        e.consume ();
    }

    private void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT);
	myGL.glColor3f (1.0f, 1.0f, 1.0f);
	myGL.glPushMatrix ();

//	    myGL.glTranslatef (-1.0f, 0.0f, 0.0f);
//	    myGL.glRotatef ((float)shoulder, 0.0f, 0.0f, 1.0f);
//	    myGL.glTranslatef (1.0f, 0.0f, 0.0f);
//	    myAUX.auxWireBox (2.0, 0.4, 1.0);	/* shoulder */
//
//	    myGL.glTranslatef (1.0f, 0.0f, 0.0f);
//	    myGL.glRotatef ((float)elbow, 0.0f, 0.0f, 1.0f);
//	    myGL.glTranslatef (1.0f, 0.0f, 0.0f);
//	    myAUX.auxWireBox (2.0, 0.4, 1.0);	/* elbow */
	
		myGL.glTranslatef(-1.0f, 0f, 0f);
		myGL.glTranslatef(1f, 0f, 0f);
		myAUX.auxWireBox(1, 1.5, 0.5); /* body */
		
	myGL.glPopMatrix ();
		
	myGL.glPushMatrix();
		myGL.glTranslatef(-.25f, .450f, 0f);
		myGL.glTranslatef(.25f, .45f, 0f);
		myAUX.auxWireBox(.3, .3, 0.2); /* neck */
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef(-.5f, 1.1f, 0f);
//		myGL.glRotatef ((float)head, 5.0f, .0f, .0f);
		myGL.glRotatef ((float)head, .0f, 2.0f, 5.0f);
		myGL.glTranslatef(.5f, .3f, 0f);
		myAUX.auxWireBox(.8, .8, 0.2); /* head */
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef (-1.0f, .55f, 0.0f);
	    myGL.glRotatef ((float)shoulder, -2.0f, -5.0f, 2.0f);
	    myGL.glTranslatef (.0f, 0.0f, 0.0f);
	    myAUX.auxWireBox (1.0, 0.4, .3);	/* shoulder left*/
    
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef (1.0f, .55f, 0.0f);
		myGL.glRotatef ((float)shoulder, .0f, .0f, -4.0f);
		myGL.glTranslatef (.0f, 0.0f, 0.0f);
		myAUX.auxWireBox (1.0, 0.4, .3);	/* shoulder right*/
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef (-1f, .55f, 0.0f);
	    myGL.glRotatef ((float)elbow, 2.0f, -5.0f, 3.0f);
		myGL.glTranslatef (-1.0f, 0.0f, 0.0f);
		myAUX.auxWireBox (1.0, 0.4, .3);	/* elbow left*/
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef (1f, .55f, 0.0f);
		myGL.glRotatef ((float)elbow, .0f, .0f, -4.0f);
		myGL.glTranslatef (1.f, 0.0f, 0.0f);
		myAUX.auxWireBox (1.0, 0.4, .3);	/* elbow right*/
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef (-.2f, -.55f, 0.0f);
		myGL.glRotatef ((float)leg, 2.0f, -5.0f, 3.0f);
		myGL.glTranslatef (-.2f, -.7f, 0.0f);
		myAUX.auxWireBox (.3, 1, .3);	/* leg left */
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef (.2f, -.55f, 0.0f);
		myGL.glRotatef ((float)leg, 2.0f, -5.0f, 3.0f);
		myGL.glTranslatef (.2f, -.7f, 0.0f);
		myAUX.auxWireBox (.3, 1, .3);	/* leg right */
	myGL.glPopMatrix();
	
	myGL.glPushMatrix();
		myGL.glTranslatef (.2f, -.75f, 0.0f);
		myGL.glRotatef ((float)elbow, 2.0f, -5.0f, 3.0f);
		myGL.glTranslatef (.2f, -1.5f, 0.0f);
		myAUX.auxWireBox (.3, 1, .3);	/* foot right */
	myGL.glPopMatrix();

	myGL.glPushMatrix();
		myGL.glTranslatef (-.2f, -.75f, 0.0f);
		myGL.glRotatef ((float)elbow, 2.0f, -5.0f, 3.0f);
		myGL.glTranslatef (-.2f, -1.5f, 0.0f);
		myAUX.auxWireBox (.3, 1, .3);	/* foot left */
	myGL.glPopMatrix();
	
	myGL.glFlush ();
    }

    private void myinit () {
	myGL.glShadeModel (GL.GL_FLAT);
    }

    public void componentMoved (ComponentEvent e) {}
    public void componentShown (ComponentEvent e) {}
    public void componentHidden (ComponentEvent e) {}

    public void componentResized (ComponentEvent e) {
        // get window width and height by myself
	myReshape (getSize ().width, getSize ().height);
        display ();
        repaint ();
    }

    private void myReshape (int w, int h) {
	myGL.glViewport (0, 0, w, h);
	myGL.glMatrixMode (GL.GL_PROJECTION);
	myGL.glLoadIdentity ();
	myGLU.gluPerspective (60.0, (double)w/(double)h, 1.0, 20.0);
	myGL.glMatrixMode (GL.GL_MODELVIEW);
	myGL.glLoadIdentity ();
	myGL.glTranslatef (0.0f, 0.0f, -5.0f);
    }

    public void update (Graphics g) {
    	// skip the clear screen command....
	paint (g);
    }

    public void paint (Graphics g) {
	myGL.glXSwapBuffers (g, this);
    }

    public void init () {
	myAUX.auxInitPosition (0, 0, 400, 400);
	myAUX.auxInitWindow (this);
	myinit ();

	// as call auxKeyFunc()
        addKeyListener(this);

        // as call auxReshapeFunc()
        addComponentListener (this);
	myReshape (getSize ().width, getSize ().height);

        // call display as call auxIdleFunc(display)
	display ();
    }

}
