package com.nure.postalOffice.Application;

import com.nure.postalOffice.DBObjects.Department;
import com.nure.postalOffice.DBObjects.Package;
import com.nure.postalOffice.DBObjects.User;
import com.nure.postalOffice.Service.DepartmentServiceDAO;
import com.nure.postalOffice.Service.PackageServiceDAO;
import com.nure.postalOffice.Service.UsersServiceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
@FxmlView("alterPack.fxml")
public class AlterPackageController {

    public static AdminController adminController;

    public static Package pack;
    @FXML
    public Label idLabel;
    @FXML
    public ComboBox<String> senderBox;
    @FXML
    public ComboBox<String> receiverBox;
    @FXML
    public ComboBox<Integer> senderDepartmentBox;
    @FXML
    public ComboBox<String> statusBox;
    @FXML
    public ComboBox<Integer> receiverDepartmentBox;
    @FXML
    public DatePicker dateReceivingBox;
    @FXML
    public DatePicker dateDepartureBox;
    @Autowired
    private PackageServiceDAO packageServiceDAO;
    @Autowired
    private UsersServiceDAO usersServiceDAO;
    @Autowired
    private DepartmentServiceDAO departmentServiceDAO;


    public void initialize() {
        idLabel.setText(Long.toString(pack.getId()));
        senderBox.getItems().addAll(usersServiceDAO.getAllUsers().stream().map(User::getUsername).collect(Collectors.toList()));
        senderBox.setValue(pack.getSender());
        receiverBox.getItems().addAll(usersServiceDAO.getAllUsers().stream().map(User::getUsername).collect(Collectors.toList()));
        receiverBox.setValue(pack.getReceiver());
        senderDepartmentBox.getItems().addAll(departmentServiceDAO.getAllDepartments().stream().map(Department::getId).collect(Collectors.toList()));
        senderDepartmentBox.setValue(pack.getSenderDepartmentId());
        receiverDepartmentBox.getItems().addAll(departmentServiceDAO.getAllDepartments().stream().map(Department::getId).collect(Collectors.toList()));
        receiverDepartmentBox.setValue(pack.getReceiverDepartmentId());
        statusBox.getItems().addAll("Accepted", "Shipping", "Received", "Delivered");
        statusBox.setValue(pack.getStatus());
        dateDepartureBox.setValue(Instant.ofEpochMilli(pack.getDateDeparture().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        dateReceivingBox.setValue(Instant.ofEpochMilli(pack.getDateReceiving().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @FXML
    public void saveButtonPressed() {
        long id = pack.getId();
        packageServiceDAO.deleteFromDatabase(id);
        packageServiceDAO.addToDatabase(id, senderBox.getValue(), receiverBox.getValue(), senderDepartmentBox.getValue(),
                receiverDepartmentBox.getValue(), statusBox.getValue(),
                Date.from(dateDepartureBox.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(dateReceivingBox.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Stage stage = (Stage) idLabel.getScene().getWindow();
        stage.close();
        adminController.refresh();
    }
}
