package com.example.lanacbenzinskihstanica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import java.sql.*;

public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    public void cancelButtonAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
     }

     @FXML
     private Label loginMessageLabel;


     public void loginButtonOnAction(ActionEvent e) {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
           validateLogin();
        }
        else {
            loginMessageLabel.setText("Unesite korisnicko ime i lozinku.");
        }
     }

     public void validateLogin() {
         Statement statement = null;
         Connection connectionDB= null;
         ResultSet resultSet = null;

         try {
             connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
             statement = connectionDB.createStatement();
             resultSet = statement.executeQuery("select * from zaposlenik where korisnicko_ime='" + usernameTextField.getText() + "' and lozinka='" + passwordPasswordField.getText() + "'");

             while(resultSet.next()) {
                 Zaposlenik zaposlenik = new Zaposlenik(resultSet.getInt("id"),resultSet.getString("jmbg"),resultSet.getString("ime"),resultSet.getString("prezime"), resultSet.getDate("datum_zaposlenja"), resultSet.getDate("datum_prestanka"),resultSet.getString("korisnicko_ime"), resultSet.getString("lozinka"),resultSet.getString("adresa"),resultSet.getBoolean("u_radnom_odnosu"),resultSet.getInt("uloga_id"),resultSet.getInt("mjesto_id"),resultSet.getInt("benzinska_stanica_id"));
                 if(zaposlenik.getUlogaId() == 1 && zaposlenik.getuRadnomOdnosu()==true) {
                     openAdministrativniRadnik();
                 }
                else if(zaposlenik.getUlogaId() == 2 && zaposlenik.getuRadnomOdnosu()==true){
                     openTrgovac();
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

     public void openTrgovac() {
         try {
             FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("trgovac.fxml"));
             Scene scene = new Scene(fxmlLoader.load(), 520, 345);
             Stage stage = new Stage();
             stage.setScene(scene);
             stage.show();
         }
         catch (Exception e) {
             e.printStackTrace();
         }
     }

    public void openAdministrativniRadnik() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("administrativniRadnik.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 508, 390);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




    }
