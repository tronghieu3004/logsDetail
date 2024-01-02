package com.lifesup.log.repository;

import com.lifesup.log.model.LogEntity;

import jakarta.persistence.EntityManager;
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
    @Query("SELECT l FROM LogEntity l WHERE " +
            "(l.model LIKE %:keyword% or " +
            "l.fullMessage LIKE %:keyword% or " +
            "l.userMessage LIKE %:keyword% or " +
            "l.promt LIKE %:keyword% OR " +
            "l.reply LIKE %:keyword% or " +
            "l.apiKey LIKE %:keyword%) AND" +
            " l.createdAt  >= :days")
    List<LogEntity> smartSearch(String keyword,LocalDateTime days, Sort sort);

//    //Phân trang dùng PageAble
//    Page<LogEntity> findAll2(Pageable pageable);
default List<LogEntity> filterAndSearch(EntityManager entityManager, String keyword, LocalDateTime days, Sort sort) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT l FROM LogEntity l WHERE ");

        sqlBuilder.append("(l.model LIKE :keyword or ");
        sqlBuilder.append("l.fullMessage LIKE :keyword or ");
        sqlBuilder.append("l.userMessage LIKE :keyword or ");
        sqlBuilder.append("l.promt LIKE :keyword OR ");
        sqlBuilder.append("l.reply LIKE :keyword or ");
        sqlBuilder.append("l.apiKey LIKE :keyword) AND ");
        sqlBuilder.append("l.createdAt >= :days");
        sqlBuilder.append("order by :colName :direct");

        String dynamicQuery = sqlBuilder.toString();
        return entityManager.createQuery(dynamicQuery, LogEntity.class)
                .setParameter("keyword", keyword)
                .setParameter("days", days)
                .setParameter("colName", sort.get())
                .setParameter("direct", sort)
                .getResultList();
    }
}
