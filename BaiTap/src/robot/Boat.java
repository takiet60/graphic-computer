package robot;

/*
 *  bezmesh.java
 *  This program renders a lighted, filled Bezier surface,
 *  using two-dimensional evaluators.
 */

import java.awt.Color;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;
public class Boat extends GLCanvas{
	private static final int width = 256;
	private static final int height = 256;
	private int texName[] = new int[5];
	private static double c = 0, v = 0, v2 = 0;
	private static boolean direction = true;
	private byte image1[][][] = new byte[width][height][4];
	private byte image2[][][] = new byte[width][height][4];
	private byte image3[][][] = new byte[width][height][4];
	private byte image4[][][] = new byte[width][height][4];
	private byte image5[][][] = new byte[width][height][4];
	private static double a = 0;
	private static final float ctrlpoints [][][] ={
			{{-2.5f, -.2f,  3.0f},{-.5f, -2.f,  2.0f},{ .5f, -2.f, 2.0f},{ 2.5f, -.2f,  3.0f}},
			{{-2.5f, -0.1f,  2.0f},{-0.5f, -1.5f,  -1.0f},{ 0.5f, -0.5f,  -1.0f},{ 2.5f, -0.1f, 2.0f}},
			{{-2.5f,  0.1f,  2.0f},{-0.5f,  1.5f,  -1.0f},{ 0.5f,  0.5f,  -1.0f},{ 2.5f, 0.1f,  2.0f}},
			{{-2.5f,  0.2f, 3.0f},{-0.5f,  2.f, 2.0f},{ 0.5f,  2.f,  2.0f},{ 2.5f,  0.2f, 3.0f}}};
	private int rotateX = 0, rotateY = 0, rotateZ = 0;
	
	private void makeCheckImage() throws IOException {

		File f = new File("src//robot//Scene2.bmp");
		BufferedImage s1 = ImageIO.read(f);
		f = new File("src//robot//Scene1.bmp");
		BufferedImage s2 = ImageIO.read(f);
		f = new File("src//robot//Scene3.bmp");
		BufferedImage s3 = ImageIO.read(f);
		f = new File("src//robot//Scene4.bmp");
		BufferedImage s4 = ImageIO.read(f);
		f = new File("src//robot//Scene5.bmp");
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


	private void initlights() {

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

	public void display() throws InterruptedException {
		float mat_ambient[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		float mat_diffuse[] = { 0.1f, 0.5f, 0.8f, 1.0f };
		float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float low_shininess[] = { 10.0f };
		float mat_emission[] = { 0.3f, 0.2f, 0.2f, 0.0f };
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		// draw boat

		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_specular);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, low_shininess);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, mat_emission);
		
		myGL.glPushMatrix ();
		
//		myGL.glTranslatef(0.5f, 0f,(float) a + -0.05f);
		myGL.glTranslatef(0.5f, 0f,-10f);
		
//		
		myGL.glRotatef((float) rotateX, 0, 1, 0);
		myGL.glRotatef(-100, 200, 100, 20);
		myGL.glScalef(0.3f, 0.3f, 0.3f);
		myGL.glEvalMesh2 (GL.GL_FILL, 0, 20, 0, 20);
		myGL.glPopMatrix ();
		
		myGL.glFlush ();
		
		// draw room
		// Center
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
		myGL.glBegin(GL.GL_QUADS);
		myGL.glTexCoord2f(0.0f, 0.0f);
		myGL.glVertex3f(-2, 2.0f, -25f);

		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, -25f);

		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(2f, -2.0f, -25f);

		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(2.0f, 2.0f, -25f);

