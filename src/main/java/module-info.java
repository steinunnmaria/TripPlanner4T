module com.example.tripplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql.rowset;


    opens com.example.tripplanner to javafx.fxml;
    exports com.example.tripplanner;
    exports com.example.tripplanner.Controllers;
    opens com.example.tripplanner.Controllers to javafx.fxml;
}
