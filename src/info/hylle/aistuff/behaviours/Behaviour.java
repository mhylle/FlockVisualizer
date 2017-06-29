package info.hylle.aistuff.behaviours;

import info.hylle.aistuff.actors.Actor;

public abstract class Behaviour {
    protected float weight;

    public Behaviour(float weight) {
        this.weight = weight;
    }

    public abstract void update(Actor actor);

    public abstract Actor getActor();
}
