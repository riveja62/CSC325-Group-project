module com.example.softwareengineerticketproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.softwareengineerticketproject to javafx.fxml;
    exports com.example.softwareengineerticketproject;
}