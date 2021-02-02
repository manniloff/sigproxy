package sigproxy.smpp.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sigproxy.smpp.server.model.Message;
import sigproxy.smpp.server.service.MongoTestService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MongController {
    private final MongoTestService mongoTestService;

    @GetMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(mongoTestService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(mongoTestService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = {"", "/"}, produces = "application/json")
    ResponseEntity<?> create(@RequestBody Message newMessage) {
        try {
            int id = mongoTestService.create(newMessage);
            return new ResponseEntity<>("Created",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> update(@RequestBody Message newMessage, @PathVariable int id) {
        try {
            int result = mongoTestService.update(newMessage);
            if(result != 0 ){
                return ResponseEntity.ok(result);
            }
            return new ResponseEntity<>("Not Modified", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = {"/{id}"}, produces = "application/json")
    ResponseEntity<?> deleteById(@PathVariable int id) {
        try {
            mongoTestService.delete(id);
            return ResponseEntity.ok("Deleted user with id - " + id);
        } catch (Exception e) {
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

}
