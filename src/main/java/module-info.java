module com.example.mmvlauncher {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.mmvlauncher to javafx.fxml, com.google.gson;
    exports com.example.mmvlauncher;
}