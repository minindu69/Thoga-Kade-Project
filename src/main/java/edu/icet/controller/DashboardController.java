package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {

    @FXML
    private JFXButton addCustomerBtn;

    @FXML
    private JFXButton viewCustomerBtn;

    @FXML
    void addCustomerBtnOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../../view/add_customer_form.fxml")))));
            stage.getIcons().add(new Image("img/add-user.png"));
            stage.setTitle("Add Customer");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void viewCustomerBtnOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../." +
                    "./view/view_customer.fxml")))));
            stage.getIcons().add(new Image("img/contact-list.png"));
            stage.setTitle("View Customer");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchCustomerBtnOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../../view/search_customer.fxml")))));
            stage.getIcons().add(new Image("img/search.png"));
            stage.setTitle("Search Customer");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomerBtnOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../." +
                    "./view/delete_customer.fxml")))));
            stage.getIcons().add(new Image("img/delete.png"));
            stage.setTitle("Delete Customer");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
