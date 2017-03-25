package cisics;

import cisics.force.Attraction;
import cisics.particles.Particle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public class ParticleSystemApp  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        Pane p = new Pane();
        borderPane.setCenter(p);

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

        Circle c1 = new Circle(10);
        Circle c2 = new Circle(10);
        p.getChildren().addAll(c1, c2);

        ps.announcer.when(Object.class, (ann, e) -> {
            System.out.println(p1.position + " " +  p2.position);
            //Platform.runLater(() -> {
                c1.setLayoutX(p1.position.getX());
                c1.setLayoutY(p1.position.getY());

                c2.setLayoutX(p2.position.getX());
                c2.setLayoutY(p2.position.getY());

            //});
        });





        Button btn = new Button("simulate");
        btn.setOnAction(e -> {
            ps.start();
        });

        Button btn1 = new Button("nothing");
        btn1.setOnAction(e -> {
            f.strength *= 100;

        });

        Button btn2 = new Button("nothing");
        btn2.setOnAction(e -> {

        });

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(btn, btn1, btn2);
        borderPane.setBottom(fp);



        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });



        primaryStage.setScene(new Scene(borderPane,800,600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
