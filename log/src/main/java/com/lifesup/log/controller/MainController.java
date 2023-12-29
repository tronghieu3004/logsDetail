package com.lifesup.log.controller;

import com.lifesup.log.model.LogEntity;
import com.lifesup.log.service.LogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.DELETE})
public class MainController {

    private final LogService logService;
    public MainController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("/logs")
    public List<LogEntity> getAll(){
        return logService.getAll();
    }
    @GetMapping("/log")
    public LogEntity getLog(@RequestParam int id){
        return logService.getLog(id);
    }
    @GetMapping("/logs/sort")
    public List<LogEntity> sortByData(@RequestParam String columnName,@RequestParam String typeSort){
        return logService.sortByData(columnName,typeSort);
    }
    @GetMapping("/logs/filter")
    public List<LogEntity> nDayAgo(@RequestParam int nDayAgo){
         return logService.nearDay(nDayAgo);
    }
    @GetMapping("/logs/search")
    public List<LogEntity> searchAllFields(@RequestParam(name = "find") String keyword) {
        return logService.searchAllFields(keyword);
    }
    @DeleteMapping("/log")
    public String delete(@RequestParam int id) {
        return logService.delete(id);
    }
    @PostMapping("/log")
    public List<LogEntity> add(@RequestBody List<LogEntity> listIn ){
        return logService.addAll(listIn);
    }
    @GetMapping("/logs/searchsmart")
    public List<LogEntity> smartSearch(@RequestParam(name = "find")String keyword,@RequestParam String days,@RequestParam(name = "columnName") String colName,@RequestParam String direct){
        return logService.smartSearch(keyword,days,colName,direct);
    }
}
