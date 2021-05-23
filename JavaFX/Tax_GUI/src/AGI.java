
import CalculateAGI.CalcAGI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.swing.*;

public class AGI extends Demographics
{
    public Label lblHeader = new Label("Adjusted Gross Income");
    public Label lblW2 = new Label("W2 Income:");
    public Label lblCap = new Label("Capital Gains");
    public Label lblSchE = new Label("Schedule E:");
    public Label lblSchC = new Label("Schedule C");
    public Label lblRetirement = new Label("Retirement:");
    public Label lblHSA = new Label("HSA Contribution:");
    public Label lblStu = new Label("Student Loan Interest");
    public Label lblIRA = new Label("IRA Contribution:");
    public Label lblSETax = new Label("Self-Employment Tax");
    public TextField tfW2 = new TextField();
    public TextField tfCap = new TextField();
    public TextField tfSchE = new TextField();
    public TextField tfSchC = new TextField();
    public TextField tfRetirement = new TextField();
    public TextField tfHSA = new TextField();
    public TextField tfStu = new TextField();
    public TextField tfIRA = new TextField();
    public TextField tfSETax = new TextField();
    public ListView<String> results = new ListView<>();


    @Override
    protected Pane getPane()
    {
        Pane pane = super.getPane();
        lblHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 22; " +
                           "-fx-background-color: lightblue");
        pane.getChildren().add(lblHeader);
        lblHeader.relocate(62, 180);

        // make AGI input and label onto grid
        GridPane agiGrid = new GridPane();
        agiGrid.setVgap(5);
        agiGrid.setHgap(35);
        agiGrid.setStyle("-fx-border-color: black; -fx-border-width: 2px");
        agiGrid.setPadding(new Insets(10, 10, 10, 10));
        lblW2.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblCap.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblSchE.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblSchC.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblRetirement.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblHSA.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblStu.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblIRA.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblSETax.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        agiGrid.add(lblW2, 0, 0);
        agiGrid.add(tfW2, 1, 0);
        agiGrid.add(lblCap, 0, 1);
        agiGrid.add(tfCap, 1, 1);
        agiGrid.add(lblSchE, 0, 2);
        agiGrid.add(tfSchE, 1, 2);
        agiGrid.add(lblSchC, 0, 3);
        agiGrid.add(tfSchC, 1, 3);
        agiGrid.add(lblRetirement, 0, 4);
        agiGrid.add(tfRetirement, 1, 4);
        agiGrid.add(lblHSA, 0, 5);
        agiGrid.add(tfHSA, 1, 5);
        agiGrid.add(lblStu, 0, 6);
        agiGrid.add(tfStu, 1, 6);
        agiGrid.add(lblIRA, 0, 7);
        agiGrid.add(tfIRA, 1, 7);
        agiGrid.add(lblSETax, 0, 8);
        agiGrid.add(tfSETax, 1, 8);
        pane.getChildren().add(agiGrid);
        agiGrid.setLayoutX(20);
        agiGrid.setLayoutY(220);

        // add buttons to pane
        HBox btnPane = new HBox(20);
        Button btnCalculate = new Button("Calculate");
        Button btnClear = new Button("Clear");
        btnCalculate.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        btnClear.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        btnPane.getChildren().addAll(btnCalculate, btnClear);
        pane.getChildren().add(btnPane);
        btnPane.setLayoutX(100);
        btnPane.setLayoutY(550);

        // create listview
        results.setStyle("-fx-font-weight: bold; -fx-font-size: 14;" +
                "-fx-font-family: Consolas");

        results.setPrefSize(440, 650);
        pane.getChildren().add(results);
        results.setLayoutX(800);
        results.setLayoutY(20);

        // clear text fields
        btnClear.setOnAction(e ->
        {
            tfW2.clear();
            tfCap.clear();
            tfSchE.clear();
            tfSchC.clear();
            tfRetirement.clear();
            tfHSA.clear();
            tfStu.clear();
            tfIRA.clear();
            tfSETax.clear();
        });


