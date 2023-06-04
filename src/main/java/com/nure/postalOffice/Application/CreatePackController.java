package com.nure.postalOffice.Application;

import com.nure.postalOffice.DBObjects.Department;
import com.nure.postalOffice.DBObjects.User;
import com.nure.postalOffice.Service.DepartmentServiceDAO;
import com.nure.postalOffice.Service.PackageServiceDAO;
import com.nure.postalOffice.Service.UsersServiceDAO;
import com.nure.postalOffice.Service.CreatedPackServiceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.stream.Collectors;

@Controller
@FxmlView("createPack.fxml")
public class CreatePackController {
    public static User currentUser;

    @FXML
    public ComboBox<Integer> senderDepartmentBox;
    @FXML
    public ComboBox<Integer> receiverDepartmentBox;
    @FXML
    public TextField receiverTextField;
    @FXML
    public Label errorLabel;
    @Autowired
    private CreatedPackServiceDAO createdPackServiceDAO;
    @Autowired
    private DepartmentServiceDAO departmentServiceDAO;
    @Autowired
    private PackageServiceDAO packageServiceDAO;
    @Autowired
    private UsersServiceDAO usersServiceDAO;


    public void initialize() {
        receiverDepartmentBox.getItems().addAll(departmentServiceDAO.getAllDepartments().stream().map(Department::getId).collect(Collectors.toList()));
        senderDepartmentBox.getItems().addAll(departmentServiceDAO.getAllDepartments().stream().map(Department::getId).collect(Collectors.toList()));
    }

    @FXML
    public void saveButtonPressed() {
        errorLabel.setStyle("-fx-font-fill:red;");
        errorLabel.setText("");
        if (usersServiceDAO.getAllUsers().stream().map(User::getUsername).noneMatch(username -> username.equals(receiverTextField.getText()))) {
            errorLabel.setText("no such receiver username find in database");
            return;
        }
        if (senderDepartmentBox.getValue() == null || receiverDepartmentBox.getValue() == null) {
            errorLabel.setText("choose sender`s and receiver's departments");
            return;
        }
        long id;
        do {
            id = (long) (Math.random() * 9999999999L);
        } while (id < 1000000000 || id > 9999999999L || packageServiceDAO.getPackagesByParams("WHERE p.ID = ?", new Object[]{id}).size() != 0 || createdPackServiceDAO.getPackagesByParams("WHERE p.ID = ?", new Object[]{id}).size() != 0);
        createdPackServiceDAO.addToDatabase(id, currentUser.getUsername(), receiverTextField.getText(), senderDepartmentBox.getValue(), receiverDepartmentBox.getValue(), new Date());
        errorLabel.setStyle("-fx-font-fill:green;");
        errorLabel.setText("success");
    }
}
