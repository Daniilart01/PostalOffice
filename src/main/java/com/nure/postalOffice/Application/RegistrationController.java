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
@FxmlView("registration.fxml")
public class RegistrationController {
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordFieldRepeat;
    @FXML
    public Label incorrectDataLabel;

    @Autowired
    private UsersServiceDAO usersServiceDAO;

    @FXML
    public void registerButtonPressed() {
        if (usernameField.getText().length() > 24 || usernameField.getText().length() < 4) {
            incorrectDataLabel.setText("username should be in length 4-24");
            usernameField.setText("");
            passwordField.setText("");
            passwordFieldRepeat.setText("");
            return;
        }
        if (passwordField.getText().length() < 6 || passwordField.getText().length() > 16) {
            incorrectDataLabel.setText("password should be in length 6-16");
            usernameField.setText("");
            passwordField.setText("");
            passwordFieldRepeat.setText("");
            return;
        }
        if (!passwordField.getText().equals(passwordFieldRepeat.getText())) {
            incorrectDataLabel.setText("passwords doesn't match");
            passwordField.setText("");
            passwordFieldRepeat.setText("");
            return;
        }
        if (usersServiceDAO.getAllUsers().stream().map(User::getUsername).anyMatch(s -> s.equals(usernameField.getText()))) {
            incorrectDataLabel.setText("username already exists");
            usernameField.setText("");
            passwordField.setText("");
            passwordFieldRepeat.setText("");
            return;
        }
        incorrectDataLabel.setText("Registration Successful!");
        incorrectDataLabel.setStyle("-fx-text-fill: green;");
        usersServiceDAO.addToDatabase(new User(usernameField.getText(), passwordField.getText()));

        runLoginForm();
    }

    private void runLoginForm() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
        FxWeaver fxWeaver = JavaFxApp.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
