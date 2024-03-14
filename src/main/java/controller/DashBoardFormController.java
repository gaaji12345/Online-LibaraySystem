package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    public AnchorPane root;
    public AnchorPane subPain;
    public Button btnUser;
    public Button btnBook;
    public Button btndetail;
    public Button btnBranches;
    public Button btnLogOut;
    public AnchorPane mainPain;

    public void userOn_Action(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/UserFormController.fxml"));
        Parent load= fxmlLoader.load();
      UserFormController controller=fxmlLoader.getController();
        mainPain.getChildren().clear();
        mainPain.getChildren().add(load);
    }

    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {
        Stage window= (Stage) root.getScene().getWindow();
        window.close();

        Parent load= FXMLLoader.load(getClass().getResource("/view/LoginFormController.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(load));
        stage.show();

    }

    public void book_OnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/BookFormController.fxml"));
        Parent load= fxmlLoader.load();
       BookFormController controller=fxmlLoader.getController();
        mainPain.getChildren().clear();
        mainPain.getChildren().add(load);
    }

    public void detail_OnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/DetailFormController.fxml"));
        Parent load= fxmlLoader.load();
       DetailFormController controller=fxmlLoader.getController();
        mainPain.getChildren().clear();
        mainPain.getChildren().add(load);
    }

    public void branches_oNAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/AdminFormController.fxml"));
        Parent load= fxmlLoader.load();
        AdminFormController controller=fxmlLoader.getController();
        mainPain.getChildren().clear();
        mainPain.getChildren().add(load);


    }
}
