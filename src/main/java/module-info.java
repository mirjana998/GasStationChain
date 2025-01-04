module com.example.lanacbenzinskihstanica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lanacbenzinskihstanica to javafx.fxml;
    exports com.example.lanacbenzinskihstanica;
}