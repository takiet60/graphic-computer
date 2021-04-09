package robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Frame;

import javax.imageio.ImageIO;

import jgl.GLCanvas;
import jgl.GL;

public class Robot extends GLCanvas {
	/* Create checkerboard texture */
	private int body = 0, head = 0, shoulder = 0, neck = 0, elbow = 0, feetL = 0, feetR = 0, legL = 0, legR = 0;
	private static final int width = 256;
	private static final int height = 256;
	private static double c = 0, v = 0, v2 = 0;
	private static boolean direction = true;
	private static double a = 0;
	private int texName[] = new int[5];
	private byte image1[][][] = new byte[width][height][4];
	private byte image2[][][] = new byte[width][height][4];
	private byte image3[][][] = new byte[width][height][4];
	private byte image4[][][] = new byte[width][height][4];
	private byte image5[][][] = new byte[width][height][4];

	private void makeImage() throws IOException {
		File f = new File("src//robot//BedRoom_Center.bmp");
		BufferedImage s1 = ImageIO.read(f);
		f = new File("src//robot//BedRoom_Top.bmp");
		BufferedImage s2 = ImageIO.read(f);
		f = new File("src//robot//BedRoom_Bottom.bmp");
		BufferedImage s3 = ImageIO.read(f);
		f = new File("src//robot//BedRoom_Left.bmp");
		BufferedImage s4 = ImageIO.read(f);
		f = new File("src//robot//BedRoom_Right.bmp");
		BufferedImage s5 = ImageIO.read(f);

		for (int k = 0; k < width; k++) {
			for (int k2 = 0; k2 < height; k2++) {
				Color co = new Color(s1.getRGB(k, k2));
				image1[k][k2][0] = (byte) co.getRed();
				image1[k][k2][1] = (byte) co.getGreen();
				image1[k][k2][2] = (byte) co.getBlue();
				image1[k][k2][3] = (byte) 255;
				co = new Color(s2.getRGB(k, k2));
				image2[k][k2][0] = (byte) co.getRed();
				image2[k][k2][1] = (byte) co.getGreen();
				image2[k][k2][2] = (byte) co.getBlue();
				image2[k][k2][3] = (byte) 255;
				co = new Color(s3.getRGB(k, k2));
				image3[k][k2][0] = (byte) co.getRed();
				image3[k][k2][1] = (byte) co.getGreen();
				image3[k][k2][2] = (byte) co.getBlue();
				image3[k][k2][3] = (byte) 255;
				co = new Color(s4.getRGB(k, k2));
				image4[k][k2][0] = (byte) co.getRed();
				image4[k][k2][1] = (byte) co.getGreen();
				image4[k][k2][2] = (byte) co.getBlue();
				image4[k][k2][3] = (byte) 255;
				co = new Color(s5.getRGB(k, k2));
				image5[k][k2][0] = (byte) co.getRed();
				image5[k][k2][1] = (byte) co.getGreen();
				image5[k][k2][2] = (byte) co.getBlue();
				image5[k][k2][3] = (byte) 255;
			}
		}
	}

	private void myinit() throws IOException {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
		myGL.glEnable(GL.GL_DEPTH_TEST);

		makeImage();
		myGL.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
		// Center
		myGL.glGenTextures(5, texName);
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image1);
		// Top
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image2);
		myGL.glEnable(GL.GL_TEXTURE_2D);
		// Bottom
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image3);
		myGL.glEnable(GL.GL_TEXTURE_2D);

		// Left
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image4);
		myGL.glEnable(GL.GL_TEXTURE_2D);
		// Right
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);

		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image5);
		myGL.glEnable(GL.GL_TEXTURE_2D);

	}

	public void display() {

		float no_mat[]={0.0f,0.0f,0.0f,1.0f};
		float mat_ambient[]={0.7f,0.7f,0.7f,1.0f};
		float mat_ambient_color[]={0.8f,0.8f,0.2f,1.0f};
		float mat_diffuse[]={0.1f,0.5f,0.8f,1.0f};
		float mat_specular[]={1.0f,1.0f,1.0f,1.0f};
		float low_shininess[]={5.0f};
		float high_shininess[]={100.0f};
		float mat_emission[]={0.3f,0.2f,0.2f,0.0f};
		float mat_shininess[] = { 50.0f };
		float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };

		myGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, low_shininess);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, mat_emission);

		// Center
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
		myGL.glBegin(GL.GL_QUADS);
		myGL.glTexCoord2f(0.0f, 0.0f);
		myGL.glVertex3f(-2, 2.0f, -20f);

		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, -20f);

		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(2f, -2.0f, -20f);

		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(2.0f, 2.0f, -20f);

		myGL.glEnd();
