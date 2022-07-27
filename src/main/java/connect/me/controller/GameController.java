package connect.me.controller;

import connect.me.constant.Constant;
import connect.me.model.ComponentModel;
import connect.me.model.LevelReponseModel;
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

@Controller
public class GameController {

    @GetMapping
    public String getTable(Model model) {
        return "redirect:/level/1";
    }

    @GetMapping(value = "/level/{level}")
    public String getGameLevel(@PathVariable String level,  Model model) throws Exception {
        int levelNumber;
        try {
            levelNumber = Integer.parseInt(level);
        } catch (Exception e) {
            return "redirect:/";
        }

        if (levelNumber > Constant.MAXLEVEL) {
            return "redirect:/";
        }

        LevelReponseModel levelReponseModel = new LevelService().loadRow(level, Constant.TableTypes.CHALLENGE);
        ComponentModel[][] table = levelReponseModel.getTable();

        model.addAttribute("level", level);
        model.addAttribute("img", "http://localhost:8080/image/blocked.png");
        model.addAttribute("row1", table[0]);
        model.addAttribute("row2", table[1]);
        model.addAttribute("row3", table[2]);
        model.addAttribute("row4", table[3]);
        model.addAttribute("canSolve", true);
        model.addAttribute("canNext", false);
        return "game";
    }

    @GetMapping(value = "/level/{level}/width")
    public String getGameLevelWidth(@PathVariable String level,  Model model) throws Exception {
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

        LevelReponseModel levelReponseModel = new LevelService().loadRow(level, Constant.TableTypes.WIDTH);
        ComponentModel[][] table = levelReponseModel.getTable();

        model.addAttribute("level", level);
        model.addAttribute("row1", table[0]);
        model.addAttribute("row2", table[1]);
        model.addAttribute("row3", table[2]);
        model.addAttribute("row4", table[3]);
        model.addAttribute("time", levelReponseModel.getWatch().getTotalTimeMillis());
        model.addAttribute("nextLevel", Integer.parseInt(level) + 1);
        model.addAttribute("img", "http://localhost:8080/image/blocked.png");
        return "game";
    }

    @GetMapping(value = "/level/{level}/deep")
    public String getGameLevelDeepResult(@PathVariable String level,  Model model) throws Exception {
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

        LevelReponseModel levelReponseModel = new LevelService().loadRow(level, Constant.TableTypes.DEEP);
        ComponentModel[][] table = levelReponseModel.getTable();

        model.addAttribute("level", level);
        model.addAttribute("row1", table[0]);
        model.addAttribute("row2", table[1]);
        model.addAttribute("row3", table[2]);
        model.addAttribute("row4", table[3]);
        model.addAttribute("time", levelReponseModel.getWatch().getTotalTimeMillis());
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
