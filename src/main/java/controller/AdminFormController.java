package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFormController {

    public TextField txtName;
    public TextField txtPassword;
    public Button btnConform;
    public AnchorPane root;

    private String name="Gaaji";
    private String password="1234";

    public void conform_OnAction(ActionEvent actionEvent) throws IOException {

        String userPass=txtPassword.getText ();
        String userName=txtName.getText ();
        if (userName.equals (name) && userPass.equals (password)){
            var stage3 = new Stage();
            stage3.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/hostel/view/UserAcc.fxml"))));
            stage3.show();

            Stage stage4 = (Stage) root.getScene().getWindow();
            stage4.close();
        }else{
            new Alert(Alert.AlertType.ERROR, "INVALID ADMIN ATHUENTICATION").show ();
            txtPassword.clear ();
            txtName.clear ();
        }

    }
    }
}
