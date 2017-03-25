package cisics.integrators;

import cisics.particles.Particle;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public interface IIntegrator {
    void advance(Particle p, Double time, double deltaT);
}
