package com.lifesup.log.repository;

import com.lifesup.log.model.LogEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Integer> {
    @Query(value = "SELECT * FROM tbl_log " +
            "WHERE MATCH(promt, full_message, user_message, reply) " +
            "AGAINST(:keyword IN NATURAL LANGUAGE MODE) ORDER BY  created_at DESC ",
            nativeQuery = true)
    List<LogEntity> searchAllFields(String keyword);

    @Query("SELECT l FROM LogEntity l WHERE l.createdAt > :nDaysAgo")
    List<LogEntity> nearDay(LocalDateTime nDaysAgo);
    List<LogEntity> findAllByApiKey(String key);
//    @Query(value = "SELECT * FROM tbl_log " +
//            "WHERE MATCH(promt, full_message, user_message, reply) AGAINST(:keyword IN NATURAL LANGUAGE MODE) " +
//            "AND created_at <= :days " +
//            "ORDER BY :sort", nativeQuery = true)
    @Query("SELECT l FROM LogEntity l WHERE " +
            "(l.model LIKE %:keyword% or " +
            "l.fullMessage LIKE %:keyword% or " +
            "l.userMessage LIKE %:keyword% or " +
            "l.promt LIKE %:keyword% OR " +
            "l.reply LIKE %:keyword% or " +
            "l.apiKey LIKE %:keyword%) AND" +
            " l.createdAt  >= :days")
    List<LogEntity> smartSearch(String keyword,LocalDateTime days, Sort sort);

}