        btnCalculate.setOnAction(e ->
        {

            if (!checkNull(tfName, "Name field")){
                return;
            }
            else if (!checkNull(tfEmail, "Email field")){
                return;
            }
            else if (!checkNull(tfW2, "W2 field")){
                return;
            }
            else if (!checkNull(tfCap, "Capital field")){
                return;
            }
            else if (!checkNull(tfSchE, "Schedule E field")){
                return;
            }
            else if (!checkNull(tfSchC, "Schedule C field")){
                return;
            }
            else if (!checkNull(tfRetirement, "Retirement field")){
                return;
            }
            else if (!checkNull(tfHSA, "HSA field")){
                return;
            }
            else if (!checkNull(tfStu, "Student Loan Interest field")){
                return;
            }
            else if (!checkNull(tfIRA, "IRA Contribution field")){
                return;
            }
            else if (!checkNull(tfSETax, "Self-Employment tax field")){
                return;
            }

            if (!rbS.isSelected() && !rbMFJ.isSelected() && !rbMFS.isSelected() &&
                !rbHOH.isSelected())
            {
                new Alert(Alert.AlertType.ERROR, "Must select a filing status").showAndWait();
                return;
            }

            String name = tfName.getText().toString();
            String status = "";

            if (rbHOH.isSelected()){
                status = "HOH";
            }
            else if (rbMFJ.isSelected()){
                status = "MFJ";
            }
            else if (rbMFS.isSelected()){
                status = "MFS";
            }
            else if (rbS.isSelected()){
                status = "S";
            }

            double W2 = Double.parseDouble(tfW2.getText());
            double captial = Double.parseDouble(tfCap.getText());
            double SchE = Double.parseDouble(tfSchE.getText());
            double SchC = Double.parseDouble(tfSchC.getText());
            double retirement = Double.parseDouble(tfRetirement.getText());
            double hsa = Double.parseDouble(tfHSA.getText());
            double stu = Double.parseDouble(tfStu.getText());
            double ira = Double.parseDouble(tfIRA.getText());
            double seTax = Double.parseDouble(tfSETax.getText());

            CalcAGI agi = new CalcAGI(name, status, W2, captial, SchE, SchC, retirement,
                                      hsa, stu, ira, seTax);


            double hsaDeduct = agi.getHSA_Deduct();
            double stuDeduct = agi.getStu_Deduct();
            double iraDeduct = agi.getIRA_Dedcut();
            double seTaxDeduct = agi.getSE_Dedcut();
            double agiNum = agi.getAGI();


            String fName = String.format("%-35s", "Name:") + name;
            String fStatus = String.format("%-35s", "Filing Status:") + status;
            String fW2 = String.format("%-35s", "W2:") + W2;
            String fCap = String.format("%-35s", "Capital Gains:") + captial;
            String fSchE = String.format("%-35s", "Schedule E:") + SchE;
            String fSchC = String.format("%-35s", "Schedule C:") + SchC;
            String fRet = String.format("%-35s", "Retirement:") + retirement;
            String fHSA = String.format("%-45s", "HSA Deduction:") + "(" + hsaDeduct + ")";
            String fStu = String.format("%-45s", "Student Loan Deduction:") + "(" + stuDeduct + ")";
            String fIRA = String.format("%-45s", "IRA Deduction") + "(" + iraDeduct + ")";
            String fSE = String.format("%-45s", "SE Tax Deduction:") + "(" + seTaxDeduct + ")";
            String fAGI = String.format("%-35s", "Adjusted Gross Income:") + agiNum;

            results.getItems().add(0, fName);
            results.getItems().add(1, fStatus);
            results.getItems().add(2, fW2);
            results.getItems().add(3, fCap);
            results.getItems().add(4, fSchE);
            results.getItems().add(5, fSchC);
            results.getItems().add(6, fRet);
            results.getItems().add(7, "");
            results.getItems().add(8, fHSA);
            results.getItems().add(9, fStu);
            results.getItems().add(10, fIRA);
            results.getItems().add(11, fSE);
            results.getItems().add(12, "");
            results.getItems().add(13, fAGI);

        });
        
        return pane;
    }


    public static boolean checkNull(TextField text, String s)
    {
        if (text.getText().trim().isEmpty())
        {
            new Alert(Alert.AlertType.ERROR, s + " cannot be empty" ).showAndWait();
            return false;
        }
        else
        {
            return true;
        }
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
