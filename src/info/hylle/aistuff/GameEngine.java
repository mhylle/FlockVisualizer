package info.hylle.aistuff;

import info.hylle.aistuff.actors.Actor;
import info.hylle.aistuff.behaviours.AvoidBehaviour;
import info.hylle.aistuff.behaviours.Behaviour;
import info.hylle.aistuff.behaviours.SeekDistanceBehaviour;
import info.hylle.aistuff.behaviours.WanderBehaviour;
import info.hylle.aistuff.geometry.Vector3d;
import info.hylle.aistuff.utils.Colorfilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
  private int screenWidth = 800;
  private int screenHeight = 600;

  private JFrame m_mainFrame;

  private java.util.List<Actor> obstacles = new ArrayList<Actor>();
  private GameRunner gameRunner;
  private java.util.List<Actor> actors = new ArrayList<Actor>();
  private static final int NUMBER_OF_DRONES = 200;


  public static void main(String[] args) {
//    String s = System.getProperty("java.library.path");

//    System.out.println("s = " + s);
//    System.loadLibrary("jogl");
    new GameEngine();
  }

  private GameEngine() {
    m_mainFrame = new JFrame();
    m_mainFrame.setSize(screenWidth, screenHeight);
    JPanel mainPanel = new JPanel(null) {
      public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g);
        synchronized (actors) {
          for (Actor actor : actors) {
            actor.paint(g);
          }
        }
      }
    };
    mainPanel.setBackground(new Color(96, 96, 111));
    m_mainFrame.getContentPane().add(mainPanel);
    m_mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        gameRunner.stop();
        System.exit(0);
      }
    });

    URL url = ClassLoader.getSystemResource("arrow.gif");

    try {
      BufferedImage image = ImageIO.read(url);

//            createWall(image);
//            createMiddleBlocks(image);


      createBirds(image);
//            createEnemyLeaders(image);

    } catch (IOException e) {
      e.printStackTrace();
    }

    m_mainFrame.setVisible(true);

    gameRunner = new GameRunner();
    Thread worker = new Thread(gameRunner);
    worker.start();
  }

  private void createBirds(BufferedImage image) {
    for (int i = 0; i < NUMBER_OF_DRONES; i++) {
      Actor actor = new Actor(new Color(196, 196, 45), image);
      actor.setFilter(new Colorfilter(false, true, false));
      actor.setName("drone" + i);
      actor.direction = Actor.getRandomDirection();
      actor.speed = 2f;
      actor.setPosition(Actor.getRandomPostion(800, 600, 1));
      Behaviour wanderBehaviour = new WanderBehaviour(.05f, 60);
      actor.addBehaviour(wanderBehaviour);
      actors.add(actor);
    }

    java.util.List<Behaviour> seekers = new ArrayList<Behaviour>();
    java.util.List<Behaviour> avoiders = new ArrayList<Behaviour>();

    for (Actor actor : actors) {
      Behaviour avoidBehaviour = new AvoidBehaviour(0.02f, actor, 50);
      Behaviour seekBehaviour = new SeekDistanceBehaviour(0.01f, actor, 700);
      seekers.add(seekBehaviour);
      avoiders.add(avoidBehaviour);
    }

    for (Actor actor : actors) {
      for (Behaviour avoider : avoiders) {
        if (!avoider.getActor().equals(actor)) {
          actor.addBehaviour(avoider);
        }
      }
      for (Behaviour seeker : seekers) {
        if (!seeker.getActor().equals(actor)) {
          actor.addBehaviour(seeker);
        }
      }
    }
  }

  private void createMiddleBlocks(BufferedImage image) {
    Actor obstacle = new Actor(new Color(196, 196, 45), image);
    obstacle.setFilter(new Colorfilter(false, false, true));
    obstacle.setPosition(new Vector3d(200, 200, 0));
    obstacle.direction = new Vector3d(1, 0, 0);
    actors.add(obstacle);
    obstacles.add(obstacle);
    Actor obstacle1 = new Actor(new Color(196, 196, 45), image);
    obstacle1.setFilter(new Colorfilter(false, false, true));
    obstacle1.setPosition(new Vector3d(600, 200, 0));
    obstacle1.direction = new Vector3d(1, 0, 0);
    actors.add(obstacle1);
    obstacles.add(obstacle1);
    Actor obstacle2 = new Actor(new Color(196, 196, 45), image);
    obstacle2.setFilter(new Colorfilter(false, false, true));
    obstacle2.setPosition(new Vector3d(200, 400, 0));
    obstacle2.direction = new Vector3d(1, 0, 0);
    actors.add(obstacle2);
    obstacles.add(obstacle2);
    Actor obstacle3 = new Actor(new Color(196, 196, 45), image);
    obstacle3.setFilter(new Colorfilter(false, false, true));
    obstacle3.setPosition(new Vector3d(600, 400, 0));
    obstacle3.direction = new Vector3d(1, 0, 0);
    actors.add(obstacle3);
    obstacles.add(obstacle3);
  }

  private void createWall(BufferedImage image) {
    int topWall = screenWidth / 100;
    int sideWall = screenHeight / 100;
    for (int i = 0; i < topWall; i++) {
      Actor topObstacle = new Actor(new Color(196, 196, 45), image);
      topObstacle.setFilter(new Colorfilter(false, false, true));
      topObstacle.setPosition(new Vector3d(i * 100, 0, 0));
      topObstacle.direction = new Vector3d(0, 1, 0);
      actors.add(topObstacle);
      obstacles.add(topObstacle);
      Actor bottomObstacle = new Actor(new Color(196, 196, 45), image);
      bottomObstacle.setFilter(new Colorfilter(false, false, true));
      bottomObstacle.setPosition(new Vector3d(i * 100, (int) (screenHeight - image.getHeight()), 0));
      bottomObstacle.direction = new Vector3d(0, -1, 0);
      actors.add(bottomObstacle);
      obstacles.add(bottomObstacle);
    }
    for (int i = 0; i < sideWall; i++) {
      Actor leftObstacle = new Actor(new Color(196, 196, 45), image);
      leftObstacle.setFilter(new Colorfilter(false, false, true));
      leftObstacle.setPosition(new Vector3d(0, i * 100, 0));
      leftObstacle.direction = new Vector3d(1, 0, 0);
      actors.add(leftObstacle);
      obstacles.add(leftObstacle);
      Actor rightObstacle = new Actor(new Color(196, 196, 45), image);
      rightObstacle.setFilter(new Colorfilter(false, false, true));
      rightObstacle.setPosition(new Vector3d((int) (screenWidth - image.getWidth()), i * 100, 0));
      rightObstacle.direction = new Vector3d(-1, 0, 0);
      actors.add(rightObstacle);
      obstacles.add(rightObstacle);
    }
  }

  private void createEnemyLeaders(BufferedImage image) {
    Actor leader = new Actor(new Color(48, 48, 196), image);
    leader.setFilter(new Colorfilter(true, true, false));
    leader.direction = Actor.getRandomDirection();
    leader.speed = 3f;
    leader.setPosition(Actor.getRandomPostion(800, 600, 0));
    Behaviour wanderBehaviour = new WanderBehaviour(.05f, 60);
    leader.addBehaviour(wanderBehaviour);
    actors.add(leader);

    Behaviour seekBehaviour = new SeekDistanceBehaviour(.05f, leader, 100);
    Actor enemy = new Actor(new Color(196, 48, 48), image);
    enemy.setFilter(new Colorfilter(false, true, true));
    enemy.direction = Actor.getRandomDirection();
    enemy.speed = 2.5f;
    enemy.setPosition(Actor.getRandomPostion(800, 600, 0));

    enemy.addBehaviour(wanderBehaviour);
    enemy.addBehaviour(seekBehaviour);
    actors.add(enemy);

    Behaviour avoidance = new AvoidBehaviour(0.1f, leader, 40);

    for (int i = 0; i < NUMBER_OF_DRONES; i++) {
      Actor drone = new Actor(new Color(48, 196, 48), image);
      drone.setFilter(new Colorfilter(true, false, true));
      drone.direction = Actor.getRandomDirection();
      drone.speed = 3f;
      drone.setPosition(Actor.getRandomPostion(800, 600, 0));

      drone.addBehaviour(seekBehaviour);
      drone.addBehaviour(new WanderBehaviour(0.03f, 15));
      drone.addBehaviour(avoidance);

      Behaviour enemyAvoidBehaviour = new AvoidBehaviour(0.5f, enemy, 50 + new Random().nextInt(100));
      drone.addBehaviour(enemyAvoidBehaviour);
      actors.add(drone);
    }


    for (Actor actor : obstacles) {

      Behaviour avoidBehaviour = new AvoidBehaviour(.2f, actor, 40);
      for (Actor actor1 : actors) {
        actor1.addBehaviour(avoidBehaviour);
      }
    }
  }

  public void update() {

    for (Actor actor : actors) {
      actor.update();
    }

    m_mainFrame.repaint();
  }

  class GameRunner implements Runnable {
    private boolean notStopped = true;

    public void run() {
      while (notStopped) {
        try {
          int tick = 25;
          Thread.sleep(tick);
          synchronized (actors) {
            update();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    void stop() {
      notStopped = false;
    }
  }
}
