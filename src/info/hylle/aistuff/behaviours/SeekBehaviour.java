package info.hylle.aistuff.behaviours;

import info.hylle.aistuff.actors.Actor;
import info.hylle.aistuff.geometry.Vector2d;
import info.hylle.aistuff.geometry.Vector3d;

public class SeekBehaviour extends Behaviour{
    private Actor target;

    public SeekBehaviour(float weight, Actor target) {
        super(weight);
        this.target = target;
    }

    public void update(Actor actor) {
        Vector3d targetDirection = target.getPosition().subtract(actor.getPosition());
        targetDirection.normalize();
        actor.direction = actor.direction.add(targetDirection.scalar(weight));
    }

    public Actor getActor() {
        return target;
    }
}
