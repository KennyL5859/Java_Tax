
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
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;




public class Final extends Itemize
{
    @Override
    protected Pane getPane()
    {
        Pane pane = super.getPane();
        Button btnEmail = new Button("Email");
        btnEmail.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        pane.getChildren().add(btnEmail);
        btnEmail.relocate(400, 45);

        btnEmail.setOnAction(e ->
        {
            email();
        });

        return pane;
    }

    public void email()
    {
        String to = "kennethlin5566@gmail.com";
        String from = "kennethlin88@gmail.com";
        final String username = "kennethlin88@gmail.com";
        final String password = "wtal zwal nkrg vwti";

        String subject = "Java Email";
        String body = "Welcome to JavaMail";


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try
        {
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }
        catch (MessagingException x)
        {
            throw new RuntimeException(x);
        }


    }

    public void start(Stage stage)
    {
        // add menu bar to pane
        Pane pane = getPane();
        Menu menu = new Menu("Menu");
        MenuItem m1 = new MenuItem("Save as");
        menu.getItems().add(m1);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        pane.getChildren().add(menuBar);
        FileChooser fileC = new FileChooser();

        Scene scene = new Scene(pane, 1250, 700);
        stage.setTitle("Tax GUI");
        stage.setScene(scene);
        stage.show();

        // write to csv file
        m1.setOnAction(e ->
        {
            File file = fileC.showSaveDialog(stage);

            if (file != null)
            {
                File fileS = new File(file.getAbsolutePath());
                writeToExcel(fileS);
            }
        });
    }

    public void writeToExcel(File file)
    {
        String name = tfName.getText().toString();
        String status = "";

        if (rbMFJ.isSelected()){
            status = "MFJ";
        }
        else if (rbHOH.isSelected()){
            status = "HOH";
        }
        else if (rbMFS.isSelected()){
            status = "MFS";
        }
        else if (rbS.isSelected()){
            status = "S";
        }

        double W2 = Double.parseDouble(tfW2.getText());
        double cap = Double.parseDouble(tfCap.getText());
        double schE = Double.parseDouble(tfSchE.getText());
        double schC = Double.parseDouble(tfSchC.getText());
        double retire = Double.parseDouble(tfRetirement.getText());
        double hsa = Double.parseDouble(tfHSA.getText()) * -1;
        double stu = Double.parseDouble(tfStu.getText()) * -1;
        double ira = Double.parseDouble(tfIRA.getText()) * -1;
        double seTax = Double.parseDouble(tfSETax.getText()) * -1;
        double mortInt = Double.parseDouble(tfMortInt.getText());
        double mortIns = Double.parseDouble(tfMortIns.getText());
        double charit = Double.parseDouble(tfChari.getText());
        double med = Double.parseDouble(tfMed.getText());
        double slTax = Double.parseDouble(tfSLTax.getText());
        CalcAGI agiS = new CalcAGI(name, status, W2, cap, schE, schC, retire,
                hsa, stu, ira, seTax);
        double agi = agiS.getAGI();

        Deductions ded = new Deductions(mortInt, mortIns, charit, med, slTax);
        double charitDeduct = ded.getCharDeduct(agi);
        double medDeduct = ded.getMedDeduct(agi);
        double slTaxDeduct = ded.getSLTaxDeduct();
        double itemize = ded.getItemize(agi);
        String iOrS = ded.itemOrStandard(status, itemize);



        try (PrintWriter writer = new PrintWriter(file))
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Client Name," + tfName.getText() + "\n");
            sb.append("Filing Status," + status + "\n\n");
            sb.append("W2 Income," + W2 + "\n");
            sb.append("Capital Gains," + cap + "\n");
            sb.append("Schedule E Income," + schE + "\n");
            sb.append("Schedule C Income," + schC + "\n");
            sb.append("Retirement Income," + retire + "\n\n");
            sb.append("HSA Deduction,," + hsa + "\n");
            sb.append("Student Loan Deduction,," + stu + "\n");
            sb.append("IRA Contribution Deduction,," + ira + "\n");
            sb.append("Self-Employment Tax,," + seTax + "\n\n");
            sb.append("Adjusted Gross Income," + agi + "\n\n\n");
            sb.append("Mortage Interest Deduction,," + mortInt + "\n");
            sb.append("Mortage Insurance Deduction,," + mortIns + "\n");
            sb.append("Charitable Contributions Deduction,," + charitDeduct + "\n");
            sb.append("Medical Expense Deduction,," + medDeduct + "\n");
            sb.append("State & Local Tax Deduction,," + slTaxDeduct + "\n\n");
            sb.append("Total Itemized Deduction,," + itemize + "\n\n");
            sb.append(iOrS);
            writer.write(sb.toString());
        }
        catch (IOException x)
        {
            x.printStackTrace();;
        }

    }


    public static void main(String[] args)
    {
        Application.launch(args);
    }

}
