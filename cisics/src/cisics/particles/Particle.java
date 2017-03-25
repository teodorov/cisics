package cisics.particles;

import javafx.geometry.Point2D;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public class Particle {
    public Point2D position    = new Point2D(0, 0);
    public Point2D velocity    = new Point2D(0, 0);
    public Point2D force       = new Point2D(0, 0);
    public double mass = 10;
    public double age = 0;
    boolean isDead = false;
    boolean isFixed = false;

    public boolean isFree() {
        return !isFixed;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void fix() {
        isFixed = true;
        velocity = new Point2D(0, 0);
    }

    public void free() {
        isFixed = false;
    }


    public Point2D subtract(Particle other) {
        return this.position.subtract(other.position);
    }

    public double distance(Particle other) {
        return this.position.distance(other.position);
    }

    public double squaredDistance(Particle other) {
        Point2D delta = other.position.subtract(position);
        return delta.dotProduct(delta);
    }

    public double r(Particle other) {
        return Math.sqrt(this.squaredDistance(other));
    }

    public double dotProduct(Particle other) {
        return this.position.dotProduct(other.position);
    }

    public Point2D nearestPointAlongLine(Point2D p1, Point2D p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();

        if (x1 == x2) return new Point2D(x1, position.getY());
        if (y1 == y2) return new Point2D(position.getX(), y1);

        double x21 = x2 - x1;
        double y21 = y2 - y1;

        double x = position.getX();
        double y = position.getY();

        double t = ((y - y1) / x21 + (x - x1) / y21) /
                (x21 / y21 + y21 / x21);
        return new Point2D(x1 + t * x21, y1 + t * y21);
    }
}
