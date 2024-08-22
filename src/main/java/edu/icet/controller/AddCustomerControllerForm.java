package edu.icet.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import edu.icet.db.DBConnection;
import edu.icet.model.Customer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerControllerForm implements Initializable {

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbTitles;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    public void addCustomerOnAction(ActionEvent actionEvent) {
        if (!(txtName.getText()==null||cmbTitles.getValue()==null||txtAddress.getText()==null||dateDOB.getValue()==null)){

            // add customer to array list
            DBConnection.getInstance().getConnection().add(new Customer(txtId.getText(),cmbTitles.getValue(),txtName.getText(),txtAddress.getText(),dateDOB.getValue()));

            //clear form
            clearForm();

            //generate ID
            generateID();

            //show success msg
            handleShowDialog("Success","Add Customer Successfully!",Alert.AlertType.INFORMATION);

        }else {

            //clear form
            clearForm();

            //show error msg
            handleShowDialog("Error","Oops! Fill out All the Fields ",Alert.AlertType.ERROR);

        }

    }

    //clear form
    private void clearForm(){
        txtName.setText(null);
        txtAddress.setText(null);
        cmbTitles.setValue(null);
        dateDOB.setValue(null);
    }

    //generate ID
    private void generateID(){
        txtId.setText(String.format("C%04d",DBConnection.getInstance().getConnection().size()+1));
    }

    //Show Dialog
    private void handleShowDialog(String title,String msg,Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //generate ID
        generateID();

        //set read-only
        txtId.setEditable(false);

        //set titles
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Miss.");
        cmbTitles.setItems(titles);

        //focus combo box
        Platform.runLater(() -> cmbTitles.requestFocus());

    }

}
