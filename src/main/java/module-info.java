module org.graded {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.logging.log4j.core;
    requires org.apache.poi.ooxml;
    opens org.graded to javafx.fxml;
    exports org.graded;
}