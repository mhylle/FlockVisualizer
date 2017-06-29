package info.hylle.aistuff.behaviours;

import info.hylle.aistuff.actors.Actor;
import info.hylle.aistuff.geometry.Vector2d;
import info.hylle.aistuff.geometry.Vector3d;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 09-10-2009
 * Time: 23:09:25
 * To change this template use File | Settings | File Templates.
 */
public class HideBehaviour extends Behaviour{
    private Actor target;

    public HideBehaviour(float weight, Actor target) {
        super(weight);
        this.target = target;
    }

    public void update(Actor actor) {
        Vector3d targetDirection = target.getPosition().subtract(actor.getPosition());
        
    }

    public Actor getActor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
