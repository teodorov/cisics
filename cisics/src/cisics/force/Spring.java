package cisics.force;

import cisics.particles.Particle;
import javafx.geometry.Point2D;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public class Spring extends Force {
    double springConstant = 0.2;
    double damping = 0.8;
    double restLenght = 10;
    Particle from;
    Particle to;

    public void apply() {
        if (!(isOn() && (from.isFree() || to.isFree()))) return;
        Point2D delta = from.subtract(to);
        double distance = from.distance(to);

        if (distance == 0) {
            delta = new Point2D(0, 0);
        }
        else {
            delta = delta.multiply(1/distance);
        }

        //spring force is proportional to how much it is stretched
        double springForce = - (distance - restLenght) * springConstant;

        //damping force is proportional to the velocity along line between a & b
        Point2D velocity = from.velocity.subtract(to.velocity);
        double dampingForce = - damping * (velocity.dotProduct(velocity));

        //force 'from' is the same as force 'to' in opposite direction
        double r = springForce + dampingForce;
        delta = delta.multiply(r);

        if (from.isFree()) {
            from.force = from.force.add(delta);
        }
        if (to.isFree()) {
            to.force = to.force.subtract(delta);
        }
    }
}
