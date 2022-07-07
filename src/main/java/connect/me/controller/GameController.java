package connect.me.controller;

import connect.me.constant.Constant;
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
public class GameController {

    @GetMapping
    public String getTable(Model model) {
        return "redirect:/level/1";
    }

    @GetMapping(value = "/level/{level}")
    public String getGameLevel(@PathVariable String level,  Model model) {
        int levelNumber;
        try {
            levelNumber = Integer.parseInt(level);
        } catch (Exception e) {
            return "redirect:/";
        }

        if (levelNumber > Constant.MAXLEVEL) {
            return "redirect:/";
        }

        ArrayList<String> arrayList = new ArrayList<>();

        model.addAttribute("test", arrayList);

        model.addAttribute("level", level);
        model.addAttribute("img", "http://localhost:8080/image/blocked.png");
        model.addAttribute("row1", new LevelService().loadRow(level, 1, false));
        model.addAttribute("row2", new LevelService().loadRow(level, 2, false));
        model.addAttribute("row3", new LevelService().loadRow(level, 3, false));
        model.addAttribute("row4", new LevelService().loadRow(level, 4, false));
        model.addAttribute("canSolve", true);
        model.addAttribute("canNext", false);
        return "game";
    }

    @GetMapping(value = "/level/{level}/solved")
    public String getGameLevelCompleted(@PathVariable String level,  Model model) {
        int levelNumber;
        try {
            levelNumber = Integer.parseInt(level);
        } catch (Exception e) {
           return "redirect:/";
        }

        if (levelNumber > Constant.MAXLEVEL) {
            return "redirect:/";
        }

        if (levelNumber < Constant.MAXLEVEL) {
            model.addAttribute("canNext", true);
            model.addAttribute("nextLevel", levelNumber + 1);
        }
        model.addAttribute("level", level);
        model.addAttribute("row1", new LevelService().loadRow(level, 1, true));
        model.addAttribute("row2", new LevelService().loadRow(level, 2, true));
        model.addAttribute("row3", new LevelService().loadRow(level, 3, true));
        model.addAttribute("row4", new LevelService().loadRow(level, 4, true));
        model.addAttribute("nextLevel", Integer.parseInt(level) + 1);
        model.addAttribute("img", "http://localhost:8080/image/blocked.png");
        return "game";
    }


    @GetMapping(value = "/image/{image}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> image(@PathVariable String image) throws IOException{
        File file = new File("src/main/java/connect/me/img/"+image);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" +file.getName())
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .body(Files.readAllBytes(file.toPath()));
    }
}
