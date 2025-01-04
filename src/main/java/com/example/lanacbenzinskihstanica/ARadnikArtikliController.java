package com.example.lanacbenzinskihstanica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ARadnikArtikliController implements Initializable {

    static int controlPoslovnicaId = 0;
    static int controlZaposleniId = 0;
    static int controlArtikalId = 0;

    public int benzinskaId;
    public int kategorijaId;
    @FXML
    private TableView<Artikal> artikliTableView = new TableView<>();
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
    private Button dodajButton;

    @FXML
    private Button azurirajButton;

    @FXML
    private Button izlazButton;

    @FXML
    private TextField idTextField;
    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField mjernaTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TextField zalihaTextField;
    @FXML
    private TextField poslovnicaTextField;

    @FXML
    private ChoiceBox<String> kategorijaChoiceBox;

    ObservableList<String> kategorijaObservable = FXCollections.observableArrayList("Goriva i maziva", "Hrana i pice", "Cigarete");

    @FXML
    private PasswordField lozinkaPasswordField;

    @FXML
    private Label upozorenjeLabel;

    @FXML
    private TextField idTextField1;
    @FXML
    private TextField cijenaTextField1;

    @FXML Button obrisiButton;

    @FXML
    void rowClicked(MouseEvent event) {
        Artikal clickedArtikal = artikliTableView.getSelectionModel().getSelectedItem();
        idTextField1.setText(String.valueOf(clickedArtikal.getIdArtikal()));
        cijenaTextField1.setText(String.valueOf(clickedArtikal.getCijenaArtikal()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kategorijaChoiceBox.setItems(kategorijaObservable);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idArtikal"));
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivArtikal"));
        mjernaColumn.setCellValueFactory(new PropertyValueFactory<>("mjernaJedinica"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaArtikal"));
        zalihaColumn.setCellValueFactory(new PropertyValueFactory<>("zaliha"));
        poslovnicaColumn.setCellValueFactory(new PropertyValueFactory<>("benzinskaId"));
        kategorijaColumn.setCellValueFactory(new PropertyValueFactory<>("kategorijaId"));
        read();
        artikliTableView.setItems(artikalObservable);
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

    public void dodajButtonOnAction(ActionEvent ae) {
        controlPoslovnicaId = 0;
        controlZaposleniId = 0;
        controlArtikalId = 0;
        upozorenjeLabel.setText("");
        if(idTextField.getText().equals("") || nazivTextField.getText().equals("") || mjernaTextField.getText().equals("") || zalihaTextField.getText().equals("") || cijenaTextField.getText().equals("") || poslovnicaTextField.getText().equals("") || kategorijaChoiceBox.getValue()==null || lozinkaPasswordField.getText().equals("")) {
            upozorenjeLabel.setText("Unesite sve potrebne podatke i lozinku.");
        }
        else {
            checkZaposleni();
            if(controlZaposleniId == 0) {
                upozorenjeLabel.setText("Pogresna lozinka.");
            }
            else {
                checkPoslovnicaId(benzinskaId);
                kategorijaId = kategorijaSet();
                if (controlPoslovnicaId == 0) {
                    upozorenjeLabel.setText("Dati id poslovnice ne postoji u bazi.");
                } else {
                    checkIdArtikal(Integer.valueOf(idTextField.getText()));
                    if(controlArtikalId == 1) {
                        upozorenjeLabel.setText("Dati id artikla vec postoji u bazi.");
                    }
                    else {
                        insertArtikal();
                        Stage stage = (Stage) dodajButton.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        }
    }

    public void azurirajButtonOnAction() {
        upozorenjeLabel.setText("");
        controlZaposleniId = 0;
        if(idTextField1.getText().equals("")) {
            upozorenjeLabel.setText("Odaberite artikal za azuriranje.");
        }
        else {
            if (cijenaTextField1.getText().equals("")) {
                upozorenjeLabel.setText("Unesite cijenu za azuriranje.");
            } else {
                if (lozinkaPasswordField.getText().equals("")) {
                    upozorenjeLabel.setText("Unesite lozinku.");
                } else {
                    checkZaposleni();
                    if (controlZaposleniId == 0) {
                        upozorenjeLabel.setText("Pogresna lozinka.");
                    } else {
                        updateArtikal();
                        Stage stage = (Stage) dodajButton.getScene().getWindow();
                        stage.close();
                    }

                }
            }
        }
    }

    public void obrisiButtonOnAction() {
        controlZaposleniId = 0;
        if(idTextField1.getText().equals("")) {
            upozorenjeLabel.setText("Odaberite artikal za brisanje.");
        }
        else {
                if (lozinkaPasswordField.getText().equals("")) {
                    upozorenjeLabel.setText("Unesite lozinku.");
                } else {
                    checkZaposleni();
                    if (controlZaposleniId == 0) {
                        upozorenjeLabel.setText("Pogresna lozinka.");
                    } else {
                        deleteArtikal();
                        Stage stage = (Stage) dodajButton.getScene().getWindow();
                        stage.close();
                    }

                }
            }
        }


    public void izlazButtonOnAction() {
        Stage stage = (Stage) izlazButton.getScene().getWindow();
        stage.close();
    }

    public void checkPoslovnicaId(int poslovnicaId) {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from benzinska_stanica where id='" + poslovnicaId + "'");
            while (resultSet.next()) {
                BenzinskaStanica benzinskaStanica = new BenzinskaStanica(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(5), resultSet.getString(3),resultSet.getInt(4) );
                controlPoslovnicaId = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connectionDB != null)
                try {
                    connectionDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void insertArtikal() {
        PreparedStatement pStatement = null;
        Connection connectionDB = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            pStatement = connectionDB.prepareStatement("insert into artikal values(?,?,?,?,?,?,?)");
            pStatement.setInt(1,Integer.valueOf(idTextField.getText()));
            pStatement.setString(2, nazivTextField.getText());
            pStatement.setBigDecimal(3, BigDecimal.valueOf(Double.valueOf(cijenaTextField.getText())));
            pStatement.setString(4, mjernaTextField.getText());
            pStatement.setInt(5, kategorijaId);
            pStatement.setInt(6, benzinskaId);
            pStatement.setInt(7,Integer.valueOf(zalihaTextField.getText()));
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connectionDB != null) {
                try {
                    connectionDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void checkZaposleni() {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from zaposlenik where lozinka='" + lozinkaPasswordField.getText() + "'");
            while (resultSet.next()) {
                    Zaposlenik zaposlenik = new Zaposlenik(resultSet.getInt("id"), resultSet.getString("jmbg"), resultSet.getString("ime"), resultSet.getString("prezime"), resultSet.getDate("datum_zaposlenja"), resultSet.getDate("datum_prestanka"), resultSet.getString("korisnicko_ime"), resultSet.getString("lozinka"), resultSet.getString("adresa"), resultSet.getBoolean("u_radnom_odnosu"), resultSet.getInt("uloga_id"), resultSet.getInt("mjesto_id"), resultSet.getInt("benzinska_stanica_id"));
                    upozorenjeLabel.setText("Ispravna lozinka.");
                    benzinskaId = zaposlenik.getBenzinskaId();
                    controlZaposleniId = 1;
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connectionDB != null)
                try {
                    connectionDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public int kategorijaSet() {
        if(kategorijaChoiceBox.getValue().equals("Goriva i maziva")) {
            return 1;
        }
        else if(kategorijaChoiceBox.getValue().equals("Hrana i pice")) {
            return 2;
        }
        else {
            return 3;
        }
    }

    void checkIdArtikal(int idArtikal) {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from artikal where id='" + idArtikal + "'");
            while (resultSet.next()) {
                Artikal artikal = new Artikal(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getBigDecimal(3).doubleValue(), resultSet.getInt(7), resultSet.getInt(6), resultSet.getInt(5));
                controlArtikalId = 1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connectionDB != null)
                try {
                    connectionDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void updateArtikal() {
        PreparedStatement pStatement = null;
        Connection connectionDB = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            pStatement = connectionDB.prepareStatement("update artikal set cijena=? where id=?");
            pStatement.setBigDecimal(1, BigDecimal.valueOf(Double.valueOf(cijenaTextField1.getText())));
            pStatement.setInt(2,Integer.valueOf(idTextField1.getText()) );
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connectionDB != null) {
                try {
                    connectionDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteArtikal() {
        PreparedStatement pStatement = null;
        Connection connectionDB = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            pStatement = connectionDB.prepareStatement("delete from artikal where id=?");
            pStatement.setInt(1,Integer.valueOf(idTextField1.getText()) );
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connectionDB != null) {
                try {
                    connectionDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


