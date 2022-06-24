package com.nure.postalOffice.Application;

import com.nure.postalOffice.DBObjects.Package;
import com.nure.postalOffice.DBObjects.User;
import com.nure.postalOffice.Service.PackageServiceDAO;
import com.nure.postalOffice.Service.UsersServiceDAO;
import com.nure.postalOffice.Service.CreatedPackServiceDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("user.fxml")
public class UserController {

    public static User currentUser;
    @FXML
    public TableView<Package> receivingParcelsTableView;
    @FXML
    public TableView<Package> sentParcelsTableView;

    @Autowired
    private UsersServiceDAO usersServiceDAO;
    @Autowired
    private PackageServiceDAO packageServiceDAO;
    @Autowired
    private CreatedPackServiceDAO createdPackServiceDAO;


    public void initialize() {
        sentParcelsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        sentParcelsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("receiver"));
        sentParcelsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("receiverDepartmentId"));
        sentParcelsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dateDeparture"));
        sentParcelsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateReceiving"));
        sentParcelsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        sentParcelsTableView.setItems(FXCollections.observableList(packageServiceDAO.getPackagesByParams("WHERE SENDER = ?", new Object[]{currentUser.getUsername()})));

        receivingParcelsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        receivingParcelsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("sender"));
        receivingParcelsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("receiverDepartmentId"));
        receivingParcelsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dateDeparture"));
        receivingParcelsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateReceiving"));
        receivingParcelsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        receivingParcelsTableView.setItems(FXCollections.observableList(packageServiceDAO.getPackagesByParams("WHERE RECEIVER = ?", new Object[]{currentUser.getUsername()})));
        for (TableColumn<Package,?> column : receivingParcelsTableView.getColumns()) {
            column.setStyle("-fx-background-color: #ffeebf;");
        }
        for (TableColumn<Package,?> column : sentParcelsTableView.getColumns()) {
            column.setStyle("-fx-background-color: #ffaea8;");
        }
    }

    @FXML
    public void createParcelButtonPressed() {
        CreatePackController.currentUser = currentUser;
        FxWeaver fxWeaver = JavaFxApp.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(CreatePackController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitButtonPressed(){
        Stage stage = (Stage) receivingParcelsTableView.getScene().getWindow();
        stage.close();
        FxWeaver fxWeaver = JavaFxApp.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}