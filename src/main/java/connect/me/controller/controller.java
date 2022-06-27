package connect.me.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping
    public String getTable(Model model) {
        return "test";
    }
}
