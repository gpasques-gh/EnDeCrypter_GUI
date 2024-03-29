module com.example.endecrypter_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens EnDeCrypter to javafx.fxml;
    exports EnDeCrypter;
}