// Top
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(-2.0f, 2.0f, 1f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, 2.0f, -20f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(2.0f, 2.0f, -20f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(2.0f, 2.0f, 1f);
		myGL.glEnd();
		// bottom
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(-2.0f, -2.0f, -20f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, 1f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(2.0f, -2.0f, 1f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(2.0f, -2.0f, -20f);
		myGL.glEnd();
		// left
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(-2.0f, 2.0f, 1f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, 1f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, -20f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(-2.0f, 2.0f, -20f);

		myGL.glEnd();

		// right
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(1.95f, 2.0f, -20f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(1.950f, -2.0f, -20f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(1.950f, -2.00f, 1f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(1.95f, 2.00f, 1f);

		myGL.glEnd();
		myGL.glFlush();

		// body
		myGL.glColor3f (1.f, 1.f, 1.0f);
		myGL.glPushMatrix();
		myGL.glRotatef((float) body, 0f, 0f, 0.f);
		myGL.glScaled(0.15, 0.15, 0.15);
//		myGL.glTranslated(0, -4, a + -20);
		myGL.glTranslated(0, -4, -30);
		myGL.glPushMatrix();
		myGL.glScalef(1.0f, 1.5f, .5f);
		myUT.glutSolidCube(1.0);

		myGL.glPopMatrix();

		myGL.glPushMatrix();
//		// neck 
		myGL.glTranslatef(.0f, .75f, 0.0f);
		myGL.glRotatef((float) neck, 1.0f, 0.0f, .0f);
		myGL.glTranslatef(.0f, .0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(.25f, .25f, .25f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		// nón
		myGL.glTranslatef(.0f, .35f, 0.0f);
		myGL.glRotatef((float) head, 1.0f, 0.0f, .0f);
		myGL.glTranslatef(.0f, 1.f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(.5f, .5f, .25f);
		myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_color);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, high_shininess);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, no_mat);
		myUT.glutSolidTorus(1, 1, 2, 10);
		myGL.glPopMatrix();
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		// head
		myGL.glTranslatef(.0f, .35f, 0.0f);
		myGL.glRotatef((float) head, 1.0f, 0.0f, .0f);
		myGL.glTranslatef(.0f, 1.f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.f, 1.f, .25f);

		myGL.glMaterialfv (GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, high_shininess);
 	    myGL.glMaterialfv (GL.GL_FRONT, GL.GL_EMISSION, no_mat);
		myUT.glutSolidSphere(.5, 32, 32);
		myGL.glPopMatrix();
		myGL.glPopMatrix();
		 myGL.glPushMatrix();
		 	// left shoulder
		 	 		myGL.glTranslatef(-.5f, .50f, 0.0f);
		 	 		myGL.glRotatef((float) shoulder, .0f, 1.f, .0f);
		 	 		myGL.glTranslatef(-.5f, .0f, 0.0f);
		 	 		myGL.glPushMatrix();
		 	 		myGL.glScalef (1.f, .4f, .25f);
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
			myUT.glutSolidCube (1.0);
		    myGL.glPopMatrix ();
		    
		 // right leg
	    	myGL.glTranslatef(-.0f, -.50f, .0f);
			myGL.glRotatef((float) legR, 1.f, 0.f, 0.f);
	    	myGL.glTranslatef(-.0f, -.50f, 0.f);
		    myGL.glPushMatrix();
			myGL.glScalef (.3f, 1.f, .25f);
			myUT.glutSolidCube (1.0);
			myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
			myGL.glMaterialfv (GL.GL_FRONT, GL.GL_SHININESS, mat_shininess);
			myGL.glLightfv (GL.GL_LIGHT0, GL.GL_POSITION, light_position);

			myGL.glEnable (GL.GL_LIGHTING);
			myGL.glEnable (GL.GL_LIGHT0);
			myGL.glEnable (GL.GL_DEPTH_TEST);
			 
		    myGL.glPopMatrix ();
	    myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(60.0, 1.0 * (double) w / (double) h, 1.0, 30.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		load();
	}

	public void load() {
		myGL.glLoadIdentity();
//		myGLU.gluLookAt(0, v2 + .8, -3 + v, c, 0, v + -5, 0, 1, 0);
		myGLU.gluLookAt(0, 0.8, -3 , 0, 0, -5, 0, 1, 0);
		myGL.glTranslatef(0.0f, 0.0f, -2f);
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {

		case 's':
			shoulder = (shoulder + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'S':
			shoulder = (shoulder - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'e':
			elbow = (elbow + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'E':
			elbow = (elbow - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'b':
			body = (body + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'B':
			body = (body - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'h':
			head = (head + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'H':
			head = (head - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'n':
			neck = (neck + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'N':
			neck = (neck - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'f':
			feetL = (feetL + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'F':
			feetL = (feetL - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'l':
			legL = (legL + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'y':
		case 'Y':
			myGL.glRotatef(30.0f, 0.0f, 1.0f, 0.0f);
			myUT.glutPostRedisplay();
			break;
		case 'L':
			legL = (legL - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'i':
		case 'I':
			myGL.glLoadIdentity();
			myGLU.gluLookAt(2.0, 2.0, 10.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
			myUT.glutPostRedisplay();
			break;
		case 'q':
		case 'Q':
			myGL.glLoadIdentity();
			myGLU.gluLookAt(0.0, 0.0, 10.0, 0.0, .0, 2.0, 0.0, 1.0, 0.0);
			myUT.glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		default:
			break;
		}
	}

	public void init() throws IOException {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutReshapeFunc("myReshape");
		myUT.glutDisplayFunc("display");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}
	
	

	public void animate() throws InterruptedException {
		while (true) {
			v -= 0.1;
			a -= 0.8;
			if (direction) {
				shoulder += 15;
				feetL += 10;
				feetR -= 10;
				legL -= 5;
				legR += 5;
				if (shoulder >= 30) {
					direction = false;
				}
			}
			if (!direction) {
				shoulder -= 15;
				feetL -= 10;
				feetR += 10;
				legL += 5;
				legR -= 5;
				if (shoulder <= -30) {
					direction = true;
				}

			}
			body = (body + 20) % 360;
			Thread.sleep(50);
			load();

			myUT.glutPostRedisplay();
		}
	}

	
	static public void main(String args[]) throws IOException, InterruptedException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		Robot mainCanvas = new Robot();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
		mainCanvas.animate();
	}
}
