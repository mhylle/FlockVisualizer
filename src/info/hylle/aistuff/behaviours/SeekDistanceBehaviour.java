package info.hylle.aistuff.behaviours;

import info.hylle.aistuff.actors.Actor;
import info.hylle.aistuff.geometry.Vector2d;
import info.hylle.aistuff.geometry.Vector3d;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 09-10-2009
 * Time: 18:11:46
 * To change this template use File | Settings | File Templates.
 */
public class SeekDistanceBehaviour extends Behaviour {
    private Actor target;
    private float distance;

    public SeekDistanceBehaviour(float weight, Actor target, float distance) {
        super(weight);
        this.target = target;
        this.distance = distance;
    }

    public void update(Actor actor) {
        Vector3d targetDirection = target.getPosition().subtract(actor.getPosition());
        if (targetDirection.lenght() > distance) {
            targetDirection.normalize();
            actor.direction = actor.direction.add(targetDirection.scalar(weight));
        }
    }

    public Actor getActor() {
        return target;
    }
}