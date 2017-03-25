package cisics.force;

import cisics.particles.Particle;
import javafx.geometry.Point2D;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */

public class Attraction extends Force {
    public double strength;
    double minimumDistance = 5.0;
    double minimumDistanceSquared = 25.0;

    public Particle from;
    public Particle to;

    public void apply() {
        if (!(isOn() && (from.isFree() || to.isFree()))) return;
        double force;
        double length;

        Point2D a2b = from.subtract(to);
        double sqa2b = a2b.dotProduct(a2b);
        if (sqa2b < minimumDistanceSquared) {
            sqa2b = minimumDistanceSquared;
        }

        force = strength * from.mass * to.mass / sqa2b;
        length = Math.sqrt(sqa2b);
        a2b = a2b.multiply(force/length);

        if (from.isFree()) {
            from.force = from.force.subtract(a2b);
        }
        if (to.isFree()) {
            to.force = to.force.add(a2b);
        }
    }
}
