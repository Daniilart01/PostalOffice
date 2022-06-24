package com.nure.postalOffice.Application;

import com.nure.postalOffice.DBObjects.User;
import com.nure.postalOffice.Service.UsersServiceDAO;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("login.fxml")
public class LoginController {
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Label incorrectDataLabel;

    @Autowired
    private UsersServiceDAO usersServiceDAO;


    @FXML
    public void loginButtonPressed() {
        User user = usersServiceDAO.getUserWithUsername(usernameField.getText());
        if (user == null) {
            incorrectDataLabel.setText("incorrect username or password");
        } else {
            if (user.getPassword().equals(passwordField.getText())) {
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.close();
                login(user);
            } else {
                incorrectDataLabel.setText("incorrect username or password");
            }
        }
    }

    @FXML
    public void registerButtonPressed() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
        FxWeaver fxWeaver = JavaFxApp.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(RegistrationController.class);
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void login(User user) {
        UserController.currentUser = user;
        FxWeaver fxWeaver = JavaFxApp.applicationContext.getBean(FxWeaver.class);
        Parent root;
        if (user.getRole().equals("admin")) {
            root = fxWeaver.loadView(AdminController.class);
        } else {
            root = fxWeaver.loadView(UserController.class);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
