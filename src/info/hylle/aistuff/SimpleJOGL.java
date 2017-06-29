//package info.hylle.aistuff;
//
//import com.sun.opengl.util.Animator;
//import info.hylle.aistuff.actors.Actor;
//import info.hylle.aistuff.behaviours.AvoidBehaviour;
//import info.hylle.aistuff.behaviours.Behaviour;
//import info.hylle.aistuff.behaviours.SeekDistanceBehaviour;
//import info.hylle.aistuff.behaviours.WanderBehaviour;
//import info.hylle.aistuff.geometry.Vector3d;
//import info.hylle.aistuff.utils.Colorfilter;
//import info.hylle.aistuff.utils.InputHandler;
//
//import javax.imageio.ImageIO;
//import javax.media.opengl.GL;
//import javax.media.opengl.GLAutoDrawable;
//import javax.media.opengl.GLCanvas;
//import javax.media.opengl.GLEventListener;
//import javax.media.opengl.glu.GLU;
//import java.awt.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//
//
///**
// * SimpleJOGL.java <BR>
// * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
// * <p/>
// * This version is equal to Brian Paul's version 1.2 1999/10/21
// */
//public class SimpleJOGL implements GLEventListener {
//    java.util.List<Actor> actors = new ArrayList<Actor>();
//    java.util.List<Actor> obstacles = new ArrayList<Actor>();
//    private static final int NUMBER_OF_DRONES = 200;
//
//    public static float minX = 0;
//    public static float maxX = 6.5f;
//    public static float minY = 0;
//    public static float maxY = 4.5f;
//
//
//    private boolean lightingEnabled;    // Lighting ON/OFF
//    private boolean lightingChanged = false;  // Lighting changed
//    private boolean blendingEnabled;    // Blending OFF/ON
//    private boolean blendingChanged = false;  // Blending changed
//
//    private int filter;        // Which texture to use
//    private int[] textures = new int[3];  // Storage For 3 Textures
//
//    private float xrot;        // X Rotation
//    private float yrot;        // Y Rotation
//
//    private float xspeed = 0.00f;    // X Rotation Speed
//    private boolean increaseX;
//    private boolean decreaseX;
//
//    private float yspeed = 0.00f;    // Y Rotation Speed
//    private boolean increaseY;
//    private boolean decreaseY;
//
//    private float z = -5.0f;      // Depth Into The Screen
//    private boolean zoomIn;
//    private boolean zoomOut;
//
//    private float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
//    private float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
//    private float[] lightPosition = {0.0f, 0.0f, 2.0f, 1.0f};
//    private static GLCanvas canvas;
//
//    private void update() {
//        if (zoomOut) {
//            z -= 0.05f;
//        }
//        if (zoomIn) {
//            z += 0.05f;
//        }
//        if (decreaseX) {
//            xspeed -= 0.01f;
//        }
//        if (increaseX) {
//            xspeed += 0.01f;
//        }
//        if (increaseY) {
//            yspeed += 0.01f;
//        }
//        if (decreaseY) {
//            yspeed -= 0.01f;
//        }
//    }
//
//    public void toggleBlending() {
//        blendingEnabled = !blendingEnabled;
//        blendingChanged = true;
//    }
//
//    public void toggleLighting() {
//        lightingEnabled = !lightingEnabled;
//        lightingChanged = true;
//    }
//
//    public void increaseXspeed(boolean increase) {
//        increaseX = increase;
//    }
//
//    public void decreaseXspeed(boolean decrease) {
//        decreaseX = decrease;
//    }
//
//    public void increaseYspeed(boolean increase) {
//        increaseY = increase;
//    }
//
//    public void decreaseYspeed(boolean decrease) {
//        decreaseY = decrease;
//    }
//
//    public void zoomIn(boolean zoom) {
//        zoomIn = zoom;
//    }
//
//    public void zoomOut(boolean zoom) {
//        zoomOut = zoom;
//    }
//
//    public void switchFilter() {
//        filter = (filter + 1) % textures.length;
//    }
//
////    private void loadGLTextures(GLAutoDrawable gldrawable) throws IOException {
////
////        TextureReader.Texture texture =
////                TextureReader.readTexture("demos/data/images/glass.png");
////
////        GL gl = gldrawable.getGL();
////
////        //Create Nearest Filtered Texture
////        gl.glGenTextures(3, textures, 0);
////        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]);
////
////        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
////        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
////
////        gl.glTexImage2D(GL.GL_TEXTURE_2D,
////                0,
////                3,
////                texture.getWidth(),
////                texture.getHeight(),
////                0,
////                GL.GL_RGB,
////                GL.GL_UNSIGNED_BYTE,
////                texture.getPixels());
////
////        //Create Linear Filtered Texture
////        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[1]);
////        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
////        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
////
////        gl.glTexImage2D(GL.GL_TEXTURE_2D,
////                0,
////                3,
////                texture.getWidth(),
////                texture.getHeight(),
////                0,
////                GL.GL_RGB,
////                GL.GL_UNSIGNED_BYTE,
////                texture.getPixels());
////
////        //Create MipMapped Texture (Only with GL4Java 2.1.2.1 and later!)
////        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[2]);
////        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
////        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER,
////                GL.GL_LINEAR_MIPMAP_NEAREST);
////
////        glu.gluBuild2DMipmaps(GL.GL_TEXTURE_2D,
////                3,
////                texture.getWidth(),
////                texture.getHeight(),
////                GL.GL_RGB,
////                GL.GL_UNSIGNED_BYTE,
////                texture.getPixels());
////    }
//
//
//    public static void main(String[] args) {
//        Frame frame = new Frame("Simple JOGL Application");
//        canvas = new GLCanvas();
//
//        SimpleJOGL simpleJOGL = new SimpleJOGL();
//        canvas.addGLEventListener(simpleJOGL);
//
////           GLDisplay neheGLDisplay = GLDisplay.createGLDisplay("Lesson 08: Blending");
//
//        InputHandler inputHandler = new InputHandler(simpleJOGL, canvas);
//
//        canvas.addKeyListener(inputHandler);
//
//        frame.add(canvas);
//        frame.setSize(800, 600);
//        final Animator animator = new Animator(canvas);
//        frame.addWindowListener(new WindowAdapter() {
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//                // Run this on another thread than the AWT event queue to
//                // make sure the call to Animator.stop() completes before
//                // exiting
//                new Thread(new Runnable() {
//
//                    public void run() {
//                        animator.stop();
//                        System.exit(0);
//                    }
//                }).start();
//            }
//        });
//        // Center frame
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        animator.start();
//    }
//
//    public void init(GLAutoDrawable drawable) {
//        // Use debug pipeline
//        // drawable.setGL(new DebugGL(drawable.getGL()));
//
//        GL gl = drawable.getGL();
//        System.err.println("INIT GL IS: " + gl.getClass().getName());
//
//        // Enable VSync
//        gl.setSwapInterval(1);
//
//        gl.glShadeModel(GL.GL_SMOOTH);           // Enables Smooth Color Shading
//        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // This Will Clear The Background Color To Black
//        gl.glClearDepth(1.0);                    // Enables Clearing Of The Depth Buffer
//        gl.glEnable(GL.GL_DEPTH_TEST);           // Enables Depth Testing
//        gl.glDepthFunc(GL.GL_LEQUAL);            // The Type Of Depth Test To Do
//
//        // Really Nice Perspective Calculations
//        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
//        gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightAmbient, 0);   // Setup The Ambient Light
//        gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, lightDiffuse, 0);   // Setup The Diffuse Light
//        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPosition, 0); // Position The Light
//        gl.glEnable(GL.GL_LIGHT1);  // Enable Light One
//
//        gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);  // Full Brightness.  50% Alpha (new )
//
//        // Set The Blending Function For Translucency (new )
//        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE);
//        URL url = ClassLoader.getSystemResource("arrow.gif");
//
//        try {
//            BufferedImage image = ImageIO.read(url);
//
////            createWall(image);
////            createMiddleBlocks(image);
//
//            createBirds(image);
////            createEnemyLeaders(image);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Setup the drawing area and shading mode
//        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
//        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
//    }
//
//    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
//        GL gl = drawable.getGL();
//        GLU glu = new GLU();
//
//        if (height <= 0) { // avoid a divide by zero error!
//
//            height = 1;
//        }
//        final float h = (float) width / (float) height;
//        gl.glViewport(0, 0, width, height);
//        gl.glMatrixMode(GL.GL_PROJECTION);
//        gl.glLoadIdentity();
//        glu.gluPerspective(45.0f, h, 1.0, 20.0);
//        gl.glMatrixMode(GL.GL_MODELVIEW);
//        gl.glLoadIdentity();
//    }
//
//    public void display(GLAutoDrawable drawable) {
//        update();
//        GL gl = drawable.getGL();
//
//        // Clear the drawing area
//        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
//        // Reset the current matrix to the "identity"
//        gl.glLoadIdentity();
//        gl.glTranslatef(0.0f, 0.0f, z);
//        gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
//        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
//
////        drawCube(gl);
//
//
//        for (Actor actor : actors) {
//            actor.update();
//            actor.paint(gl);
//        }
//
//        xrot += xspeed;
//        yrot += yspeed;
//
//        // toggle lighting
//        if (lightingChanged) {
//            if (lightingEnabled) {
//                gl.glEnable(GL.GL_LIGHTING);
//            } else {
//                gl.glDisable(GL.GL_LIGHTING);
//            }
//            lightingChanged = false;
//        }
//
//        // toggle blending
//        if (blendingChanged) {
//            if (blendingEnabled) {
//                gl.glEnable(GL.GL_BLEND);    // Turn Blending On
//                gl.glDisable(GL.GL_DEPTH_TEST);  // Turn Depth Testing Off
//            } else {
//                gl.glDisable(GL.GL_BLEND);    // Turn Blending Off
//                gl.glEnable(GL.GL_DEPTH_TEST);  // Turn Depth Testing On
//            }
//            blendingChanged = false;
//        }
//        // Flush all drawing operations to the graphics card
//        gl.glFlush();
//    }
//
//    private void drawCube(GL gl) {
//        gl.glBegin(GL.GL_QUADS);
//        // Front Face
//        gl.glNormal3f(0.0f, 0.0f, 1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f);
//        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f);
//        gl.glVertex3f(1.0f, -1.0f, 1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f);
//        gl.glVertex3f(1.0f, 1.0f, 1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f);
//        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
//        // Back Face
//        gl.glNormal3f(0.0f, 0.0f, -1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f);
//        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f);
//        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f);
//        gl.glVertex3f(1.0f, 1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f);
//        gl.glVertex3f(1.0f, -1.0f, -1.0f);
//        // Top Face
//        gl.glNormal3f(0.0f, 1.0f, 0.0f);
//        gl.glTexCoord2f(0.0f, 1.0f);
//        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f);
//        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f);
//        gl.glVertex3f(1.0f, 1.0f, 1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f);
//        gl.glVertex3f(1.0f, 1.0f, -1.0f);
//        // Bottom Face
//        gl.glNormal3f(0.0f, -1.0f, 0.0f);
//        gl.glTexCoord2f(1.0f, 1.0f);
//        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f);
//        gl.glVertex3f(1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f);
//        gl.glVertex3f(1.0f, -1.0f, 1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f);
//        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
//        // Right face
//        gl.glNormal3f(1.0f, 0.0f, 0.0f);
//        gl.glTexCoord2f(1.0f, 0.0f);
//        gl.glVertex3f(1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f);
//        gl.glVertex3f(1.0f, 1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f);
//        gl.glVertex3f(1.0f, 1.0f, 1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f);
//        gl.glVertex3f(1.0f, -1.0f, 1.0f);
//        // Left Face
//        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
//        gl.glTexCoord2f(0.0f, 0.0f);
//        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f);
//        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f);
//        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f);
//        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
//        gl.glEnd();
//    }
//
//    private void createBirds(BufferedImage image) {
//        for (int i = 0; i < NUMBER_OF_DRONES; i++) {
//            Actor actor = new Actor(new Color(196, 196, 45), image);
//            actor.setFilter(new Colorfilter(false, true, false));
//            actor.setName("drone" + i);
//            actor.direction = Actor.getRandomDirection();
//            actor.speed = .004f;
//            actor.setPosition(new Vector3d(0,0,0));
//            Behaviour wanderBehaviour = new WanderBehaviour(.05f, 60);
//            actor.addBehaviour(wanderBehaviour);
//            actors.add(actor);
//        }
//
//        java.util.List<Behaviour> seekers = new ArrayList<Behaviour>();
//        java.util.List<Behaviour> avoiders = new ArrayList<Behaviour>();
//
//        for (Actor actor : actors) {
//            Behaviour avoidBehaviour = new AvoidBehaviour(0.02f, actor, .5f);
//            Behaviour seekBehaviour = new SeekDistanceBehaviour(0.01f, actor, 3);
//            seekers.add(seekBehaviour);
//            avoiders.add(avoidBehaviour);
//        }
//
//        for (Actor actor : actors) {
//            for (Behaviour avoider : avoiders) {
//                if (!avoider.getActor().equals(actor)) {
//                    actor.addBehaviour(avoider);
//                }
//            }
//            for (Behaviour seeker : seekers) {
//                if (!seeker.getActor().equals(actor)) {
//                    actor.addBehaviour(seeker);
//                }
//            }
//        }
//    }
//
//    private void createMiddleBlocks(BufferedImage image) {
//        Actor obstacle = new Actor(new Color(196, 196, 45), image);
//        obstacle.setFilter(new Colorfilter(false, false, true));
//        obstacle.setPosition(new Vector3d(-maxX / 2, maxX / 2, 0));
//        obstacle.direction = new Vector3d(1, 0, 0);
//        actors.add(obstacle);
//        obstacles.add(obstacle);
//        Actor obstacle1 = new Actor(new Color(196, 196, 45), image);
//        obstacle1.setFilter(new Colorfilter(false, false, true));
//        obstacle1.setPosition(new Vector3d(maxX / 2, maxX / 2, 0));
//        obstacle1.direction = new Vector3d(1, 0, 0);
//        actors.add(obstacle1);
//        obstacles.add(obstacle1);
//        Actor obstacle2 = new Actor(new Color(196, 196, 45), image);
//        obstacle2.setFilter(new Colorfilter(false, false, true));
//        obstacle2.setPosition(new Vector3d(maxX / 2, -maxX / 2, 0));
//        obstacle2.direction = new Vector3d(1, 0, 0);
//        actors.add(obstacle2);
//        obstacles.add(obstacle2);
//        Actor obstacle3 = new Actor(new Color(196, 196, 45), image);
//        obstacle3.setFilter(new Colorfilter(false, false, true));
//        obstacle3.setPosition(new Vector3d(-maxX / 2, -maxX / 2, 0));
//        obstacle3.direction = new Vector3d(1, 0, 0);
//        actors.add(obstacle3);
//        obstacles.add(obstacle3);
//    }
//
////       private void createWall(BufferedImage image) {
////           int topWall = screenWidth / 100;
////           int sideWall = screenHeight / 100;
////           for (int i = 0; i < topWall; i++) {
////               Actor topObstacle = new Actor(new Color(196, 196, 45), image);
////               topObstacle.setFilter(new Colorfilter(false, false, true));
////               topObstacle.setPosition(new Vector3d(i * 100, 0,0));
////               topObstacle.direction = new Vector3d(0, 1,0);
////               actors.add(topObstacle);
////               obstacles.add(topObstacle);
////               Actor bottomObstacle = new Actor(new Color(196, 196, 45), image);
////               bottomObstacle.setFilter(new Colorfilter(false, false, true));
////               bottomObstacle.setPosition(new Vector3d(i * 100, (int) (screenHeight - image.getHeight()),0));
////               bottomObstacle.direction = new Vector3d(0, -1,0);
////               actors.add(bottomObstacle);
////               obstacles.add(bottomObstacle);
////           }
////           for (int i = 0; i < sideWall; i++) {
////               Actor leftObstacle = new Actor(new Color(196, 196, 45), image);
////               leftObstacle.setFilter(new Colorfilter(false, false, true));
////               leftObstacle.setPosition(new Vector3d(0, i * 100,0));
////               leftObstacle.direction = new Vector3d(1, 0,0);
////               actors.add(leftObstacle);
////               obstacles.add(leftObstacle);
////               Actor rightObstacle = new Actor(new Color(196, 196, 45), image);
////               rightObstacle.setFilter(new Colorfilter(false, false, true));
////               rightObstacle.setPosition(new Vector3d((int) (screenWidth - image.getWidth()), i * 100,0));
////               rightObstacle.direction = new Vector3d(-1, 0,0);
////               actors.add(rightObstacle);
////               obstacles.add(rightObstacle);
////           }
////       }
//
//    private void createEnemyLeaders(BufferedImage image) {
//        Actor leader = new Actor(new Color(48, 48, 196), image);
//        leader.setFilter(new Colorfilter(true, true, false));
//        leader.direction = Actor.getRandomDirection();
//        leader.speed = 0.0063f;
//        leader.setPosition(Actor.getRandomPostion(1, 1, 1));
//        Behaviour wanderBehaviour = new WanderBehaviour(.05f, 60);
//        leader.addBehaviour(wanderBehaviour);
//        actors.add(leader);
//
//        Behaviour seekBehaviour = new SeekDistanceBehaviour(.05f, leader, 6);
//        Actor enemy = new Actor(new Color(196, 48, 48), image);
//        enemy.setFilter(new Colorfilter(false, true, true));
//        enemy.direction = Actor.getRandomDirection();
//        enemy.speed = 0.005f;
//        enemy.setPosition(Actor.getRandomPostion(1, 1, 1));
//
//        enemy.addBehaviour(wanderBehaviour);
//        enemy.addBehaviour(seekBehaviour);
//        actors.add(enemy);
//
//        Behaviour avoidance = new AvoidBehaviour(0.1f, leader, 40);
//
//        for (int i = 0; i < NUMBER_OF_DRONES; i++) {
//            Actor drone = new Actor(new Color(48, 196, 48), image);
//            drone.setFilter(new Colorfilter(true, false, true));
//            drone.direction = Actor.getRandomDirection();
//            drone.speed = 0.004f;
//            drone.setPosition(Actor.getRandomPostion(1, 1, 1));
//
//            drone.addBehaviour(seekBehaviour);
//            drone.addBehaviour(new WanderBehaviour(0.03f, 60));
//            drone.addBehaviour(avoidance);
//
//            Behaviour enemyAvoidBehaviour = new AvoidBehaviour(0.5f, enemy, 1);
//            drone.addBehaviour(enemyAvoidBehaviour);
//            actors.add(drone);
//        }
//
//
//        for (Actor actor : obstacles) {
//
//            Behaviour avoidBehaviour = new AvoidBehaviour(.2f, actor, 1);
//            for (Actor actor1 : actors) {
//                actor1.addBehaviour(avoidBehaviour);
//            }
//        }
//    }
//
//
//    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
//    }
//}