		myGL.glEnd();
		// Top
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(-2.0f, 2.0f, 1f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, 2.0f, -25f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(2.0f, 2.0f, -25f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(2.0f, 2.0f, 1f);
		myGL.glEnd();
		// bottom
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(-2.0f, -2.0f, -25f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, 1f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(2.0f, -2.0f, 1f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(2.0f, -2.0f, -25f);
		myGL.glEnd();
		// left
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(-2.0f, 2.0f, 1f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, 1f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(-2.0f, -2.0f, -25f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(-2.0f, 2.0f, -25f);

		myGL.glEnd();

		// right
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);
		myGL.glBegin(GL.GL_QUADS);

		myGL.glTexCoord2f(0.0f, 0.0f);

		myGL.glVertex3f(1.95f, 2.0f, -25f);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(1.950f, -2.0f, -25f);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(1.950f, -2.00f, 1f);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(1.95f, 2.00f, 1f);

		myGL.glEnd();

		myGL.glFlush();
	}

	private void myinit() throws IOException {
		myGL.glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
		myGL.glEnable (GL.GL_DEPTH_TEST);
		myGL.glMap2f (GL.GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
			      0.0f, 1.0f, 12, 4, ctrlpoints);
		myGL.glEnable (GL.GL_MAP2_VERTEX_3);
		myGL.glEnable (GL.GL_AUTO_NORMAL);
		myGL.glEnable (GL.GL_NORMALIZE);
		myGL.glMapGrid2f (20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
		initlights();
		makeCheckImage();
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

//		myGL.glDisable(GL.GL_TEXTURE_2D);
		myGL.glClearColor(0.0f, 0.1f, 0.1f, 0.0f);
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(60.0, 1.0 * (double) w / (double) h, 1.0, 100.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		load();
	}

	

	public void load() {
		myGL.glLoadIdentity();
//		myGLU.gluLookAt(0, v2 + 0.8, -3 + v, c, 0, v + -5, 0, 1, 0);
		myGLU.gluLookAt(0, 0.8, -3 , 0, 0, -5, 0, 1, 0);
		myGL.glTranslatef(0.0f, 0.0f, -2f);
	}

	public void animate() throws InterruptedException {
	while (true) {
		v -= 0.1;
		a -= 0.2;
		if (direction) {
			rotateX += 10;
			if(rotateX <= 30) {
				direction = false;
			}
		}
		if (!direction) {
			rotateX += 10;
			if (rotateX >= -30) {
				direction = true;
			}

		}
		Thread.sleep(50);
		load();

		myUT.glutPostRedisplay();
	}
}
	
	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 27:
			System.exit(0);
			break;
		case 'x':
			rotateX = (rotateX + 1) % 360;
			myUT.glutPostRedisplay ();
			System.out.println("x"  + rotateX);
			break;
		case 'X':
			rotateX = (rotateX - 1) % 360;
			myUT.glutPostRedisplay ();
			System.out.println("x"  + rotateX);
			break;
		case 'y':
			rotateY = (rotateY + 1) % 360;
			myUT.glutPostRedisplay ();
			System.out.println("y"  + rotateY);
			break;
		case 'Y':
			rotateY = (rotateY - 1) % 360;
			myUT.glutPostRedisplay ();
			System.out.println("y"  + rotateY);
			break;
		case 'z':
			rotateZ = (rotateZ + 1) % 360;
			myUT.glutPostRedisplay ();
			break;

		case 'Z':
			rotateZ = (rotateZ - 1) % 360;
			myUT.glutPostRedisplay ();
			break;
		default:
			break;

		}

	}

	public void mouse(int button, int state, int x, int y) throws InterruptedException {
		if (button == GLUT.GLUT_LEFT_BUTTON) {
			if (state == GLUT.GLUT_DOWN) {
			}
		} else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
			if (state == GLUT.GLUT_DOWN) {
				myUT.glutIdleFunc(null);
			}
		}
	}


	public void init() throws IOException {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutReshapeFunc("myReshape");
		myUT.glutDisplayFunc("display");
		myUT.glutMouseFunc("mouse");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
		initlights();

	}

	static public void main(String args[]) throws IOException, InterruptedException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		Boat mainCanvas = new Boat();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
		mainCanvas.animate();
	}

}
