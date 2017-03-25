package cisics;

import cisics.force.Attraction;
import cisics.force.Force;
import cisics.integrators.EulerIntegrator;
import cisics.integrators.IIntegrator;
import cisics.particles.Particle;
import javafx.geometry.Point2D;
import plug.utils.announcement.Announcer;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public class ParticleSystem {
    Announcer announcer = new Announcer();

    Collection<Particle> particles;
    Collection<Force> forces;
    IIntegrator integrator = new EulerIntegrator();
    double friction = 0.5;
    double gravity = 0;
    double deltaT = 1/60;
    int iterations = 50;

    void clearForces() {
        particles.forEach(particle -> particle.force = Point2D.ZERO);
    }

    void applyForces() {
        for (Particle particle : particles) {
            particle.force = particle.force
                    .add(particle.velocity.multiply(-friction))
                    .add(0, gravity);
        }
        for (Force force : forces) {
            force.apply();
        }
    }

    void advanceTime(double time, double delta) {
        clearForces();
        applyForces();
        for (Particle particle : particles) {
            if (!particle.isFree()) continue;
            integrator.advance(particle, time, delta);
        }
    }

    boolean atEnd(double time) {
        return (time / deltaT) > iterations;
    }

    void simulation() {
        double time = 0;
        //System.out.println("start:" + time);
        while (!atEnd(time)) {
            //System.out.println(time);
            advanceTime(time, deltaT);
            time += deltaT;
            announcer.announce(new Object());
        }
    }

    void start() {
        new Thread(this::simulation).run();
    }

    public static void main(String args[]) {
        Particle p1 = new Particle();
        p1.position = new Point2D(200, 200);
        p1.mass = 10;
        Particle p2 = new Particle();
        p2.position = new Point2D(400, 400);
        p2.mass = 10;

        Attraction f = new Attraction();
        f.strength = p1.r(p2);
        f.from = p1;
        f.to = p2;

        ParticleSystem ps = new ParticleSystem();
        ps.deltaT = 0.07;
        ps.particles = Arrays.asList(p1, p2);
        ps.forces = Arrays.asList(f);
        ps.friction = 10;

        ps.announcer.when(Object.class, (ann, e) -> {
            System.out.println(p1.position + " " +  p2.position);
        });

        ps.start();
    }
}
