package sigproxy.smpp.server.controller;

import com.cloudhopper.smpp.type.SmppChannelException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import sigproxy.smpp.server.client.Client;
import sigproxy.smpp.server.model.SMPPSessionConfig;
import sigproxy.smpp.server.server.Server;
import sigproxy.smpp.server.service.SMPPSessionConfigService;

import java.util.Optional;

@Controller
@RequestMapping("/cloudhopper")
@RequiredArgsConstructor
@Slf4j
public class ClounhopperController {
    private final SMPPSessionConfigService smppSessionConfigService;

    @GetMapping(value = "/server/start")
    ResponseEntity<?> startServer() throws SmppChannelException {
        try {
            log.info("Start cloudhopper server");
            Server.startServer();
            return ResponseEntity.ok("Server Started");
        } catch (Exception e) {
            log.error("Exception on starting cloudhopper server: ", e.getMessage());
            return new ResponseEntity<>("Faild Starting", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/client/start")
    ResponseEntity<?> startClient() {
        try{
            log.info("Get list of configurations");
            Client.createClient();
            return ResponseEntity.ok("Client Started");
        } catch (Exception e) {
            log.error("Exception on getting configurations: ", e.getMessage());
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/configuration", produces = "application/json")
    ResponseEntity<?> findAllConfiguration() {
        try{
            log.info("Get list of configurations");
            return ResponseEntity.ok(smppSessionConfigService.findAll());
        } catch (Exception e) {
            log.error("Exception on getting configurations: ", e.getMessage());
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = {"/configuration/{id}"}, produces = "application/json")
    ResponseEntity<?> findById(@PathVariable int id) {
        try {
            log.info("Getting configuration by id");
            return ResponseEntity.ok(smppSessionConfigService.findById(id));
        } catch (Exception e) {
            log.error("Exception on getting configuration by id: ", e);
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/configuration/create", produces = "application/json")
    ResponseEntity<?> createSessionConfiguration(@RequestBody SMPPSessionConfig newSessionConfig) {
        try {
            int id = smppSessionConfigService.create(newSessionConfig);
            if (id != 0) {
                log.info("Creating a new configuration");
                return new ResponseEntity<>("Created",HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Configuration with name - " + newSessionConfig.getConfig().getName() + ", exists! Change configuration name and try again.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Exception on creating a configuration: ", e);
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/configuration/update/{id}", produces = "application/json")
    ResponseEntity<?> update(@RequestBody SMPPSessionConfig updateSessionConfig,@PathVariable int id) {
        try {
            log.info("Updating configuration");
            Optional<SMPPSessionConfig> result = smppSessionConfigService.update(updateSessionConfig,id);
            if(result.isPresent()){
                return ResponseEntity.ok(result);
            }
            return new ResponseEntity<>("Not Modified", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            log.error("Exception on creating or updating an user: ", e);
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = {"/configuration/delete/{id}"}, produces = "application/json")
    ResponseEntity<?> deleteById(@PathVariable int id) {
        try {
            log.info("Deleting configuration by id");
            smppSessionConfigService.deleteById(id);
            return ResponseEntity.ok("Deleted configuration with id - " + id);
        } catch (Exception e) {
            log.error("Exception on deleting user by id: ", e);
            return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
        }
    }
}
