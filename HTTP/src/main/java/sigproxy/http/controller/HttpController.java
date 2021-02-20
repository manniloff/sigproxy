package sigproxy.http.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/http")
public class HttpController {
    @GetMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> testConnection() {
        return ResponseEntity.ok("Connected with Http");
    }
}
