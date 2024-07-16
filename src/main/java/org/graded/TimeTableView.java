package org.graded;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TimeTableView extends HBox {
    private final String[] text;
    private final int DEFAULT_MAX_VALUE = 10;
    private String bg_color;
    public TimeTableView(String bg_color, Double[] it, String... text) {
        this.text = text;
        this.bg_color=bg_color;
        this.getStyleClass().add("custom-view");
        this.setStyle("-fx-background-color: " + bg_color + ";");
        init();
    }

    private void init() {
        this.setPadding(new Insets(10.8, 10, 10, 10.8));
        this.setFillHeight(true);
        this.setMaxSize(Double.MAX_VALUE, Region.USE_COMPUTED_SIZE);
        for (int i = 0; i < text.length; i++) {
            String s = text[i];
            var t = new Text(s.trim());
            t.setWrappingWidth(90);
            if (bg_color.equals("#073b4c60"))
                t.setFill(Paint.valueOf("#fafafa"));
            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            separator.setPrefWidth(DEFAULT_MAX_VALUE);
            separator.setVisible(false);
            t.setTextAlignment(TextAlignment.CENTER);
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(t);
            if (i < text.length - 1)
                this.getChildren().add(separator);
        }
    }


}
