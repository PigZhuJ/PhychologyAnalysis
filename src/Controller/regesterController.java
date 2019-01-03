package Controller;

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

public class regesterController extends Controller {

    @FXML
    private Button CancelButton;

    @FXML
    private TextField adminAccount;

    @FXML
    private TextField adminNumber;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField adminPassword;

    @FXML
    void handleConfirm(ActionEvent event) throws ClassNotFoundException {
        String administratorId=adminNumber.getText().trim();
        String administratorName=adminAccount.getText().trim();
        String administratorPwd=adminPassword.getText().trim();
        try{
            if(administratorId.equals("")==false&&administratorName.equals("")==false&&administratorPwd.equals("")==false) {
                DBRegister.registerAccount(administratorId, administratorName, administratorPwd);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Register successfully");
                alert.setHeaderText("Finished");
                alert.setContentText("");
                alert.showAndWait();
                stage.close();
                ViewLoader.showStage(model, "/View/Login.fxml", "Login", new Stage());
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input Error");
                alert.setContentText("");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Register Error");
            alert.setContentText("");
            alert.showAndWait();
            adminNumber.setText("");
            adminAccount.setText("");
            adminPassword.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void HandleCancle(ActionEvent event) throws IOException {
        stage.close();
        ViewLoader.showStage(model, "/View/Login.fxml", "Login", new Stage());
    }

}
