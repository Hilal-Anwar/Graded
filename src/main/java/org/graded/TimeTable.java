package org.graded;


import javafx.scene.text.Text;

public record TimeTable(String time, String grade, String roomNo, String subject) {
    public Double[] getDataList() {
        return new Double[]{new Text(time).getLayoutBounds().getWidth(),
                new Text(grade).getLayoutBounds().getWidth(),
                new Text(roomNo).getLayoutBounds().getWidth(),
                new Text(subject).getLayoutBounds().getWidth()};
    }
}
