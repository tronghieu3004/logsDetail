package com.lifesup.log.controller;

import com.lifesup.log.model.LogEntity;
import com.lifesup.log.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.DELETE})
public class MainController {
    @Autowired
    LogRepository logRepo;
    @GetMapping("/logs")
    public List<LogEntity> getAll(){
        return logRepo.findAll();
    }
    @GetMapping("/log")
    public LogEntity getLog(@RequestParam int id){
        Optional<LogEntity> log = logRepo.findById(id);
        if(log.isPresent()) {
            return log.get();
        }else {
            throw new RuntimeException("log not found for the id "+id);
        }
    }
    @DeleteMapping("/log")
    public String delete(@RequestParam int id) {
        Optional<LogEntity> log = logRepo.findById(id);
        if(log.isPresent()) {
            logRepo.delete(log.get());
            return "Log is deleted with id "+id;
        }else {
            throw new RuntimeException("Employee not found for the id "+id);
        }
    }
}
