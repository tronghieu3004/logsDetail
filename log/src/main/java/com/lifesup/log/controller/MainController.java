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
    @GetMapping("/logs/sort")
    public List<LogEntity> sortByDate(@RequestParam String collum_name){
        if (collum_name.equals("Created_At_Desc")){
        return logRepo.findAllByOrderByCreatedAtDesc();
        }
        if (collum_name.equals("Updated_At_Desc")) {
            return logRepo.findAllByOrderByUpdatedAtDesc();
        }
        if (collum_name.equals("Model")) {
            return logRepo.findAllByOrderByModel();
        }
        if (collum_name.equals("Total_Cost_Asc")) {
            return logRepo.findAllByOrderByTotalCostAsc();
        }
        if (collum_name .equals("Total_Cost_Desc")) {
            return logRepo.findAllByOrderByTotalCostDesc();
        }
        if (collum_name .equals("Promt_Cost_Asc")) {
            return logRepo.findAllByOrderByPromtCostAsc();
        }
        if (collum_name .equals("Promt_Cost_Desc")) {
            return logRepo.findAllByOrderByPromtCostDesc();
        }
        if (collum_name .equals("Promt_Token_Asc")) {
            return logRepo.findAllByOrderByPromtTokensAsc();
        }
        if (collum_name .equals("Promt_Token_Desc")) {
            return logRepo.findAllByOrderByPromtTokensDesc();
        }
        if (collum_name .equals("Completion_Cost_Asc")) {
            return logRepo.findAllByOrderByCompletionCostAsc();
        }
        if (collum_name .equals("Completion_Cost_Desc")) {
            return logRepo.findAllByOrderByCompletionCostDesc();
        }
        if (collum_name .equals("Completion_Token_Asc")) {
            return logRepo.findAllByOrderByCompletionTokensAsc();
        }
        if (collum_name .equals("Completion_Token_Desc")) {
            return logRepo.findAllByOrderByCompletionTokensDesc();
        }
        if (collum_name .equals("Total_Token_Asc")) {
            return logRepo.findAllByOrderByTotalTokensAsc();
        }
        if (collum_name .equals("Total_Token_Desc")) {
            return logRepo.findAllByOrderByTotalTokensDesc();
        }
        return logRepo.findAll();
    }
    @GetMapping("/search")
    public List<LogEntity> searchAllFields(@RequestParam(name = "find") String keyword) {
        System.out.println(keyword);
        if (keyword == null) {
            return logRepo.findAll();
        }
        return logRepo.searchAllFields(keyword);
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
