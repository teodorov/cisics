package cisics.integrators;

import cisics.particles.Particle;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public class EulerIntegrator implements IIntegrator {

    @Override
    public void advance(Particle p, Double time, double deltaT) {
        p.velocity = p.velocity.add(p.force.multiply(deltaT/p.mass));
        p.position = p.position.add(p.velocity.multiply(deltaT));
    }
}
