package sigproxy.smpp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/smpp")
public class SmppController {
    @GetMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> testConnection() {
        return ResponseEntity.ok("Connected with Smpp");
    }
}
