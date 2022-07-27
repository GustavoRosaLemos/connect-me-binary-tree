package connect.me.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import connect.me.constant.Constant;
import connect.me.controller.SearchController;
import connect.me.model.ComponentModel;
import connect.me.model.LevelReponseModel;
import connect.me.model.TableModel;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LevelService {
    public LevelReponseModel loadRow(String level, Constant.TableTypes tableType) throws Exception {
        StopWatch watch = new StopWatch();
        ObjectMapper objectMapper = new ObjectMapper();
        TableModel tableModel = objectMapper.readValue(new File("src/main/java/connect/me/levels/level" + level + ".json"), TableModel.class);
        if (tableType == Constant.TableTypes.DEEP) {
            watch.start();
            ComponentModel[][] table = new SearchController(tableModel.getRows(), "inicio").deepFind();
            if(watch.isRunning()) {
                watch.stop();
            }
            if (table != null) {
                tableModel.setRows(table);
            }
        } else if(tableType == Constant.TableTypes.WIDTH) {
            watch.start();
            ComponentModel[][] table = new SearchController(tableModel.getRows(), "inicio").widthFind();
            if(watch.isRunning()) {
                watch.stop();
            }
            if (table != null) {
                tableModel.setRows(table);
            }
        }
        return new LevelReponseModel(tableModel.getRows(), watch);
    }
}
