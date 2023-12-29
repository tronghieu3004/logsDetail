package com.lifesup.log.service;

import com.lifesup.log.model.LogEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LogService {
    List<LogEntity> sortByData(String columnName,String typeSort);
    List<LogEntity> getAll();
    LogEntity getLog(int id);
    List<LogEntity> searchAllFields(@RequestParam(name = "find") String keyword);
    String delete(@RequestParam int id);
    List<LogEntity> nearDay(int nDayAgo);
    List<LogEntity> addAll(List<LogEntity> listInput);
    List<LogEntity> smartSearch(String keyword, String days, String colName, String direct);
}
