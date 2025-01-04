package com.example.lanacbenzinskihstanica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TrgovacMagacinController implements Initializable {

    @FXML
    private TableView<Artikal> stanjeTableView = new TableView<>();
    @FXML
    private TableColumn<Artikal, Integer> idColumn;
    @FXML
    private TableColumn<Artikal, String> nazivColumn;
    @FXML
    private TableColumn<Artikal, String> mjernaColumn;
    @FXML
    private TableColumn<Artikal, Double> cijenaColumn;
    @FXML
    private TableColumn<Artikal, Integer> zalihaColumn;
    @FXML
    private TableColumn<Artikal, Integer> poslovnicaColumn;
    @FXML
    private TableColumn<Artikal, Integer> kategorijaColumn;
    private ObservableList<Artikal> artikalObservable= FXCollections.observableArrayList();
    @FXML
    private Button cancelButton;
    @FXML
    private Button okButton;
    @FXML
    private PasswordField lozinkaPasswordField;

    public void cancelButtonAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idArtikal"));
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivArtikal"));
        mjernaColumn.setCellValueFactory(new PropertyValueFactory<>("mjernaJedinica"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaArtikal"));
        zalihaColumn.setCellValueFactory(new PropertyValueFactory<>("zaliha"));
        poslovnicaColumn.setCellValueFactory(new PropertyValueFactory<>("benzinskaId"));
        kategorijaColumn.setCellValueFactory(new PropertyValueFactory<>("kategorijaId"));
        read();
        stanjeTableView.setItems(artikalObservable);
    }

    public void read(){
        Statement statement = null;
        Connection connectionDB= null;
        ResultSet resultSet = null;
        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from lanac_benzinskih_pumpi.artikli_poslovnice_111");

            while(resultSet.next()) {
                Artikal artikal = new Artikal(resultSet.getInt(1) , resultSet.getString(2),resultSet.getString(4), resultSet.getBigDecimal(3).doubleValue(), resultSet.getInt(7), resultSet.getInt(6), resultSet.getInt(5));
                artikalObservable.add(artikal);
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
