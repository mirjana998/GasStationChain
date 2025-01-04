package com.example.lanacbenzinskihstanica;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.sql.Date;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class NovaIFController implements Initializable {

    static int controlNaplati = 0;
    static int controlSacuvaj = 0;
    static int controlId = 0;
    static int controlBroj = 0;

    public int idFakture;
    public String komentar = "";
    public double ukupnoFaktura = 0;
    public Date datumIzdavanja;
    public int zaposlenikId;
    public int benzinskaStanicaId;
    public int tipPlacanjaId;
    public int racunPartneraId;
    public int poslovniPartnerId;
    public int findZaliha;


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

    @FXML
    void rowClicked(MouseEvent event) {
        Artikal clickedArtikal = artikliTableView.getSelectionModel().getSelectedItem();
        idTextField.setText(String.valueOf(clickedArtikal.getIdArtikal()));
        nazivTextField.setText(clickedArtikal.getNazivArtikal());
        kolicinaTextField.setText(String.valueOf(1));
        zalihaTextField.setText(String.valueOf(clickedArtikal.getZaliha()));
        cijenaTextField.setText(String.valueOf(Double.valueOf(Integer.valueOf(kolicinaTextField.getText()) * clickedArtikal.getCijenaArtikal())));
    }

    private ObservableList<Artikal> artikalObservable = FXCollections.observableArrayList();
    private ObservableList<StavkaFakture> stavkaFaktureObservableList = FXCollections.observableArrayList();

    @FXML
    private TextField idTextField;
    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField kolicinaTextField;

    @FXML
    private TextField brojRacunaTextfield;
    @FXML
    private Button dodajButton;
    @FXML
    private TextField idPoslovnogPartneraTextfield;
    @FXML
    private Button naplatiButton;
    @FXML
    private Button zakljuciButton;
    @FXML
    private Button izlazButton;
    @FXML
    private Button sacuvajButton;
    @FXML
    private TableView<StavkaFakture> stavkaFaktureTableView = new TableView<>();
    @FXML
    private TableColumn<StavkaFakture, Integer> idStavke;
    @FXML
    private TableColumn<StavkaFakture, String> nazivStavke;
    @FXML
    private TableColumn<StavkaFakture, Double> cijenaStavke;
    @FXML
    private TableColumn<StavkaFakture, Integer> kolicinaStavke;
    @FXML
    private ChoiceBox<String> tipPlacanjaChoiceBox;
    ObservableList<String> tipPlacanja = FXCollections.observableArrayList("Placanje gotovinom", "Bankovno placanje");
    @FXML
    private Label upozorenjeLabel;
    @FXML
    private Label ukupnoLabel;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private Button obrisiButton;
    @FXML
    private Label zakljuciLabel;
    @FXML
    private PasswordField lozinkaPasswordField;
    @FXML
    private Label sacuvajLabel;
    @FXML
    private TextField zalihaTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipPlacanjaChoiceBox.setItems(tipPlacanja);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idArtikal"));
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivArtikal"));
        mjernaColumn.setCellValueFactory(new PropertyValueFactory<>("mjernaJedinica"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaArtikal"));
        zalihaColumn.setCellValueFactory(new PropertyValueFactory<>("zaliha"));
        poslovnicaColumn.setCellValueFactory(new PropertyValueFactory<>("benzinskaId"));
        kategorijaColumn.setCellValueFactory(new PropertyValueFactory<>("kategorijaId"));
        idStavke.setCellValueFactory(new PropertyValueFactory<>("artikalId"));
        nazivStavke.setCellValueFactory(new PropertyValueFactory<>("artikalNaziv"));
        kolicinaStavke.setCellValueFactory(new PropertyValueFactory<>("kolicinaStavke"));
        cijenaStavke.setCellValueFactory(new PropertyValueFactory<>("cijenaStavke"));
        read();
        artikliTableView.setItems(artikalObservable);

    }

    public void read() {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;
        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from artikal");

            while (resultSet.next()) {
                Artikal artikal = new Artikal(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getBigDecimal(3).doubleValue(), resultSet.getInt(7), resultSet.getInt(6), resultSet.getInt(5));
                artikalObservable.add(artikal);

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

    public void izlazButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) izlazButton.getScene().getWindow();
        stage.close();
    }

    public void dodajButtonOnAction(ActionEvent ae) {
        upozorenjeLabel.setText("");
        if (controlSacuvaj == 0) {
            upozorenjeLabel.setText("Sacuvajte podatke o broju racuna i nacinu placanja!");
        } else {
            if(Integer.valueOf(kolicinaTextField.getText())> Integer.valueOf(zalihaTextField.getText())) {
                upozorenjeLabel.setText("Kolicina unesenog artikla veca od trenutnog stanja u magacinu. Pokusajte ponovo.");
            }
            else {
                int check = 0;
                int brojFakture = Integer.valueOf(brojRacunaTextfield.getText());
                StavkaFakture novaStavka = new StavkaFakture(Integer.valueOf(idTextField.getText()), String.valueOf(nazivTextField.getText()), brojFakture, Double.valueOf(cijenaTextField.getText()), Integer.valueOf(kolicinaTextField.getText()));
                for (int i = 0; i < stavkaFaktureTableView.getItems().size(); i++){
                    StavkaFakture pomocnaStavka = stavkaFaktureTableView.getItems().get(i);
                    if (pomocnaStavka.getArtikalId() == novaStavka.getArtikalId()) {
                        check++;
                        if((Integer.valueOf(kolicinaTextField.getText()) + pomocnaStavka.getKolicinaStavke()) > Integer.valueOf(zalihaTextField.getText())) {
                            upozorenjeLabel.setText("Kolicina unesenog artikla veca od trenutnog stanja u magacinu. Pokusajte ponovo.");
                        }
                        else {
                            pomocnaStavka.setCijenaStavke(pomocnaStavka.getCijenaStavke() + (novaStavka.getCijenaStavke() * Integer.valueOf(kolicinaTextField.getText())));
                            pomocnaStavka.setKolicinaStavke(pomocnaStavka.getKolicinaStavke() + Integer.valueOf(kolicinaTextField.getText()));
                            stavkaFaktureTableView.refresh();
                        }
                    }
                }
                if (check == 0) {
                    novaStavka.setCijenaStavke(Double.valueOf(cijenaTextField.getText()) * Double.valueOf(kolicinaTextField.getText()));
                    stavkaFaktureObservableList.add(novaStavka);
                    stavkaFaktureTableView.setItems(stavkaFaktureObservableList);
                    stavkaFaktureTableView.refresh();
                }
            }
        }
    }

    public void obrisiButtonOnAction(ActionEvent ae) {
        stavkaFaktureTableView.getItems().removeAll(stavkaFaktureTableView.getSelectionModel().getSelectedItem());
    }

    public void naplatiButtonOnAction(ActionEvent ae) {
        double ukupno = 0;
        for (int i = 0; i < stavkaFaktureTableView.getItems().size(); i++) {
            StavkaFakture pomocnaStavka = stavkaFaktureTableView.getItems().get(i);
            ukupno = ukupno + pomocnaStavka.getCijenaStavke();
        }
        ukupnoLabel.setText(String.valueOf(ukupno));
        ukupnoFaktura = ukupno;
        controlNaplati = Integer.valueOf(brojRacunaTextfield.getText());
    }

    public void sacuvajButtonOnAction(ActionEvent ae) {
        controlBroj = 0;
        sacuvajLabel.setText("");
        controlId = 0;
        if (brojRacunaTextfield.getText().isEmpty()) {
            sacuvajLabel.setText("Unesite broj racuna!");
        } else {
            checkBrojRacuna(Integer.valueOf(brojRacunaTextfield.getText()));
            if(controlBroj == 0) {
                if (tipPlacanjaChoiceBox.getValue() == null) {
                    sacuvajLabel.setText("Odaberite tip placanja!");
                } else if (tipPlacanjaChoiceBox.getValue().equals("Placanje gotovinom")) {
                    tipPlacanjaId = 1;
                    if (Integer.valueOf(idPoslovnogPartneraTextfield.getText()) == 1 || idPoslovnogPartneraTextfield.getText().equals("")) {
                        idFakture = Integer.valueOf(brojRacunaTextfield.getText());
                        poslovniPartnerId = 1;
                        checkIdRacunPoslovniPartner();
                        komentar = "Racun za fizicko lice.";
                        sacuvajLabel.setText("Sacuvano.");
                    }
                    else {
                        checkIdPoslovniPartner();
                        if (controlId == 0) {
                            sacuvajLabel.setText("Pogresan id poslovnog partnera.");
                        } else if (controlId == 1) {
                            checkIdRacunPoslovniPartner();
                            idFakture = Integer.valueOf(brojRacunaTextfield.getText());
                            komentar = "Racun za pravno lice.";
                            sacuvajLabel.setText("Sacuvano.");
                        }
                    }
                } else if (tipPlacanjaChoiceBox.getValue().equals("Bankovno placanje")) {
                    tipPlacanjaId = 2;
                    if (Integer.valueOf(idPoslovnogPartneraTextfield.getText()) == 1 || idPoslovnogPartneraTextfield.getText().equals("")) {
                        idFakture = Integer.valueOf(brojRacunaTextfield.getText());
                        poslovniPartnerId = 1;
                        checkIdRacunPoslovniPartner();
                        komentar = "Racun za fizicko lice.";
                        sacuvajLabel.setText("Sacuvano.");

                    } else {
                        checkIdPoslovniPartner();
                        if (controlId == 0) {
                            sacuvajLabel.setText("Pogresan id poslovnog partnera.");
                        } else if (controlId == 1) {
                            checkIdRacunPoslovniPartner();
                            idFakture = Integer.valueOf(brojRacunaTextfield.getText());
                            komentar = "Racun za pravno lice.";
                            sacuvajLabel.setText("Sacuvano.");
                        }
                    }

                }
                controlSacuvaj = Integer.valueOf(brojRacunaTextfield.getText());
            }
            else {
                sacuvajLabel.setText("Dati broj racuna vec postoji u sistemu.");
            }
        }
    }

    public void checkBrojRacuna(int id) {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from faktura where id='" + id + "'");

            while (resultSet.next()) {
                Faktura faktura = new Faktura(resultSet.getInt(1), resultSet.getString(7),resultSet.getBigDecimal(3).doubleValue(), resultSet.getDate(2), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(8), resultSet.getInt(9));
                if(faktura.getIdFaktura() == Integer.valueOf(brojRacunaTextfield.getText())) {
                    controlBroj = 1;
                }
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

    public void checkIdPoslovniPartner() {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from lanac_benzinskih_pumpi.poslovni_partneri where id='" + idPoslovnogPartneraTextfield.getText() + "'");

            while (resultSet.next()) {
                PoslovniPartnerPrikaz poslovniPartnerPrikaz = new PoslovniPartnerPrikaz(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(7), resultSet.getString(6), resultSet.getString(8));
                poslovniPartnerId = poslovniPartnerPrikaz.getIdPoslovniPartner();
                System.out.println("" + poslovniPartnerId);
                controlId = 1;
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

    public void checkIdRacunPoslovniPartner() {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from lanac_benzinskih_pumpi.racuni_partnera where poslovni_partner_id='" + poslovniPartnerId + "'");
            while (resultSet.next()) {
                RacunPartnera racunPartnera = new RacunPartnera(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4));
                racunPartneraId = racunPartnera.getIdRacun();
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

    public void zakljuciButtonOnAction(ActionEvent ae) {
        if (controlNaplati == 0) {
            zakljuciLabel.setText("Potvrdite naplatu!");
        } else if (controlNaplati == Integer.valueOf(brojRacunaTextfield.getText()) && controlSacuvaj == Integer.valueOf(brojRacunaTextfield.getText())) {
            if (lozinkaPasswordField.getText().isBlank()) {
                zakljuciLabel.setText("Unesite lozinku.");
            } else {
                Statement statement = null;
                Connection connectionDB = null;
                ResultSet resultSet = null;

                try {
                    connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
                    statement = connectionDB.createStatement();
                    resultSet = statement.executeQuery("select * from zaposlenik where lozinka='" + lozinkaPasswordField.getText() + "'");

                    if (resultSet == null) {
                        zakljuciLabel.setText("Pogresna lozinka!");
                    } else {
                        while (resultSet.next()) {
                            Zaposlenik zaposlenik = new Zaposlenik(resultSet.getInt("id"), resultSet.getString("jmbg"), resultSet.getString("ime"), resultSet.getString("prezime"), resultSet.getDate("datum_zaposlenja"), resultSet.getDate("datum_prestanka"), resultSet.getString("korisnicko_ime"), resultSet.getString("lozinka"), resultSet.getString("adresa"), resultSet.getBoolean("u_radnom_odnosu"), resultSet.getInt("uloga_id"), resultSet.getInt("mjesto_id"), resultSet.getInt("benzinska_stanica_id"));
                            zakljuciLabel.setText("Ispravna lozinka.");
                            zaposlenikId = zaposlenik.getIdZaposlenik();
                            benzinskaStanicaId = zaposlenik.getBenzinskaId();
                            long temp = System.currentTimeMillis();
                            datumIzdavanja = new java.sql.Date(temp);
                            insertFaktura();
                            insertIzlaznaFaktura();
                            for (int i = 0; i < stavkaFaktureTableView.getItems().size(); i++) {
                                StavkaFakture pomocnaStavka = stavkaFaktureTableView.getItems().get(i);
                                insertStavkaFakture(pomocnaStavka.getArtikalId(),pomocnaStavka.getKolicinaStavke(),pomocnaStavka.getCijenaStavke());
                                updateStavkaArtikal(pomocnaStavka.getArtikalId(),pomocnaStavka.getKolicinaStavke());
                            }
                            Stage stage = (Stage) zakljuciButton.getScene().getWindow();
                            stage.close();
                        }
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
        }

    }

    public void insertFaktura() {
        PreparedStatement pStatement = null;
        Connection connectionDB = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            pStatement = connectionDB.prepareStatement("insert into faktura values(?,?,?,?,?,?,?,?,?)");
            pStatement.setInt(1, idFakture);
            pStatement.setDate(2, datumIzdavanja);
            pStatement.setBigDecimal(3, BigDecimal.valueOf(ukupnoFaktura));
            pStatement.setInt(4, zaposlenikId);
            pStatement.setInt(5, benzinskaStanicaId);
            pStatement.setInt(6, tipPlacanjaId);
            pStatement.setString(7, komentar);
            if (poslovniPartnerId != 0) {
                pStatement.setInt(8, racunPartneraId);
                pStatement.setInt(9, poslovniPartnerId);
            }
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

    public void insertIzlaznaFaktura() {
        PreparedStatement pStatement = null;
        Connection connectionDB = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            pStatement = connectionDB.prepareStatement("insert into izlazna_faktura values(?,?)");
            pStatement.setInt(1, idFakture);
            pStatement.setDate(2, datumIzdavanja);
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


    public void insertStavkaFakture(int artikalId, int kolicina, double cijena) {
        PreparedStatement pStatement = null;
        Connection connectionDB = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            pStatement = connectionDB.prepareStatement("insert into stavka_fakture values(?,?,?,?)");
            pStatement.setInt(1, idFakture);
            pStatement.setInt(2, artikalId);
            pStatement.setInt(3, kolicina);
            pStatement.setBigDecimal(4, BigDecimal.valueOf(cijena));
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

    public void findStavkaArtikal(int id) {
        Statement statement = null;
        Connection connectionDB = null;
        ResultSet resultSet = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            statement = connectionDB.createStatement();
            resultSet = statement.executeQuery("select * from artikal where id='" + id + "'");
            while (resultSet.next()) {
                Artikal artikal = new Artikal(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getBigDecimal(3).doubleValue(), resultSet.getInt(7), resultSet.getInt(6), resultSet.getInt(5));
                findZaliha = artikal.getZaliha();
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

    public void updateStavkaArtikal(int id, int kolicina) {
        findStavkaArtikal(id);
        PreparedStatement pStatement = null;
        Connection connectionDB = null;

        try {
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanac_benzinskih_pumpi", "root", "sifra1234");
            pStatement = connectionDB.prepareStatement("update artikal set zaliha=? where id=?");
            pStatement.setInt(1, findZaliha-kolicina);
            pStatement.setInt(2, id);
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


