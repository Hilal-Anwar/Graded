package org.graded;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class LayoutAnimator {
    private final Pane[] nodes;
    private int imgIndex = 0;
    private final Pane pane;

    public LayoutAnimator(Pane pane, Pane... nodes) {
        this.nodes = nodes;
        this.pane = pane;
    }


    public void animate() {
        Timeline animation = getTimeline();
        pane.getChildren().add(nodes[imgIndex]);
        animation.play();
    }

    private Timeline getTimeline() {
        EventHandler<ActionEvent> eventHandler = e -> {
            if (imgIndex < nodes.length - 1) {
                // Adding Children
                pane.getChildren().remove(nodes[imgIndex]);
                imgIndex++;
                pane.getChildren().add(nodes[imgIndex]);
                FadeTransition ft = new FadeTransition(Duration.seconds(2), nodes[imgIndex]);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
            } else if (imgIndex == nodes.length - 1) {
                imgIndex = 0;
                pane.getChildren().remove(nodes[nodes.length - 1]);
                pane.getChildren().add(nodes[imgIndex]);
                FadeTransition ft = new FadeTransition(Duration.seconds(2), nodes[imgIndex]);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
            }
        };
        var v1 = new KeyFrame(Duration.seconds(30  ),eventHandler);
        var v2 = new KeyFrame(Duration.seconds(15  ));
        var v3 = new KeyFrame(Duration.seconds(15  ));

        // Timeline Animation
        Timeline animation = new Timeline( v1,v2,v3);

        animation.setCycleCount(Timeline.INDEFINITE);
        return animation;
    }
}