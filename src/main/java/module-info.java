module com.example.petstore {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.petstore to javafx.fxml;
    exports com.example.petstore;
}