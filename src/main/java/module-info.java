module com.example.cwtask4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cwtask4 to javafx.fxml;
    exports com.example.cwtask4;
}