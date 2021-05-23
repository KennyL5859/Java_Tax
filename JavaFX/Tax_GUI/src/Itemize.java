
import CalculateAGI.CalcAGI;
import CalculateAGI.Deductions;
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

public class Itemize extends AGI
{
    public Label lblMortInt = new Label("Mortgage Interest:");
    public Label lblMortIns = new Label("Mortgage Insurance:");
    public Label lblCharit = new Label("Charitable Contribution:");
    public Label lblMed = new Label("Medical Expense:");
    public Label lblSLTax = new Label("State & Local Tax:");
    public Label lblItemize = new Label("Itemized");
    public TextField tfMortInt = new TextField();
    public TextField tfMortIns = new TextField();
    public TextField tfChari = new TextField();
    public TextField tfMed = new TextField();
    public TextField tfSLTax = new TextField();


    @Override
    protected Pane getPane()
    {
        Pane pane = super.getPane();

        // set the itemized label
        lblItemize.setStyle("-fx-font-weight: bold; -fx-font-size: 22; " +
                            "-fx-background-color: lightblue");
        pane.getChildren().add(lblItemize);
        lblItemize.relocate(540, 180);

        // set the itemized gridpane
        GridPane itemPane = new GridPane();
        itemPane.setVgap(5);
        itemPane.setHgap(35);
        itemPane.setStyle("-fx-border-color: black; -fx-border-width: 2px");
        itemPane.setPadding(new Insets(10, 10, 10, 10));
        lblMortIns.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblMortInt.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblCharit.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblMed.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        lblSLTax.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        itemPane.add(lblMortInt, 0, 0);
        itemPane.add(tfMortInt, 1, 0);
        itemPane.add(lblMortIns, 0, 1);
        itemPane.add(tfMortIns, 1, 1);
        itemPane.add(lblCharit, 0, 2);
        itemPane.add(tfChari, 1, 2);
        itemPane.add(lblMed, 0, 3);
        itemPane.add(tfMed, 1, 3);
        itemPane.add(lblSLTax, 0, 4);
        itemPane.add(tfSLTax, 1, 4);
        pane.getChildren().add(itemPane);
        itemPane.relocate(400, 220);

        HBox hbButttons = new HBox(20);
        Button btnICalculate = new Button("Calculate");
        Button btnIClear = new Button("Clear");
        btnICalculate.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        btnIClear.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        hbButttons.getChildren().addAll(btnICalculate, btnIClear);
        pane.getChildren().add(hbButttons);
        hbButttons.relocate(515, 415);

        btnIClear.setOnAction(e ->
        {
            tfMortInt.clear();
            tfMortIns.clear();
            tfChari.clear();
            tfMed.clear();
            tfSLTax.clear();
        });

        btnICalculate.setOnAction(e ->
        {
            if (!checkNull(tfMortInt, "Mortage Interest field")){
                return;
            }
            else if (!checkNull(tfMortIns, "Mortgage Insurance field")){
                return;
            }
            else if (!checkNull(tfChari, "Charitable contributions field")){
                return;
            }
            else if (!checkNull(tfMed, "Medical expense field")){
                return;
            }
            else if (!checkNull(tfSLTax, "State & Local tax field")){
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

            double sAgi = agi.getAGI();
            double mortInt = Double.parseDouble(tfMortInt.getText());
            double mortIns = Double.parseDouble(tfMortIns.getText());
            double charit = Double.parseDouble(tfChari.getText());
            double medExp = Double.parseDouble(tfMed.getText());
            double slTax = Double.parseDouble(tfSLTax.getText());

            Deductions ded = new Deductions(mortInt, mortIns, charit, medExp, slTax);

            double charDeduct = ded.getCharDeduct(sAgi);
            double medDeduct = ded.getMedDeduct(sAgi);
            double slDeduct = ded.getSLTaxDeduct();
            double itemize = ded.getItemize(sAgi);


            String mortIntDeduct = String.format("%-45s", "Mortgage Interest Deduction:") + "("
                    + mortInt + ")";

            String mortInsDeduct = String.format("%-45s", "Mortgage Insurance Deduction:") + "("
                    + mortIns + ")";

            String charDeduction = String.format("%-45s", "Charitable Deduction:") + "("
                    + charDeduct + ")";

            String medExpDeduct = String.format("%-45s", "Medical Expense Deduction:") + "("
                    + medDeduct + ")";

            String slTaxDeduct = String.format("%-45s", "State/Local Tax Deduction:") + "("
                    + slDeduct + ")";

            String itemizeAmount = String.format("%-45s", "Total Itemized Amount:") + itemize;


            String iOrS = ded.itemOrStandard(status, itemize);

            results.getItems().add(14, "--------------------------------------------------");
            results.getItems().add(15, mortInsDeduct);
            results.getItems().add(16, mortIntDeduct);
            results.getItems().add(17, charDeduction);
            results.getItems().add(18, medExpDeduct);
            results.getItems().add(19, slTaxDeduct);
            results.getItems().add(20, "");
            results.getItems().add(21, itemizeAmount);
            results.getItems().add(22, "");
            results.getItems().add(23, iOrS);
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
