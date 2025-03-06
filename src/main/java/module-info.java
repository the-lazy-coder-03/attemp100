module com.example.attemp100 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.attemp100 to javafx.fxml;
    exports com.example.attemp100;
}