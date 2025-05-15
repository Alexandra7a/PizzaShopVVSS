module pizzashop {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens pizzashop.model to javafx.base;
    opens pizzashop.controller to javafx.fxml;
    opens pizzashop to javafx.fxml;
    opens pizzashop.service to javafx.base, org.junit.jupiter.api;

    exports pizzashop.model;
    exports pizzashop.controller;
    exports pizzashop.service;
    exports pizzashop;

}
