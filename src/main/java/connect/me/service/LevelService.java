package connect.me.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import connect.me.constant.Constant;
import connect.me.controller.SearchController;
import connect.me.model.ComponentModel;
import connect.me.model.TableModel;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LevelService {
    public ComponentModel[] loadRow(String level, int column, Constant.TableTypes tableType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TableModel tableModel = objectMapper.readValue(new File("src/main/java/connect/me/levels/level" + level + ".json"), TableModel.class);
        return tableModel.getRows()[column - 1];
    }
}
