package com.example.lanacbenzinskihstanica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TrgovacPoslovniPartnerController implements Initializable {

    @FXML
    private TableView<PoslovniPartnerPrikaz> poslovniPTableView = new TableView<>();
    @FXML
    private TableColumn<PoslovniPartnerPrikaz,String> adresaPP;
    @FXML
    private TableColumn<PoslovniPartnerPrikaz,String> brRacunaPP;
    @FXML
    private TableColumn<PoslovniPartnerPrikaz,String> emailPP;
    @FXML
    private TableColumn<PoslovniPartnerPrikaz, Integer> idPP;
    @FXML
    private TableColumn<PoslovniPartnerPrikaz,String> jibPP;
    @FXML
    private TableColumn<PoslovniPartnerPrikaz,String> mjestoPP;
    @FXML
    private TableColumn<PoslovniPartnerPrikaz,String> nazivPP;
    @FXML
    private TableColumn<PoslovniPartnerPrikaz,String> telefonPP;
    private ObservableList<PoslovniPartnerPrikaz> listaPoslovniP = FXCollections.observableArrayList();;
    @FXML
    private Button izlazButton;
    @FXML
    public void izlazButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) izlazButton.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPP.setCellValueFactory(new PropertyValueFactory<>("idPoslovniPartner"));
        jibPP.setCellValueFactory(new PropertyValueFactory<>("jib"));
        nazivPP.setCellValueFactory(new PropertyValueFactory<>("nazivPoslovniP"));
        telefonPP.setCellValueFactory(new PropertyValueFactory<>("telefonPoslovniP"));
        adresaPP.setCellValueFactory(new PropertyValueFactory<>("adresaPoslovniP"));
        mjestoPP.setCellValueFactory(new PropertyValueFactory<>("mjestoPoslovniP"));
        emailPP.setCellValueFactory(new PropertyValueFactory<>("emailPoslovniP"));
        brRacunaPP.setCellValueFactory(new PropertyValueFactory<>("brojRacunaP"));
        read();
        poslovniPTableView.setItems(listaPoslovniP);
    }

    public void read() {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;
        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from lanac_benzinskih_pumpi.poslovni_partneri");

            while(resultSet.next()) {
                PoslovniPartnerPrikaz poslovniPartnerPrikaz = new PoslovniPartnerPrikaz(resultSet.getInt(1) , resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(7), resultSet.getString(6), resultSet.getString(8));
                listaPoslovniP.add(poslovniPartnerPrikaz);
                if(listaPoslovniP.contains(poslovniPartnerPrikaz)) {
                }

            }
        }
        catch(SQLException e) { e.printStackTrace(); }
        finally {
            if(resultSet!=null)
                try { resultSet.close(); }
                catch(SQLException e) { e.printStackTrace();}
            if(statement!=null)
                try {statement.close();}
                catch(SQLException e) { e.printStackTrace();}
            if(connectionDB!=null)
                try{ connectionDB.close();}
                catch(SQLException e) { e.printStackTrace();}
        }
    }


}