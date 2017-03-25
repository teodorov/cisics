package cisics.force;

import cisics.particles.Particle;
import javafx.geometry.Point2D;

import java.util.function.BiFunction;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public class DynamicAttraction extends Attraction {
    BiFunction<Particle, Particle, Double> strength;

    @Override
    public void apply() {
        if (!(isOn() && (from.isFree() || to.isFree()))) return;
        double force = strength.apply(from, to);

        if (from.isFree()) {
            from.force = from.force.subtract(force, force);
        }
        if (to.isFree()) {
            to.force = to.force.add(force, force);
        }
    }
}
