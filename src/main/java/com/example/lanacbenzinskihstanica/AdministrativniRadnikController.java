package com.example.lanacbenzinskihstanica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdministrativniRadnikController {

    @FXML
    private Button izlazButton;

    @FXML
    private Button novaUFButton;

    @FXML
    private Button prikazMagacinButton;

    @FXML
    private Button prikazPartnerButton;

    @FXML
    private Button artikliButton;

    public void novaUFButtonOnAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("administrativniRadnikUF.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,846,667));
            stage.show();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void prikazMagacinButtonOnAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("trgovacMagacin.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,649,400));
            stage.show();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void prikazPartnerButtonOnAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("trgovacPoslovniPartner.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,910,400));
            stage.show();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void artikliButtonOnAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("administrativniRadnikArtikli.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,858,643));
            stage.show();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void izlazButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) izlazButton.getScene().getWindow();
        stage.close();
    }

}