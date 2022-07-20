package connect.me.controller;

import connect.me.constant.Constant;
import connect.me.model.ComponentModel;
import connect.me.model.TableModel;
import connect.me.service.LevelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

@Controller
public class SearchController {

    private ComponentModel[][] convertTableModelToMatriz(TableModel tableModel){
        ComponentModel[][] matriz = new TableModel[][];
        for(int l = 0; l < 3; l++){
            for(int c = 0; c < 3; c++){
                matriz[l][c] = tableModel.getRows().get(c).get(l);
            }
        }
        return matriz;
    }

    public TableModel deepFind(TableModel tableModel){
        ComponentModel[][] matriz = convertTableModelToMatriz(tableModel);

    }

    public TableModel
}
