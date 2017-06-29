package info.hylle.aistuff.geometry;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 08-10-2009
 * Time: 21:24:07
 * To change this template use File | Settings | File Templates.
 */
public class Vector2d {
    protected float x;
    protected float y;
    Vector2d subtractResult;
    Vector2d addResult;
    Vector2d scalarResult;

    public Vector2d() {
        this(0, 0);
    }

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void normalize() {
        if (lenght() != 0) {
            setX(getX() / lenght());
            setY(getY() / lenght());
        }
    }

    public Vector2d subtract(Vector2d vector2d) {
        if (subtractResult == null) {
            subtractResult = new Vector2d();
        }
        subtractResult.setX(getX() - vector2d.getX());
        subtractResult.setY(getY() - vector2d.getY());
        return subtractResult;
    }

    public Vector2d add(Vector2d vector2d) {
        if (addResult == null) {
            addResult = new Vector2d();
        }
        addResult.setX(getX() + vector2d.getX());
        addResult.setY(getY() + vector2d.getY());

        return addResult;
    }

    public Vector2d scalar(float scalar) {
        if (scalarResult == null) {
            scalarResult = new Vector2d();
        }
        scalarResult.setX(scalar * getX());
        scalarResult.setY(scalar * getY());
        return scalarResult;
    }

    public float lenght() {
        return (float) Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }


    public float dot(Vector2d vector2d) {
        return getX() * vector2d.getX() + getY() * vector2d.getY();
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
