package connect.me.controller;

import org.apache.tomcat.jni.Buffer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class GameController {

    @GetMapping
    public String getTable(Model model) {
        model.addAttribute("level", "5");
        model.addAttribute("img", "http://localhost:8080/image/BlockedObject.png");
        model.addAttribute("");
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
