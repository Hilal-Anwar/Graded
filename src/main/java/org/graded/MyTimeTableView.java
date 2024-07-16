package org.graded;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class MyTimeTableView implements Initializable {
    public Text title;
    @FXML
    VBox root_layout;
    @FXML
    ListView<TimeTableView> custList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<TimeTableView> co = new ArrayList<>();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.execute(() -> {
                Map<String, String> color_ = Map.of("03:00", "#ef476f40",
                        "04:00", "#f78c6b40", "05:00",
                        "#ffd16640", "06:00", "#06d6a030", "07:00", "#118ab240");
                var default_code = color_.get("07:00");
                TimeTableLoader timeTableLoader = new TimeTableLoader();
                var vr = timeTableLoader.getTimeTableLinkedList();
                int rt = 0;
                var frt = vr.getFirst().getDataList();
                var vm = vr.getFirst();
                co.add(new TimeTableView("#073b4c60", frt, vm.time(), vm.grade(), vm.roomNo(), vm.subject()));
                for (int i = 1; i < vr.size(); i++) {
                    var k = vr.get(i);
                    co.add(new TimeTableView(color_.get(k.time().substring(0,k.time().indexOf(' '))) != null ?
                            color_.get(k.time().substring(0,k.time().indexOf(' '))) : default_code, frt, k.time(), k.grade(), k.roomNo(), k.subject()));
                    rt++;
                }

                var list = FXCollections.observableList(co);
                custList.setItems(list);
            });
        }

    }
}
