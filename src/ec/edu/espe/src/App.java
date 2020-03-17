
package ec.edu.espe.src;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Prácticas - Sección 9: Búsqueda de una ubicación central
 *
 * @author Dany Fernando Lasso Ayala
 */
public class App extends Application {

    Rectangle residenciaAdministrativa, residenciaEstudiantes, bloqueA, bloqueC, coliseo, canchas;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    Circle todosEstudiantes, grupoEstudio;
    Label labelTodosEstudiantes , labelGrupoEstudio;

    @Override
    public void start(Stage primaryStage) {
        String mapEspePath = "img/espe.jpg";
        Image mapEspe = new Image(getClass().getResource(mapEspePath).toString());
        ImageView mv = new ImageView(mapEspe);
        mv.setLayoutX(50);
        mv.setLayoutY(20);

        //Formas
        Color color = Color.rgb(255, 250, 0, 0.7);

        residenciaAdministrativa = new Rectangle(380, 210, 75, 60);

        residenciaAdministrativa.setFill(color);
        residenciaAdministrativa.setOnMousePressed(circleOnMousePressedEventHandler);
        residenciaAdministrativa.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        residenciaEstudiantes = new Rectangle(50, 300, 200, 30);
        residenciaEstudiantes.setFill(Color.rgb(0, 43, 255, 0.7));
        residenciaEstudiantes.setOnMousePressed(circleOnMousePressedEventHandler);
        residenciaEstudiantes.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        bloqueA = new Rectangle(400, 150, 40, 40);
        bloqueA.setFill(Color.rgb(255, 0, 0, 0.7));
        bloqueA.setOnMousePressed(circleOnMousePressedEventHandler);
        bloqueA.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        bloqueC = new Rectangle(500, 150, 40, 40);
        bloqueC.setFill(Color.rgb(255, 0, 255, 0.65));
        bloqueC.setOnMousePressed(circleOnMousePressedEventHandler);
        bloqueC.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        coliseo = new Rectangle(100, 210, 100, 60);
        coliseo.setFill(Color.rgb(255, 166, 0, 0.7));
        coliseo.setOnMousePressed(circleOnMousePressedEventHandler);
        coliseo.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        canchas = new Rectangle(200, 150, 150, 40);
        canchas.setFill(Color.rgb(2, 156, 82, 0.6));
        canchas.setOnMousePressed(circleOnMousePressedEventHandler);
        canchas.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        todosEstudiantes = new Circle(350, 210, 10);
        todosEstudiantes.setFill(Color.rgb(0, 0, 255, 0.6));
        labelTodosEstudiantes = new Label("todos  los\n"
                + "estudiantes", todosEstudiantes);
        labelTodosEstudiantes.setLayoutX(350);
        labelTodosEstudiantes.setLayoutY(210);

        grupoEstudio = new Circle(345, 230, 10);
        grupoEstudio.setFill(Color.rgb(0, 255, 0, 0.6));
        labelGrupoEstudio = new Label("grupo de\nestudio", grupoEstudio);
        labelGrupoEstudio.setLayoutX(345);
        labelGrupoEstudio.setLayoutY(230);
        
        Group root = new Group();
        root.getChildren().add(mv);
        root.getChildren().add(residenciaAdministrativa);
        root.getChildren().add(residenciaEstudiantes);
        root.getChildren().add(bloqueA);
        root.getChildren().add(bloqueC);
        root.getChildren().add(canchas);
        root.getChildren().add(coliseo);
        root.getChildren().add(todosEstudiantes);
        root.getChildren().add(grupoEstudio);
        root.getChildren().add(labelTodosEstudiantes);
        root.getChildren().add(labelGrupoEstudio);
        
        Scene scene = new Scene(root, 700, 500);

        primaryStage.setTitle("Prácticas - Sección 9: Búsqueda de una ubicación central");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Rectangle) (t.getSource())).getTranslateX();
            orgTranslateY = ((Rectangle) (t.getSource())).getTranslateY();

        }
    };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((Rectangle) (t.getSource())).setTranslateX(newTranslateX);
            ((Rectangle) (t.getSource())).setTranslateY(newTranslateY);
            if (t.getSource().equals(bloqueA) || t.getSource().equals(bloqueC) || t.getSource().equals(residenciaEstudiantes)) {
                grupoEstudio.setTranslateX(newTranslateX / 2);
                grupoEstudio.setTranslateY(newTranslateY / 2);
            }
            todosEstudiantes.setTranslateX(newTranslateX / 10);
            todosEstudiantes.setTranslateY(newTranslateY / 10);
            labelTodosEstudiantes.setTranslateX((newTranslateX / 10) + 2);
        }
    };

}
