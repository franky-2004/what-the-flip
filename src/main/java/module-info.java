module org.fventura.whattheflip {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens org.fventura.whattheflip to javafx.fxml;
    exports org.fventura.whattheflip;
}