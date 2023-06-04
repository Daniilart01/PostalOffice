package com.nure.postalOffice.Application;

import com.nure.postalOffice.DBObjects.Package;
import com.nure.postalOffice.DBObjects.User;
import com.nure.postalOffice.DBObjects.CreatedPack;
import com.nure.postalOffice.Service.PackageServiceDAO;
import com.nure.postalOffice.Service.UsersServiceDAO;
import com.nure.postalOffice.Service.CreatedPackServiceDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;


@Controller
@FxmlView("admin.fxml")
public class AdminController {


    @FXML
    public TableView<Package> packageTableView;
    @FXML
    public TableView<User> usersTableView;
    @FXML
    public TableView<CreatedPack> waitingPackageTableView;

    @FXML
    public Label errorLabelPackages;
    @FXML
    private Label errorLabelUsers;
    @FXML
    public ComboBox<String> searchOptions;
    @FXML
    public TextField searchText;

    @Autowired
    private PackageServiceDAO packageServiceDAO;
    @Autowired
    private UsersServiceDAO usersServiceDAO;
    @Autowired
    private CreatedPackServiceDAO createdPackServiceDAO;


    public void initialize() {


        packageTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        packageTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("sender"));
        packageTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("receiver"));
        packageTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
        packageTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateDeparture"));
        packageTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateReceiving"));
        packageTableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("senderDepartmentId"));
        packageTableView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("senderDepartmentCity"));
        packageTableView.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("senderDepartmentCountry"));
        packageTableView.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("senderDepartmentNumber"));
        packageTableView.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("receiverDepartmentId"));
        packageTableView.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("receiverDepartmentCity"));
        packageTableView.getColumns().get(12).setCellValueFactory(new PropertyValueFactory<>("receiverDepartmentCountry"));
        packageTableView.getColumns().get(13).setCellValueFactory(new PropertyValueFactory<>("receiverDepartmentNumber"));

        packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getAllPackages()));

        usersTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("username"));
        usersTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("password"));
        usersTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("role"));
        usersTableView.setItems(FXCollections.observableList(usersServiceDAO.getAllUsers()));

        waitingPackageTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        waitingPackageTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("sender"));
        waitingPackageTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("receiver"));
        waitingPackageTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("senderDepartmentId"));
        waitingPackageTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("receiverDepartmentId"));
        waitingPackageTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateCreating"));
        waitingPackageTableView.setItems(FXCollections.observableList(createdPackServiceDAO.getAllPackages()));

        searchOptions.getItems().addAll("Id", "Sender", "Receiver", "Sender Department", "Receiver Department", "Status");
    }


    @FXML
    public void resizeUsers() {
        errorLabelUsers.setText("");
        usersTableView.getScene().getWindow().setWidth(usersTableView.getPrefWidth());
    }

    @FXML
    public void resizePackages() {
        try {
            errorLabelPackages.setText("");
            packageTableView.getScene().getWindow().setWidth(packageTableView.getPrefWidth());
        } catch (Exception ignored) {

        }
    }

    @FXML
    public void resizeWaitingPackages() {
        waitingPackageTableView.getScene().getWindow().setWidth(waitingPackageTableView.getPrefWidth());
    }

    @FXML
    public void deletePackageButtonPressed() {
        Package selected = packageTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            errorLabelPackages.setText("Package is not selected!");
            return;
        }
        try {
            errorLabelPackages.setText("");
            packageServiceDAO.deleteFromDatabase(selected.getId());
            packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getAllPackages()));
        } catch (Exception exception) {
            errorLabelPackages.setText("Error altering database!");
        }
    }

    @FXML
    public void searchPackagesButtonPressed() {
        String query = "";
        errorLabelPackages.setText("");
        if (searchText.getText().isEmpty()) {
            packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getAllPackages()));
            return;
        } else if (searchOptions.getValue() == null) {
            errorLabelPackages.setText("Choose search option");
            return;
        } else if (searchOptions.getValue().equals("Id")) {
            query = "WHERE p.ID LIKE ?";
            packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getPackagesByParams(query, new Object[]{"%" + searchText.getText() + "%"})));
            return;
        } else if (searchOptions.getValue().equals("Sender")) {
            query = "WHERE SENDER = ?";
        } else if (searchOptions.getValue().equals("Receiver")) {
            query = "WHERE RECEIVER = ?";
        } else if (searchOptions.getValue().equals("Sender Department")) {
            query = "WHERE DEPARTMENT_ID_SENDER = ?";
        } else if (searchOptions.getValue().equals("Receiver Department")) {
            query = "WHERE DEPARTMENT_ID_RECEIVER = ?";
        } else if (searchOptions.getValue().equals("Status")) {
            query = "WHERE STATUS = ?";
        }
        packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getPackagesByParams(query, new Object[]{searchText.getText()})));
    }

    @FXML
    public void fixPackageButtonPressed() {
        Package selected = packageTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            errorLabelPackages.setText("Package is not selected!");
            return;
        }
        fixPackage(selected);
        errorLabelPackages.setText("");
    }

    private void fixPackage(Package packageToFix) {
        AlterPackageController.pack = packageToFix;
        FxWeaver fxWeaver = JavaFxApp.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(AlterPackageController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        AlterPackageController.adminController = this;
    }

    public void refresh() {
        packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getAllPackages()));
    }

    @FXML
    public void acceptWaitingPackageButtonPressed() {
        CreatedPack createdPack = waitingPackageTableView.getSelectionModel().getSelectedItem();
        if (createdPack == null) {
            return;
        }
        LocalDate localDate = LocalDate.now();
        packageServiceDAO.addToDatabase(createdPack.getId(), createdPack.getSender(), createdPack.getReceiver(),
                createdPack.getSenderDepartmentId(), createdPack.getReceiverDepartmentId(), "Accepted",
                Date.valueOf(localDate.plusDays(7)), Date.valueOf(localDate.plusDays(37)));
        createdPackServiceDAO.deleteFromDatabase(createdPack.getId());
        waitingPackageTableView.setItems(FXCollections.observableList(createdPackServiceDAO.getAllPackages()));
        packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getAllPackages()));
    }

    @FXML
    public void deleteUserButtonPressed() {
        errorLabelUsers.setText("");
        User user = usersTableView.getSelectionModel().getSelectedItem();
        if (user == null) {
            return;
        }
        if (user.getRole().equals("admin")) {
            errorLabelUsers.setText("Admin can not be deleted!");
            return;
        }
        usersServiceDAO.removeFromDatabase(user);
        usersTableView.setItems(FXCollections.observableList(usersServiceDAO.getAllUsers()));
        waitingPackageTableView.setItems(FXCollections.observableList(createdPackServiceDAO.getAllPackages()));
        packageTableView.setItems(FXCollections.observableList(packageServiceDAO.getAllPackages()));
    }
}
