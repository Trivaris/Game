package org.jantor.utils;

public class Vector2D {
    public int x;
    public int y;

    public static final Vector2D zeroVector = new Vector2D(0, 0);

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        this.normalize();
    }

    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
        this.normalize();
    }

    public void normalize() {
        this.x = this.x == 0 ? 0 : this.x / Math.abs(this.x);
        this.y = this.y == 0 ? 0 : this.y / Math.abs(this.y);
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

    public void toZero() {
        this.x = 0;
        this.y = 0;
    }

}
