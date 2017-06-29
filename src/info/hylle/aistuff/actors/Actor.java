package info.hylle.aistuff.actors;

import info.hylle.aistuff.behaviours.Behaviour;
import info.hylle.aistuff.geometry.Vector3d;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Actor {

  static Random random = new Random();

  public Color color;
  public BufferedImage texture;
  public Vector3d position;
  public Vector3d direction;

  public Vector3d origin;

  public float speed;

  private List<Behaviour> behaviours;
  private ImageFilter filter;
  private Vector3d scaledDirection;
  private Polygon shape;
  private String name;


  public static float minX = 0;
  public static float maxX = 6.5f;
  public static float minY = 0;
  public static float maxY = 4.5f;

  private float width = 0.035f;
  private float height = 0.05f;
  private float depth = 0.025f;

  public Actor(Color color, BufferedImage texture) {
    this.color = color;
    this.texture = texture;
    behaviours = new ArrayList<Behaviour>();

    shape = new Polygon();
    shape.addPoint(0, 0);
    shape.addPoint(texture.getWidth() / 2, texture.getHeight());
    shape.addPoint(texture.getWidth(), 0);

//
//         g2.drawLine(0, 0, texture.getWidth() / 2, texture.getHeight());
//        g2.drawLine(texture.getWidth() / 2, texture.getHeight(), texture.getWidth(), 0);
//        g2.drawLine(texture.getWidth(), 0, 0, 0);
    this.origin = new Vector3d((float) width / 2, height / 2, depth / 2);
  }

  public ImageFilter getFilter() {
    return filter;
  }

  public void setFilter(ImageFilter filter) {
    this.filter = filter;
  }

  public static Vector3d getRandomPostion(int rangeX, int rangeY, int rangeZ) {
    int x1 = random.nextInt(rangeX);
    int y1 = random.nextInt(rangeY);
    int z1 = random.nextInt(rangeZ);
    return new Vector3d(x1, y1, z1);
  }

  public Vector3d getPosition() {
    return position;
  }

  public void setPosition(Vector3d position) {
    this.position = position;
  }

  public static Vector3d getRandomDirection() {
    double rotation = random.nextDouble() * 2 * Math.PI;
    return new Vector3d((float) Math.cos(rotation), (float) Math.sin(rotation), 2f);
  }

  public void update() {
    scaledDirection = this.direction.scalar(speed);
    this.direction.normalize();
    position = position.add(scaledDirection);

//        if (position.getX() > maxX) {
//            position.setX(-maxX);
//        } else if (position.getX() < -maxX) {
//            position.setX(maxX);
//        }
//        if (position.getY() > maxY) {
//            position.setY(-maxY);
//        } else if (position.getY() < -maxY) {
//            position.setY(maxY);
//        }

    for (Behaviour behaviour : behaviours) {
      behaviour.update(this);
    }
  }


  public void paint(Graphics g) {
    createImage((Graphics2D) g, 0);
//        System.out.println("painting");
    // Move the "drawing cursor" around


//        Vector3d temp = new Vector3d(position.getX(), position.getY(), 1f);

//        drawCube(gl);
    // Drawing Using Triangles
//        gl.glBegin(GL.GL_TRIANGLES);
//            gl.glColor3f(1.0f, 0.0f, 0.0f);    // Set the current drawing color to red
//            gl.glVertex3f(temp.getX() + width/2, temp.getY()+ height, temp.getZ()-depth/2);   // Top
//            gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to green
//            gl.glVertex3f(temp.getX(), temp.getY(), temp.getZ()); // Bottom Left
//            gl.glColor3f(0.0f, 0.0f, 1.0f);    // Set the current drawing color to blue
//            gl.glVertex3f(temp.getX() + width, temp.getY(), temp.getZ());  // Bottom Right
//        // Finished Drawing The Triangle
//        gl.glEnd();


  }

//    private void drawCube(GL gl) {
//           gl.glBegin(GL.GL_QUADS);
//           // Front Face
//           gl.glNormal3f(0.0f, 0.0f, 1.0f);
//           gl.glTexCoord2f(0.0f, 0.0f);
//                    gl.glColor3f(1.0f, 0.0f, 0.0f);    // Set the current drawing color to red
//           gl.glVertex3f(position.getX()-width, position.getY()-height, position.getZ() + depth);
//           gl.glTexCoord2f(1.0f, 0.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()-height, position.getZ() + depth);
//           gl.glTexCoord2f(1.0f, 1.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()+height, position.getZ() + depth);
//           gl.glTexCoord2f(0.0f, 1.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()+height, position.getZ() + depth);
//           // Back Face
//           gl.glNormal3f(0.0f, 0.0f, -1.0f);
//           gl.glTexCoord2f(1.0f, 0.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()-height, position.getZ() - depth);
//           gl.glTexCoord2f(1.0f, 1.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()+height, position.getZ() - depth);
//           gl.glTexCoord2f(0.0f, 1.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()+height, position.getZ() - depth);
//           gl.glTexCoord2f(0.0f, 0.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()-height, position.getZ() - depth);
//           // Top Face
//           gl.glNormal3f(0.0f, 1.0f, 0.0f);
//           gl.glTexCoord2f(0.0f, 1.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()+height, position.getZ() - depth);
//           gl.glTexCoord2f(0.0f, 0.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()+height, position.getZ() + depth);
//           gl.glTexCoord2f(1.0f, 0.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()+height, position.getZ() + depth);
//           gl.glTexCoord2f(1.0f, 1.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()+height, position.getZ() - depth);
//           // Bottom Face
//           gl.glNormal3f(0.0f, -1.0f, 0.0f);
//           gl.glTexCoord2f(1.0f, 1.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()-height, position.getZ() - depth);
//           gl.glTexCoord2f(0.0f, 1.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()-height, position.getZ() - depth);
//           gl.glTexCoord2f(0.0f, 0.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()-height, position.getZ() + depth);
//           gl.glTexCoord2f(1.0f, 0.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()-height, position.getZ() + depth);
//           // Right face
//           gl.glNormal3f(1.0f, 0.0f, 0.0f);
//           gl.glTexCoord2f(1.0f, 0.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()-height, position.getZ() - depth);
//           gl.glTexCoord2f(1.0f, 1.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()+height, position.getZ() - depth);
//           gl.glTexCoord2f(0.0f, 1.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()+height, position.getZ() + depth);
//           gl.glTexCoord2f(0.0f, 0.0f);
//           gl.glVertex3f(position.getX()+width, position.getY()-height, position.getZ() + depth);
//           // Left Face
//           gl.glNormal3f(-1.0f, 0.0f, 0.0f);
//           gl.glTexCoord2f(0.0f, 0.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()-height, position.getZ() - depth);
//           gl.glTexCoord2f(1.0f, 0.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()-height, position.getZ() + depth);
//           gl.glTexCoord2f(1.0f, 1.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()+height, position.getZ() + depth);
//           gl.glTexCoord2f(0.0f, 1.0f);
//           gl.glVertex3f(position.getX()-width, position.getY()+height, position.getZ() - depth);
//           gl.glEnd();
//       }


  private void createImage(Graphics2D g2, float rotation) {
    rotation += Math.toRadians(90);
    g2.rotate(rotation, origin.getX(), origin.getY());

    Image img = texture.getScaledInstance(texture.getWidth(), texture.getHeight(), BufferedImage.SCALE_SMOOTH);

    FilteredImageSource filteredSrc = new FilteredImageSource(img.getSource(), filter);
    img = Toolkit.getDefaultToolkit().createImage(filteredSrc);
    g2.drawImage(img, null, null);
    g2.rotate(-rotation, origin.getX(), origin.getY());
  }

  private void createPolygon(Graphics2D g2, float rotation) {
    rotation += Math.toRadians(-90);
    g2.rotate(rotation, origin.getX(), origin.getY());
    g2.fillPolygon(shape);

    g2.rotate(-rotation, origin.getX(), origin.getY());
  }

  public void addBehaviour(Behaviour wanderBehaviour) {
    behaviours.add(wanderBehaviour);
  }


  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public boolean equals(Object obj) {
    if (obj instanceof Actor) {
      Actor otherObj = (Actor) obj;
      if (this.getName() != null && otherObj.getName() != null) {
        return this.getName().equals(otherObj.getName());
      }


    }
    return false;
  }
}
