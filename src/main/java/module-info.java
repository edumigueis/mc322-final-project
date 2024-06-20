module mc322project {
    requires javafx.controls;
    requires com.gluonhq.maps;
    requires com.gluonhq.attach.storage;
    requires com.gluonhq.attach.util;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires org.kordamp.ikonli.javafx;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    exports mc322project;

    opens mc322project.ui.controllers to javafx.fxml; // Open controller packages to allow FXML loading
    opens mc322project.ui.helpers to javafx.fxml; // Open components packages to allow FXML loading
    opens mc322project.ui.components to javafx.fxml; // Open components packages to allow FXML loading

    opens mc322project.core.itinerary to com.fasterxml.jackson.databind;
    opens mc322project.entities.activities to com.fasterxml.jackson.databind;
    opens mc322project.entities to com.fasterxml.jackson.databind;
    opens mc322project.helpers.location to com.fasterxml.jackson.databind;
    opens mc322project.helpers to com.fasterxml.jackson.databind;
}