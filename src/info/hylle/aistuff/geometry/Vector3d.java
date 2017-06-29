package info.hylle.aistuff.geometry;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 08-10-2009
 * Time: 21:24:07
 * To change this template use File | Settings | File Templates.
 */
public class Vector3d {
    protected float x;
    protected float y;
    protected float z;
    Vector3d subtractResult;
    Vector3d addResult;
    Vector3d scalarResult;

    public Vector3d() {
        this(0, 0,0);
    }

    public Vector3d(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void normalize() {
        if (lenght() != 0) {
            setX(getX() / lenght());
            setY(getY() / lenght());
            setZ(getZ() / lenght());
        }
    }

    public Vector3d subtract(Vector3d vector2d) {
        if (subtractResult == null) {
            subtractResult = new Vector3d();
        }
        subtractResult.setX(getX() - vector2d.getX());
        subtractResult.setY(getY() - vector2d.getY());
        subtractResult.setZ(getZ() - vector2d.getZ());
        return subtractResult;
    }

    public Vector3d add(Vector3d vector2d) {
        if (addResult == null) {
            addResult = new Vector3d();
        }
        addResult.setX(getX() + vector2d.getX());
        addResult.setY(getY() + vector2d.getY());
        addResult.setZ(getZ() + vector2d.getZ());

        return addResult;
    }

    public Vector3d scalar(float scalar) {
        if (scalarResult == null) {
            scalarResult = new Vector3d();
        }
        scalarResult.setX(scalar * getX());
        scalarResult.setY(scalar * getY());
        scalarResult.setZ(scalar * getZ());
        return scalarResult;
    }

    public float lenght() {
        return (float) Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
    }


    public float dot(Vector3d vector2d) {
        return getX() * vector2d.getX() + getY() * vector2d.getY()+ getZ() * vector2d.getZ();
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}