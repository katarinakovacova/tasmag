package org.example.tasmag.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @GetMapping("/tasks")
    public ResponseEntity<String> getTasks() {
        return ResponseEntity.ok("This is the list of tasks!");
    }
}
