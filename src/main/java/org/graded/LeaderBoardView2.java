package org.graded;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class LeaderBoardView2 implements Initializable {
    public Text title;
    @FXML
    VBox root_layout;
    @FXML
    ListView<CustomView> custList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<CustomView> co = new ArrayList<>();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.execute(() -> {
                StudentDataLoader studentDataLoader = new StudentDataLoader();
                var vr = studentDataLoader.getStudentStream();
                int ind = 1;
                for (var k : vr) {
                    if (ind > 10) {
                        co.add(new CustomView(ind,
                                "b5.png",
                                k.name(), "Class " + k.grade(),
                                "" + (int) k.points(), "#6c6c6a30"));
                    }
                    ind++;
                    if (ind == 21)
                        break;
                }

                var list = FXCollections.observableList(co);
                custList.setItems(list);
            });
        }

    }
}

