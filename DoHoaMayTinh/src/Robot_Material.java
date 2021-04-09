import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLU;
import jgl.glu.GLUquadricObj;

public class Robot_Material extends GLCanvas{
	private int startList;

	private int body = 0, head = 0, shoulder = 0, neck = 0, elbow = 0, feetL = 0, feetR = 0, legL = 0, legR = 0 ;
	
    public void errorCallback (int errorCode) {
	String estring;

	estring = myGLU.gluErrorString (errorCode);
	System.err.println ("Quadric Error: "+estring);
    }

    private void myinit () {
	GLUquadricObj qobj;
	float mat_ambient[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	float mat_shininess[] = { 50.0f };
	float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };
	float model_ambient[] = { 0.5f, 0.5f, 0.5f, 1.0f };

	myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);

	myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
	myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
	myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, mat_shininess);
	myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, light_position);
	myGL.glLightModelfv (GL.GL_LIGHT_MODEL_AMBIENT, model_ambient);

	myGL.glEnable (GL.GL_LIGHTING);
	myGL.glEnable (GL.GL_LIGHT0);
	myGL.glEnable (GL.GL_DEPTH_TEST);

	/*  Create 4 display lists, each with a different quadric object.
	 *  Different drawing styles and surface normal specifications
	 *  are demonstrated.
	 */
	startList = myGL.glGenLists (4);
	qobj = myGLU.gluNewQuadric();
	myGLU.gluQuadricCallback (qobj, myGLU.GLU_ERROR, "errorCallback");

	myGLU.gluQuadricDrawStyle (qobj, GLU.GLU_FILL); /* smooth shaded */
	myGLU.gluQuadricNormals (qobj, GLU.GLU_SMOOTH);
	myGL.glNewList (startList, GL.GL_COMPILE);
	    myGLU.gluSphere (qobj, 0.75, 35, 30);
	myGL.glEndList();

	myGLU.gluQuadricDrawStyle (qobj, GLU.GLU_FILL); /* flat shaded */
	myGLU.gluQuadricNormals (qobj, GLU.GLU_FLAT);
	myGL.glNewList (startList+1, GL.GL_COMPILE);
	    myGLU.gluCylinder (qobj, 1.5, 0.3, 1.0, 15, 5);
	myGL.glEndList();

	myGLU.gluQuadricDrawStyle (qobj, GLU.GLU_LINE); /* all polygons wireframe */
	myGLU.gluQuadricNormals (qobj, GLU.GLU_NONE);
	myGL.glNewList (startList+2, GL.GL_COMPILE);
	    myGLU.gluDisk (qobj, 0.25, 1.0, 20, 4);
	myGL.glEndList();

	myGLU.gluQuadricDrawStyle (qobj, GLU.GLU_SILHOUETTE); /* boundary only  */
	myGLU.gluQuadricNormals (qobj, GLU.GLU_NONE);
	myGL.glNewList (startList+3, GL.GL_COMPILE);
	    myGLU.gluPartialDisk (qobj, 0.0, 1.0, 20, 4, 0.0, 225.0);
	myGL.glEndList();
    }

    public void display () {
    	float no_mat[]={0.0f,0.0f,0.0f,1.0f};
    	float mat_ambient[]={0.7f,0.7f,0.7f,1.0f};
    	float mat_ambient_color[]={1.f,0.8f,0.2f,1.0f};
    	float mat_diffuse[]={0.1f,0.5f,0.8f,1.0f};
    	float mat_specular[]={1.0f,1.0f,1.0f,1.0f};
    	float no_shininess[]={0.0f};
    	float low_shininess[]={5.0f};
    	float high_shininess[]={100.0f};
    	float mat_emission[]={0.3f,0.2f,0.2f,0.0f};
    	myGL.glClear (GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

    	myGL.glPushMatrix ();
		// body 
		myGL.glTranslatef(.0f, -0.5f, 0.0f);
		myGL.glRotatef((float) body, 0.0f, 1.0f, .0f);
		myGL.glTranslatef(.0f, .75f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef (1.0f, 1.5f, .5f);
		myUT.glutSolidCube (1.0);
	    myGL.glPopMatrix ();
	    
	    myGL.glPushMatrix();
	 // neck 
	  		myGL.glTranslatef(.0f, .75f, 0.0f);
	  		myGL.glRotatef((float) neck, 1.0f, 0.0f, .0f);
	  		myGL.glTranslatef(.0f, .0f, 0.0f);
	  		myGL.glPushMatrix();
	  		myGL.glScalef (.25f, .25f, .25f);
	  		 myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, high_shininess);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, no_mat);
	  		myUT.glutSolidCube (1.0);
	  	    myGL.glPopMatrix ();
	  	 myGL.glPopMatrix();
	  	myGL.glPushMatrix();
	    	// nón
	 		myGL.glTranslatef(.0f, .35f, 0.0f);
	 		myGL.glRotatef((float) head, 1.0f, 0.0f, .0f);
	 		myGL.glTranslatef(.0f, 1.f, 0.0f);
	 		myGL.glPushMatrix();
	 		myGL.glScalef (.5f, .5f, .25f);
	 		myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_color);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, high_shininess);
	 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, no_mat);
	 		myUT.glutSolidTorus(1, 1, 2, 10);;
	 	    myGL.glPopMatrix ();
	    myGL.glPopMatrix();
 	    
 	    myGL.glPushMatrix();
 	    	// head
 	 		myGL.glTranslatef(.0f, .35f, 0.0f);
 	 		myGL.glRotatef((float) head, 1.0f, 0.0f, .0f);
 	 		myGL.glTranslatef(.0f, 1.f, 0.0f);
 	 		myGL.glPushMatrix();
 	 		myGL.glScalef (1.f, 1.f, .25f);
 	 		myUT.glutSolidSphere (.5, 32, 32	);
 	 	    myGL.glPopMatrix ();
 	    myGL.glPopMatrix();
 	    
 	    myGL.glPushMatrix();
 	// left shoulder
 	 		myGL.glTranslatef(-.2f, .50f, 0.0f);
 	 		myGL.glRotatef((float) shoulder, .0f, 1.f, .0f);
 	 		myGL.glTranslatef(-.5f, .0f, 0.0f);
 	 		myGL.glPushMatrix();
 	 		myGL.glScalef (.7f, .4f, .25f);
 	 		myUT.glutSolidCube (1.0);
 	 	    myGL.glPopMatrix ();
 	 	    
 	 	    // left elbow
 	 		myGL.glTranslatef(-.5f, .0f, 0.0f);
 	 		myGL.glRotatef((float) elbow, .0f, 1.0f, .0f);
 	 		myGL.glTranslatef(-.5f, .0f, 0.0f);
 	 		myGL.glPushMatrix();
 	 		myGL.glScalef (1.f, .4f, .25f);
	 		myUT.glutSolidTorus(1., -.75, 2, 3);
 	 	    myGL.glPopMatrix ();
 	 	 myGL.glPopMatrix();
 	    
 	    
 	 	myGL.glPushMatrix();
 	 	 // right shoulder
	  	    myGL.glTranslatef(0.5f, .5f, 0.f);
	  	    myGL.glRotatef((float) shoulder, 0.f, -1.f, .0f);
	  	    myGL.glTranslatef(.5f, 0.f, 0.f);
	  	    myGL.glPushMatrix();
	 		myGL.glScalef (1.f, .4f, .25f);
	 		myUT.glutSolidCube(1);
	 	    myGL.glPopMatrix ();
	 	    
	 	    // right elbow
	  	    myGL.glTranslatef(0.5f, .0f, 0.f);
	  	    myGL.glRotatef((float) elbow, 0.f, -1.f, .0f);
	  	    myGL.glTranslatef(0.5f, 0.f, 0.f);
	  	    myGL.glPushMatrix();
	 		myGL.glScalef (1.f, .4f, .25f);
	 		myUT.glutSolidTorus(.8, .75, 2, 3);
	 	    myGL.glPopMatrix ();
	 	myGL.glPopMatrix();
 	 	 
	    
	    
	    myGL.glPushMatrix();
	    	// left feet
	    	myGL.glTranslatef(-.25f, -.75f, .0f);
	    	myGL.glRotatef((float) feetL, 1.f, 0.f, 0.f);
	    	myGL.glTranslatef(-.0f, -.5f, 0.f);
		    myGL.glPushMatrix();
			myGL.glScalef (.3f, 1.f, .25f);
			myUT.glutSolidCube (1.0);
		    myGL.glPopMatrix ();
		    
		 // left leg
	    	myGL.glTranslatef(-.0f, -.50f, .0f);
	    	myGL.glRotatef((float) legL, 1.f, 0.f, 0.f);
	    	myGL.glTranslatef(-.0f, -.50f, 0.f);
		    myGL.glPushMatrix();
			myGL.glScalef (.3f, 1.f, .25f);
			myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_color);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, no_shininess);
		    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
			myUT.glutSolidCube (1.0);
		    myGL.glPopMatrix ();
	    myGL.glPopMatrix();
	    
	    myGL.glPushMatrix();
    	// right feet
    	myGL.glTranslatef(.25f, -.75f, .0f);
		myGL.glRotatef((float) feetR, 1.f, 0.f, 0.f);
    	myGL.glTranslatef(-.0f, -.5f, 0.f);
	    myGL.glPushMatrix();
		myGL.glScalef (.3f, 1.f, .25f);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_color);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, low_shininess);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, no_mat);
		myUT.glutSolidCube (1.0);
	    myGL.glPopMatrix ();
	    
	 // right leg
    	myGL.glTranslatef(-.0f, -.50f, .0f);
		myGL.glRotatef((float) legR, 1.f, 0.f, 0.f);
    	myGL.glTranslatef(-.0f, -.50f, 0.f);
	    myGL.glPushMatrix();
		myGL.glScalef (.3f, 1.f, .25f);
		myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_color);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, no_mat);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, no_shininess);
	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
		myUT.glutSolidCube (1.0);
		 
	    myGL.glPopMatrix ();
    myGL.glPopMatrix();

	myGL.glPopMatrix ();
	myGL.glFlush ();
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
	myUT.glutInitWindowSize (600, 600);
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
	mainFrame.setSize (608, 627);
	Robot_Material mainCanvas = new Robot_Material ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }
}
