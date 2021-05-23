
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;

public class Demographics extends Application
{
    public TextField tfName = new TextField();
    public TextField tfEmail = new TextField();
    public Label lblName = new Label("Name");
    public Label lblEmail = new Label("Email");
    RadioButton rbS = new RadioButton("Single");
    RadioButton rbMFJ = new RadioButton("Married Filing Jointly");
    RadioButton rbMFS = new RadioButton("Married Filing Separately");
    RadioButton rbHOH = new RadioButton("Head of Household");

    protected Pane getPane()
    {
        // create a name and email field grid
        GridPane demoGrid = new GridPane();
        demoGrid.setHgap(30);
        demoGrid.setVgap(10);
        demoGrid.setPadding(new Insets(10, 10, 20, 10));
        lblName.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblEmail.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        tfName.setPrefWidth(260);
        demoGrid.add(lblName, 0, 0);
        demoGrid.add(tfName, 1, 0);
        demoGrid.add(lblEmail, 0, 1);
        demoGrid.add(tfEmail, 1, 1);
        demoGrid.setAlignment(Pos.TOP_LEFT);
        demoGrid.relocate(0, 20);

        // create the grid for radio buttons
        GridPane radioGrid = new GridPane();
        radioGrid.setHgap(15);
        radioGrid.setVgap(5);
        radioGrid.setPadding(new Insets(10, 10, 10, 10));
        rbS.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        rbMFJ.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        rbMFS.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        rbHOH.setStyle("-fx-font-weight: bold; -fx-font-size: 14");

        radioGrid.add(rbS, 0, 0);
        radioGrid.add(rbMFJ, 0, 1);
        radioGrid.add(rbMFS, 1, 0);
        radioGrid.add(rbHOH, 1, 1);

        ToggleGroup group = new ToggleGroup();
        rbS.setToggleGroup(group);
        rbMFJ.setToggleGroup(group);
        rbMFS.setToggleGroup(group);
        rbHOH.setToggleGroup(group);

        // create pane and add demo and radio grid
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(demoGrid, radioGrid);
        demoGrid.setLayoutX(20);
        radioGrid.relocate(20, 100);

        return pane;
    }


    @Override
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(getPane(), 1250, 700);
        primaryStage.setTitle("Tax GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
