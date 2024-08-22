package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearchCustomerController implements Initializable {

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXComboBox<String> cmbTitles;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private JFXButton deleteCustomerBtn;

    @FXML
    private JFXButton searchCustomerBtn;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton updateCustomerBtn;

    private Customer searchedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //disable fields
        disableFields();

        //set titles
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr. ");
        titles.add("Miss. ");
        cmbTitles.setItems(titles);

        //focus search field
        Platform.runLater(() -> txtSearch.requestFocus());

    }

    @FXML
    void searchCustomerOnAction(ActionEvent event) {
        customerValidation();
    }

    //enable fields
    void enableFields(){
        //set editable
        txtId.setDisable(true);
        cmbTitles.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        dateDOB.setDisable(false);
    }

    //disable fields
    void disableFields(){
        //set editable
        txtId.setDisable(true);
        cmbTitles.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        dateDOB.setDisable(true);
    }

    //customer validation
    void customerValidation(){
        String searchID = txtSearch.getText().trim();
        if (!searchID.isEmpty()){
            searchedCustomer = search(searchID);
            if (searchedCustomer!=null){
                //set values
                txtId.setText(searchedCustomer.getId());
                cmbTitles.setValue(searchedCustomer.getTitle());
                txtName.setText(searchedCustomer.getName());
                txtAddress.setText(searchedCustomer.getAddress());
                dateDOB.setValue(searchedCustomer.getDob());
                //enable fields
                enableFields();
            }else{
                //show not found msg
                handleShowDialog("Not Found","Oops! There is no ID : "+searchID+" Customer",
                        Alert.AlertType.WARNING);
                clearForm();
            }
        }else {
            //show error msg
            handleShowDialog("Error","Oops! Please Enter the Customer ID",
                    Alert.AlertType.ERROR);
            clearForm();
        }
    }

    //search customer
    Customer search(String txtSearch){
        for (Customer customer:DBConnection.getInstance().getConnection()){
            if (txtSearch.equals(customer.getId())){
                return  customer;
            }
        }
        return null;
    }

    @FXML
    void updateCustomerOnAction(ActionEvent event) {
        updateCustomerConfirmation();
    }

    void updateCustomerConfirmation(){
        if (!txtId.getText().trim().isEmpty()){
            //set updated values
            searchedCustomer.setId(txtId.getText());
            searchedCustomer.setTitle(cmbTitles.getValue());
            searchedCustomer.setName(txtName.getText());
            searchedCustomer.setFullName(cmbTitles.getValue()+ " " +txtName.getText());
            searchedCustomer.setAddress(txtAddress.getText());
            searchedCustomer.setDob(dateDOB.getValue());

            //get confirmation
            Optional<ButtonType> res = handleShowDialog("Update",
                    "Do you want to Update Customer ID : "+searchedCustomer.getId()+
                            " ?",
                    Alert.AlertType.CONFIRMATION);

            if (res.isPresent() && res.get()==ButtonType.OK){
                //update DB
                UpdateCustomerController.updateCustomer(searchedCustomer);

                //show success msg
                handleShowDialog("Success","Customer has been Updated Successfully!",Alert.AlertType.INFORMATION);

                //clear
                clearForm();

                //disable fields
                disableFields();

            }

        }else {
            //show Error msg
            handleShowDialog("Error","Please Search Customer to Update!",Alert.AlertType.ERROR);
        }
    }

    @FXML
    void deleteCustomerOnAction(ActionEvent event) {
        deleteCustomerConfirmation();
    }

    void deleteCustomerConfirmation(){
        if (!txtId.getText().trim().isEmpty()) {
            //get confirmation
            Optional<ButtonType> res = handleShowDialog("Delete","Do you want to Delete Customer ID : "+searchedCustomer.getId()+
                            " ?",
                    Alert.AlertType.CONFIRMATION);
            if (res.isPresent() && res.get() == ButtonType.OK){
                //delete customer
                DeleteCustomerController.deleteCustomer(searchedCustomer);

                //clear
                clearForm();

                //disable fields
                disableFields();

                //show success msg
                handleShowDialog("Success","Customer has been Deleted Successfully!",Alert.AlertType.INFORMATION);

            }

        }else {
            //show Error msg
            handleShowDialog("Error","Please Search Customer to Delete!",Alert.AlertType.ERROR);
        }
    }

    //Show Dialog
    private Optional<ButtonType> handleShowDialog(String title, String msg, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        return alert.showAndWait();
    }

    //clear form
    void clearForm(){
        txtSearch.setText("");
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        cmbTitles.setValue(null);
        dateDOB.setValue(null);
    }

}
