package Controller;

import DBUtils.DBLogin;
import DBUtils.DBRegister;
import Utils.Controller;
import Utils.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends Controller {

    @FXML
    private Button CancelButton;

    @FXML
    private TextField adminAccount;

    @FXML
    private TextField adminNumber;

    @FXML
    private Button regesiterButton;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField adminPassword;


    @FXML
    void handleRegester(ActionEvent event) throws IOException {
        stage.close();
        ViewLoader.showStage(model, "/View/regester.fxml", "regester", new Stage());
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String administratorId=adminNumber.getText().trim();
        String administratorName=adminAccount.getText().trim();
        String administratorPwd=adminPassword.getText().trim();
        try{
            if(administratorId.equals("")==false&&administratorName.equals("")==false&&administratorPwd.equals("")==false) {
               if( DBLogin.Login(administratorId, administratorName, administratorPwd)){
                   stage.close();
                   ViewLoader.showStage(model, "/View/PsychologyBoundary.fxml", "Psychology Analysis", new Stage());
               }else {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("NO account,Please Regester first");
                   alert.setContentText("");
                   alert.showAndWait();
               }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error account");
                alert.setContentText("");
                alert.showAndWait();
                adminNumber.setText("");
                adminAccount.setText("");
                adminPassword.setText("");
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error");
            alert.setContentText("");
            alert.showAndWait();
            adminNumber.setText("");
            adminAccount.setText("");
            adminPassword.setText("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void HandleCancle(ActionEvent event) {
        System.exit(0);
    }

}
