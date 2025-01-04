package com.example.lanacbenzinskihstanica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TrgovacController {
    @FXML
    private Button izlazButton;

    @FXML
    private Button novaIzlaznaFakturaButton;

    @FXML
    private Button prikazStanjaMagacinButton;

    @FXML
    private Button prikazPoslovniPartnerButton;


    public void izlazButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) izlazButton.getScene().getWindow();
        stage.close();
    }

    public void setNovaIzlaznaFakturaButtonOnAction(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("trgovacNovaIF.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,846,667));
            stage.show();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void PrikazStanjaMagacinButtonOnAction(ActionEvent e) {
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

    public void prikazPoslovniPartnerButtonOnAction(ActionEvent ae) {
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

}


