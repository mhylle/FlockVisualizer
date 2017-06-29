package info.hylle.aistuff.behaviours;

import info.hylle.aistuff.actors.Actor;
import info.hylle.aistuff.geometry.Vector2d;
import info.hylle.aistuff.geometry.Vector3d;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 09-10-2009
 * Time: 00:42:11
 * To change this template use File | Settings | File Templates.
 */
public class WanderBehaviour extends Behaviour{

    static Random random = new Random();
    int changeInterval;
    int tick;
    Vector3d direction;

    public WanderBehaviour(float weight, int changeInterval) {
        super(weight);
        this.changeInterval = changeInterval;
    }

    public void update(Actor actor) {
        if (tick == 0) {
            this.direction = Actor.getRandomDirection();
        }
        tick++;
        tick %= changeInterval;

        actor.direction = actor.direction.add(direction.scalar(weight));
    }

    public Actor getActor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
