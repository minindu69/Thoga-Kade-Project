package edu.icet.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.db.DBConnection;
import edu.icet.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewCustomerController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private DatePicker dateDOB;

    private Customer selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //load customer data
        loadCustomersData();

        //set disable
        disableFields();

        //set titles
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Miss.");
        cmbTitle.setItems(titles);

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, t1) -> {
            if (t1 != null) {
                selectedCustomer=t1;
                System.out.println(selectedCustomer);
                setValues(t1);
                enableFields();
            }

        });

    }

    private void setValues(Customer t1){
        txtId.setText(t1.getId());
        cmbTitle.setValue(t1.getTitle().trim());
        txtName.setText(t1.getName());
        txtAddress.setText(t1.getAddress());
        dateDOB.setValue(t1.getDob());
    }

    @FXML
    void reloadBtnOnAction(ActionEvent event) {
        //clear selection
        tblCustomers.getSelectionModel().clearSelection();
        loadCustomersData();

        //clear fields
        clear();

        //set disable
        disableFields();

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        //clear selection
        tblCustomers.getSelectionModel().clearSelection();

        if (!(txtName.getText()==null||cmbTitle.getValue()==null||txtAddress.getText()==null||dateDOB.getValue()==null)){

            Optional<ButtonType> res = handleShowDialog("Update",
                    "Do you want to Update Customer ID : "+selectedCustomer.getId()+" ?",Alert.AlertType.CONFIRMATION);

            if (res.isPresent() && res.get() == ButtonType.OK){
                //set updated values
                selectedCustomer.setTitle(cmbTitle.getValue());
                selectedCustomer.setName(txtName.getText());
                selectedCustomer.setFullName(cmbTitle.getValue()+ " " +txtName.getText());
                selectedCustomer.setAddress(txtAddress.getText());
                selectedCustomer.setDob(dateDOB.getValue());

                //update selected customer
                UpdateCustomerController.updateCustomer(selectedCustomer);

            }

            clear();

            tblCustomers.refresh();
            disableFields();

            //show success msg
            handleShowDialog("Success","Customer has been Updated Successfully!",Alert.AlertType.INFORMATION);

        }else {
            handleShowDialog("Error","Please Select a Customer From Table",Alert.AlertType.ERROR);
        }

    }

    //enable fields
    private void enableFields(){
        //set editable
        txtId.setDisable(true);
        cmbTitle.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        dateDOB.setDisable(false);
    }

    private void disableFields(){
        //set editable
        txtId.setDisable(true);
        cmbTitle.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        dateDOB.setDisable(true);
    }

    //clear
    private void clear(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        cmbTitle.setValue(null);
        dateDOB.setValue(null);
    }

    //Show Dialog
    private Optional<ButtonType> handleShowDialog(String title, String msg, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        return alert.showAndWait();
    }

    //load customers
    void loadCustomersData(){
        System.out.println();
        System.out.println("Load data...");
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        //get customer list
        List<Customer> customerList =  DBConnection.getInstance().getConnection();
        //add customers
        customerObservableList.addAll(customerList);
        System.out.println(customerList);
        System.out.println("---------------------------------------");
        System.out.println();

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>( "fullName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));

        tblCustomers.setItems(customerObservableList);
        tblCustomers.refresh();
    }

}



