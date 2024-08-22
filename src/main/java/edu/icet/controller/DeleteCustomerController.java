package edu.icet.controller;

        import com.jfoenix.controls.JFXButton;
        import com.jfoenix.controls.JFXComboBox;
        import com.jfoenix.controls.JFXTextField;
        import edu.icet.db.DBConnection;
        import edu.icet.model.Customer;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.DatePicker;

public class DeleteCustomerController extends SearchCustomerController{

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
    private JFXTextField txtSearch;

    static void deleteCustomer(Customer selectedCustomer){
        //remove customer
        System.out.println("-------------Delete---------------------");
        DBConnection.getInstance().getConnection().remove(selectedCustomer);
        System.out.println(DBConnection.getInstance().getConnection());
        System.out.println("Deleted Successfully!");
        System.out.println("----------------------------------------");
        System.out.println();
    }

    @Override
    void searchCustomerOnAction(ActionEvent event) {
        customerValidation();
        disableFields();
    }

    @Override
    void deleteCustomerOnAction(ActionEvent event) {
        deleteCustomerConfirmation();
        clearForm();
    }

}

