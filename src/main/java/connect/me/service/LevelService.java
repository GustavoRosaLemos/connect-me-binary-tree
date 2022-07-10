package connect.me.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import connect.me.model.ComponentModel;
import connect.me.model.TableModel;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LevelService {
    public List<ComponentModel> loadRow(String level, int column, boolean completed) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TableModel tableModel = objectMapper.readValue(new File("src/main/java/connect/me/levels/level" + level + ".json"), TableModel.class);
        return tableModel.getRows().get(column - 1);
    }
}
