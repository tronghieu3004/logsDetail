package com.lifesup.log.service;

import com.lifesup.log.model.LogEntity;
import com.lifesup.log.repository.LogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class LogServiceImpl implements LogService {
    private LogRepository repository;
    @Autowired
    public LogServiceImpl(LogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LogEntity> sortByData(@RequestParam String collumName, @RequestParam String typeSort) {
        Sort sortByCollum;
        if (typeSort.toLowerCase(Locale.ROOT).equals("DESC".toLowerCase(Locale.ROOT))) {
            sortByCollum = Sort.by(Sort.Direction.DESC, collumName);
        } else {
            sortByCollum = Sort.by(Sort.Direction.ASC, collumName);
        }
        return repository.findAll(sortByCollum);
    }

    public List<LogEntity> getAll() {
        return repository.findAll();
    }

    public LogEntity getLog(int id) {
        Optional<LogEntity> log = repository.findById(id);
        if (log.isPresent()) {
            return log.get();
        } else {
            throw new RuntimeException("log not found for the id " + id);
        }
    }

    public List<LogEntity> searchAllFields(@RequestParam(name = "find") String keyword) {
        List<LogEntity> logEntityList;
        System.out.println(keyword);
        if (keyword == null) {
            return repository.findAll();
        }
        logEntityList = repository.searchAllFields(keyword);
        logEntityList.addAll(repository.findAllByApiKey(keyword));
        return  logEntityList;
    }

    public String delete(@RequestParam int id) {
        Optional<LogEntity> log = repository.findById(id);
        if (log.isPresent()) {
            repository.delete(log.get());
            return "Log is deleted with id " + id;
        } else {
            throw new RuntimeException("Employee not found for the id " + id);
        }
    }

    @Override
    public List<LogEntity> nearDay(@RequestParam int days) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nDaysAgo = today.minusDays(days);
        return repository.nearDay(nDaysAgo);
    }

    @Override
    public List<LogEntity> addAll(List<LogEntity> listIn) {
        if (listIn.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.println(this.toString());
        return repository.saveAll(listIn);

    }
    public List<LogEntity> smartSearch(String keyword, String days, String colName, String direct){
        Sort sort;
        int day;
        if (direct.equals("")){direct = "desc";}
        if (keyword.equals("")) { keyword ="";}
        if (colName.equals("")){colName="createdAt";}
        if (days == "") {day =0;}else {day = Integer.parseInt(days); }
        LocalDateTime today,dayStart;
        today = LocalDateTime.now();
        dayStart = today.minusDays(day);
        if (direct.toLowerCase(Locale.ROOT).equals("DESC".toLowerCase(Locale.ROOT))) {
            sort = Sort.by(Sort.Direction.DESC, colName);
        } else {
            sort = Sort.by(Sort.Direction.ASC, colName);
        }
        return repository.smartSearch(keyword, dayStart, sort);
    }

}

